output "instance_id" {
  description = "ID of the EC2 instance"
  value       = aws_instance.app_server.id
  sensitive   = false
}

output "instance_public_ip" {
  description = "Public IP address of the EC2 instance"
  value       = aws_instance.app_server.public_ip
  sensitive   = false
}

output "module_1_test_output_1" {
  description = "module_1_test_output_1"
  value       = module.module_1.test_output_1
}

# availability_zones is list
# convert to map
output "az_as_list" {
  value = var.availability_zones
}
output "az_as_map" {
  value = { for i, zone in var.availability_zones: i => zone }
}