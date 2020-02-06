# Simple recipe to add desktop icon and executable to run
# CompuLab U-Boot Tool

DESCRIPTION = "CompuLab U-Boot Tool for imx8 SoC"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "\
    file://cl-uboot.imx8.work \
"

S = "${WORKDIR}"

do_install_prepend() {
    mv ${S}/cl-uboot.imx8.work ${S}/cl-uboot.work
}


PACKAGE_ARCH = "${MACHINE_ARCH}"
RDEPENDS_${PN}_remove  = " mtd-utils u-boot-compulab "
RDEPENDS_${PN}_append  = " imx-boot mmc-utils "
COMPATIBLE_MACHINE = "(cl-som-imx8|ucm-imx8m-mini)"
