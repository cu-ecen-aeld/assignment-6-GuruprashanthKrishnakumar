# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f098732a73b5f6f3430472f5b094ffdb"

SRC_URI = "git://git@github.com/cu-ecen-aeld/assignment-7-GuruprashanthKrishnakumar.git;protocol=ssh;branch=master \
	file://0001-Modprobe-to-insmode.patch \
	file://S98lddmodules_scull.sh"

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "f94acb0c4dcedb62efd73535f192f69df57f2297"

S = "${WORKDIR}/git"
inherit module

inherit update-rc.d
INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME:${PN} = "S98lddmodules_scull.sh"

FILES:${PN} += "${bindir}/scull"
FILES:${PN} += "${bindir}/scull_load"
FILES:${PN} += "${bindir}/scull_unload"
FILES:${PN} += "${sysconfdir}/*"

do_configure () {
	:
}

do_compile () {
	oe_runmake
}

EXTRA_OEMAKE:append:task-install = " -C ${STAGING_KERNEL_DIR} M=${S}/scull"
EXTRA_OEMAKE += "KERNELDIR=${STAGING_KERNEL_DIR}"

do_install () {
	# TODO: Install your binaries/scripts here.
	# Be sure to install the target directory with install -d first
	# Yocto variables ${D} and ${S} are useful here, which you can read about at 
	# https://docs.yoctoproject.org/ref-manual/variables.html?highlight=workdir#term-D
	# and
	# https://docs.yoctoproject.org/ref-manual/variables.html?highlight=workdir#term-S
	# See example at https://github.com/cu-ecen-aeld/ecen5013-yocto/blob/ecen5013-hello-world/meta-ecen5013/recipes-ecen5013/ecen5013-hello-world/ecen5013-hello-world_git.bb
	install -d ${D}${bindir}
	install -d ${D}${sysconfdir}/init.d
	install -d ${D}${base_libdir}/modules/5.15.68-yocto-standard/
	install -m 0755 ${S}/scull/scull_load ${D}${bindir}/
    install -m 0755 ${S}/scull/scull_unload ${D}${bindir}
	install -m 0755 ${WORKDIR}/S98lddmodules_scull.sh ${D}${sysconfdir}/init.d	
	install -m 0755 ${S}/scull/scull.ko ${D}${base_libdir}/modules/5.15.68-yocto-standard/
}



