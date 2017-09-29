
app.factory('PagerService', function() {
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
			// more than 10 total pages so calculate start and end pages
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
		var endIndex = Math.min(startIndex + pageSize - 1, totalItems - 1);

		// create an array of pages to ng-repeat in the pager control
		var pages = _.range(startPage, endPage + 1);

		// return object with all pager properties required by the view
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
}).directive("selectPicker",
        [
            "$timeout",
            function($timeout) {
                return {
                    restrict: "A",
                    require: ["?ngModel", "?collectionName"],
                    compile: function(tElement, tAttrs, transclude) {
                        console.log("init bootstrap-select");
                        $(tElement).selectpicker();

                        if (angular.isUndefined(tAttrs.ngModel)) {
                            throw new Error("Please add ng-model attribute!");
                        } else if (angular.isUndefined(tAttrs.collectionName)) {
                            throw new Error("Please add data-collection-name attribute!");
                        }

                        return function(scope, element, attrs, ngModel) {
                            if (angular.isUndefined(ngModel)){
                                return;
                            }

                            scope.$watch(attrs.ngModel, function(newVal, oldVal) {
                            	console.log("value");
                                if (newVal !== oldVal) {
                                    $timeout(function() {
                                        console.log("value selected");
                                        $(element).selectpicker("val", element.val());
                                    });
                                }
                            });

                            scope.$watch(attrs.collectionName, function(newVal, oldVal) {
                                $timeout(function() {
                                    console.log("select collection updated");
                                    $(element).selectpicker("refresh");
                                });
                            });

                            ngModel.$render = function() {
                               $(element).selectpicker("val", ngModel.$viewValue || "");
                            };

                            ngModel.$viewValue = $(element).val();
                        };
                    }
                }
            }
        ]
    )