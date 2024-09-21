- docker `build` -t name:version
- docker pull | push | login

## image
- docker images  
- rmi 
- docker tag <imageid> name:version/latest

## Container
- run <command> --name=c1 -it -d -v -e... --network=n1 -p...
- ps -a
- start | stop | restart
- rm
- exec c1 <command>

---
- `dockerfile`
  - ENDPOINT : cannot override, [echo, helloworld]
  - CMD : can override [echo, helloworld]
  - ENDPOINT + CMD : [echo], [helloworld]
  - ADD
  - WORKDIR
  - COPY
  - EXEC
  - EXPOSE 8080
  - RUN pip install flask
