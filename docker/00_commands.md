- docker `build` -t name:version
- docker pull | push | login

## image
- docker images  
- docker rmi image-1
- docker tag <imageid> name:version/latest

## Container
- docker run < command > --name=c1 -it -d -v -e... --network=n1 --user < userID > -p... --cap-add < CAPABILITY > image-1
- docker ps -a
- docker start | stop | restart c1
- docker rm c1
- docker exec c1 <command>

---
- `dockerfile`
  - ENDPOINT : cannot override, [echo, helloworld]
  - CMD : can override [echo, helloworld]
  - ENDPOINT + CMD : [echo], [helloworld]
  - RUN
  - ADD
  - WORKDIR
  - COPY
  - EXEC
  - USER < userId > - by default all process run the `root user` (with limited set of **capability**)
    - --cap-add < CAPABILITY > eg: MAC_ADMIN
    - --cap-drop < CAPABILITY >