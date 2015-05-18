var myApp = angular.module('RedisCacheListAPP', []);

myApp.controller('RedisCacheListCtrl', function($scope, $http) {
	$scope.cacheName = "";
	$http.get("cacheApi/getAllRedisCacheDisplayList").success(function(response) {
		$scope.cacheList = response;
	});

	$scope.deleteCache = function(cacheName) {
		if (confirm("Sure to remove All cache in " + cacheName + " ?")) {
			$http.get("cacheApi/removeRedisCache?cacheName=" + cacheName).success(
					function(response) {
						console.debug("removeRedis response ", response);
						location.reload(); 
					});
		}
	}

		
});