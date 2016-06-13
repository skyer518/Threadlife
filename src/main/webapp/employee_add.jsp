<%--
  Created by IntelliJ IDEA.
  User: alek
  Date: 2016/6/12
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <meta charset="UTF-8">
</head>
<body>
<div align="center">
    <form action="employee_add.action" method="post">
        <table>
            <tr>
                <td>员工姓名：</td>
                <td><input name="employee.name" maxlength="10" required placeholder="员工姓名"/></td>
                <td></td>
            </tr>
            <tr>
                <td>手机号码：</td>
                <td><input name="employee.telephone" type="tel" placeholder="手机号码"/></td>
                <td></td>
            </tr>
            <tr>
                <td>电子邮件：</td>
                <td><input name="employee.email" type="email" placeholder="电子邮件"/></td>
                <td></td>
            </tr>
            <tr>
                <td colspan="3" align="center"><input type="submit" value="添加"/></td>
            </tr>

        </table>
    </form>
</div>
</body>
</html>
