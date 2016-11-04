SERVER
host name: micha-Inspiron-560.local
url: http://10.0.0.36:8080/

DATABASE:
cd $MONGODB/bin
sudo mongodb &
sudo mongo

GUI
sudo vi /etc/default/grub
GRUB_CMDLINE_LINUX_DEFAULT="quiet splash"
GRUB_CMDLINE_LINUX_DEFAULT="text"
sudo update-grub

CONFIGURATION
sudo vi /etc/profile

WILDFLY
<interface name="management">
	<inet-address value="${jboss.bind.address.management:127.0.0.1}"/>
</interface>
<interface name="public">
	<inet-address value="${jboss.bind.address:0.0.0.0}"/>
</interface>

<interface name="management">
	<any-address/>
</interface>
<interface name="public">
	<any-address/>
</interface>

<interface name="management">
	<any-address/>
</interface>
<interface name="public">
	<any-address/>
</interface>

COMMANDS
shutdown -r now
shutdown -h now 
$WILDFLY/bin/jboss-cli.sh -c --command=:shutdown
$WILDFLY/bin/jboss-cli.sh -c --command=:reload
$WILDFLY/bin/standalone.sh &
scp /Users/michaellasso/Documents/Workspace/helloworld/Readme.txt micha@micha-Inspiron-560.local:Readme1.txt
ubuntu@54.85.253.210
scp $SERVER:Readme.txt $HOME

AWS COMMANDS
-to access aws services with user permissions, input credentials
aws configure
-To login into EC2
ssh ec2-user@54.86.129.41 -i credentials.pem
-To gain root access
sudo su
-To update amazon linux operation system
yum update -y
-To install apache server
yum install httpd -y
-To check if server is running
service httpd status
service httpd start
-to start as a service
chkconfig httpd on
-To show what disks are mounted
lsblk
-to format volume to file system
mkfs -t ext4 /dev/xdfv
-to mount
mkdir /fileserver
mount /dev/xdfv /fileserver
cd fileserver
--create files
cd..
umount /dev/xdfv
-META-DATA
curl http://169.254.169.254/latest/meta-data/

switch back to job in linux


