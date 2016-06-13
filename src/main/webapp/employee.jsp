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

<h1 align="center">员工列表</h1>
<a href="employee_add.jsp">添加员工</a>
<table border="1px" align="center">
    <thead>
    <th colspan="6">
        <h3> c:forEach </h3></th>
    </thead>
    <tr>
        <td>序号</td>
        <td>名字</td>
        <td>联系人</td>
        <td>电话</td>
        <td>邮件</td>
        <td>操作</td>
    </tr>
    <c:forEach items="${employeeList}" var="employee" varStatus="step">
        <tr>
            <td>${step.count}</td>
            <td>${employee.name}</td>
            <td>${employee.contact}</td>
            <td>${employee.telephone}</td>
            <td>${employee.email}</td>
            <td><a href="employee_show.action?employee.id=${employee.id}">详细</a></td>
        </tr>

    </c:forEach>
</table>
<br/>
<table border="1px" align="center">
    <thead>

    <th colspan="6"><h3> s:iterator </h3>
    </th>
    </thead>
    <tr>
        <td>序号</td>
        <td>名字</td>
        <td>联系人</td>
        <td>电话</td>
        <td>邮件</td>
        <td>操作</td>
    </tr>

    <s:iterator value="employeeList" status="st">
        <tr>
            <td><s:property value="#st.count"/></td>
            <td><s:property value="name"/></td>
            <td><s:property value="contact"/></td>
            <td><s:property value="telephone"/></td>
            <td><s:property value="email"/></td>
            <td><a href="employee_show.action?employee.id=<s:property value='id'/>">详细</a></td>
        </tr>

    </s:iterator>
</table>

</body>
</html>
