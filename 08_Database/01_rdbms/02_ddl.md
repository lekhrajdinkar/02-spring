# DDL
- define, modify, and manage the **structure** of database.
- **Implicitly commits** changes
## A. Schemas

---
## B. Tables
### create 
```
ALTER TABLE employees ADD COLUMN department VARCHAR(50);
```
### Alter
```
ALTER TABLE employees ADD COLUMN department VARCHAR(50);
```
### drop
```
DROP TABLE employees;
```
### truncate
- Empties a table but retains its structure
```
TRUNCATE TABLE employees
```

---
## C Indexes