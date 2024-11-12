# Harness
- `Account`: lekhrajdinkar
- `Organization`: default
- module - **Cd** , CI, security testing, etc

--- 
## project:1 - maps-outbound-api
- https://app.harness.io/ng/account/e0wDKKO_S46x3M75TWv0iw/home/orgs/default/projects/mapsoutboundapi/details
### common
- **pipeline**
    - pipeline-1 :: `build-deploy-pipeline`
        - trigger : git-push
        - stages : build, ecr-push, deploy
- **services**
- **connectors**
    - k8s-cluster-connector
    - `github-repo-connector`
    - terraform-hcp-connector
    - aws-secret-manager-connector
    - aws-account-connector
    - nexus-repo-connector (optional)
    - service-now-connector (optional)
- **environment**
  - env-group : `oz-dev`
      - `dev1`
        - infrastructure definition : none
      - `dev2`
      - `prod`
### more
- **delegate**
    - k8s-delegate
- **secrets**
    - added by aws-account-connector
    - added manual


---
## project:2 - maps-outbound-ui (`hold`)
- https://app.harness.io/ng/account/e0wDKKO_S46x3M75TWv0iw/module/cd/orgs/default/projects/frontendproject/overview