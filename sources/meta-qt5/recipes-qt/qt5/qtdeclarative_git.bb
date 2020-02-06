require qt5.inc
require qt5-git.inc
require qt5-ptest.inc

HOMEPAGE = "http://www.qt.io"
LICENSE = "GFDL-1.3 & BSD & ( GPL-3.0 & The-Qt-Company-GPL-Exception-1.0 | The-Qt-Company-Commercial ) & ( GPL-2.0+ | LGPL-3.0 | The-Qt-Company-Commercial )"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPL3;md5=e6a600fd5e1d9cbde2d983680233ad02 \
    file://LICENSE.GPL2;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSE.GPL3-EXCEPT;md5=763d8c535a234d9a3fb682c7ecb6c073 \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
"

SRC_URI += "file://0001-Use-OE_QMAKE_PATH_EXTERNAL_HOST_BINS-to-locate-qmlca.patch \
            file://0001-Use-python3-explicitly.patch \
           "

DEPENDS += "qtbase"

PACKAGECONFIG ??= "qml-debug qml-network ${@bb.utils.contains('DISTRO_FEATURES', 'qt5-static', 'static', '', d)}"
PACKAGECONFIG[qml-debug] = "-qml-debug,-no-qml-debug"
PACKAGECONFIG[qml-network] = "-qml-network, -no-qml-network"
PACKAGECONFIG[static] = ",,qtdeclarative-native"

do_install_append_class-nativesdk() {
    # qml files not needed in nativesdk
    rm -rf ${D}${OE_QMAKE_PATH_QML}
}

SRCREV = "51a158929b55dffbdfb757da8bd6a2cd181906c6"

BBCLASSEXTEND =+ "native nativesdk"
