resource "aws_ecs_task_definition" "service" {
  family = "students_ecs"
  container_definitions = jsonencode([
    {
      name      = "students"
      image     = aws_ecr_repository.students.name
      memory    = 512
      essential = true
      portMappings = [
        {
          containerPort = 8080
          hostPort      = 8080
        }
      ]
    }
  ])
}