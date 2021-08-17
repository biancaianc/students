resource "aws_instance" "web" {
  ami           = "ami-0102ef3da1a6c47ca"
  instance_type = "t2.micro"
  iam_instance_profile = aws_iam_instance_profile.test_profile.name
  vpc_security_group_ids = ["sg-0e3449ac9bda66de9"]
}

resource "aws_iam_instance_profile" "test_profile" {
  name = "test_profile"
  role = aws_iam_role.student_role.name
}