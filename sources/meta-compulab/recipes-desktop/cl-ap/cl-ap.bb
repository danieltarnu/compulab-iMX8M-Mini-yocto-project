# Simple recipe to add desktop icon and executable to run
# CompuLab Access Point

DESCRIPTION = "CompuLab Access Point"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=4a0e2a2916052068a420bbc50873f515"

PR = "r1"

SRC_URI = " \
	file://cl-ap \
	file://cl-ap.service \
	file://cl-ap.sh \
	file://cl-ap.work \
	file://cl-ap-inter.work \
	file://cl-ap.desktop \
	file://cl-ap.png \
	file://cl-ap.env \
	file://COPYING \
"

S = "${WORKDIR}"

do_install() {
    mkdir -p ${D}/usr/local/bin/
    mkdir -p ${D}/usr/share/applications/
    mkdir -p ${D}/usr/share/cl-ap/
    cp ${S}/cl-ap ${D}/usr/local/bin/
    cp ${S}/cl-ap.work ${D}/usr/local/bin/
    cp ${S}/cl-ap-inter.work ${D}/usr/local/bin/
    cp ${S}/cl-ap.png ${D}/usr/share/applications/
    cp ${S}/cl-ap.desktop ${D}/usr/share/applications/
    cp ${S}/cl-ap.env ${D}/usr/share/cl-ap/

    if ${@bb.utils.contains('DISTRO_FEATURES','sysvinit','true','false',d)}; then
            install -d ${D}/etc/init.d
            install -m 0755 ${S}/cl-ap.sh  ${D}/etc/init.d/cl-ap
    fi

    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -d ${D}/lib/systemd/system
        install -m 644 ${S}/cl-ap.service ${D}/lib/systemd/system/
    fi
}

pkg_postinst_${PN} () {

    if [ -n "$D" ]; then
        OPTS="--root=$D"
    fi

    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        systemctl $OPTS enable cl-ap.service
        systemctl $OPTS disable hostapd
        systemctl $OPTS disable dnsmasq
    fi

    if ${@bb.utils.contains('DISTRO_FEATURES','sysvinit','true','false',d)}; then
        update-rc.d $OPTS cl-ap defaults
    fi
}

pkg_prerm_${PN} () {

    /usr/local/bin/cl-ap.work stop
    /usr/local/bin/cl-ap.work clean

}

pkg_postrm_${PN} () {

    if [ -n "$D" ]; then
        OPTS="--root=$D"
    fi

    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        systemctl $OPTS disable cl-ap.service
    fi

    if ${@bb.utils.contains('DISTRO_FEATURES','sysvinit','true','false',d)}; then
        update-rc.d $OPTS cl-ap remove
    fi

}

FILES_${PN} = " \
    /etc/init.d/* \
    /lib/systemd/system/* \
    /usr/local/bin/* \
    /usr/share/applications/* \
    /usr/share/cl-ap/* \
"

RDEPENDS_${PN} = "dialog bash crda hostapd dnsmasq iptables"
RDEPENDS_${PN}_append = " ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'xterm', '', d)}"
PACKAGE_ARCH = "all"
