(function() {



	/**
	 * Initialize
	 */
	function init() {
		// Register event listeners
		$('get-btn').addEventListener('click', getArticle);
		$('post-btn').addEventListener('click', postArticle);
	}



	// -----------------------------------
	// Helper Functions
	// -----------------------------------

	/**
	 * A helper function that creates a DOM element <tag options...>
	 * 
	 * @param tag
	 * @param options
	 * @returns
	 */
	function $(tag, options) {
		if (!options) {
			return document.getElementById(tag);
		}

		var element = document.createElement(tag);

		for ( var option in options) {
			if (options.hasOwnProperty(option)) {
				element[option] = options[option];
			}
		}

		return element;
	}


	/**
	 * AJAX helper
	 * 
	 * @param method -
	 *            GET|POST|PUT|DELETE
	 * @param url -
	 *            API end point
	 * @param callback -
	 *            This the successful callback
	 * @param errorHandler -
	 *            This is the failed callback
	 */
	function ajax(method, url, data, callback) {
		var xhr = new XMLHttpRequest();

		xhr.open(method, url, true);

		xhr.onload = function() {
			switch (xhr.status) {
			case 200:
				callback(xhr.responseText);
				break;
			case 403:
				onSessionInvalid();
				break;
			}
		};

		xhr.onerror = function() {
			console.error("The request couldn't be completed.");
		};

		if (data === null) {
			xhr.send();
		} else {
			xhr.setRequestHeader("Content-Type",
					"application/json;charset=utf-8");
			xhr.send(data);
		}
	}

	// -------------------------------------
	// AJAX call server-side APIs
	// -------------------------------------

	function getArticle() {

		// The request parameters
		var url = './ParseServlet';
		var params = 'read_URL=' + document.getElementById("url-input").value;
		var req = JSON.stringify({});

		// make AJAX call
		ajax('GET', url + '?' + params, req,
		// successful callback
		function(res) {
			var info = JSON.parse(res);
			if (info) {
				listInfo(info);
			}
		});
	}

	function postArticle() {

		// The request parameters
		var url = './ParseServlet';
		var params = 'read_URL=' + document.getElementById("url-input").value;
		var req = JSON.stringify({});

		// make AJAX call
		ajax('POST', url + '?' + params, req,
		// successful callback
		function(res) {
			var info = JSON.parse(res);
			if (info) {
				listInfo(info);
			}
		});
	}


	// -------------------------------------
	// Create restaurant list
	// -------------------------------------

	/**
	 * 
	 * @param info -
	 *            A JSON objects
	 */

	function listInfo(info) {	
		var res = $('result');
		res.innerHTML = '';

		var li = $('li', {
			id : 'result'
		});
		
		var title = $('div', {
			width : '100%'
		});
		title.innerHTML = "Title: " + info.Title;

		li.appendChild(title);

		var author = $('div', {
			width : '100%'
		});
		author.innerHTML = "Author: " + info.Author;

		li.appendChild(author);

		var date = $('div', {
			width : '100%'
		});
		date.innerHTML = "Date: " + info.Date;

		li.appendChild(date);

		var sum = $('div', {
			width : '100%'
		});
		sum.innerHTML = "Summary: " + info.Description_and_Summary;

		li.appendChild(sum);

		res.appendChild(li);
	}

	init();

})();

// END