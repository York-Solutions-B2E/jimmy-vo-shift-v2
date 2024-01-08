resource "aws_instance" "ubuntu" {
  ami                         = "ami-0c7217cdde317cfec"
  subnet_id                   = aws_subnet.subnet-1.id
  instance_type               = "t2.micro"
  associate_public_ip_address = true
  security_groups             = [aws_security_group.allow_web.id]
  key_name                    = var.key_name



  provisioner "remote-exec" {
    inline = ["echo 'Wait until SSH is ready'"]

    connection {
      type        = "ssh"
      user        = var.ssh_user
      private_key = file(var.private_key_path)
      host        = self.public_ip
    }
  }

  provisioner "local-exec" {
    command = "ansible-playbook  -i ${aws_instance.ubuntu.public_ip}, --private-key ${var.private_key_path} dependency.yaml --vault-password-file=.vault_pass.txt"
  }

}