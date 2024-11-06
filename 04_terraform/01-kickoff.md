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
- `terraform -version`  # Terraform v1.9.0
``` 
  console       Try Terraform expressions at an interactive command prompt
  fmt           Reformat your configuration in the standard style
  force-unlock  Release a stuck lock on the current workspace
  get           Install or upgrade remote Terraform modules
  graph         Generate a Graphviz graph of the steps in an operation
  import        Associate existing infrastructure with a Terraform resource
  login         Obtain and save credentials for a remote host , [ HCP-cluser > org >project > workspace ]
  logout        Remove locally-stored credentials for a remote host
  metadata      Metadata related commands
  output        Show output values from your root module
  providers     Show the providers required for this configuration
  refresh       Update the state to match remote systems
  show          Show the current state or a saved plan
  state         Advanced state management
  taint         Mark a resource instance as not fully functional
  test          Execute integration tests for Terraform modules
  untaint       Remove the 'tainted' state from a resource instance
  version       Show the current Terraform version
  workspace     Workspace management
```
