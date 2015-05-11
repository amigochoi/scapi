var myApp = angular.module('ApiListAPP', []);

myApp
		.controller(
				'ApiListCtrl',
				function($scope) {
					$scope.param = {
						userId : "123",
						userName : "name",
						userEmail : "em@il.com",
						userPhone : "1234567"
					};

					$scope.apiList = [
							{
								'name' : 'get User',
								'url' : '../api/user/getUser?userId={userId}&userName={userName}&userEmail={userEmail}&userPhone={userPhone}'
							},
						];

					$scope.showApiList = {};

					$scope.updateAPI = function() {
						$scope.showApiList = angular.copy($scope.apiList);

						for ( var i in $scope.showApiList) {
							var tempUrl = $scope.apiList[i].url;

							// replace the $scope.param json field by field name
							for ( var z in Object.keys($scope.param)) {
								var jsonName = Object.keys($scope.param)[z];
								tempUrl = tempUrl.replace("{" + jsonName + "}",
										$scope.param[jsonName]);
							}

							$scope.showApiList[i].url = tempUrl;
						}

					}

				});