# A. Elastic Beanstalk : deployments

## 1. All at once 
- deploy all in one go 
- fastest
- downtime

## 2.1 Rolling
- update a few instances at a time (bucket), 
- and then move onto the next bucket once the first bucket is healthy

## 2.2 Rolling (with additional batches)
- like rolling, but spins up new instances to move the batch (so that the old application is still available)

## 3 Immutable 
- spins up new instances in a new ASG, 
- deploys version to these instances,
- and then swaps all the instances when everything is healthy

## 4 Blue Green
- create a new environment and switch over when ready
- Traffic Splitting: canary testing – send a small % of traffic to new deployment
