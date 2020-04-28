<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>StudentrLogin</title>
</head>
<body>
<div
        style="height:250px;width:450px;margin-left: 500px;margin-top: 150px;">
    <form
            action="${pageContext.request.contextPath}/app/student/sLogin"
            method="post">
        <div style="height: 250px; width: 350px; margin-left: 100px;">
            <table align="center">
                <tr>
                    <td><h3>学生登陆</h3></td>
                </tr>
                <tr>
                    <td>学号:</td>
                    <td><input type="text" required="required" name="id" oninput = "value=value.replace(/[^\d]/g,'')"/></td>
                </tr>
                <tr>
                    <td>姓名：</td>
                    <td><input type="text" required="required" name=name></td>
                </tr>
                <tr>
                    <td></td>
                    <td><font color="red" >${requestScope.error}</font></td>
                </tr>
                <tr>
                    <td><input type="submit" value="登陆"></td>
                    <td><input type="reset" value="重置"></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/app/teacher/tLoginPage">
                            <input type="button" value="老师登陆" />
                        </a>
                    </td>
                </tr>

            </table>
        </div>
    </form>
</div>
</body>
</html>
