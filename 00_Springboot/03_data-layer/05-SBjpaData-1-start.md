- https://chatgpt.com/c/8ace7914-f8cc-465e-873a-7b45974bb7b2
--- 
# SpringBoot JPA Data
## A Intro
- Provide Abstractions.
- @PersistenceContext - txn-scoped.
- AC - 3 bean - datasource, EMfactory/Sessionfactory, TxnManager.
- @Transactional
   - Create Reliable transaction with ACID.
   - provides abstraction, no to write start and commit/rollback.
- ACID (reliable transaction)
  - A tomicity : SB - @Transactional
  - C onsistency : underlying DB sol - constraints, Fk, etc
  - I solated : underlying DB sol.  
    `@Transactional(isolation="choose one") / Optimistic concurrency control`
    - RU(no concurrency - All 3 problem : dirty read, phantom, RR )
    - RC (dirty fixed, but still - phantom, RR)
    - Non-R (dirty fixed, non-R fixed, but still phantom)
    - Serializable ( All fixed )
    - D urable : underlying DB sol - permanent after commit.
   
- **optimistic Locks** : read TS, Write TS, etc (TS=timestampe and version)
  - add in entity
    - `@Version` private long version;
  - ObjectOptimisticLockingFailureException

- More on Concurrency-Control / isolations (by underlying DB)
   - optimistic. eg @Transactional::Isolation::underlyingDB mechanism
   - pessimistic. Custom Sync Code






