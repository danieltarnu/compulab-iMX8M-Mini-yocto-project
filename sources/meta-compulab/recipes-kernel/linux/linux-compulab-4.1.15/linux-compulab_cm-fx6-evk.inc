do_configure_append () {
    oe_runmake cm_fx6_defconfig
}

SRC_URI_append_cm-fx6-evk = " \
	file://cm-fx6-evk/0001-ARM-dts-update-support-for-cm-fx6.patch \
	file://cm-fx6-evk/0002-ARM-i.MX6-cm-fx6-add-cm-fx6-platform-driver.patch \
	file://cm-fx6-evk/0003-ARM-i.MX6-cm-fx6-Add-defconfig.patch \
	file://cm-fx6-evk/0004-ARM-i.MX6-cm-fx6-add-gpc-node.patch \
	file://cm-fx6-evk/0005-ARM-i.MX6-audio-add-analog-audio-support.patch \
	file://cm-fx6-evk/0006-ARM-i.MX6-add-depends-property-to-gpmi-nand.patch \
	file://cm-fx6-evk/0007-ARM-i.MX6-update-defconfig-for-nand.patch \
	file://cm-fx6-evk/0008-ARM-i.MX6-add-mxc_dvi-driver.patch \
	file://cm-fx6-evk/0009-ARM-i.MX6-dts-refactoring-of-the-second-video-output.patch \
	file://cm-fx6-evk/0010-ARM-i.MX6-dts-ldo-hdmi-changes.patch \
	file://cm-fx6-evk/0011-ARM-i.MX6-dts-udshc3-set-polarity-value.patch \
	file://cm-fx6-evk/0012-ARM-i.MX6-dts-sata-refactoring.patch \
	file://cm-fx6-evk/0013-ARM-i.MX6-dts-ldb-refactoring.patch \
	file://cm-fx6-evk/0014-ARM-i.MX6-dts-enable-mipi_dsi.patch \
	file://cm-fx6-evk/0015-ARM-i.MX6-pcie-refactoring.patch \
	file://cm-fx6-evk/0016-ARM-i.MX6-dts-clean-up-unnecesary-code.patch \
	file://cm-fx6-evk/0017-ARM-i.MX6-update-defconfig.patch \
	file://cm-fx6-evk/0018-Bluetooth-btmrvl-disable-SD8787-AMP-device.patch \
	file://cm-fx6-evk/0019-ARM-i.MX6-HDMI-Fix-HDMI-PHY-init-hang.patch \
	file://cm-fx6-evk/0020-gpio-reset-Add-gpio-can-sleep-option.patch \
	file://cm-fx6-evk/0021-ARM-i.MX6-dts-set-gpio-can-sleep-for-mipi_dsi_reset.patch \
	file://cm-fx6-evk/0022-ARM-i.MX6-dts-configure-all-mxc-UARTs.patch \
	file://cm-fx6-evk/0023-ARM-i.MX6-dts-add-hdmi-to-fb0-mapping-for-eval-board.patch \
	file://cm-fx6-evk/0024-ARM-i.MX6-dts-refactoring-hdmi-mipi-configuration.patch \
	file://0034-ARM-i.MX6-dts-add-ov564x-mipi-camera-support.patch \
	file://0057-ARM-i.MX6-dts-fix-ldb-timings.patch \
	file://cm-fx6-evk/0061-ARM-i.MX6-cm-fx6-dts-Add-parallel-camera-support.patch \
	file://cm-fx6-evk/0062-ARM-i.MX6-cm-fx6-defconfig-enable-tvp5150.patch \
	file://cm-fx6-evk/0065-ARM-i.MX6-cm-fx6-defconfig-enable-POSIX_MQUEUE.patch \
	file://0067-ARM-i.MX6-dts-disable-tvp5150-by-default.patch \
	file://cm-fx6-evk/0068-ARM-i.MX6-cm-fx6-dts-tvp5150-to-enable-uart5-to-disa.patch \
	file://0070-ARM-i.MX6-dts-enable-snvs_poweroff.patch \
	file://cm-fx6-evk/0072-ARM-i.MX6-cm-fx6-Update-defconfig-for-snvs_poweroff.patch \
	file://cm-fx6-evk/0075-ARM-i.MX6-sb-fx6-dts-Change-lvds-timing-field-name.patch \
	file://cm-fx6-evk/0076-ARM-i.MX6-dts-Add-extended-display-settings.patch \
	file://cm-fx6-evk/0077-ARM-i.MX6-dts-Add-available-mode-string.patch \
	file://cm-fx6-evk/0078-ARM-i.MX6-dts-Configure-the-parallel-interface-a-the.patch \
	file://cm-fx6-evk/0092-ARM-i.MX6-cm-fx6-config-Enable-DMA_CMA.patch \
	file://cm-fx6-evk/0093-ARM-i.MX6-cm-fx6-Remove-invalid-operating-points.patch \
	file://cm-fx6-evk/0095-ARM-i.MX6-solo-Add-single-freq-option.patch \
"

SRC_URI_append_cm-fx6-evk += "${@bb.utils.contains('COMPULAB_FEATURES', 'user_space_governon', 'file://cm-fx6-evk/0094-cm-fx6-defconfig-CONFIG_CPU_FREQ_DEFAULT_GOV_USERSPA.patch', '', d)}"
