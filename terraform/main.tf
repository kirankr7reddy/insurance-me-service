# aws_key_pair.default {
#   key_name   = "mikey"
#   public_key = file("C:/Users/kiran/Downloads/mikey-clean.pub") 
# }

resource "aws_security_group" "insureme_sg" {
  name = "insureme-sg"

  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port   = 8080
    to_port     = 8080
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port   = 9100
    to_port     = 9100
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_instance" "test" {
  ami                    = "ami-0360c520857e3138f" 
  instance_type          = "t3.small"
  key_name               = "mikey"  # uses existing key
  vpc_security_group_ids = [aws_security_group.insureme_sg.id]

  tags = {
    Name = "insureme-test"
  }
}

resource "aws_instance" "prod" {
  ami                    = "ami-0360c520857e3138f" 
  instance_type          = "t3.small"
  key_name               = "mikey"  # uses existing key
  vpc_security_group_ids = [aws_security_group.insureme_sg.id]

  tags = {
    Name = "insureme-prod"
  }
}

output "test_public_ip" {
  value = aws_instance.test.public_ip
}

output "prod_public_ip" {
  value = aws_instance.prod.public_ip
}
