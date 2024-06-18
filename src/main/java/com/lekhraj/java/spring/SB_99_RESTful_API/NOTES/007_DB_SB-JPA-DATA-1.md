# persistence-with-springBoot 
- https://www.baeldung.com/persistence-with-spring-series
- DB-06-SpringJpaData-1 : https://chatgpt.com/c/a874b751-9880-4225-a96c-9052773037fa
- DB-06-SpringJpaData-2 : https://chatgpt.com/c/8ace7914-f8cc-465e-873a-7b45974bb7b2
- DB-06-SpringJpaData-3 : https://chat.openai.com/c/7b6dd03e-ca98-44d5-87a6-73c23026a009

## A. Annotations/general Notes
-  data.sql, schema.sql, JPA-buddy-PlugIn
- `@EnableJpaRepositories`("example.baeldung.com.repo")
- `@EntityScan`("example.baeldung.com.entity")
- `@EnableTransactionManagement`
- `@Query` - if having indexed/named parameter then bind using `@Param` on arg.
- `@Modifying` - method should run inside Transaction , ReturnTypes - int,void
- testing : @Sql, @SqlConfig, @SqlGroup - 

---
## B. Multiple DataSource : 
  - DBP_01_Multiple Data Sources : https://chatgpt.com/c/7c2da8f5-3f44-4e71-b4f9-4cdadacf0ec5
  - Create 2 beans - Ds1() and Ds2(), Apply ConfigurationProperties on method -> binds returnType(DataSource).
  - create 2 beans - emF1 , emF2
  - Create 2 beans - Txm1 , Txm2
  - Create 2 sets of entities and repos:
    - package1 : entities1, repos1, : use Datasource1 
    - package2 : entities2, repos2  : Use Datasource2. how, see below ?
  - ```
    @EnableJpaRepositories(
       basePackages = "dao1/repos1/*", 
       entityManagerFactoryRef = "emF1", 
       transactionManagerRef = "txn1"
    )
    // same for another set
    ```

---
## C. Spring JPA Data
- Purpose
  - DAO/persistence layer usually consists of a lot of boilerplate code (CRUD methods, wire em, etc)
  - SJD makes it possible to remove the DAO implementations entirely.
  - find this interface and automatically create an implementation for it.
- Exception 
  - translation is still enabled by the use of the `@Repository`.
  - converts to `DataAccessException` hierachy
  
- Repos: check bottom/more
  - `CrudRepository<E,ID>` - returns Iterable / `ListCrudRepository<E,ID>` - returns List
  - `PagingAndSortingRepository<E,ID>` --> `Pageable` p = PageRequest(0,5, `Sort` s)
  - `JpaRepository<E,ID>`
  - Custom repo  - own interface and impl class
  - Composite repo - mix of above (each is called `fragments` then)

- Transactions
  - @EnableTransactionManagement and @Transactional

---
## D, Define more Queries
### 1. Derive methods
- methodName :: `<introducer> [distinct|Top|FIRST]+ "By" + <criteria> + "OrderBy"+propertyName () + Desc|Asc`
- `introducer` : find, read, query, count and get
- `Criteria` :: 
  - propertyName + `Is|IsNot` (T v)
  - propertyName + `IsNull|IsNotNull` (v)
  - booleanPropertyName + `True|False` ()
  - propertyName + `StartingWith` (String prefix)
  - propertyName + `EndingWith` (String suffix)
  - propertyName + `NameLike`(String likePattern);
  - propertyName + `LessThan | LessThanEqual | GreaterThan | GreaterThanEqual | Between` (int...)
  - propertyName + `In` (Collection c)
  - propertyName + `After` (ZonedDateTime)
  - propertyName + `Before` (ZonedDateTime)
  
```
List<User> findByNameIs(String name);
List<User> findByNameEquals(String name);
List<User> findByNameIsNot(String name);
List<User> findByNameIsNull();
List<User> findByNameIsNotNull();

List<User> findByNameStartingWith(String prefix);
List<User> findByNameStartingWith(String prefix);
List<User> findByNameEndingWith(String suffix);
List<User> findByNameContaining(String infix);

List<User> findByAgeLessThan(Integer age);
List<User> findByAgeLessThanEqual(Integer age);
List<User> findByAgeGreaterThan(Integer age);
List<User> findByAgeGreaterThanEqual(Integer age);
List<User> findByAgeBetween(Integer startAge, Integer endAge);

List<User> findByAgeIn(Collection<Integer> ages);

List<User> findByBirthDateAfter(ZonedDateTime birthDate);
List<User> findByBirthDateBefore(ZonedDateTime birthDate);
```

- Grouping : And or (Not fan of it.)
  - eg: List<User> `findByNameOrAgeAndActiveCustom`(String name, Integer age, Boolean active);
    - possibility-1 : (name = 'John' OR age = 30) AND active = true
    - possibility-2 : name = 'John' OR (age = 30 AND active = true)
    - its better to use @Query then, to take control.
    - `@Query("SELECT u FROM User u WHERE u.name = ?1 OR (u.age = ?2 AND u.active = ?3)")`


### 2. @Query
```
------------------------
// orderby / sorting
------------------------
@Query(value = "JPQL", nativeQuery = f)
- List<E> l = repo.findAll(Sort.by(Sort.Direction.ASC, "name"));

Sort.by("name")
Sort.by("price").descending()
Sort.by("price").descending().and(Sort.by("name"))

------------------------
// pagination
------------------------
@Query(value = "JPQL", nativeQuery = f)
- Pageable<E> l = repo.findAll(Pageable pageable);

@Query(value = "SQL", nativeQuery = True, countQuery = '..count(*)..')
- Pageable<E> l = repo.findAll(Pageable pageable);

Pageable sortedByName =PageRequest.of(0, 3, Sort.by("name"));
Pageable sortedByPriceDesc =  PageRequest.of(0, 3, Sort.by("price").descending());
Pageable sortedByPriceDescNameAsc = PageRequest.of(0, 5, Sort.by("price").descending().and(Sort.by("name")));



@Query(value = "SELECT u FROM User u WHERE u.name IN :names")
List<User> findUserByNameList(@Param("names") Collection<String> names);    
``` 
---
### More
- JPA Buddy plugin :
    - generation of JPA entities, Spring Data JPA repositories, DTOs,
    - initialization DDL scripts,
    - Flyway versioned migrations,
    - provides an advanced tool for reverse engineering.


- SB Repos methods
    ```
  //CRUD methods
    <S extends T> S save(S entity);
    T findOne(ID primaryKey);
    Iterable<T> findAll();
    Long count();
    void delete(T entity);
    boolean exists(ID primaryKey);
  
  //PagingAndSortingRepository methods
     Iterable<T> findAll(Sort sort);
     Page<T> findAll(Pageable pageable);
    ```

