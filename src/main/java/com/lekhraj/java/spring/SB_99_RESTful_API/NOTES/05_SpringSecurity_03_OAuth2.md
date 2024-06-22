## Application types and Flow
- handle this on 3 type on architecture/flows:
    - server-side web applications (SpringMVC, JSP) :
    - browser-based applications / SPA :
    - native/mobile apps :
    - connected devices (M2M) :
  
---
## OpenIdConnect
- Token based Authentication.  `ID-Token`
- extension over OAuth2.

--- 
##  OAuth2
- https://auth0.com/intro-to-iam/what-is-oauth-2
- Definition : OAuth standard protocol to solve `Delegated Authorization`.
    > - allow appl(Client) to access resources hosted by other web apps, on behalf of a user/resource-owner.
    > - provides consented access.
    > - restricts actions of what the client app can perform on resources, without sharing user credential.

- Token based Authorization. `Access-Token` + `Refresh Token` (long expiry )
- Token based:
  - format : JWT. 
  - token/s with multiple scope. (roles)
  - received on callback URI
- http-redirection (header-`location:url-2`, responseCode - `302`)
- client must identify/authenticate itself, when requesting an Access Token.
- Resource server must validate JWT and has role-based access code.

- Key Components : 
  - user/`resourceOwner`, `Client`, `resource-Server`, `Authorization-Server`.
      - Eg: photoPrint-App <--> Google-Drive
  - `Scopes`
    - specify exactly the `reason`, for which access to resources may be granted.
    - dependent on the Resource Server
  - `Authorization Code`
    - OAuth 2 Authorization server may not directly return an Access Token.
    - Instead, and for better security, an Authorization Code may be returned, which is then exchanged for an Access Token.

###  OAuth2 : Grant Types
- grants are the set of steps a Client has to perform to get resource access authorization.

A. `Authorization Code` Grant
- AuthServer return single-use Authorization Code to the Client, which is then exchanged for an Access Token.
- for traditional web apps where the exchange can securely happen on the server side.

B. `Implicit` Grant


C. `client-credential` Grant
- Client acquire its own credentials(client id, client secret) from the Authorization Server, identify itself when requesting an Access Token.






