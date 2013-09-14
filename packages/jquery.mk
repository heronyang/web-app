jquery-fetch:
	@fetch $(FETCH_OPTION) "[dict( \
		url='http://code.jquery.com/jquery-2.0.3.min.js', \
		sha1sum='fbf9c77d0c4e3c34a485980c1e5316b6212160c8', \
		), dict( \
		url='http://code.jquery.com/jquery-2.0.3.min.map', \
		sha1sum='83c9ec8ebb61ee9916f624a1e75ba47b0f1b0ec4', \
	)]"

jquery-build:
	mkdir -p $(WEB_MEDIA_DIR) && \
		cp $(TARBALL_DIR)/jquery-2.0.3.min.js $(WEB_MEDIA_DIR)/ && \
		cp $(TARBALL_DIR)/jquery-2.0.3.min.map $(WEB_MEDIA_DIR)/

jquery-clean:

jquery-install:
