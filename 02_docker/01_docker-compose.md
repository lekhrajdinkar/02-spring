## Docker compose
- docker-compose.yml `up`

### version-1
  - container-name-1 :
    - image or build : 
    - ports:
    - environment:
      - USER_NAME: xxxxx
      - PASSWORD: xxxxxx
    - link:
      - db:db or just db, which become hostname.
      - redis:rd
  - container-name-2 :
  - container-name-3 :
  - sequence of container matters. v2 has depends-on.
---
### version-2
  - by default create one network(name:current_dir_default) and associate all containers with it.
  - `services`:
      - container-name-1 :
        - link not needed, already **link all container/s (within default n/w)** with each other.
        - for external network can add link.
      - container-name-1 :
      - `depends on` :
      - network/s:
        - network-1
        - network-2
        - one container can be associated with multiple networks
  - `networks`:
    - can also create 
    - network-1
    - network-2
---
### version-3
  - lot more improvement
  - pending...
