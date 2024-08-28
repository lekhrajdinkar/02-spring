## 1
You have multiple AWS accounts within a single AWS Region managed 
by AWS Organizations and you would like to ensure all Amazon EC2 
instances in all these accounts can communicate privately. 
Which of the following solutions provides the capability at the `CHEAPEST` cost 

- VPC peering
  - only pay for the data transfer
- AWS Transit Gateway
  - pay for the data transfer
  - incur costs for each attachment (VPC, VPN, etc.)
- AWS PrivateLink
  - high costs associated with endpoint services and data processing
- VPN Connections
  - VPN gateway attach to each VPC.
  - Site2Site connection
  - VPN gateway charges and data transfer costs