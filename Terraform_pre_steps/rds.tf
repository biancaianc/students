resource "aws_db_instance" "default" {
  allocated_storage    = 20
  identifier = "students"
  engine               = "mysql"
  engine_version       = "8.0.23"
  instance_class       = "db.t2.micro"
  name                 = "students_schema"
  username             = "admin"
  password             = "admin123"
  skip_final_snapshot  = true
  vpc_security_group_ids = ["sg-0e3449ac9bda66de9"]
  publicly_accessible = true
}