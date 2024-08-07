# A. AWS Organisation (global srv)
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
    
- `organizational policies`
  - `SCP` : Service Control Policies ( access policies )
    - permission boundary can be imposed to child accounts by SCPs.
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
  - Configure `AWS SSO`/ `Identity Center` for centralized access management
    - create `cross account role` in permission-Set of IP
  - centrally manage and govern multiple AWS accounts.
  - policies
      
- `centralized log` : Send CloudWatch Logs to central account
---
## Screenshot
- ![img_1.png](../99_img/security/org/img_1.png)
- ![img.png](../99_img/security/org/img.png)

---
# B. AWS org > Control tower
- Another service on top of `AWS org` to set-up new, secure, and compliant `multi-account AWS environmnet`, reducing the time and complexity involved.
- Automate ongoing `policy management` using `guardrails`
  - Detect `policy violations` : built-in governance and compliance 
  - remediate them.
  - type:
    - `Preventive` guardrails : restrict, using `SCP`
    - `detective` guardrails : notify non-compliance using `AWS config ?`
- `interactive dashboard` : see compliance in all acct in org.
- ![img_10.png](../99_img/security/org-2/img_10.png)
