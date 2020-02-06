#!/bin/bash

### BEGIN INIT INFO
# Provides:		bt-start
# Required-Start:	$syslog
# Required-Stop:	$syslog
# Default-Start:	2 3 4 5
# Default-Stop:
# Short-Description:	CompuLab AP Start Up script
### END INIT INFO

case "$1" in
start)
	/usr/local/bin/cl-ap.work start
	;;
stop)
	/usr/local/bin/cl-ap.work stop
	;;
status)
	;;

*)
	echo "Usage: $0 {start|stop|status}"
	exit 1
esac

exit 0
