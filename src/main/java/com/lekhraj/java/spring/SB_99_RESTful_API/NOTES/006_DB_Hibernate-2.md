https://www.baeldung.com/learn-jpa-hibernate
---
##  A. Query : parent class. 
- key point:
    - TypedQuery<T> : T = ResultType
    - NamedQuery  : centralized, Pre-validated, use unique name in PC to avoid collision.
    - https://www.baeldung.com/hibernate-named-query
    - `@NamedQuery` and `@NamedNativeQuery` : SB-JPA-DATA repository uses these BTS with `@Query` / reusable queries/ organize code.
    - use NamedNativeQuery only if certain feature not support by JPQL, and need DB specific optimization, etc
    - name=<unique in PC>,query='static JPQL/SQL'
    - timeout = 1, fetchSize = 10, cacheable=t/f,  resultClass=result.class
    - q.getSingleResult(); and q.getResultList()/Set();
    - `Pagination`
      - Hibernate : q.setFirstResult((pageNumber-1) * pageSize); //offset position
      - Hibernate : q.setMaxResults(pageSize);
      - Another way (old): get ids and then sublist ids
      ```
        //STEP-1 : sort ids as well / gives total count.
        Query q = entityManager.createQuery("Select f.id from Foo f order by f.id");
        List<Integer> ids = q.getResultList();
      
        //STEP-2
        Query query = entityManager.createQuery("Select f from Foo e where f.id in :ids");
        query.setParameter("ids", fooIds.subList(0,10));
        List<Foo> fooList = query.getResultList();
       ```
      - Hibernate : `ScrollableResults` API
        - Reduce database calls.
        - when don't want to load all data into memory at once.
        - Also gives total count, without any additional query.
        - ScrollableResults resultScroll = query.scroll(ScrollMode.FORWARD_ONLY);
        - query.setFetchSize(10);
        - ```
          while (scrollableResults.next()) {
             Employee employee = (Employee) scrollableResults.get(0);
          }
          ```

---
## B. Writing Queries
- JPQL / HQL joins 
  - INNER : JOIN, 
  - OUTER : (LEFT JOIN / RIGHT JOIN)
  - FETCH JOIN  : load associated entities eagerly
  - since relation is already defined with 121,12M, M2M, etc. FK/common col already  added. hence dont need to explicitly define ON-condition with Join.
  - ``` 
    @Query("SELECT e, d FROM Employee e JOIN Department d ON e.department.id = d.id AND d.name = :departmentName")
    List<Object[]> findEmployeesByDepartmentName(@Param("departmentName") String departmentName);
    ```
- https://chatgpt.com/c/1375c062-4b67-437d-860b-e065a2980f57
1. STATIC
- JPQL and Native-SQL
- JPQL take advantage of OOP of entity class.
- Positional parameters ?1,2,etc vs named parameters (preferred)
- Sorting :: order by  e.feild1, e.feild2
- sorting entities in a 1-2-M relation, meaning list on many side `@OrderBy("childName ASC")` List<T> children. // Parent has many Children
- Sorting : in JPQL/HQPL itself :  NULLS LAST, NULLS FIRST at the end.
- Sorting Query Results with Spring Data : https://www.baeldung.com/spring-data-sorting#sorting-with-spring-data

2. DYNAMIC : Criteria API (exclude)

---
## C. Manipulate result Set
1. Tuple (more like Object[],hetrogeneous)
   - https://chatgpt.com/c/7a6449ba-dede-478f-9778-1c7a9a5d5d9d
   - use Tuple / List<Tuple> : for single ot mutlipe result
   - add <artifactId>javatuples</artifactId> 
     - Im-mutable : Pair, triplet - hence maintain data integrity.
2. `class RT implements ResultTransformer` 
   - First @override transformTuple(,) 
   - then use it on any query , q.setResultTransformer(RT rt)
   - inbuilt ResultTransformer.
     - Transformers.TO_ARRAY
     - Transformers.TO_LIST 
     - Transformers.ALIAS_TO_ENTITY_CLASS
3. `@SqlResultSetMapping`
   - columns from @NamedNativeSQL --> map to target -->  EntityClass or Tuple/Object[]
4. pagination/sorting

---
## D. Write/Update
- Batch 
  - Custom batch code. for>flush/clear after 20.
  - `hibernate.jdbc.batch_size`=20
  - `spring.jpa.properties.hibernate.jdbc.batch_size`=20
  - @BatchSize(size = 20) at Entity level for all Operations (CRUD)
  - fact:@GeneratedValue(strategy = GenerationType.IDENTITY ) will disable batch-INSERT Silently. USE SEQUENCE.

---
## E. Transaction management
- https://www.baeldung.com/transaction-configuration-with-jpa-and-spring
- @Bean PlatformTransactionManager
- Enable  :: @EnableTransactionManagement, or add spring-data-* dependencies
- Enable logging::org.springframework.transaction=DEBUG
- 2 ways : 
  - Declarative : @Transactional 
  - programmatic : `AOP + TransactionTemplate`

- `Nested transaction` is also possible
  - Propagation.NESTED
  - inner txn :  independent transaction within the context of an existing/outer transaction.
  - if inner transaction rolls back, it only rolls back the nested transaction, not the outer transaction.
  - Not all databases and transaction managers provide full support for nested transactions.
  - `transactionManager.setNestedTransactionAllowed(true);`
  - it uses Savepoints.
  
- way-1 : @Transactional : 
  - manage transaction boundaries in a declarative way.
  - This means that all database operations within that scope, are part of a single transaction.
  - Spring creates proxies, to inject transactional-logic, before and after the running method.
  - its Java Dynamic Proxy :: Any self-invocation calls will not start any transaction.
  - only public methods.
  - Attributes:
    -  `propagation` - requires_new-->AlwaysNewT , Required -> t1 else t2new, mandatory->t1 else ex , nested->t1->inner-t1, SUPPORTS->t1,none , never->none
    -  `isolation` to define fix-level for `concurrency side effects` :  Dirty-read, Non-repeatable read, Phantom read. 
      - READ_UNCOMMITTED, READ_COMMITTED, REPEATABLE_READ, and SERIALIZABLE.
    -  `timeout` 
    -  `readOnly` flag – a hint for the persistence provider that the transaction should be read only. // optimize performance.
    -  `rollbackFor / noRollbackFor` 
      - Rule-1:Rollback on Checked-Exception-1, etc
      - if we don't mention any rule, rollback happens on unchecked-exceptions/RuntimeException
      - @Transactional(`rollbackFor` = {Exception.class, SpecificException.class})
      - @Transactional(`rollbackForClassName` = {"java.lang.Exception", "com.example.SpecificException"})
   - For SUPPORTS, if an active transaction exists,then use it. Else gets executed non-transactional:

- Way-2 (pending) : :
  - why? : Mixing the database I/O with other types of I/O in a transactional context isn’t a great idea.
  - https://chatgpt.com/c/7b6dd03e-ca98-44d5-87a6-73c23026a009
  - check com.lekhraj.java.spring.SB_99_RESTful_API.service.`StudentServiceImpl`

---
## pending:
1. TransactionTemplate prg
2. ScrollableResults program - when processing large dataset, (not sending large Dataset in batches to UI)
3. Query Plan Cache
4. @NamedQuery More attribute
   - cacheMode=GET, IGNORE, NORMAL, PUT, or REFRESH
   - flushMode=ALWAYS, AUTO, COMMIT, MANUAL, or PERSISTENCE_CONTEXT