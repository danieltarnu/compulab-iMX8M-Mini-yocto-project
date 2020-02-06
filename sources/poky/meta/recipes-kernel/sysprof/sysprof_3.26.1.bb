SUMMARY = "System-wide Performance Profiler for Linux"
HOMEPAGE = "http://www.sysprof.com"
LICENSE = "GPLv3+"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504 \
                    file://src/sp-application.c;endline=17;md5=40e55577ef122c88fe20052acda64875"

GNOMEBASEBUILDCLASS = "meson"
inherit gnomebase gettext systemd upstream-version-is-even

DEPENDS = "glib-2.0 libxml2-native glib-2.0-native"

SRC_URI[archive.md5sum] = "6f9f947960ba79bb1269d8ee49b7db78"
SRC_URI[archive.sha256sum] = "d8b9d5c2246696e4a3776a312731dc7c014fbd33478bb14d5512c6f1f35a3b11"
SRC_URI += " \
           file://define-NT_GNU_BUILD_ID.patch \
           file://0001-Do-not-build-anything-in-help-as-it-requires-itstool.patch \
           "

PACKAGECONFIG ?= "${@bb.utils.contains_any('DISTRO_FEATURES', '${GTK3DISTROFEATURES}', 'gtk', '', d)}"
PACKAGECONFIG[gtk] = "-Denable_gtk=true,-Denable_gtk=false,gtk+3"
PACKAGECONFIG[sysprofd] = "-Dwith_sysprofd=bundled,-Dwith_sysprofd=none,polkit"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""

SYSTEMD_SERVICE_${PN} = "${@bb.utils.contains('PACKAGECONFIG', 'sysprofd', 'sysprof2.service', '', d)}"

# We do not yet work for aarch64.
COMPATIBLE_HOST = "^(?!aarch64).*"

FILES_${PN} += " \
               ${datadir}/dbus-1/system-services \
               ${datadir}/dbus-1/system.d \
               "
