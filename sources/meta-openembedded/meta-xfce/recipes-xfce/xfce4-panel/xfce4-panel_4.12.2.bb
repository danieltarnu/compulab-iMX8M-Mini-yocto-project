SUMMARY = "Xfce4 Panel"
SECTION = "x11"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=26a8bd75d8f8498bdbbe64a27791d4ee"
DEPENDS = "libxfce4util garcon libxfce4ui xfconf exo gtk+ gtk+3 dbus cairo virtual/libx11 libxml2 libwnck"

inherit xfce gtk-doc distro_features_check gtk-icon-cache

REQUIRED_DISTRO_FEATURES = "x11"

SRC_URI[md5sum] = "803c9aabc2925dc97f96de125c52ba2e"
SRC_URI[sha256sum] = "42058abb81b8f87691d3999627447de71c3285bcf055f308aab5cefab2de0ce9"
SRC_URI += " \
    file://0001-clock-time-make-change-of-system-s-timezone-change-t.patch \
    file://0002-windowmenu-do-not-display-desktop-icon-when-no-windo.patch \
"

EXTRA_OECONF += "--enable-gtk3"

python populate_packages_prepend() {
    plugin_dir = d.expand('${libdir}/xfce4/panel/plugins/')
    plugin_name = d.expand('${PN}-plugin-%s')
    do_split_packages(d, plugin_dir, '^lib(.*).so$', plugin_name,
                      '${PN} plugin for %s', extra_depends='', prepend=True,
                      aux_files_pattern=['${datadir}/xfce4/panel/plugins/%s.desktop',
                                         '${sysconfdir}/xdg/xfce/panel/%s-*',
                                         '${datadir}/icons/hicolor/48x48/apps/*-%s.png',
                                         '${bindir}/*%s*'])
}

PACKAGES_DYNAMIC += "^${PN}-plugin-.*"

PACKAGES =+ "${PN}-gtk3"

FILES_${PN} += "${libdir}/xfce4/panel/migrate \
                ${libdir}/xfce4/panel/wrapper-1.0"

FILES_${PN}-gtk3 = " \
    ${libdir}/libxfce4panel-2.0${SOLIBS} \
    ${libdir}/xfce4/panel/wrapper-2.0 \
"
FILES_${PN}-dbg += " \
    ${libdir}/xfce4/panel/plugins/.debug \
"
