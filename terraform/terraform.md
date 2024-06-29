# Terraform /IAC
- Switching from one provider (e.g., AWS) to another (e.g., Azure) usually requires rewriting the configuration.
- However, Terraform provides ways to make this process more manageable/consistent.
  - Define common Variables,Outputs, modules, etc across providers. 

## install
1. windows: install binary in local, set PATH.
2. HCP (cloud plateform) : 
    - https://app.terraform.io/app/lekhrajdinkar-org/workspaces/banzai-dev_api-driven
    - org > project > workspace(cli/api driven)
    - store state, run history, workspace Variable, long running plan

3. Commands :
   - `fmt` , `validate`
   - `plan` , `apply` , `destroy`
   - `login` - to connect to  [ HCP-cluser >org >project > workspace ]
   - `show`, `state` [ list, etc ]
   
4. `HCL` (HashiCorp Configuration Language)
  - can make declaration anywhere. But its good practice to have seperarte files.
    - main.tf
    - backend.tf
    - variable.tf
    - provider.tf
    - dev-1.tfvars
    - for each resource type - s3.tf, sqs.tf etc
    
  - Terraform configurations can also be written in `JSON`
  - `state file` - keep it secure and encrypted.
  - `provider` : always check.
    - aws : https://registry.terraform.io/providers/hashicorp/aws/latest
  - variable set : use variable across workspace
  - `variable`  (32 char)
     - var.* 
     - Simple  - number, string, list, map, bool, tuple, object
     - repalce default value:  terraform apply `-var` var1=val1
     -  *.`tfvar` terraform apply `-var-file` var1=val1`
     - `validation` { condition=, error_message=}
     -  sensitive = true : apply will prompt eo enter values, or can privide via tfvar file too.
  - `output`, 
  - `modules`, 
  - `resource` -  `attribute` (optional,mandatory) and `argument`
  - String interppolation : eg `web-sg-${var.resource_tags["project"]}-${var.resource_tags["environment"]}`
  - Resource Lifecycle
  - `locals` { instance_count = var.environment == "production" ? 5 : 1 }
    - name to complex expressions or repeated values, making your configuration easier to read and maintain.
    - with in a configuration.


--- 
##  Tutorial
- https://developer.hashicorp.com/terraform/tutorials/aws-get-started/
- https://developer.hashicorp.com/terraform/tutorials/configuration-language
- https://developer.hashicorp.com/terraform/tutorials/cli
- https://developer.hashicorp.com/terraform/tutorials/modules/module
- https://developer.hashicorp.com/terraform/tutorials/provision
- https://developer.hashicorp.com/terraform/tutorials/state/state-import

