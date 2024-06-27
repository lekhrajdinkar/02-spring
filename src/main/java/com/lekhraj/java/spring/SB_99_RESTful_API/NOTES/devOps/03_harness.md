# harness
- https://developer.harness.io/docs/

---
## harness projects
### frontend project
- module : CD
- https://app.harness.io/ng/account/e0wDKKO_S46x3M75TWv0iw/module/cd/orgs/default/projects/frontendproject/get-started

- developer guide:
  - https://developer.harness.io/docs/platform/git-experience/configure-git-experience-for-harness-entities/#step_1_add_a_remote_pipeline
  - https://developer.harness.io/docs/continuous-integration/use-ci/set-up-build-infrastructure/define-a-docker-build-infrastructure/

- delegates:
  - docker run  --cpus=1 --memory=2g     -e DELEGATE_NAME=docker-harnnessdelegate     -e NEXT_GEN="true"     -e DELEGATE_TYPE="DOCKER"     -e ACCOUNT_ID=e0wDKKO_S46x3M75TWv0iw     -e DELEGATE_TOKEN=MGY2OGJmMWQwYjMwZGM5NDYzZDM5NGFlMDg5Mzk4NzY=     -e DELEGATE_TAGS=""     -e LOG_STREAMING_SERVICE_URL=https://app.harness.io/log-service/ -e DELEGATE_TAGS="windows-amd64" -e RUNNER_URL=http://localhost:3000   -e MANAGER_HOST_AND_PORT=https://app.harness.io harness/delegate:24.06.83304 
