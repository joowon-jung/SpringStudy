<%@ page contentType="text/html;charset=euc-kr" %>

<html>
	<head><title>Test Page</title></head>
	<body>
		
		<a href = "/Spring13/app/user/xml/test01">/Spring13/app/user/xml/test01</a><br/><br/>
		<a href = "/Spring13/app/user/json/test02">/Spring13/app/user/json/test02</a><br/><br/>
		<a href = "/Spring13/app/user/user01">/Spring13/app/user/user01</a><br/><br/>
		<a href = "/Spring13/app/user/user01/userName">/Spring13/app/user/user01/userName</a><br/><br/>
		
		아이디 : ${user.userId}<br/>
		이   름 : ${user.userName}<br/>

		<br/><br/>
		
		<form  method="post" action="/Spring13/app/user/user02/userName02">

			아  이  디 : <input type="text" name="userId" value="${ userForm.userId }"><br/><br/>
			패스워드 : <input type="text" name="password" value="${userForm.password}"><br/><br/>
			<input type="submit" name="submit" value="Enter"/>

		</form>
		
	</body>
</html>