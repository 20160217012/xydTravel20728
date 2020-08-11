<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<button onclick="test()">发送请求</button>
	<script type="text/javascript">
		function test() {
			$.ajax({ 
				type:"get",
				url:"localhost:8080/travel/test",
				//在发送请求前设置请求头
				headers:{
					"xydLoginToke":"5682911f-9474-4b20-9e36-d475e132a5ba"
				},
				sucess:function(){
					alert("请求成功！！！");
				},
				error: function(){
					alert("请求失败！！！");
				}
			});
		}
	</script>
</body>
</html>