# Copyright (C) 2012-2016 Freescale Semiconductor
# Copyright 2017 NXP
# Copyright 2018 (C) O.S. Systems Software LTDA.
# Released under the MIT license (see COPYING.MIT for the terms)
DESCRIPTION = "Freescale Multimedia codec libs"
LICENSE = "Proprietary"
SECTION = "multimedia"
LIC_FILES_CHKSUM = "file://COPYING;md5=75abe2fa1d16ca79f87cde926f05f72d"

# Backward compatibility
PROVIDES += "libfslcodec"

SRC_URI = "${FSL_MIRROR}/${PN}-${PV}.bin;fsl-eula=true"
SRC_URI[md5sum] = "ea080472c3fe5f5ae4760ba16c8ea6c5"
SRC_URI[sha256sum] = "6bb54f91c3ca18567e14d95d3858022dc9be00dc86e9edfdb544d1240a3b2b04"

inherit fsl-eula-unpack autotools pkgconfig

# Choose between Soft Float-Point and Hard Float-Point
EXTRA_OECONF = "${@bb.utils.contains('TUNE_FEATURES', 'callconvention-hard', '--enable-fhw', '', d)}"

PACKAGECONFIG ?= ""
PACKAGECONFIG_imxvpu = "vpu"

PACKAGECONFIG[vpu] = "--enable-vpu,--disable-vpu,imx-vpu"

do_install_append() {
    # FIXME: This link points to nowhere
    if [ -e ${D}${libdir}/imx-mm/audio-codec/lib_src_ppp_arm11_elinux.so ]; then
        rm ${D}${libdir}/imx-mm/audio-codec/lib_src_ppp_arm11_elinux.so
    fi

    # LTIB move the files around or gst-fsl-plugin won't find them
    for p in $(find ${D}${libdir}/imx-mm -mindepth 2 -maxdepth 2 -not -type d); do
            mv $p ${D}${libdir}
    done
    rmdir ${D}${libdir}/imx-mm/video-codec

}

python __set_insane_skip() {
    # Ensure we have PACKAGES expanded
    bb.build.exec_func("read_subpackage_metadata", d)

    for p in d.getVar('PACKAGES', True).split():
        # Even though we are packaging libraries those are plugins so we
        # shouldn't rename the packages to follow its sonames.
        d.setVar("DEBIAN_NOAUTONAME_%s" % p, "1")

        # FIXME: All binaries lack GNU_HASH in elf binary but as we don't have
        # the source we cannot fix it. Disable the insane check for now.
        if p == 'imx-codec-test-bin':
            # FIXME: includes the DUT .so files so we need to deploy those
            d.setVar("INSANE_SKIP_%s" % p, "ldflags textrel libdir file-rdeps")
        else:
            d.setVar("INSANE_SKIP_%s" % p, "ldflags textrel")
}

do_package_qa[prefuncs] += "__set_insane_skip"

python __split_libfslcodec_plugins() {
    codecdir = bb.data.expand('${libdir}', d)
    do_split_packages(d, codecdir, '^lib_([^_]*).*_arm.*_elinux\.so\..*',
                      aux_files_pattern='${libdir}/imx-mm/audio-codec/wrap/lib_%sd_wrap_arm*_elinux.so.*',
                      output_pattern='imx-codec-%s',
                      description='Freescale i.MX Codec (%s)',
                      extra_depends='')
    pkgs = d.getVar('PACKAGES', True).split()
    for pkg in pkgs:
        meta = pkg[10:]
        if meta != '':
            d.setVar('RREPLACES_%s' % pkg, ' libfslcodec-%s' % meta)
            d.setVar('RPROVIDES_%s' % pkg, ' libfslcodec-%s' % meta)
            d.setVar('RCONFLICTS_%s' % pkg, ' libfslcodec-%s' % meta)
        else :
            d.setVar('RREPLACES_%s' % pkg, ' libfslcodec')
            d.setVar('RPROVIDES_%s' % pkg, ' libfslcodec')
            d.setVar('RCONFLICTS_%s' % pkg, ' libfslcodec')
}

python __set_metapkg_rdepends() {
    # Allow addition of all codecs in a image; useful specially for
    # debugging.
    codec_pkgs = oe.utils.packages_filter_out_system(d)
    codec_pkgs = filter(lambda x: x not in ['imx-codec-test-bin', 'imx-codec-test-source'],
                        codec_pkgs)
    d.appendVar('RDEPENDS_imx-codec-meta', ' ' + ' '.join(codec_pkgs))
}

PACKAGESPLITFUNCS =+ "__split_libfslcodec_plugins __set_metapkg_rdepends"

PACKAGES_DYNAMIC = "${PN}-*"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

PACKAGES += "${PN}-meta ${PN}-test-bin ${PN}-test-source"

ALLOW_EMPTY_${PN} = "1"
ALLOW_EMPTY_${PN}-meta = "1"

# Ensure we get warnings if we miss something
FILES_${PN} = ""

FILES_${PN}-dev += "${libdir}/imx-mm/*/*${SOLIBSDEV} \
                    ${libdir}/imx-mm/*/*/*${SOLIBSDEV} \
                    ${libdir}/pkgconfig/*.pc ${includedir}/imx-mm/*"

FILES_${PN}-test-bin += "${datadir}/imx-mm/*/examples/*/bin"

FILES_${PN}-test-source += "${datadir}/imx-mm/*"

# FIXME: The wrap and lib names does not match
FILES_${PN}-oggvorbis += "${libdir}/imx-mm/audio-codec/wrap/lib_vorbisd_wrap_arm*_elinux.so.*"
FILES_${PN}-nb += "${libdir}/imx-mm/audio-codec/wrap/lib_nbamrd_wrap_arm*_elinux.so.*"
FILES_${PN}-wb += "${libdir}/imx-mm/audio-codec/wrap/lib_wbamrd_wrap_arm*_elinux.so.*"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "(mx6|mx7|mx8)"
