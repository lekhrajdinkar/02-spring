# Role and permission management
- enabling `fine-grained control` over database access and operations.
- **granting/revoking** `privileges` on `resource` to `role` :point_left:
  - like allow/deny of aws iam.

## **role**, 
- represents to:
  - user-1
  - group-1(user-1, user-2, ..., another-role-1,...)
- role has **attributes**. can alter role add/remove attribute.
```
 === Attributes ===
LOGIN     : Enables the role to log in as a user.
SUPERUSER : Grants all privileges.
CREATEDB  : Allows the role to create databases.
CREATEROLE: Allows the role to create and manage other roles.
INHERIT   : Allows a role to inherit privileges from granted roles.
NOINHERIT   : Prevents privilege inheritance.
REPLICATION : Grants the ability to manage streaming replication.

-- example --
-- add "NO" prefix to remove, WITH is optional.

ALTER ROLE admin WITH SUPERUSER;
ALTER ROLE user_role WITH LOGIN NOINHERIT;
ALTER ROLE user_role NOLOGIN;  
```
- examples:
```
--  create role
CREATE ROLE user_role;
CREATE ROLE app_r;
CREATE ROLE app_rw;
CREATE ROLE app_rwx;

--  role horarchy : inherit privelges from one role to another
-- eg: user_role inherits admin privileges
GRANT admin TO user_role; 
```
## **Privileges**: 
- like verbs in k8s 
- like actions in aws iam
- **ALTER DEFAULT PRIVILEGES** - Set privileges for future database objects.
```
  ALL    : Grants all privileges.
  SELECT : Permission to query data.
  INSERT : Permission to add data.
  UPDATE : Permission to modify data.
  DELETE : Permission to remove data.
  USAGE  : Grants access to schemas or sequences.
  CONNECT: Permission to connect to the database.
```
## **DB Resources**: 
- table, schema, view, etc

## examples:
```
-- table
GRANT ALL ON TABLE employees TO admin; --admin is role
GRANT SELECT, INSERT ON TABLE employees TO user_role;
REVOKE DELETE ON TABLE employees FROM user_role;

-- schema
GRANT USAGE ON SCHEMA public TO user_role;
GRANT CREATE ON SCHEMA public TO admin;

-- column level
GRANT SELECT (salary) ON employees TO user_role;

-- function ;leve;
GRANT EXECUTE ON FUNCTION calculate_bonus() TO admin;


```
