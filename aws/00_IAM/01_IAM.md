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

---
## Advance Polices
### Conditions
- Allow Access Based on IP Address
```
        {
            "Effect": "Allow",
            "Action": "s3:*",
            "Resource": "arn:aws:s3:::example-bucket/*",
            "Condition": {
                "IpAddress": {
                    "aws:SourceIp": "203.0.113.0/24"
                },
                // always AND
                "StringEquals": {
                        "aws:SourceVpc": "vpc-12345678"
                }
            }
        }
```

- Allow Access During Specific Time Period
```
            "Condition": {
                "DateGreaterThan": {
                    "aws:CurrentTime": "2024-07-19T09:00:00Z"
                },
                "DateLessThan": {
                    "aws:CurrentTime": "2024-07-19T17:00:00Z"
                }
            }
```

- Allow Access Based on MFA Authentication
```
            "Condition": {
                "Bool" / "BoolIfExist": {
                    "aws:MultiFactorAuthPresent": "true"
                }
            }
```

- tag
```
"Condition": {
                "StringNotEquals" / "StringEquals": {
                    "ec2:ResourceTag/Environment": "Production",
                    "aws:principleTag/Departmnet": "d1"
                }
            }
```
- more eg :
  - "aws:requestedRegion": "true"
  - "aws:priciplaOrgId": "0-xxxxxxxxx"
  - ![img.png](../99_img/security/org/img-4.png)