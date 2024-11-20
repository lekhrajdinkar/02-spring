> boiler plate code remove : lombok, mapstruct, modelMapper
---
- web
  - https://chatgpt.com/c/831f36a0-bce4-4372-87d4-9ab6528babc5 - web kickoff
  - https://chatgpt.com/c/7d23b0fe-a7a5-43d5-9ced-69d4a344e31a - error handling
  - https://chatgpt.com/c/f4a0c9cd-c6cb-414e-888c-605c2d50340c - ext server deploy
  - https://chatgpt.com/c/efc733ec-0a20-4be2-88da-df50535517b3 - MVC overview
  - https://chatgpt.com/c/d7dba5ab-7f7a-4c1c-a443-f67f15ca09a2 - servlet3
---

# web
## web request
- idempotent
- non-idempotent

## 1 WebSocket connection - intro
- **persistent**(stateful), **bi-directional** communication channel between a client and a server over a single, **long-lived** TCP connection. 
- WebSocket connections remain open and allow for **real-time data exchange** between the client and the serve.
- 
## 2 web-aware Spring ApplicationContext : `WebApplicationContext`
- IAC container for springMVC application.
- AC aware of the web-specific features and contexts in a Servlet environment.
    - can access the `ServletContext`, provides access to the Servlet API
    - access the `ServletConfig`
- supports **web-scopes** for beans
    - request - bean is created for each HTTP request
    - session -
    - global session - never used
    - Web socket - bean is created for each WebSocket connection

## 3 CORS
- https://chatgpt.com/c/79fe00c5-9852-4956-b0ec-be9a6657359c

## 4 security threats
- XSS and CSRF 
  -  https://chatgpt.com/c/c86b5fd1-c1b8-4a6b-bb8b-bbd832a606aa
  - https://chatgpt.com/c/734bd77f-75d3-4be2-acbc-d80ef8b61b21
  - https://chatgpt.com/c/e1547ff9-6ce4-4645-8fae-56d1924daa47