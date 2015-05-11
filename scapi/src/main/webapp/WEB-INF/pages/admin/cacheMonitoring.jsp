<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Cache Monitoring</title>
<script src="../js/jquery-2.1.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="../css/admin.css"/>
<script>
	$(function() {
		$(".dataRow").mouseover(function() {
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

		$(".dataRow")
				.click(
						function() {
							window.location.href = "./elementList?cacheName="
									+ $(this).attr("id");
						});

	});
</script>
</head>

<body>
	<table>
		<tr>
			<td>cacheName</td>
			<td>numberOfElements</td>
			<td>inMemorySize</td>
			<td>status</td>
			<td>liveSeconds</td>

		</tr>


		<c:forEach items="${cacheDisplayList}" var="cacheDisplay"
			varStatus="status">
			<tr class="dataRow" id="${cacheDisplay.cacheName}">
				<td>${cacheDisplay.cacheName}</td>
				<td>${cacheDisplay.numberOfElements}</td>
				<td>${cacheDisplay.inMemorySize}</td>
				<td>${cacheDisplay.status}</td>
				<td>${cacheDisplay.liveSeconds}</td>
			</tr>
		</c:forEach>


	</table>


</body>
</html>