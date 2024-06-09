## A. Hibernate Annotation
- https://chatgpt.com/c/1375c062-4b67-437d-860b-e065a2980f57
### Common Annotation
- @Entity(name="new_entityname")
- @Basic(fetch=EAGER,optional=true).
  - this is already added by default on all column.
  - basic mapping, field to a db column.
  
- @Table(name="new_tablename") 
- @Column(name="new_colname"), more 
  - Lenght=255, string-valued column length. / @Length
  - Precision and Scale. for decimal feild.

- @Transient

- @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(updatable = false) int id.

- @OneToOne, @OneToMany, @ManyToOne, @ManyToMany.
- `@JoinTable and @JoinColumn()`
- `@WhereJoinTable(clause = "columnInJoinTable='value-1'")` https://www.baeldung.com/hibernate-wherejointable

- `@MappedSuperclass`
- `@Inheritance(strategy = InheritanceType.SINGLE_TABLE)` : on parentClass
- `@DiscriminatorColumn(name="value-1",discriminatorType = DiscriminatorType.INTEGER)` : on parentClass
- `@DiscriminatorValue("1")` : on ChildClass
- https://www.baeldung.com/hibernate-inheritance


### Advance
- `@Convert`
- `@Lob` byte[]
- @Temporal(TemporalType.DATE) private Date birthDate; //java.util.Date
- `@Enumerated`(EnumType.STRING/ORDINAL) MyEnum
  - enums to their ordinal values or names
  - hibernate automatically converts to enum value, if not mentioned.
- `@embedded and @embeddable`
  - define a class whose instances can be embedded in an entity. 
  - This class does not have its own table but shares the table of the owning entity.
  - @Embedded @AttributeOverrides : use to override columnName whiling embedding
  ``` 
  @Embedded  
  @AttributeOverrides({
        @AttributeOverride(name = "street", column = @Column(name = "home_street")),
        @AttributeOverride(name = "city", column = @Column(name = "home_city")), ...
    })
  ```

---

## B.1 Entity Relationships
- Cascade operation : ALL ,PERSIST, MERGE, REMOVE, REFRESH, DETACH : https://www.baeldung.com/jpa-cascade-types
- https://chatgpt.com/c/1375c062-4b67-437d-860b-e065a2980f57
- owner owns the foreign key and defines @JoinColumn(name="newName")
- uni-directional or bi-directional : both has references of each other.
- `1-2-1` 
  - any class can be owner
  - other class : mapperBy=propertyName mentioned in owner-class.
- `C1:1-2-M and C2:M-2-1(owner : many side)`
  - C1:mapperBy=propertyName mentioned in owner-class
- `C1:M-2-M and C2:M-2-M`
    -  no one is owner.
    - create @JoinTable.
    - use this to filter data : @WhereJoinTable https://www.baeldung.com/hibernate-wherejointable
```
    @ManyToMany
    @JoinTable(
      name = "student_course",
      joinColumns = @JoinColumn(name = "student_id"),
      inverseJoinColumns = @JoinColumn(name = "course_id")
    )
 ```
---
## B.2 Inheritance mapping
- https://www.baeldung.com/hibernate-inheritance
- let's have `parent-Entity (2 col,2 record) / Child-1-Entity(2 col, 2 record), child-2-Entity (2 col, 2 record)`
- Strategies:
  1. `Single table` (6 col) : for all 3 entities with `discrminator` column / lots of null / 
  2. `table per class` :
    - parent class table - 2 col
    - Child-1/2 class table - 4 col
  3. `Joined` : 
    - meaning Joined subclass. child joined to parent
    - Like table per class, but child table will have only prop defined in entity, not from parent.
      - parent class table - 2 col
      - Child-1/2 class table - 2 col + FK to parent table.
  4. `MappedSuperclass` – the parent classes, can’t be entities.

- Polymorphic queries. https://chatgpt.com/c/1375c062-4b67-437d-860b-e065a2980f57
  - implicit : query for parent-entity, automatically includes ALL child records --> 6 records.
  - explicit : use Treat, to change this behaviour.
  
```
List<Vehicle> vehicles = session.createQuery("FROM Vehicle", Vehicle.class).getResultList();
// Implicit polymorphism: vehicles list will contain instances of both Car and Bike
List<Car> cars = session.createQuery("FROM Car", Car.class).getResultList();
// Explicit polymorphism: cars list will contain only instances of Car
List<Vehicle> vehicles = session.createQuery("FROM Vehicle v WHERE TREAT(v AS Car).numberOfDoors > 3", Vehicle.class) .getResultList();
```
---
## C.Common task in DB
1. Default value
- @Column(columnDefinition = "varchar(255) default 'John Snow'")
- Inside Entity class :: private String firstName = "John Snow";

2. Nulluble constraint
- @Column(nullable=t/f)
- @Basic(optional=t/f)
- validator-@NotNull : can apply on any bean, not just entity eg:jackson.

3. unique Contraint
- @Column(unique=t/f) : Single column
- @UniqueConstraint : single/composite column
```
@Table(uniqueConstraints = {
   @UniqueConstraint(columnNames = { "personNumber", "isActive" }) 
 }, ...)
```

## D. Validator 
- <artifactId>spring-boot-starter-validation</artifactId>
- @Size(min = 3, max = 15) : on String
- @length(min = 3, max = 15) : on Collectoin

## E.Converter
- usecase:performing encryption/decryption, data transformations that are NOT directly supported by JPA, etc
- @Convert(converter=abc.class)
- Automatic/inbuilt
  - Boolean to int (t-1, f-0)
  - Boolean to String(t-"true")
  - java.time.LocalDate to java.sql.Date  <<< 
  - java.time.LocalDateTime to java.sql.Timestamp <<<
  - enum to string. String f1
  - enum to ordinal, int f1

---
## Z.More

- Scenario-1::
  - t1::pk=id has (1toM) t2,
  - t2::pk=id,fk=t1_id. 
  - can make fk unique ? I think NO. 
 
---
### pending
1. 
