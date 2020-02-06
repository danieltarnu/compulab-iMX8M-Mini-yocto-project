FILESEXTRAPATHS_prepend_cl-som-imx6 := "${THISDIR}/compulab:"

SRC_URI_append_cl-som-imx6 += " file://bt-start \
				file://fb-unblank \
				file://buffdrop \
				file://wifi-start \
"

inherit update-alternatives
DEPENDS_append = " update-rc.d-native"

do_install_append_cl-som-imx6 () {
	install -m 0755    ${WORKDIR}/bt-start	${D}${sysconfdir}/init.d
	install -m 0755    ${WORKDIR}/fb-unblank	${D}${sysconfdir}/init.d
	install -m 0755    ${WORKDIR}/buffdrop	${D}${sysconfdir}/init.d
	install -m 0755    ${WORKDIR}/wifi-start	${D}${sysconfdir}/init.d
	update-rc.d -r ${D} bt-start defaults
	update-rc.d -r ${D} fb-unblank defaults
	update-rc.d -r ${D} buffdrop defaults
	update-rc.d -r ${D} wifi-start defaults
}
