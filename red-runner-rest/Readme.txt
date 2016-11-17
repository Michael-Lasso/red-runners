STATUS CODES
Status codes in the 100x range (from 100-199) are informational, and describe the processing for the request.
Status codes in the 200x range (from 200-299) indicate the action requested by the client was received, understood, accepted and processed successfully
Status codes in the 300x range (from 300-399) indicate that the client must take additional action to complete the request, such as following a redirect
Status codes in the 400x range (from 400-499) is intended for cases in which the client seems to have erred and must correct the request before continuing. The aforementioned 404 is an example of this.
Status codes in the 500x range (from 500-599) is intended for cases where the server failed to fulfill an apparently valid request.

PROFILING TOOLS
Activity Monitor

66089104

getinktattoo@hotmail.com

COMMANDS FOR PROFILING
top -> similar to activity monitor
ps -> to show process
lsof -p (number of process) -> list of files use by a process
sudo fs_usage (number of process) -> file system usage of process
heap (number of process) -> all the objects allocated for that process
leaks (number of process) -> attempt to identify memory leakage(unreachable memory)

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


