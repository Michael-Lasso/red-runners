#!/bin/sh

#######################################################
#######   Simple Script to launch Java Program   ######
#######################################################

# In order for this script to work, 
#1. The first parameter must be starting date

if [ $# -eq 1 ]; then

	echo "Found one argument"
		
fi

if [ -h $0 ]; then		
	LSOUTPUT=`ls -l $0`
	IDX=`expr index "$LSOUTPUT" "->"`
	IDX=$((IDX+2))
	FULLPATH=${LSOUTPUT:$IDX} 
	PROG=`basename $FULLPATH`
	PROG_DIR=`dirname $FULLPATH`		
else       
   PROG=`basename $0`
   PROG_DIR=`dirname $0`
   PROG_DIR=`cd $PROG_DIR/.;pwd`
fi

HOMEDIR=`cd ${PROG_DIR}/..;pwd`
LOGDIR=$HOMEDIR/logs

JAVAHOME=/path_to/jdk1.8
JAVABIN=$JAVAHOME/bin
BINDIR=$HOMEDIR/bin
JARSDIR=$HOMEDIR/lib

JARS=`find $JARSDIR -name "*.jar" -exec printf :{} ';'`

LOGDIR=$HOMEDIR/logs
CONFIGDIR=$HOMEDIR/config

CLASSPATH=$CONFIGDIR:$CLASSPATH:$JARS
export CLASSPATH
export CONFIGDIR

echo "CLASSPATH IS: $CLASSPATH"

cd $ENVDIR
JVM_OPTS="-Dssui.iniFileName=$CONFIGDIR/ssui.ini -Dapp=projectName -Dlog.dir=$LOGDIR  -Xms512m -Xmx1g"
APP_EXE=com.package.mainClass
JAVA_EXE=$JAVABIN/java

$JAVA_EXE $JVM_OPTS $APP_EXE
