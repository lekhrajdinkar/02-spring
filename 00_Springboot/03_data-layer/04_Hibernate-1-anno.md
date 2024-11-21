- anno : - https://chatgpt.com/c/1375c062-4b67-437d-860b-e065a2980f57
--- 
# Hibernate
## A. Annotation
### Common Annotation
  - `@Entity`(name="new_entityname"),  @version feild
  - `@Basic`(fetch=FetchType.EAGER,optional=true).
    - this is already added by default on all column.
    - basic mapping, field to a db column.
  - `@Table`(name="new_tablename") 
  - `@Column`(name="new_colname", updateble=f/t), more 
    - Lenght=255, string-valued column length. / @Length
    - Precision and Scale. for decimal feild.
  - `@Transient`
  - @embedded, @embeddedId, @Embeddable
  
### relationship
- @OneToOne, @OneToMany, @ManyToOne, @ManyToMany. : Add FK and create JoinTable bts.
- `@JoinTable and @JoinColumn()`
- `@WhereJoinTable(clause = "columnInJoinTable='value-1'")` https://www.baeldung.com/hibernate-wherejointable
- `@OrderBy("colInManySide ASC")` Sorting Children within Each Parent
- `@Fetch @batchSize` - use together in relation : 1-2-M,etc
  
### Inheritance
- `@MappedSuperclass`
- `@Inheritance(strategy = InheritanceType.SINGLE_TABLE)` : on parentClass
- `@DiscriminatorColumn(name="columnName",discriminatorType = DiscriminatorType.INTEGER)` : on parentClass
- `@DiscriminatorValue("1")` : on ChildClass

### Performance
- q.setFetchSize(10)
- @BatchSize(size = 20) at Entity level for All operations.
- custom code to achieve batch behaviour :
  - for loop >> on iteration 20, perform flush amd clear em/session.
- Small Fetch Size: May lead to more frequent database calls, increasing network latency and overhead.
- Large Fetch Size: Reduces the number of database calls but consumes more memory as more rows are loaded into memory at once.
- `@BatchSize` on Entity level
- option for relation/many-side:
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
    
  - @OneToMany(`fetch` = FetchType.LAZY/EAGER), etc
  - @Basic(fetch=FetchType.EAGER)

### Conversion
- `@Convert (converter=abc.class)` 
  - more like binder - which jackson for JSON<-->Object.
  - converter for DB::table<-->Object/Entity
  - use case : performing encryption/decryption, data transformations, that are NOT directly supported by JPA, etc
  - implement `AttributeConverter<entityType, dbType>`
    - dbType convertToDatabaseColumn(entityType)
    - entityType convertToEntityAttribute(dbType)
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


### Other
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
- `@SqlResultSetMapping` : Map @NamedNativeQuery result to target(Entity/Tuple)

### Annotation from SB-Jpa-Data-starter
- `@EnableTransactionManagement`
- `@Transactional`
- `@Query` + `@Param`
- `@QueryHints`({@QueryHint(name = "org.hibernate.fetchSize", value = "10")})
-----
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
       @UniqueConstraint(columnNames = { "personNumber", "isActive" }) ,
       @UniqueConstraint(columnNames = { "personNumber" }) 
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
- then inject `@EmbeddedId` ABC id;

---

## D. Validator 
- <artifactId>spring-boot-starter-validation</artifactId>
- @Min / @Max / @DecimalMin/MAX / @Range- on numbetypes
- @Size(min = 3, max = 15) : on String
- @length(min = 3, max = 15) : on Collection
- @Null/ @NotNull / @NotEmpty / @NotBlank
- @AssertTrue / @AssertFalse
- @Pattern(regexp = "^[0-9]{10}$") String phoneNo
- Date:
  - @Past
  - @PastOrPresent
  - @Future
  - @FutureOrPresent
- More (H-specific) : @URL, @email, @CreditCardNumber, @Currency, @UUID
- ### Custom validator:
  - create custom Annotation1 and annotate with `@Constraint(validatedBy = Validator1.class)`
  - class Validator1 implements `ConstraintValidator`<Annotation1, String> // string:: TypeOfvalueBeingValidate 
  - Apply @Annotation1

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
