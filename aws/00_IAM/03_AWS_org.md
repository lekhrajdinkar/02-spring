# AWS Organisation (global srv)

## key term
- `organisation unit, OU` : Applied one policy(scp) : `AWSFULLAccess`
  - `OU (root)` :  
      - `Management-account` (main) : 
        - Don't apply SCP, have full access already.
        - even if we apply, no impact.
      - `member-account`-1
      - member-account-2
      - `ou (dev)`  : `SCP-2`, `SCP-3`
        - dev-account-1
        - dev-account-2
        - ...
      - `ou (prod)`
        - dev-account-1
        - dev-account-2
        - ...
      - ...
      - can have nesting > nesting> ...
    
- `policies`
  - `SCP` : Service Control Policies 
    - IAM policies - apply to `member-acct` or `OU`.
    - by default, allow NOTHING.
    - child OU `inherit SCP` from parent OU
    - strategies:
      - `AllowedList` : deny all first, then start adding allowed items.
      - `Blocklist` : allow all first, then start adding blocks.
  - `tag policies` : 
    - create tags and create polices around it. 
    - eg : ccgg has tag on attmid
  - `backup polices` : org wide backup plan.

## benefit / purpose
- `hierarchical structure of OUs`
  - API to create member-acct and organize them.
  - ou by business unit, ou by project, ou by env
  
- `Cost`
  - Aggr all usage and give more `saving`
  - `consolidated` billing.
  
- `security`
  - Configure `AWS SSO` for centralized access management
  - create cross account role.
  - centrally manage and govern multiple AWS accounts.
  - policies
      
- `centralized log` : Send CloudWatch Logs to central account

---
## Screenshot
- ![img_1.png](../99_img/security/org/img_1.png)
- ![img.png](../99_img/security/org/img.png)