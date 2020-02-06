do_package_fix() {
    if [ -e "${WORKDIR}/image/etc/pulse/daemon.conf" ];then
        sed -i '$a\default-sample-rate = 48000\' ${WORKDIR}/image/etc/pulse/daemon.conf
    fi

if [ -e "${WORKDIR}/image/etc/pulse/system.pa" ];then
cat << eof >> ${WORKDIR}/image/etc/pulse/system.pa
### Automatically load driver modules for Bluetooth hardware
.ifexists module-bluetooth-policy.so
load-module module-bluetooth-policy
.endif

.ifexists module-bluetooth-discover.so
load-module module-bluetooth-discover
.endif
eof
fi
}
addtask package_fix before do_package after do_install

PACKAGE_ARCH_mx8 = "${MACHINE_SOCARCH}"
