var myApp = angular.module('ApiListAPP', []);

myApp
		.controller(
				'ApiListCtrl',
				function($scope) {
					$scope.param = {
						member_type : "tvbcom",
						extid : "1234",
						request_party : "mytv",
						request_party_user : "mytvapi",
						tran_amount : "1",
						action : "add",
						action_info : "",
						remark : "",
						hashcode : "zxcvASDF",
						page : "1",
						hits : "50"
					};

					$scope.apiList = [
							{
								'name' : 'add coin',
								'url' : '../api/addCoin?member_type={member_type}&extid={extid}&request_party={request_party}&request_party_user={request_party_user}&tran_amount={tran_amount}&action={action}&action_info={action_info}&remark={remark}&hashCode={hashcode}'
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