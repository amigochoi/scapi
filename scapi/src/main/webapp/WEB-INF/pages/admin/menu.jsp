<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="AdminAPP">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin menu</title>
<script src="${ctx}/js/angular.min.js"></script>
<script src="${ctx}/js/admin/adminApp.js"></script>
<link rel="stylesheet" href="${ctx}/css/bootstrap.css">
</head>
<body ng-controller="AdminCtrl">
	<div class="col-md-12">
		<div class="btn-groups-toolbar">
			<h3>Menu</h3>
		</div>
	</div>
	<div class="col-md-6">
		<ul>
			<li ng-repeat="item in menuList"><a href="{{item.url}}">{{item.name}}</a></li>
		</ul>
	</div>
</body>
</html>