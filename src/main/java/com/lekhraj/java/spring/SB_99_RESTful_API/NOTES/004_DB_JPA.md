- https://www.baeldung.com/learn-jpa-hibernate
- https://chatgpt.com/c/9a8dd8ab-71b5-49d6-bcfe-8d9b0aa31971
-

## A. JPA
- JPA Specification --> ORM layer (focus)
- interact with a "relational" database without any SQL.
- Hibernate framework.
- Entity, relationships, EntityManager(CRUD) / `Session` in Hibernate.
- key components
  - SessionFactory(heavy, Created at startup) : in SB project, rename bean : @Bean(name = "entityManagerFactory") LocalSessionFactoryBean m(){...}
  - TransactionManager : `PlatformTransactionManager` --impl--> HibernateTransactionManager, JpaTransactionManager
  - Datasource (database connections pool): hikari, javax.sql.DataSource, DataSourceBuilder from SP,...

### 1. persistenceContext :
- runtime environment in which entity instances are managed and tracked by the EntityManager(Not Thread safe)
- heap(entity) <> PC <> Database :: sync/consistency
- acts as a first-level cache. reduce no. Db call.
- lifecycle of entity instances : New/Transient > managed > detached > removed/Deleted(markedForDelete).
- Transient - POJO which has no representation in the PC.
- keeps track of changes made to managed entities/ flush dirty entities to DB, on txn::commit.
- operations: persist,merge,detach,find,remove,refresh / Flush and close
- utility: view allManagedEntity, dirtyEntity,etc
- Types(based on Life-cycle)

  - Transaction-scoped (default) : @PersistenceContext
    ```
    tied to the transaction.
    It is created when the transaction starts and is closed when the transaction ends.
    eg: Spring-boot-jpa default behaviour. @@EnableTransactionManagement
    ```
  - Extended. workflows where a sequence of operations spans multiple transactions / stateful App. @PersistenceContext(type = PersistenceContextType.EXTENDED)

  ```
  Scenario for extended PC:
  A multi-step checkout process in an e-commerce application where a user needs to add items to the cart,
  provide shipping details, and make a payment. Each step might be handled by separate transactions,
  but the cart and order entities need to be kept consistent throughout the process.
  ```
      Downsides:
      - can introduce complexity in handling concurrency and transaction boundaries.
      - Increased Memory Usage.
      - the risk of stale data.
      - manage the lifecycle of an extended-persistenceContext explicitly. This includes ensuring that it is closed appropriately to avoid memory leaks.
      - Longer response times and potential deadlocks if not managed carefully

### 2. JPA Entity Lifecycle Events

- First Apply these on MyListener class's Methods
  - @PrePersist m(), @PostPersist m(),
  - @PreRemove m(), @PostRemove m(),
  - @PreUpdate m(), @PostUpdate m(),
  - @PostLoad m()
- then @EntityListeners(MyListener.class) @Entity MyEntity class
- fact : @GeneratedValue - expect key to available in @PostPersist.
- Like we seperaely, write bean process / life-cycle-hook fpr Spring bean.

---

## B. SpringBoot JPA Data

Provide Abstractions : https://chatgpt.com/c/8ace7914-f8cc-465e-873a-7b45974bb7b2

1. @PersistenceContext - txn-scoped.
2. AC - 3 bean - datasource, EMfactory/Sessionfactory, TxnManager.
3. @Transactional
   - Create Reliable transaction with ACID.
   - provides abstraction, no to write start and commit/rollback.
4. ACID (reliable transaction) : https://chatgpt.com/c/22d9f577-17f2-4d43-9013-401b18ca58e0
   - A tomicity : SB - @Transactional
   - C onsistency : underlying DB sol - constraints, Fk, etc
   - I solated : underlying DB sol.  
     `@Transactional(isolation="choose one") / Optimistic concurrency control`
     - RU(no concurrency - All 3 problem : dirty read, phantom, RR )
     - RC (dirty fixed, but still - phantom, RR)
     - Non-R (dirty fixed, non-R fixed, but still phantom)
     - Serializable ( All fixed )
   - D urable : underlying DB sol - permanent after commit.

```
ISOLATION :: wait for the other txn to commit/rollback before proceeding.
```

4. More on Concurrency-Control / isolations (by underlying DB)

- optimistic. eg @Transactional::Isolation::underlyingDB mechanism
- pessimistic. Custom Sync Code

---

## C. Scenarios
### multi-Http request environment (in ||)
1. PC per request
- http req1 --> thread-1 --> txn-1 --> PC-1 --> commit --> flush to DB
- http req2 --> thread-2 --> txn-2 --> PC-2 --> commit --> flush to same DB. (override)
- Summary:
  - Each HTTP request typically runs in its own thread and transaction.
  - Transactions are isolated from each other, but "concurrency-control-mechanisms"/isolation ensure data consistency.
  - Persistence contexts are tied to transactions and are independent for each request, ensuring that changes made in one request do not affect others until committed.
  - Careful design and configuration are necessary to handle concurrency and transaction management effectively in a multi-request environment. 
  - Developer has to write thread-safe code/ concurrent access code, etc

2. PC shared by multiple request.
    - Service class > @PC(Extended) Session/Em > @T m1() + @T m2() + ...
    - PROS : less DB call, fast | CONS : handle concurrency

3. Global PC
   - use second level cache.
   - PROS : very less DB call, faster | CONS : handle global concurrency
---

## Z. Extra
- check multiple session/em possiblities example : https://chatgpt.com/c/9a8dd8ab-71b5-49d6-bcfe-8d9b0aa31971
- EntityManager(Not Thread safe), meaning C R U D methods does not have sync and lock code.

### Pending
1. Second level Cache.



