require psmisc.inc
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3"

SRC_URI = "git://gitlab.com/psmisc/psmisc.git;protocol=https \
           file://0001-Use-UINTPTR_MAX-instead-of-__WORDSIZE.patch \
	   file://0001-Makefile.am-create-src-directory-before-attempting-t.patch \
           "
SRCREV = "67b1da268f4c0ce6859980e3dfcfaec5b2448e80"
S = "${WORKDIR}/git"
