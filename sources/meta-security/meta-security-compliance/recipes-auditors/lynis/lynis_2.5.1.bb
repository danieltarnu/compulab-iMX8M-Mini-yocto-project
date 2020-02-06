# Copyright (C) 2017 Armin Kuster  <akuster808@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Lynis is a free and open source security and auditing tool."
HOMEDIR = "https://cisofy.com/"
LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3edd6782854304fd11da4975ab9799c1"

SRCREV= "1be5154b35ce144db4f386856debe8a06b403899"
SRC_URI = "git://github.com/CISOfy/Lynis.git"
S = "${WORKDIR}/git"

inherit autotools-brokensep

do_compile[noexec] = "1"
do_configure[noexec] = "1"

do_install () {
	install -d ${D}/${bindir}
	install -d ${D}/${sysconfdir}/lynis
	install -m 555 ${S}/lynis ${D}/${bindir}

	install -d ${D}/${datadir}/lynis/db
	install -d ${D}/${datadir}/lynis/plugins
	install -d ${D}/${datadir}/lynis/include
	install -d ${D}/${datadir}/lynis/extras

	cp -r ${S}/db/* ${D}/${datadir}/lynis/db/.
	cp -r ${S}/plugins/*  ${D}/${datadir}/lynis/plugins/.
	cp -r ${S}/include/* ${D}/${datadir}/lynis/include/.
	cp -r ${S}/extras/*  ${D}/${datadir}/lynis/extras/.
        cp ${S}/*.prf ${D}/${sysconfdir}/lynis
}

FILES_${PN} += "${sysconfdir}/developer.prf ${sysconfdir}/default.prf"
FILES_${PN}-doc += "lynis.8 FAQ README CHANGELOG.md CONTRIBUTIONS.md CONTRIBUTORS.md" 

RDEPENDS_${PN} += "procps"
