FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI =+ "file://fw_env.config"

do_compile () {
	oe_runmake cm_fx6_defconfig
	oe_runmake env
}

do_install_append () {
	install -d ${D}${sysconfdir}/${MACHINE}
	install -m 0644 ${WORKDIR}/fw_env.config ${D}${sysconfdir}/${MACHINE}/fw_env.config
	ln -sf ${MACHINE}/fw_env.config ${D}${sysconfdir}/fw_env.config
}

COMPATIBLE_MACHINE = "(cl-som-imx6|cl-som-imx6ul|cl-som-imx7|cm-fx6-evk)"
