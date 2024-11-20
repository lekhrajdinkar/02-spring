# web
## 1. WebSocket connection - intro
- **persistent**(stateful), **bi-directional** communication channel between a client and a server over a single, **long-lived** TCP connection. 
- WebSocket connections remain open and allow for **real-time data exchange** between the client and the serve.
- 
## 2. web-aware Spring ApplicationContext : `WebApplicationContext`
- IAC container for springMVC application.
- AC aware of the web-specific features and contexts in a Servlet environment.
    - can access the `ServletContext`, provides access to the Servlet API
    - access the `ServletConfig`
- supports **web-scopes** for beans
    - request - bean is created for each HTTP request
    - session -
    - global session - never used
    - Web socket - bean is created for each WebSocket connection