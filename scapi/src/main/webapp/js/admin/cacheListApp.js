var myApp = angular.module('CacheListAPP', []);

myApp.controller('CacheListCtrl', function($scope, $http) {
	$scope.cacheName = "";
	$http.get("cacheApi/getAllCacheDisplayList").success(function(response) {
		$scope.cacheList = response;
	});

	$scope.viewCacheElement = function(cacheName) {
		$http.get("cacheApi/getElementList?cacheName=" + cacheName).success(
				function(response) {
					$scope.cacheName = cacheName;
					$scope.cacheElementList = response;
				});
	}

	$scope.deleteCache = function(cacheName) {
		if (confirm("Sure to remove All cache in " + cacheName + " ?")) {
			$http.get("cacheApi/removeCache?cacheName=" + cacheName).success(
					function(response) {
						console.debug("remove cache response ", response);
						location.reload(); 
					});
		}
	}
	
	$scope.deleteCacheElement = function(cacheKey) {

		if (confirm("Sure to remove cache element  " + cacheKey + " ?")) {
			$http.get("cacheApi/removeElement?cacheName=" + $scope.cacheName + "&cacheKey=" + cacheKey).success(
					function(response) {
						console.debug("remove element response ", response);
						location.reload(); 
					});
		}
	}
		
});