# Secret manager
- `Rotation`
  - enforce `rotation` of secrets every X days
  - `Automate generation of secrets` on rotation (uses `Lambda`)
  
- `replicate across region`
  - `primary`
  - `replica` in region
    - while DR, can promote as primary
    
- Integration with:
    - Amazon RDS (MySQL, PostgreSQL)
    - Aurora
    - KMS
    - ...