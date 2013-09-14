BACKBONEJS_VERSION := 1.0.0

backbonejs-fetch:
	@fetch $(FETCH_OPTION) "[dict( \
		git='https://github.com/jashkenas/backbone.git', \
		rev='1.0.0', \
		)]"

backbonejs-build:
	mkdir -p $(WEB_MEDIA_DIR) && \
		cp backbone/backbone-min.js $(WEB_MEDIA_DIR)/ && \
		cp backbone/backbone-min.map $(WEB_MEDIA_DIR)/

backbonejs-clean:

backbonejs-install:
