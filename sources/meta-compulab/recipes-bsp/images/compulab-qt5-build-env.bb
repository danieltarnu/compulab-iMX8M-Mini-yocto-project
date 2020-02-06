DESCRIPTION = "CompuLab Qt5 Build Environment Image"
LICENSE = "MIT"

inherit populate_sdk_qt5

QT5_BB = "${@bb.utils.contains('DISTRO_CODENAME', 'sumo', 'dynamic-layers/qt5-layer/', '', d)}recipes-fsl/images/fsl-image-qt5-validation-imx.bb"

require ${QT5_BB}
