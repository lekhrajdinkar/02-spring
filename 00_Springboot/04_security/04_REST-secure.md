## Secure REST
- REST APIs are stateless, they should not use sessions or cookies, use JWT which is also Stateless.
- HTTPS: Securing data in transit using `SSL/TLS`.
- Security headers
  - `Strict-Transport-Security`
  - `X-Content-Type-Options`
  - `X-Frame-Options`
  - `Content-Security-Policy`  

## Options
### 1. basic/digest Authentication
- [Security_01_Config.java](..%2F..%2Fsrc%2Fmain%2Fjava%2Fcom%2Flekhraj%2Fjava%2Fspring%2FSB_99_RESTful_API%2Fconfiguration%2FSecurity_01_Config.java)
- AuthenticationEntryPoint - Configure it differently for basic and digest.

### 2. API Keys
- https://www.baeldung.com/spring-boot-api-key-secret
- Some REST APIs use API keys for authentication.
- An API-key is like `token`, that identifies the - `API-client to the API without referencing an actual user`.
- API-key can be sent in the queryString or header.
- it’s possible to hide the key using SSL.
- Create `Custom Filter` to Check API-Check
- eg: CCGG MuleSoft API


### 3. OAuth 2.0 JWT / Authorization :green_circle:
- auth0 : https://manage.auth0.com/dashboard/us/dev-gpg8k3i38lkcqtkw/onboarding 
  - signed up with Github
  - dev-gpg8k3i38lkcqtkw
- [00_OAuth_2.0.md](00_OAuth_2.0.md)
