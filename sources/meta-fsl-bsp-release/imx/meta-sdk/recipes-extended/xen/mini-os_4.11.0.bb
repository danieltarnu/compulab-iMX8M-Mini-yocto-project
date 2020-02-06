# Copyright (C) 2017 Kurt Bodiker <kurt.bodiker@braintrust-us.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Mini-OS is a tiny OS kernel distributed with the Xen Project"
HOMEPAGE = "https://wiki.xenproject.org/wiki/Mini-OS"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=8a437231894440a8f7629caa372243d0"

# git commit hash for: xen-RELEASE-4.11.0
SRCREV_minios = "0b4b7897e08b967a09bed2028a79fabff82342dd"
SRC_URI = "\
    git://xenbits.xen.org/mini-os.git;protocol=git;nobranch=1;destsuffix=mini-os;name=minios \
"
S="${WORKDIR}/mini-os"
B="${S}"

require mini-os.inc
