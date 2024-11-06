# EMR ( Elastic MapReduce)
- Use cases: 
  - data processing 
  - `ML` 
  - `web-indexing` 
  - `big-data/hadoop`
    - emr creates `Hadoop clusters`
      - set up : Apache Spark, HBase, Presto, Flink
      - takes care of all the provisioning and configuration

- `EMR nodes/ec2-i`
  - `Master` Node: Manage the cluster, coordinate, manage health – long running
  - `Core` Node: Run tasks and store data – long running
  - `Task` Node (optional): Just to run tasks – usually `Spot instance`

- `Integrated with glue` (crawler > catalog)        