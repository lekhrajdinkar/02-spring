# Role and permission management
- enabling `fine-grained control` over database access and operations.
- FORMAT : **GRANT/REVOKE** `privileges` on `resource` to/from `role` :point_left:

## 1 **Role/user**
- role/user has **attributes**. 
- can alter role add/remove attribute.
- \du
```
LOGIN     : Enables the role to log in as a user.
SUPERUSER : Grants all privileges.
CREATEDB  : Allows the role to create databases.
CREATEROLE: Allows the role to create and manage other roles.
INHERIT   : Allows a role to inherit privileges from granted roles.
NOINHERIT   : Prevents privilege inheritance.
REPLICATION : Grants the ability to manage streaming replication.
PASSWORD: sets login password

-- add "NO" prefix to remove. eg: NOLOGIN 
-- WITH is optional.
```
- examples:
```
-->  create role
CREATE ROLE admin SUPERUSER;
CREATE ROLE user_role;

CREATE ROLE app_r;
CREATE ROLE app_rw;
CREATE ROLE app_rwx;

CREATE ROLE userAsRole WITH LOGIN PASSWORD 'secure_password'; 

--> create user
CREATE USER bob WITH PASSWORD 'password456' CREATEDB CREATEROLE;
CREATE USER alice WITH PASSWORD 'password123';
CREATE USER admin WITH PASSWORD 'adminpassword' SUPERUSER;

--> Alter attribute:
ALTER ROLE admin WITH SUPERUSER;
ALTER ROLE user_role WITH LOGIN NOINHERIT;
ALTER ROLE user_role NOLOGIN;  

-->  role inheritance. eg: user_role inherits admin privileges
GRANT admin TO user_role; 
REVOKE admin FROM user_role;

```
## 2 **Privileges**: 
- like verbs in k8s 
- like actions in aws iam
- 
```
  ALL    : Grants all privileges.
  SELECT : Permission to query data.
  INSERT : Permission to add data.
  UPDATE : Permission to modify data.
  DELETE : Permission to remove data.
  USAGE  : Grants access to schemas or sequences.
  CONNECT: Permission to connect to the database.
```
## 3 **DB Resources/object**: 
- table, schema, view, etc
---
## examples on permission :yellow_circle:
```
-- db
GRANT CONNECT ON DATABASE mydb TO alice;

-- schema
GRANT USAGE ON SCHEMA public TO user_role;
GRANT CREATE ON SCHEMA public TO admin;

-- table
GRANT ALL ON TABLE employees TO admin; --admin is role
GRANT SELECT, INSERT ON TABLE employees TO user_role;
REVOKE DELETE ON TABLE employees FROM user_role;

-- column level
GRANT SELECT (salary) ON employees TO user_role;

-- function level
GRANT EXECUTE ON FUNCTION calculate_bonus() TO admin;

-- ==== IMP: privelges on future object/table ====                <<< 
ALTER DEFAULT PRIVILEGES IN SCHEMA public  -- add this list first
GRANT SELECT ON TABLES TO user_role;

```
