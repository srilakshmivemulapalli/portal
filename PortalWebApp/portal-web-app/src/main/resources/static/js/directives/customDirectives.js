app.factory(
				'PagerService',
				function() {
					// service definition
					var service = {};

					service.GetPager = GetPager;

					return service;

					// service implementation
					function GetPager(totalItems, currentPage, pageSize) {
						// default to first page
						currentPage = currentPage || 1;

						// default page size is 10
						pageSize = pageSize || 10;

						// calculate total pages
						var totalPages = Math.ceil(totalItems / pageSize);

						var startPage, endPage;
						if (totalPages <= 10) {
							// less than 10 total pages so show all
							startPage = 1;
							endPage = totalPages;
						} else {
							// more than 10 total pages so calculate start and
							// end pages
							if (currentPage <= 6) {
								startPage = 1;
								endPage = 10;
							} else if (currentPage + 4 >= totalPages) {
								startPage = totalPages - 9;
								endPage = totalPages;
							} else {
								startPage = currentPage - 5;
								endPage = currentPage + 4;
							}
						}

						// calculate start and end item indexes
						var startIndex = (currentPage - 1) * pageSize;
						var endIndex = Math.min(startIndex + pageSize - 1,
								totalItems - 1);

						// create an array of pages to ng-repeat in the pager
						// control
						
						var pages = _.range(startPage, endPage + 1);

						// return object with all pager properties required by
						// the view
						return {
							totalItems : totalItems,
							currentPage : currentPage,
							pageSize : pageSize,
							totalPages : totalPages,
							startPage : startPage,
							endPage : endPage,
							startIndex : startIndex,
							endIndex : endIndex,
							pages : pages
						};
					}
				})
		.directive(
				"limitTo",
				function() {
					return {
						restrict : "A",
						link : function(scope, elem, attrs) {
							var limit = parseInt(attrs.limitTo);
							elem.bind("paste", function(e) {
								var data = e.originalEvent.clipboardData
										.getData('Text');
								var total = this.value.length + data.length;
								if (total >= limit) {
									this.value = this.value.concat(data.substr(
											0, limit - data.length));
								}
								if (total >= limit || data.length >= limit
										&& e.keyCode != 8 && e.keyCode != 27)
									e.preventDefault();
							});
							elem.bind("keypress", function(e) {
								if (this.value.length == limit
										&& e.keyCode != 8 && e.keyCode != 27)
									e.preventDefault();
							});
						}
					}
				})
		.directive('tooltip', function() {
			return {
				restrict : 'A',
				link : function(scope, element, attrs) {
					$(element).hover(function() {
						// on mouseenter
						$(element).tooltip('show');
					}, function() {
						// on mouseleave
						$(element).tooltip('hide');
					});
				}
			};
		}).directive('autogrow', function() {
			return {

				restrict : 'A',
				link : function postLink(scope, element, attrs) {
					// hidding the scroll of textarea
					element.css('overflow', 'hidden');
					element.css("resize", 'none');
					var update = function() {

						element.css("height", "auto");
						element.css('width',"100%");
						var height = element[0].scrollHeight;

						if (height > 0) {

							element.css("height", height + "px");
						}

					};

					scope.$watch(attrs.ngModel, function() {

						update();
					});

					attrs.$set("ngTrim", "false");
				}
			};
		}).filter('formatTimer', function() {
			return function(input) {
				function z(n) {
					return (n < 10 ? '0' : '') + n;
				}
				var seconds = input % 60;
				var minutes = Math.floor(input % 3600 / 60);
				var hours = Math.floor(input / 3600);

				return {
					'hours' : hours,
					'minutes' : minutes,
					'seconds' : seconds
				};
			};
		}).directive('fileUpload', function() {
			return {
				scope : true, //create a new scope
				link : function(scope, el, attrs) {
					el.bind('change', function(event) {
						var files = event.target.files;
						//iterate files since 'multiple' may be specified on the element
						for (var i = 0; i < files.length; i++) {
							//emit event upward
							scope.$emit("fileSelected", {
								file : files[i]
							});
						}
					});
				}
			};
		})