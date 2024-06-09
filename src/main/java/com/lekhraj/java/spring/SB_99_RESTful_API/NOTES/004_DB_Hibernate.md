## A. Hibernate Annotation
### Common Annotation
- @Entity(name="new_entityname")
- @Basic(fetch=EAGER,optional=true).
  - this is already added by default on all column.
  - basic mapping, field to a db column.
  
- @Table(name="new_tablename") 
- @Column(name="new_colname"), more 
  - Lenght=255, string-valued column length.
  - Precision and Scale. for decimal feild.

- @Transient

- @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

- @OneToOne @JoinColumn(name = "profile_id")
- @OneToMany:
- @ManyToOne:
- @ManyToMany:

- @JoinTable and @JoinColumn()

### Advance
- @Lob byte[]
- @Temporal(TemporalType.DATE) private Date birthDate; //java.util.Date
- @Enumerated(EnumType.STRING/ORDINAL) MyEnum
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

## B.Common task in DB
1. Default value
- @Column(columnDefinition = "varchar(255) default 'John Snow'")
- Inside Entity class :: private String firstName = "John Snow";

2. Nulluble constraint
- @Column(nullable=t/f)
- @Basic(optional=t/f)

3. unique Contraint
- @Column(unique=t/f)

## C. Validation
- @Size(min = 3, max = 15) : on String
- @length(min = 3, max = 15) : on Collectoin

---
## D.More
- JPA Attribute Converters
  - performing encryption/decryption, data transformations that are NOT directly supported by JPA, etc
