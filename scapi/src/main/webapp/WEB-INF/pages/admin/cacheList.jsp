<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="CacheListAPP">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin menu</title>
<script src="${ctx}/js/angular.min.js"></script>
<script src="${ctx}/js/admin/cacheListApp.js"></script>
<link rel="stylesheet" href="${ctx}/css/bootstrap.css" />
<link rel="stylesheet"
	href="${ctx}/css/font-awesome-4.3.0 2/css/font-awesome.min.css" />
</head>
<body ng-controller="CacheListCtrl">
	<div class="col-md-12">
		<div class="col-md-6"><h5>Ehcache List</h5></div>
		<div class="col-md-6"><input type="text" ng-model="searchText.cacheName" class="form-control" placeholder="Cache Name"/></div>
		<hr/>
	</div>
	
	<div class="col-md-12">
		<table class="table table-striped">
			<tr class="info">
				<td>cacheName</td>
				<td>numberOfElements</td>
				<td>avgTime</td>
				<td>status</td>
				<td>liveSeconds</td>
				<td>sizeOfMomery</td>
				<td>inMemorySize</td>
				<td>Remarks</td>
			</tr>
			<tr
				ng-repeat="item in cacheList | orderBy:'cacheName' | filter : searchText | filter : searchText">
				<td>{{item.cacheName}}</td>
				<td>{{item.numberOfElements}}</td>
				<td>{{item.avgTime}}</td>
				<td>{{item.status}}</td>
				<td>{{item.liveSeconds}}</td>
				<td>{{item.sizeOfMomery}}</td>
				<td>{{item.inMemorySize}}</td>
				<td><i class="fa fa-file" style="color: blue; cursor: pointer"
					ng-click="viewCacheElement(item['cacheName'])"></i> <i
					class="fa fa-times" style="color: red; cursor: pointer"
					ng-click="deleteCache(item['cacheName'])"></i></td>
			</tr>

		</table>
		<hr />
		<table class="table table-striped">
			<tr>
				<td>cacheKey</td>
				<td>creationTime</td>
				<td>expirationTime</td>
				<td>lastAccessTime</td>
				<td>timeToLive</td>
				<td>hitCount</td>
				<td>remainTime</td>
				<td>sizeOfMomery</td>
				<td>Remarks</td>
			</tr>
			<tr ng-repeat="item in cacheElementList">
				<td>{{item.cacheKey}}</td>
				<td>{{item.creationTime}}</td>
				<td>{{item.expirationTime}}</td>
				<td>{{item.lastAccessTime}}</td>
				<td>{{item.timeToLive}}</td>
				<td>{{item.hitCount}}</td>
				<td>{{item.remainTime}}</td>
				<td>{{item.sizeOfMomery}}</td>

				<td><i class="fa fa-times" style="color: red; cursor: pointer"
					ng-click="deleteCacheElement(item['cacheKey'])"></i></td>
			</tr>

		</table>
	</div>

</body>
</html>