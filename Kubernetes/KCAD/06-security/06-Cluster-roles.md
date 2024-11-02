- k api-resources -namespaced=true
- k api-resources -namespaced=false
- ![img.png](img.png)

---
## cluster Role and binding
- primarly to create role for `cluster`-scoped-resource. eg: node, pv
- Also, create role for `namespace`-scoped-resource. eg: pod, etc
  - this will add role for that resource in all namespace inside clusters.

- ![img_1.png](img_1.png)

