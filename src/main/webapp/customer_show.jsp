<%--
  Created by IntelliJ IDEA.
  User: 明
  Date: 2015/11/27
  Time: 11:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>

<h1>用户</h1>
<a href="customer.action">
    返回
</a>
<table>
    <tr>
        <td>名字</td>
        <td><s:property value="customer.name"/></td>
    </tr>
    <tr>
        <td>联系人</td>
        <td><s:property value="customer.contact"/></td>
    </tr>
    <tr>
        <td>电话</td>
        <td><s:property value="customer.telephone"/></td>
    </tr>
    <tr>
        <td>邮件</td>
        <td><s:property value="customer.email"/></td>
    </tr>
    <tr>
        <td>备注</td>
        <td><s:property value="customer.remark"/></td>
    </tr>
</table>

</body>
</html>
