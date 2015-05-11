<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />

<title>Admin menu</title>
<script src="../js/jquery-2.1.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="../css/admin.css"/>
<script>
	var adminOption = [ 
	                    [ "monitoring", "Java Melody" ],
						[ "cacheMonitoring", "Cache Monitoring" ] 
	                   ];
	$(function() {
		//1) init value, setup table
		var ot = $("#container table");
		var const_tableRowhtml = "<tr class='dataRow' title='{2}'><td>{1}</td></tr>";
		for ( var i in adminOption) {
			var current_tableRowHtml = const_tableRowhtml.replace("{1}",
					adminOption[i][1]).replace("{2}", adminOption[i][0]);

			ot.append(current_tableRowHtml);
		}
		//2) dynamic selector
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

		$(".dataRow").click(
				function() {

					window.location.href = "./"
							+ $(this).attr("title");
				});

	});
</script>

</head>

<body>

	<div id="container">
		<table>
		</table>
	</div>

</body>
</html>