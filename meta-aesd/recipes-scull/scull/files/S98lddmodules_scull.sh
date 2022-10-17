#! /bin/sh

case "$1" in
    start)
        /usr/bin/scull_load
        ;;
    stop)
        /usr/bin/scull_unload
        ;;
    *)
        echo "Usage: $0 {start|stop}"
    exit 1

esac
exit 0