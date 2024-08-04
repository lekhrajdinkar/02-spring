## SSM (Session Manager)
- no SSH needed / secure alternative to ssh
- policy:`AmazonSSmManagedIntanceCore`. 
- launch ec2-i1(window/Linux/mac) and attach above policy. this will make `SSM agent`, active on ec2-i1.
- `FleetManager` service > managedNode > view :: all EC2 instance managed by SSM
  - start session
  - launch blank terminal window : secure shell
  - run any command.
  - once closed, check session history.
  - Also, it sends session log data to `S3/CW`


![img.png](../99_img/moreSrv/ssm.png)

---

- 3 ways to connect to ec2-i
  - ssh
  - ec2 instance connect
  - ssm
