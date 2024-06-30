# Terraform / IAC
- Switching from one provider (e.g., AWS) to another (e.g., Azure) usually requires rewriting the configuration.
- However, Terraform provides ways to make this process more manageable/consistent.
  - Define common Variables,Outputs, modules, etc across providers. 

## install
1. windows: install binary in local, set PATH.
2. HCP (cloud plateform) : 
    - https://app.terraform.io/app/lekhrajdinkar-org/workspaces/banzai-dev_api-driven
    - org > project > workspace(cli/api driven)
    - store state, run history, workspace Variable, long running plan
---
## Commands :
   - `fmt` , `validate`
   - `plan` , `apply` , `destroy`
   - `login` - to connect to  [ HCP-cluser > org >project > workspace ]
   - `show`, `state` [ list, etc ]
   - `output`

---   
## HCL (HashiCorp Configuration Language)
  - facts:  
    - String interpolation : eg `web-sg-${var.resource_tags["project"]}-${var.resource_tags["environment"]}`
    - Terraform configurations can also be written in `JSON`
    - `state file` - keep it secure and encrypted.

  - configuration-1: 
    - can make declaration anywhere. But its good practice to have seperate files.
    - keep them in same folder.
  ```
    - main.tf
    - backend.tf - org, `TF_CLOUD_ORGANIZATION`=org1
    - variable.tf
    - provider.tf
    - dev.qa,prod.`tfvars`
    - s3-resource.tf, sqs-resource.tf, etc
    - output.tf
  ```

  - `provider` :
    - aws : https://registry.terraform.io/providers/hashicorp/aws/latest
  
  - `variable`  (32 char max)
     - types  - number, string, list, map, bool, tuple, object 
     - use : var.*
     - replace default value:  
       - `terraform apply -var var1=val1`
       - `terraform apply -var-file var1=val1.tfvar`
     - `validation` { condition=, error_message=}
     -  sensitive = true : 
       - apply will prompt to enter values, or can privide via tfvar file too.
    
  - `output`
    - sensitive = true : will not be printed on logs.
    - `terraform output` -> query : output1, json, etc

  - `locals` 
    - locals { instance_count = var.environment == "production" ? 5 : 1 }
    - name to complex expressions or repeated values
    - making your configuration easier to read and maintain.
    - sensitive = true : will not be printed on logs.
    - use : local.*



  - `resource`
    - `attribute` : (optional, mandatory)
    - `argument` : resourceType.resourceName.agrument.*
    - Resource Lifecycle : ?
    - Resource dependencies:
      - trf creates dependency graph.
      - `Implicit`, eg: ec1 > elasticIP , automatically infer by attribute
      - `Explicit` : certain scenario, need to tell explicitly using `deponds_on`
        - eg : depends_on = [aws_s3_bucket.r1, aws_instance.r1]
    - `count`
      - Manage `similar resources` with count.
      - `replicates` the given resource with given count.
      ``` 
        eg: resource "aws_instance" "app" { count = 4 }
          - length(aws_instance.app) : 4
          - aws_instance.app : list of all instances.
          - aws_instance.app.*.id : list of ids
          - aws_instance.app[0] : first instance tr provisioned.
          - aws_instance.app[count.index] : current index, useful while iterate.
      ```
    - `for-each` = var.projects # p1:{}, p2:{}, etc
      - on map : `each.key` and `each.value.?`
      - on list/set : `each.key`=index,  `each.value`=item
      - `key`(var.projects), `sort`(key(var.projects))
      - `value`(var.projects)
      - value = { for p in sort(keys(var.project)) : p => module.elb_http[p].elb_dns_name }
      
  - `variable set` 
    - use variable across workspace/s.  

  - `Functions`
    - merge(), join(), count(), length(), sort(), key(), value()

---

## modules
 - source  = `terraform-aws-modules/vpc/aws`
 - source     = "./modules/aws-instance"

--- 

## References:
- Tutorial links:
  - https://developer.hashicorp.com/terraform/tutorials/aws-get-started/
  - https://developer.hashicorp.com/terraform/tutorials/configuration-language
  - https://developer.hashicorp.com/terraform/tutorials/cli
  - https://developer.hashicorp.com/terraform/tutorials/modules/module
  - https://developer.hashicorp.com/terraform/tutorials/provision
  - https://developer.hashicorp.com/terraform/tutorials/state/state-import

---

## More
```
client-webapp = {
      public_subnets_per_vpc  = 2,
      private_subnets_per_vpc = 2,
      instances_per_subnet    = 2,
      instance_type           = "t2.micro",
      environment             = "dev"
    },
    internal-webapp = {
      public_subnets_per_vpc  = 1,
      private_subnets_per_vpc = 1,
      instances_per_subnet    = 2,
      instance_type           = "t2.nano",
      environment             = "test"
    }
---
project-root/
├── config1/
│   ├── main.tf
│   ├── variables.tf
│   ├── outputs.tf
├── config2/
│   ├── main.tf
│   ├── variables.tf
|   ├── outputs.tf

- Each configuration (in config1/ and config2/) is independent of the other. 
- You need to run Terraform commands separately for each configuration.
- Each configuration will have its own state file.
- Resources defined in one configuration are not aware of resources defined in another configuration
---




```

