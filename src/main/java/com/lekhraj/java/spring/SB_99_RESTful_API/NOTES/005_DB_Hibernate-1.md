## A. Hibernate Annotation
- https://chatgpt.com/c/1375c062-4b67-437d-860b-e065a2980f57
### Common Annotation
- @Entity(name="new_entityname")
- @Basic(fetch=FetchType.EAGER,optional=true).
  - this is already added by default on all column.
  - basic mapping, field to a db column.
  
- @Table(name="new_tablename") 
- @Column(name="new_colname", updateble=f/t), more 
  - Lenght=255, string-valued column length. / @Length
  - Precision and Scale. for decimal feild.

- @Transient

- @OneToOne, @OneToMany, @ManyToOne, @ManyToMany. : Add FK and create JoinTable bts.
- `@JoinTable and @JoinColumn()`
- `@WhereJoinTable(clause = "columnInJoinTable='value-1'")` https://www.baeldung.com/hibernate-wherejointable
- `@OrderBy("colInManySide ASC")` Sorting Children within Each Parent
- `@Fetch @batchSize` - use together in relation : 1-2-M,etc

- `@MappedSuperclass`
- `@Inheritance(strategy = InheritanceType.SINGLE_TABLE)` : on parentClass
- `@DiscriminatorColumn(name="columnName",discriminatorType = DiscriminatorType.INTEGER)` : on parentClass
- `@DiscriminatorValue("1")` : on ChildClass
- https://www.baeldung.com/hibernate-inheritance

- SB JPA DATA annotations
  - `@Query` and `@Param` 
  - `@QueryHints`({@QueryHint(name = "org.hibernate.fetchSize", value = "10")})

### Transaction
- `@EnableTransactionManagement` : from SBJpaData-starter

### Performance
- q.setFetchSize(10)
- Small Fetch Size: May lead to more frequent database calls, increasing network latency and overhead.
- Large Fetch Size: Reduces the number of database calls but consumes more memory as more rows are loaded into memory at once.

- option for relation:
  - `@Fetch` 
    - (FetchMode.SELECT) : https://chatgpt.com/c/1375c062-4b67-437d-860b-e065a2980f57
      - fetch associated entities lazily, to avoid loading unnecessary data upfront.
      - optimizing performance when dealing with large collections.
    - (FetchMode.Join) : load early

  - `@BatchSize` 
    - Use along with @Fetch.
    - @OneToMany(mappedBy = "order")  @BatchSize(size = 10) private List<OrderItem> items; 
    - optimize the loading of collections.
    - instead of issuing separate SELECT queries for each item, Hibernate will fetch 10 items at a time, reducing the number of queries executed.
    - but may increase memory usage, if batch size is big.

### Advance
#### Conversion Related
- `@Convert (converter=abc.class)` 
  - more like binder - which jackson for JSON<-->Object.
  - converter for DB::table<-->Object/Entity
  - use case : performing encryption/decryption, data transformations, that are NOT directly supported by JPA, etc
- `@Lob` byte[]
- `@Temporal(TemporalType.DATE)` private Date birthDate;
  - temporal, meaning relating to time.
  - java has seperate set of API(Java.sql.*), to deal with DB Date/time eg: connection code, etc
  - `java.util.*` and `java.time.*`  <--convert-->  `Java.sql.*`
- `@Enumerated`(EnumType.STRING/ORDINAL) MyEnum
  - enums to their ordinal values or names
  - hibernate automatically converts to enum value, if not mentioned.
- Automatic/inbuilt conversion by Hibernate :
  - Boolean to int (t-1, f-0)
  - Boolean to String(t-"true")
  - java.time.LocalDate to java.sql.Date  <<<
  - java.time.LocalDateTime to java.sql.Timestamp <<<
  - enum to string/ordinal. String/int var1
- `@fetchMode` - H-specific.

#### Other
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
- FetchType : eg - @OneToMany(fetch = FetchType.LAZY/EAGER)
- @fetchMode : defines `how` the associated entities are fetched from the database. meaning `SQS queries`.
  - @Fetch(FetchMode.SELECT/JOIN/SUBSELECT)
  - FetchMode is a Hibernate-specific concept that defines how associated entities.
  - SELECT: Specifies that associated entities should be fetched lazily, using a separate SELECT statement.
  - JOIN: Specifies that associated entities should be fetched eagerly using a single JOIN query.
  - SUBSELECT: Specifies that associated entities should be fetched lazily using a single SELECT query with a subselect.

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
    - `@wherejointable` - https://www.baeldung.com/hibernate-wherejointable
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
- `@UniqueConstraint` : single/composite column
  ```
  @Table(uniqueConstraints = {
     @UniqueConstraint(columnNames = { "personNumber", "isActive" }) 
   }, ...)
  ```
---
## D. Identifier : auto,identity,sequence,table,generic | composite
`@GeneratedValue(Strategy="XXXXX")`
  - AUTO : hibernate will choose based on dialect.
    - Oracle, PostgreSQL    : Uses `SEQUENCE` because Oracle supports sequences.
    - MySQL, SQL Server, H2 : Uses `IDENTITY` because MySQL supports auto-increment columns.
  - IDENTITY : `Auto-increment`, can apply on Number types.
  - TABLE / `@TableGenerator` :  less common, PK is generated using a "table" that holds a set of unique keys.
  ```
    CREATE TABLE ID_GEN (
        GEN_NAME VARCHAR(255) NOT NULL,
        GEN_VALUE BIGINT NOT NULL,
        PRIMARY KEY (GEN_NAME)
    );
    
  @Id
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "my_table_generator")
  @TableGenerator(name = "my_table_generator", table = "ID_GEN", 
                  pkColumnName    =     "GEN_NAME",
                  valueColumnName =     "GEN_VALUE", 
                  pkColumnValue   =     "MY_ID_GEN_1",
                  allocationSize = 1)
  private Long id;
  
  - SELECT GEN_VALUE FROM ID_GEN WHERE GEN_NAME = 'MY_ID_GEN_1';
  - UPDATE ID_GEN SET GEN_VALUE = GEN_VALUE + 1 WHERE GEN_NAME = 'MY_ID_GEN_1';
  ```
  - SEQUENCE / `@SequenceGenerator` : 
  ```
  CREATE SEQUENCE MY_SEQUENCE START WITH 1 INCREMENT BY 1;
  
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_sequence")
  @SequenceGenerator(name = "my_sequence", sequenceName = "MY_SEQUENCE", allocationSize = 1)
  private Long id;
  
  allocationSize -  number of sequence values to allocate at a time
  ```

5. Random Sequence + generic generator
- avoid predictable sequences for security
- Random values can lead to fragmented indexes, which can affect performance.
eg:
- UUID/GUID / 128 bit - globally unique
  - 550e8400-e29b-41d4-a716-446655440000
  - 32 char,string, group of 5 by Hypen.
  - incorporate TimeStamp and hardware info.
  - use-case : DB:Pk, DistributedSystem, registry keys, etc
  - prg:
    - org.hibernate.annotations.GenericGenerator : 
     ```
      @GeneratedValue( generator = "myUUID")
      @GenericGenerator(name = "myUUID",strategy = "org.hibernate.id.UUIDGenerator")
     ```
    - java.util.UUID id ;
    - class ABC `implements IdentifierGenerator` >> generator() >> `@GenericGenerator(name = "UUID",strategy = "ABC")`
    - generator(){  return UUID.randomUUID().toString(); }
    - @GenericGenerator >> parameters = @Parameter(name = "prefix", value = "prod")
    - Check : com.lekhraj.java.spring.SB_99_RESTful_API.entities.CustomIdentifier
    
- NanoID
  - unique IDs that are shorter and more URL-friendly.
  - eg: DqQrAcB9jK
  
- TimestampId
```
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        long timestamp = System.currentTimeMillis();
        int random = new Random().nextInt(999999);
        return timestamp + "-" + random;
    }
```
6. Composite Identifiers
- https://www.baeldung.com/hibernate-identifiers
- @Embeddable Class ABC : Also public no-agr const, define equal and hashcode
- then inject @EmbeddedId ABC id;

## D. Validator 
- <artifactId>spring-boot-starter-validation</artifactId>
- @Size(min = 3, max = 15) : on String
- @length(min = 3, max = 15) : on Collectoin
- @NotNull

---
## Z.More

- Scenario-1::
  - t1::pk=id has (1toM) t2,
  - t2::pk=id,fk=t1_id. 
  - can make fk unique ? I think NO. 
 
---
### pending
1. program on inheritance 
2. program on relationship
