var myApp = angular.module('ApiListAPP', []);

myApp
		.controller(
				'ApiListCtrl',
				function($scope) {
					$scope.param = {
						userId : "",
						userName : "",
						userEmail : "",
						userPhone : ""
					};

					$scope.apiList = [
							{
								'name' : 'get User',
								'url' : '../api/user/getUser',
								'params' : ['userId', 'userName', 'userEmail', 'userPhone']
							},
						];

					$scope.showApiList = {};
					
					$scope.showAllParams = function(){
						$(".keyList").css("display","inline");	
					}
					
					$scope.showCurrentParam = function(paramsList){
						console.debug("showCurrentParam");
						console.debug(paramsList);
						$(".keyList").css("display","none");
						$(".keyList").find("label").css("color","#333");
						for(var i in paramsList){
							$("#" + paramsList[i]).find("label").css("color","#428BCA");
							$("#" + paramsList[i]).css("display","inline");
						}
					}
					
					$scope.updateAPI = function() {
						$scope.showApiList = angular.copy($scope.apiList);

						for ( var i in $scope.showApiList) {
							var params = "";
							for(var y in $scope.showApiList[i]['params']){
								params +=  "&" + $scope.showApiList[i]['params'][y] + "={" +  $scope.showApiList[i]['params'][y] + "}"; 
							}
							if(params.length > 0 ){
								params = params.replace("&","?");
							}
							console.debug("params : " + params);
							var tempUrl = $scope.apiList[i].url + params;

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