FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append = " \
    file://weston.ini-launcher \
"

SRC_URI_append_ucm-imx8m-mini = " \
    file://weston.ini-ucm-imx8m-mini \
"

SRC_URI_append_cl-som-imx8x = " \
    file://weston.ini-cl-som-imx8x \
"

do_install_append() {
    WESTON_INI_DEST_DIR=${D}${sysconfdir}/xdg/weston
    cat ${WORKDIR}/weston.ini-launcher >> ${WESTON_INI_DEST_DIR}/weston.ini

    mode=$(awk '(/^\[shell\]/)&&($0="insert")' ${WESTON_INI_DEST_DIR}/weston.ini)
    if [ -z ${mode} ];then
        sed -i '$ a \\n[shell]' ${WESTON_INI_DEST_DIR}/weston.ini
    fi
    sed -i '/^\[shell\]/ a panel-position=bottom' ${WESTON_INI_DEST_DIR}/weston.ini

}

do_install_append_ucm-imx8m-mini() {
    WESTON_INI_DEST_DIR=${D}${sysconfdir}/xdg/weston
    cat ${WORKDIR}/weston.ini-ucm-imx8m-mini >> ${WESTON_INI_DEST_DIR}/weston.ini
}

do_install_append_cl-som-imx8x() {
    WESTON_INI_DEST_DIR=${D}${sysconfdir}/xdg/weston
    cat ${WORKDIR}/weston.ini-cl-som-imx8x >> ${WESTON_INI_DEST_DIR}/weston.ini
}

RDEPENDS_${PN} += "cl-launcher"
