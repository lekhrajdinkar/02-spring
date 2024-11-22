- https://www.baeldung.com/transaction-configuration-with-jpa-and-spring - topics
- https://chatgpt.com/c/7b6dd03e-ca98-44d5-87a6-73c23026a009 - programmatic txn
---
# Hibernate - Transaction management
- add @Bean **PlatformTransactionManager**
- add **@EnableTransactionManagement**, or add spring-data-*-starter dependencies
- org.springframework.transaction = **DEBUG**
- fact:
  - Mixing the database I/O with other types of I/O in a transactional context isn’t a great idea.

## A. @Transactional
- manage transaction boundaries in a **declarative** way.
- Spring creates **proxies**, to inject transactional-logic, before and after the running method.
- only **public** methods.
- @Transactional(attribtes=) : check below

### `propagation` 
- **requires_new** -> AlwaysNewT 
- **Required** -> t1 else t2new 
- **mandatory** -> t1 else ex  
- **nested** -> t1 -> inner-t1
- **SUPPORTS** -> t1 
  - if an active transaction exists,then use it. Else gets executed non-transactional:
- **none**  
- **never** -> none
- Nested transaction is also possible
  - Propagation.NESTED
  - inner txn :  independent transaction within the context of an existing/outer transaction.
  - if inner transaction rolls back, it only rolls back the nested transaction, not the outer transaction.
  - Not all databases and transaction managers provide full support for nested transactions.
  - `transactionManager.setNestedTransactionAllowed(true);`
  - it uses Save points.
  - 
### `isolation` 
- txn side effect : Dirty-read, Non-repeatable read, Phantom read.
- protection levele: READ_UNCOMMITTED, READ_COMMITTED, REPEATABLE_READ and SERIALIZABLE.
- can check: [03_ACID.md](03_ACID.md)

### `timeout`

### `readOnly` 
– t/f 
- just a hint for the persistence provider that the transaction should be read only. 
- optimizes performance.

### `rollbackFor / noRollbackFor`
- @Transactional(`rollbackFor` = {Exception.class, SpecificException.class})
- @Transactional(`rollbackForClassName` = {"java.lang.Exception", "com.example.SpecificException"})
- if we don't mention any rule, rollback happens on unchecked-exceptions/RuntimeException



## B. programmatic : `AOP + TransactionTemplate`
- check [StudentServiceImpl.java](..%2F..%2Fsrc%2Fmain%2Fjava%2Fcom%2Flekhraj%2Fjava%2Fspring%2FSB_99_RESTful_API%2Fservice%2FStudentServiceImpl.java)
