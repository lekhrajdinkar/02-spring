https://developer.hashicorp.com/terraform/tutorials/modules/module
https://developer.hashicorp.com/terraform/tutorials/modules/pattern-module-creation - check this example
---
# modules
## concept
- organize config into smaller config.
- import from Each other.
- each directory represents a separate configuration.
- benefits:
  - `Reusability`: Write code once and reuse it in multiple configurations or environments.
  - `Maintainability`: Encapsulate complex configurations into simpler, reusable components.
  - `Organization`: Keep your Terraform code organized by grouping related resources together.
- Type:
  - `remote` : 
    - "terraform-aws-modules/vpc/aws"
    - relying on the work of others to implement common infrastructure scenarios.
    - Like, packages, modules, libraries in other prog language
  - `local` : localFileSystem -  "./directory-1/child-module-1.tf"
- if child-module-1 has 2 variable and no default value set.
  - then have provide/pass value, while import module.   <<<< 
  ```
  module "child-module-1" {
    source = "./directory-1/child-module-1.tf"
    child_var_1 = value-1
    child_var_2 = value-1  <<<<
    ...
    ...
    # rather than passing soo many varaible, pass object. -- good practice.
  }
  ```
- **terraform init | get**  --> installs the module. 
- `${path.module}` --> built-in expression ,file path of the current module being executed
---

## customize module


