jquery-mobile-events-fetch:
	@fetch $(FETCH_OPTION) "[dict( \
		git='https://github.com/benmajor/jQuery-Mobile-Events.git', \
		rev='9af9c9b', \
		)]"

jquery-mobile-events-build:
	mkdir -p $(WEB_MEDIA_DIR) && cp jQuery-Mobile-Events/src/jquery.mobile-events.min.js $(WEB_MEDIA_DIR)/

jquery-mobile-events-clean:

jquery-mobile-events-install:
