
variable "ssh_user" {
  description = "The SSH username"
  type        = string
  default     = "ubuntu"
}

variable "key_name" {
  description = "The name of the key pair"
  type        = string
  default     = "scheduler"
}

variable "private_key_path" {
  description = "The path of the key pair"
  type        = string
  default = "scheduler.pem"
}

variable "AWS_ACCESS_KEY_ID" {
  description = "aws access"
  type        = string
}

variable "AWS_SECRET_ACCESS_KEY" {
  description = "aws access"
  type        = string
}

provider "aws" {
  region     = "us-east-1"
  access_key = var.AWS_ACCESS_KEY_ID
  secret_key = var.AWS_SECRET_ACCESS_KEY
}

terraform {
  backend "s3" {
    bucket         = "jimmy-scheduler-tfstate"
    dynamodb_table = "state-lock"
    key            = "global/mystatefile/terraform.tfstate"
    region         = "us-east-1"
    encrypt        = true
  }

  required_providers {
    aws = {
      source = "hashicorp/aws"
    }
  }
}
resource "aws_vpc" "prod" {
  cidr_block = "173.32.0.0/16"

  tags = {
    Name = "production"
  }
}

resource "aws_internet_gateway" "gw" {
  vpc_id = aws_vpc.prod.id
}

resource "aws_route_table" "prod_route_table" {
  vpc_id = aws_vpc.prod.id

  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.gw.id
  }

  route {
    ipv6_cidr_block = "::/0"
    gateway_id      = aws_internet_gateway.gw.id
  }
}

resource "aws_subnet" "subnet-1" {
  vpc_id     = aws_vpc.prod.id
  cidr_block = "173.32.80.0/20"
}

resource "aws_route_table_association" "a" {
  route_table_id = aws_route_table.prod_route_table.id
  subnet_id      = aws_subnet.subnet-1.id
}

resource "aws_security_group" "allow_web" {
  name        = "allow_web_traffic"
  description = "Allow Web inbound traffic"
  vpc_id      = aws_vpc.prod.id

  ingress {
    description = "HTTPS"
    from_port   = 443
    to_port     = 443
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    description = "HTTP"
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    description = "SSH"
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    description = "POSTGRES"
    from_port   = 5432
    to_port     = 5432
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = "allow_web"
  }
}



output "public_ip" {
  value = aws_instance.ubuntu.public_ip
}