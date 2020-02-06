# Video Input Test recipe to add executable to run gstreamer
# command to display video input on the default videosink.

DESCRIPTION = "Video Input Test"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=3af51c5f8b7ed40f288f000169a1595d"

PR = "r1"

SRC_URI = " \
	file://cl-camera \
	file://cl-camera.desktop \
	file://cl-camera.png \
	file://COPYING \
"

SRC_URI_append_cl-som-imx7 = " \
	file://cl-camera_cl-som-imx7 \
"

SRC_URI_append_cl-som-imx8 = " \
	file://cl-camera_cl-som-imx8 \
"

SRC_URI_append_cl-som-imx8x = " \
	file://cl-camera_cl-som-imx8x \
"

SRC_URI_append_ucm-imx8m-mini = " \
	file://cl-camera_ucm-imx8m-mini \
"

S = "${WORKDIR}"

do_install() {
	install -d ${D}/usr/local/bin/
	install -d ${D}/usr/share/applications/
	if [ -f ${S}/cl-camera_${MACHINE} ];then
	cp ${S}/cl-camera_${MACHINE} ${S}/cl-camera
	fi
	install -m 0755 ${S}/cl-camera ${D}/usr/local/bin/
	install -m 0644 ${S}/cl-camera.png ${D}/usr/share/applications/
	install -m 0644 ${S}/cl-camera.desktop ${D}/usr/share/applications/
}

FILES_${PN} = " \
	/usr/local/bin/* \
	/usr/share/applications/* \
"

ALLOW_EMPTY_${PN} = "1"
RDEPENDS_${PN} = "bash gstreamer1.0 gstreamer1.0-plugins-good"
