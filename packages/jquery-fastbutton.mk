jquery-fastbutton-fetch:
	@fetch $(FETCH_OPTION) "[dict( \
		git='https://github.com/x1024/jquery-fastbutton.git', \
		rev='6e01c2f', \
		)]"

jquery-fastbutton-build:
	mkdir -p $(WEB_MEDIA_DIR) && cp jquery-fastbutton/bin/fastbutton.js $(WEB_MEDIA_DIR)/jquery-fastbutton.js

jquery-fastbutton-clean:

jquery-fastbutton-install:
