var myApp = angular.module('AdminAPP', []);

myApp
		.controller(
				'AdminCtrl',
				function($scope) {
				
					$scope.menuList = [
							{
								'name' : 'Java Melody',
								'url' : 'monitoring'
							},
							{
								'name' : 'Ehcache List',
								'url' : 'cacheList'
							},
							{
								'name' : 'Redis cache List',
								'url' : 'redisCacheList'
							}
						];

			

				});