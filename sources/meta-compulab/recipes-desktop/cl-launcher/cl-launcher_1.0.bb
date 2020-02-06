SUMMARY = "CompuLab Weston Launcher init service"
DESCRIPTION = "CompuLab Weston Launcher init service"
SECTION = "base"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=5b7733d5d8ccd465235c379dbb5e3839"
PACKAGE_ARCH = "${MACHINE_ARCH}"
PR = "r1"

SRC_URI = "file://chromium.png \
	file://chromium \
	file://cl-launcher \
	file://cl-deploy32x32.png \
	file://cl-uboot32x32.png \
	file://cl-camera32x32.png \
	file://terminal.png \
	file://COPYING \
"

S = "${WORKDIR}"

ALLOW_EMPTY_${PN} = "1"
RDEPENDS_${PN} = "bash"
FILES_${PN} += "usr/share/compulab/*"

do_install () {

    install -d ${D}/usr/share/compulab/icons/
    install -m 0644 ${WORKDIR}/terminal.png ${D}/usr/share/compulab/icons/
    install -m 0644 ${WORKDIR}/cl-deploy32x32.png ${D}/usr/share/compulab/icons/
    install -m 0644 ${WORKDIR}/cl-uboot32x32.png ${D}/usr/share/compulab/icons/
    install -m 0644 ${WORKDIR}/cl-camera32x32.png ${D}/usr/share/compulab/icons/
    install -m 0644 ${WORKDIR}/chromium.png ${D}/usr/share/compulab/icons/

    install -d ${D}/usr/share/compulab/scripts/
    install -m 0755 ${WORKDIR}/chromium ${D}/usr/share/compulab/scripts/
    install -m 0755 ${WORKDIR}/cl-launcher ${D}/usr/share/compulab/scripts/cl-uboot
    install -m 0755 ${WORKDIR}/cl-launcher ${D}/usr/share/compulab/scripts/cl-deploy
    install -m 0755 ${WORKDIR}/cl-launcher ${D}/usr/share/compulab/scripts/cl-camera

}
