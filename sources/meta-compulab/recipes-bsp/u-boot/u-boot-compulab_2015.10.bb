require recipes-bsp/u-boot/u-boot.inc

DESCRIPTION = "u-boot which includes support for CompuLab boards."
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=0507cd7da8e7ad6d6701926ec9b84c95"

SECTION = "bootloader"
PROVIDES = "u-boot"

SRCBRANCH = "master"
SRCREV = "v2015.10"
SRC_URI = "git://git.denx.de/u-boot.git;branch=${SRCBRANCH}"
SRC_URI[md5sum] = "fd8234c5b3a460430689848c1f16acef"

SRC_URI += "file://0003-arm-imx6ul-add-u-boot-with-spl-cl.imx-target-for-cl-.patch \
	file://Fix-the-compile-issue-under-gcc6.patch \
	file://Fix-the-compile-issue-under-gcc7.patch \
"

SRC_URI_append_cm-fx6-evk += " \
	file://0200-cm-fx6-Update-mmdc-settings-with-the-lates-mst3.0-va.patch \
	file://0201-cm-fx6-config-Enable-memimfo-memtest.patch \
"

SRC_URI_append_cl-som-imx6ul += "file://cl_som_imx6ul_defconfig \
	file://cl_som_imx6ul_nospl_defconfig \
	file://0001-arm-imx6ul-add-support-for-Compulab-cl-som-imx6ul.patch \
	file://0002-arm-imx6ul-add-extraversion-for-cl-som-imx6ul.patch \
	file://0004-arm-imx6ul-enable-USB-Networking.patch \
	file://0005-arm-imx6ul-enable-nand-for-cl-som-imx6ul.patch \
	file://0006-arm-imx6ul-add-memory-size-detection-for-cl-som-imx6.patch \
	file://0007-arm-imx6ul-tag-U-Boot-version-cl-som-imx6ul-1.0.patch \
	file://0008-arm-imx6ul-fec-Fix-fec-phy-settings.patch \
	file://0009-arm-imx6ul-tag-U-Boot-version-cl-som-imx6ul-1.1.patch \
	file://0010-arm-imx6ul-Enable-CONFIG_OF_BOARD_SETUP.patch \
	file://0011-arm-imx6ul-Enable-debug-uart-in-SPL.patch \
	file://0012-arm-imx6ul-Make-SPL-read-a-raw-U-Boot.patch \
"

S = "${WORKDIR}/git"

do_compile_append () {
	oe_runmake u-boot-with-spl-cl.imx
}

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "(cl-som-imx6ul|cm-fx6-evk)"
