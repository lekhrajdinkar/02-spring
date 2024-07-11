# IAM
- IAM_01 https://chatgpt.com/c/3bfd592e-3ccc-403f-a61c-8e6ab72eacf5

## kick of
- root `user` + MFA + use strong password policy
- IAM user, `Group` (attach permission)
- permission : JSON doc / `policies`
- prebuilt policies.
- AWS CLI / prog - Access keys
- IAM `roles`
  - `trusted entities `: 
    - service - ec2, s3,etc
    - cross account service/user, 
    - `federated` user (oIP, SAML2) :
      - `outside AWS user`
      - users authenticated by an external identity provider.
  - designed to provide temporary security credentials to above entities / assume role.
  - policy json >> principle ==  trusted entities.
  - `STS` : secure token service
    - provides `temporary` security credentials(Access key + secret) + token(permission)
    - short-lived, few mins.
    - actions : `assumeRole`, `assumeRoleWithSAML`, etc

- Audit / `Reports`:
  - user level : access advisor, etc
  - account level : credential report, etc

