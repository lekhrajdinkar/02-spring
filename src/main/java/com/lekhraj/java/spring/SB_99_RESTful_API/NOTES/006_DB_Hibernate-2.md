https://www.baeldung.com/learn-jpa-hibernate
---
##  A. Query : parent class. 
- key point:
    - TypedQuery<T> : T = ResultType
    - NamedQuery  : centralized, Pre-validated, use unique name in PC to avoid collision.
    - https://www.baeldung.com/hibernate-named-query
    - `@NamedQuery` and `@NamedNativeQuery` : SB-JPA-DATA uses these BTS / reusable queries/ organize code.
    - use NamedNativeQuery only if certain feature not support by JPQL, and need DB specific optimization, etc
    - name=<unique in PC>,query='static JPQL/SQL'
    - timeout = 1, fetchSize = 10, cacheable=t/f,  resultClass=result.class
    - q.getSingleResult(); and q.getResultList()/Set();
- More/pending
    - cacheMode=GET, IGNORE, NORMAL, PUT, or REFRESH
    - flushMode=ALWAYS, AUTO, COMMIT, MANUAL, or PERSISTENCE_CONTEXT


## B. Writing Queries
- joinType - INNER, OUTER(LEFT/RIGHT)
- https://chatgpt.com/c/1375c062-4b67-437d-860b-e065a2980f57
1. STATIC
- JPQL and Native-SQL
- JPQL take advantage of OOP of entity class.
- Positional parameters vs named parameters

2. DYNAMIC : Criteria API (exclude)

--- 
## More
1. Sorting Query Results with Spring Data : https://www.baeldung.com/spring-data-sorting#sorting-with-spring-data
2. Query Plan Cache
---
## Pagination and Sorting
1. 

---
## pending:
1. Program : JPQL - Query Class, em.createQuery(), q,getSingleResult() 
2. Custom repository
3. 