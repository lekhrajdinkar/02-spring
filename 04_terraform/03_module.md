https://developer.hashicorp.com/terraform/tutorials/modules/module

## modules
- Like, packages, modules, libraries in other prog language
- Each directory represents a separate module.
- container for `multiple resources` that are used together.
- module { configuration/resources }

- benefits:
- `Reusability`: Write code once and reuse it in multiple configurations or environments.
- `Maintainability`: Encapsulate complex configurations into simpler, reusable components.
- `Organization`: Keep your Terraform code organized by grouping related resources together.
- Type:
- `remote` : `registry`: "terraform-aws-modules/vpc/aws", `VCS`
- `local` : localFileSystem -  "./directory-1/child-module-1.tf"