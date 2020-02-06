SRC_URI_append_cl-som-imx7 += " \
	file://cl-som-imx7/cl_som_imx7_defconfig \
	file://cl-som-imx7/0001-arm-imx7d-add-support-for-Compulab-cl-som-imx7.patch \
	file://cl-som-imx7/0002-arm-imx7d-cl-som-imx7-add-support-for-NAND-flash.patch \
	file://cl-som-imx7/0003-arm-imx7d-cl-som-imx7-add-support-for-I2C4-bus.patch \
	file://cl-som-imx7/0004-arm-imx7d-cl-som-imx7-add-support-for-status-LED.patch \
	file://cl-som-imx7/0005-compulab-add-a-framework-for-board-specific-device-t.patch \
	file://cl-som-imx7/0006-arm-imx7d-cl-som-imx7-add-support-for-board-specific.patch \
	file://cl-som-imx7/0007-HACK-usb-ehci-mx6-do-not-initialize-otg2-port-for-iM.patch \
	file://cl-som-imx7/0008-arm-imx7d-cl-som-imx7-add-extraversion.patch \
"
