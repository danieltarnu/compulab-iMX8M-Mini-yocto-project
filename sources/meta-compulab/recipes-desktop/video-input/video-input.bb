# Video Input Test recipe to add desktop icon and executable to run gstreamer
# command to display video input on screen in media player.

DESCRIPTION = "Video Input Test"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=3af51c5f8b7ed40f288f000169a1595d"

PR = "r1"

SRC_URI = " \
	file://video-input \
	file://video-input.desktop \
	file://video-input.png \
	file://COPYING \
"

S = "${WORKDIR}"

do_install_cl-som-imx6() {
	mkdir -p ${D}/usr/local/bin/
	mkdir -p ${D}/usr/share/applications/
	cp ${S}/video-input ${D}/usr/local/bin/
	cp ${S}/video-input.png ${D}/usr/share/applications/
	cp ${S}/video-input.desktop ${D}/usr/share/applications/
}

FILES_${PN}_cl-som-imx6 = " \
	/usr/local/bin/* \
	/usr/share/applications/* \
"

ALLOW_EMPTY_${PN} = "1"
RDEPENDS_${PN} = "gstreamer1.0 gstreamer1.0-plugins-good"
