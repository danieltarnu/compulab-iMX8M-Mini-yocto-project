FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

IMX_PATCH_append = " file://0005-add-ak4458-conf-for-multichannel-support.patch \
                     file://0006-pcm-Return-the-consistent-error-code-for-unexpected-.patch \
"
