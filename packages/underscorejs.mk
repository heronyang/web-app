UNDERSCOREJS_VERSION := 1.5.1

underscorejs-fetch:
	@fetch $(FETCH_OPTION) "[dict( \
		git='https://github.com/jashkenas/underscore.git', \
		rev='1.5.1', \
		)]"

underscorejs-build:
	mkdir -p $(WEB_MEDIA_DIR) && \
		cp underscore/underscore-min.js $(WEB_MEDIA_DIR)/ && \
		cp underscore/underscore-min.map $(WEB_MEDIA_DIR)/

underscorejs-clean:

underscorejs-install:
