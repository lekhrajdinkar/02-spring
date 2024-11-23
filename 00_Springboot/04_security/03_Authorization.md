# Authorization
## ways to do:
### `Role-based Access Control (RBAC)`
- Assigning roles to users and granting access based on those roles.
- note: cg-LDAP takes care

### `URL-based Security`
- Restricting access to web resources based on URL patterns.

### token based : `OAuth 2.0` :green_circle:
- springs helps to integrating with external authentication-providers(okta,google,etc)
- Access Token generate by Okta ; requested by UI.
- `Spring <--> okta <--> LDAP Integration`, for Authorization
- parsing and validating JWT tokens.

### `Method-level Security` 
- https://www.baeldung.com/spring-security-method-security
- Anno:
  - @PreAuthorize :point_left:
  - @PostAuthorize
  - @Secured
  - @RolesAllowed