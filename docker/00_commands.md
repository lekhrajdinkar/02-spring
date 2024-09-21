- 
- docker `build` -t name:version
- docker pull | push | login

## image
- docker images  
- docker rmi 
- docker tag <imageid> name:version/latest

## Container
- docker run <command> --name=c1 -it -d -v -e... --network=n1 -p...  <image-1>
- docker ps -a
- docker start | stop | restart c1
- docker rm c1
- docker exec c1 <command>

---
- `dockerfile`
  - **ENDPOINT**  [echo, helloworld] 
    - docker run c1 cmd-1
    - cmd-1 will be `appended` after echo helloworld
    - use `--entrypoint` option to update it
  - **CMD** can override [echo, helloworld]
    - docker run c1 cmd-1
    - cmd-1 will be `replace` echo helloworld
  - ENDPOINT + CMD : [echo], [helloworld]
  - ADD
  - WORKDIR
  - COPY
  - EXEC

---
- container sare meant to run process.
- once process terminated , container gets exited.
- CMD defines the process that run in container.
- 