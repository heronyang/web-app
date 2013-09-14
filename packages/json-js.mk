json-js-fetch:
	@fetch $(FETCH_OPTION) "[dict( \
		git='https://github.com/douglascrockford/JSON-js.git', \
		rev='e39db4b', \
		)]"

json-js-build:
	# backbonejs only needs json2.js
	mkdir -p $(WEB_MEDIA_DIR) && cp JSON-js/json2.js $(WEB_MEDIA_DIR)

json-js-clean:

json-js-install:
