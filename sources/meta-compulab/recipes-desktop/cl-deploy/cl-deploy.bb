# Simple recipe to add desktop icon and executable to run
# CompuLab Deployment Tool

DESCRIPTION = "CompuLab Deployment Tool"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=4a0e2a2916052068a420bbc50873f515"

PR = "r2"

SRC_URI = " \
	file://cl-deploy \
	file://cl-deploy.work \
	file://cl-deploy.mtd \
	file://cl-deploy.desktop \
	file://cl-deploy.png \
	file://COPYING \
"

SRC_URI_append_cl-som-imx6ul = " \
	file://cl-deploy.cl-som-imx6ul \
"

S = "${WORKDIR}"

do_install() {
	mkdir -p ${D}/usr/local/bin/
	mkdir -p ${D}/usr/share/applications/
	cp ${S}/cl-deploy ${D}/usr/local/bin/
	cp ${S}/cl-deploy.work ${D}/usr/local/bin/
	[[ -f ${S}/cl-deploy.mtd ]] && cp ${S}/cl-deploy.mtd ${D}/usr/local/bin/
	cp ${S}/cl-deploy.png ${D}/usr/share/applications/
	cp ${S}/cl-deploy.desktop ${D}/usr/share/applications/
}

do_install_append_cl-som-imx6ul() {
	cp ${S}/cl-deploy.cl-som-imx6ul ${D}/usr/local/bin/cl-deploy.platform
}

FILES_${PN} = " \
	/usr/local/bin/* \
	/usr/share/applications/* \
"

RDEPENDS_${PN} = "bash pv dialog file gzip bzip2 dosfstools util-linux xz e2fsprogs parted"
RDEPENDS_${PN}_append = " ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'xterm', '', d)}"
PACKAGE_ARCH = "all"
