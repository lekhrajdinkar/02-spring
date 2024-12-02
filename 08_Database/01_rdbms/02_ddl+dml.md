# DDL :green_circle:
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
```
```

---

# B DML :green_circle:
- Can be rolled back
- explicit commit needed
## A. INSERT
```
```
---

## A. UPDATE
```
```
---

## A. DELETE
```
```
---

## A. SELECT
```
# 1. JSONB
CREATE TABLE products ( id SERIAL PRIMARY KEY,data JSONB );
INSERT INTO products (data) VALUES ('{"name": "Laptop", "price": 1200}');

SELECT data->>'name', data->>'price' FROM products WHERE data->>'name' = 'Laptop';


```