#! /bin/sh

case "$1" in
    start)
        /usr/bin/module_load faulty
        insmod /lib/modules/5.15.68-yocto-standard/hello.ko $* || exit 1
        ;;
    stop)
        /usr/bin/module_unload faulty
        rmmod /lib/modules/5.15.68-yocto-standard/hello.ko $* || exit 1
        ;;
    *)
        echo "Usage: $0 {start|stop}"
    exit 1

esac
exit 0