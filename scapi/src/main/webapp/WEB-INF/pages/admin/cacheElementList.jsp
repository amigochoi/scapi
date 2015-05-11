<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Cache Monitoring - Cache Elements List</title>
<script src="../js/jquery-2.1.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="../css/admin.css"/>
<script>
	$(function() {
		$(".dataRow,i").mouseover(function() {
			$(this).css({
				cursor : 'pointer',
				opacity : 0.5
			});
		}).mouseout(function() {
			$(this).css({
				cursor : 'auto',
				opacity : 1
			});
		});

		$(".dataRow").click(function() {
			var removeApiLink = "./removeElement";
			var elementName = $(this).attr("id");
			var parm = {
				cacheName : "${cacheName}",
				cacheKey : elementName
			};
			if (confirm("Confirm remove Element " + elementName + " !?")) {
				$.get(removeApiLink, parm, function(data) {
					location.reload();
				});
			}
		});

		$("#removeAllElement").click(function() {
			var removeApiLink = "./flashCache";
			var cacheName = "${cacheName}";
			var parm = {
				cacheName : cacheName
			};
			if (confirm("Confirm remove All Cache for " + cacheName + " !?")) {
				$.get(removeApiLink, parm, function(data) {
					location.reload();
				});
			}
		});

	});
</script>

</head>

<body>
	<table>
		<tr>
			<td>cacheKey for [ <i id="removeAllElement">${cacheName}</i> ]
			</td>
			<td>sizeOfMomery</td>
			<td>creationTime</td>
			<td>expirationTime</td>
			<td>remainTime</td>
			<td>lastAccessTime</td>
			<td>hitCount</td>

		</tr>

		<c:forEach items="${elementList}" var="element" varStatus="status">
			<tr class="dataRow" id="${element.cacheKey}">
				<td>${element.cacheKey}</td>
				<td>${element.sizeOfMomery}</td>
				<td>${element.creationTime}</td>
				<td>${element.expirationTime}</td>
				<td>${element.remainTime} / ${element.timeToLive}</td>
				<td>${element.lastAccessTime}</td>
				<td>${element.hitCount}</td>
			</tr>
		</c:forEach>

	</table>
</body>
</html>