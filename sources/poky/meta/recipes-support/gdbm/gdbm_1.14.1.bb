SUMMARY = "Key/value database library with extensible hashing"
HOMEPAGE = "http://www.gnu.org/software/gdbm/"
SECTION = "libs"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=241da1b9fe42e642cbb2c24d5e0c4d24"


SRC_URI = "${GNU_MIRROR}/gdbm/gdbm-${PV}.tar.gz \
           file://run-ptest \
           file://ptest.patch \
          "

SRC_URI[md5sum] = "c2ddcb3897efa0f57484af2bd4f4f848"
SRC_URI[sha256sum] = "cdceff00ffe014495bed3aed71c7910aa88bf29379f795abc0f46d4ee5f8bc5f"

inherit autotools gettext texinfo lib_package ptest

# Needed for dbm python module
EXTRA_OECONF = "-enable-libgdbm-compat"

# Stop presence of dbm/nbdm on the host contaminating builds
CACHED_CONFIGUREVARS += "ac_cv_lib_ndbm_main=no ac_cv_lib_dbm_main=no"

BBCLASSEXTEND = "native nativesdk"

do_install_append () {
    # Create a symlink to ndbm.h and gdbm.h in include/gdbm to let other packages to find
    # these headers
    install -d ${D}${includedir}/gdbm
    ln -sf ../ndbm.h ${D}/${includedir}/gdbm/ndbm.h
    ln -sf ../gdbm.h ${D}/${includedir}/gdbm/gdbm.h
}

RDEPENDS_${PN}-ptest += "diffutils"

do_compile_ptest() {
    oe_runmake -C tests buildtests
}

PACKAGES =+ "${PN}-compat \
            "
FILES_${PN}-compat = "${libdir}/libgdbm_compat${SOLIBS} \
                     "
