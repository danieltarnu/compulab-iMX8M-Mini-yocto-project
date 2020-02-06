DESCRIPTION = "CompuLab XFCE desktop demo image."
LICENSE = "MIT"

require recipes-fsl/images/fsl-image-qt5-validation-imx.bb

inherit distro_features_check

IMAGE_FEATURES_remove = "x11-sato"

REQUIRED_DISTRO_FEATURES = "x11"

CORE_IMAGE_BASE_INSTALL += "\
    packagegroup-core-x11 \
    packagegroup-xfce-base \
    packagegroup-xfce-multimedia \
    packagegroup-xfce-extended \
"

PACKAGE_EXCLUDE = "xfce-polkit"
