include native-env.mk

export TARGET_DIR := $(PREFIX)/target
export WEB_MEDIA_DIR := $(PREFIX)/media

# minimal linux development environment virtual machine image 
NECESSARY_PACKAGES += jquery backbonejs underscorejs json-js jquery-fastbutton jquery-mobile-events

# DEVEL_PACKAGES: packages only useful during development and can be removed in release builds
# DEVEL_PACKAGES += dump-core gdbserver duma mtrace popt binutils oprofile
DEVEL_PACKAGES +=

PACKAGES := $(NECESSARY_PACKAGES) $(DEVEL_PACKAGES)
