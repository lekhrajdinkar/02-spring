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

- @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

- @OneToOne, @OneToMany, @ManyToOne, @ManyToMany.
- @JoinTable and @JoinColumn() : referencedColumnName

### Advance
- @Convert
- @Lob byte[]
- @Temporal(TemporalType.DATE) private Date birthDate; //java.util.Date
- @Enumerated(EnumType.STRING/ORDINAL) MyEnum
  - enums to their ordinal values or names
  - hibernate automatically converts to enum value, if not mentioned.
- @embedded and @embeddable
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

## B. Entity Relationships
- https://chatgpt.com/c/1375c062-4b67-437d-860b-e065a2980f57
- owner class/entity defines @JoinColumn(name="newName")
- uni or bi directional : both has references of each other.
- 1-2-1 
  - any class can be owner
  - other class : @mapperBy=<propertyName mentioned in owner-class>
- C1:1-2-M and C2:M-2-1(owner)
  - C1:@mapperBy=<propertyName mentioned in owner-class>
  - C1:M-2-M and C2:M-2-M
    -  no one is owner.
    - create @JoinTable.
    ```
    @ManyToMany
    @JoinTable(
      name = "student_course",
      joinColumns = @JoinColumn(name = "student_id"),
      inverseJoinColumns = @JoinColumn(name = "course_id")
    )
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
