DESCRIPTION = "A library to retrieve i.MX GPU performance data"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://COPYING;md5=80c0478f4339af024519b3723023fe28"

SRC_URI[arm-fb.md5sum] = "f554464cdf034bacfa2b29b07627bc34"
SRC_URI[arm-fb.sha256sum] = "58c5e8bda097a98471421038332472a9cc4228d3cdb967a411d2ac9600d2457e"

SRC_URI[arm-wayland.md5sum] = "b38052c3b5dd43f85855c987a814005e"
SRC_URI[arm-wayland.sha256sum] = "3fd74adae155efd5c2201b6ee4dddf01313aaec413830c291f54f9965910764f"

SRC_URI[arm-x11.md5sum] = "db519d30d708477487f308cd6f73ec17"
SRC_URI[arm-x11.sha256sum] = "8c383be816d293bc29ff4c8120f23e302a3a464d046dcade482637218b343fa1"

SRC_URI[aarch64-fb.md5sum] = "55f55c2d754f7f244e765e0d07101749"
SRC_URI[aarch64-fb.sha256sum] = "318e96536651e356af8f74a554cb0b54932c6d3111803483ff096f5fd5fc209d"

SRC_URI[aarch64-wayland.md5sum] = "f74317321de1afb7027ebd6a85a5271e"
SRC_URI[aarch64-wayland.sha256sum] = "0e9d62ab55b7346717d06e367d63bf109d72410ddaf6254bdac772df0a1107d6"

SRC_URI[aarch64-x11.md5sum] = "ccd23c0d474f39d39300149564a4b316"
SRC_URI[aarch64-x11.sha256sum] = "f2b30e562b6e6067f20eadf4a5cc3434cf09640d9368a5e996eb5e9d937d8d2d"

inherit fsl-eula-unpack2 fsl-eula-graphics

PACKAGE_ARCH = "${MACHINE_SOCARCH}"

RDEPENDS_${PN} = "imx-gpu-viv"

# Compatible only with i.MX with GPU
COMPATIBLE_MACHINE        = "(^$)"
COMPATIBLE_MACHINE_imxgpu = "${MACHINE}"
