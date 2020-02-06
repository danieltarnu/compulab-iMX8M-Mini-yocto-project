require recipes-bsp/u-boot/u-boot.inc

DESCRIPTION = "u-boot which includes support for CompuLab boards."
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=0507cd7da8e7ad6d6701926ec9b84c95"

SECTION = "bootloader"
PROVIDES = "u-boot"

SRCBRANCH = "master"
SRCREV = "v2015.07-cm-fx6-3"
SRC_URI = "https://github.com/utilite-computer/u-boot/archive/v2015.07-cm-fx6-3.zip"
SRC_URI[md5sum] = "df76d5ae2436ea9c126ed769e060ebee"

SRC_URI_append_cl-som-imx6 += " \
	file://cl_som_imx6_defconfig \
	file://Fix-the-compile-issue-under-gcc6.patch \
	file://Fix-the-compile-issue-under-gcc7.patch \
	file://0001-arm-imx6-add-SPL-to-u-boot.imx.patch \
	file://0002-arm-mx6-cl_som_mx6-Add-basic-support.patch \
	file://0003-arm-mx6-cl_som_mx6-Fix-fec-phy-connecttivity-issue.patch \
	file://0004-arm-mx6-cl_som_mx6-add-emmc-support.patch \
	file://0005-arm-mx6-cl_som_mx6-add-emmc-support-1.patch \
	file://0006-arm-mx6-cl_som_mx6-fix-board-name.patch \
	file://0007-arm-mx6-cl_som_mx6-add-imx6qp-support.patch \
	file://0008-arm-mx6-cl_som_mx6-mmc-rescan-fix-and-etc.patch \
	file://0009-arm-mx6-cl-som-imx6-add-emmc-to-boot-device-list.patch \
	file://0010-arm-mx6-cl-som-imx6-configure-i2cmux-switch-gpio.patch \
	file://0011-arm-mx6-cl-som-imx6-Define-heart-beat-led.patch \
	file://0012-arm-mx6-cl-som-imx6-Select-ENET-MAC0-TX-clock-from-P.patch \
	file://0013-arm-mx6-define-get_cpu_rev_ext.patch \
	file://0014-arm-mx6-cl-som-imx6-Add-cpu_type-environment-variabl.patch \
	file://0015-arm-mx6-cl-som-imx6-Enable-PCA953X-config.patch \
	file://0016-arm-mx6-cl-som-imx6-Add-ATP-support.patch \
	file://0017-arm-mx6-cl_som_mx6-Tag-u-boot-version-cl-som-imx6-1..patch \
	file://0018-arm-mx6qp-cl_som_mx6-update-mmdc-settings.patch \
	file://0020-arm-mx6-cl-som-imx6-change-boot-device-order.patch \
"

S = "${WORKDIR}/u-boot-2015.07-cm-fx6-3"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "(cl-som-imx6)"
