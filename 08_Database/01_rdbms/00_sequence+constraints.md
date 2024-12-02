## project: 
- code: [database](..%2F..%2F..%2Fsrc%2Fmain%2Fjava%2Fcom%2Flekhraj%2Fjava%2Fspring%2Fdatabase)
- db: postgres - running locally on pod.
- ![img.png](shopping_app/er.png)

---
## A. Architecture
### ACID
### Process/s
- operates with a **process-based architecture**, 
  - **Backend Processes** 
    - for each `client` connection.
    - Handles queries, transactions, and data operations.
  - **Postmaster Process** (Main Server Process)
    - Manages connections and spawns new backend processes for each client.
    - Coordinates system-level activities, like `crash recovery`.
  - **Background Processes**
    - `WAL Writer`: Writes WAL (Write-Ahead Logging) data to disk.
    - `Checkpointer`: Periodically writes dirty pages from shared buffers to disk.
    - `Archiver`: Archives WAL files for point-in-time recovery (if enabled).
    - `Stats Collector`: Gathers performance and usage statistics.
  - **Replication Processes**
    - WAL Sender: Streams WAL data to replicas for replication.
    - WAL Receiver: Receives WAL data on standby servers.
    
---
## B. **Constraints**
- **NOT NULL**
- **UNIQUE**
- **PRIMARY KEY** : `unique + not_null` 
- **FOREIGN KEY** : `referential integrity`
  - relationship between columns in two tables.
  - The foreign key column in one table references the primary key in another table
- **CHECK** 
- **DEFAULT**
- **DOMAIN** : custom constraint. define and reuse.
  ```
  CREATE DOMAIN positive_integer AS INT
    CHECK (VALUE > 0);
  ```
- Note: can combine multiple. point_left:
```
CREATE TABLE orders (
    order_id SERIAL PRIMARY KEY,
    emp_id INT REFERENCES employees(emp_id)   [ ON DELETE CASCADE  |  ON UPDATE CASCADE ]
    name TEXT UNIQUE NOT NULL DEFAULT 'active',
    qty INT CHECK (qty > 0) positive_integer,
);

or 

CREATE TABLE orders (
    order_id SERIAL ,
    emp_id INT,
    name TEXT
    
    PRIMARY KEY (order_id)
    PRIMARY KEY (order_id, another-column) --composite PK
    UNIQUE (TEXT)
    FOREIGN KEY (emp_id) REFERENCES employees(emp_id)    [ ON DELETE CASCADE  |  ON UPDATE CASCADE ]
);


ALTER TABLE employees 
  ADD CONSTRAINT emp_name_unique UNIQUE (name);

ALTER TABLE employees 
  DROP CONSTRAINT emp_name_unique;
```

---
## C **sequence**
- pending

