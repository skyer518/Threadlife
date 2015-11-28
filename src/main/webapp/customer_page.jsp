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

<h1>用户列表</h1>

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
    <c:forEach items="${customerList}" var="customer" varStatus="step">
        <tr>
            <td>${step.count}</td>
            <td>${customer.name}</td>
            <td>${customer.contact}</td>
            <td>${customer.telephone}</td>
            <td>${customer.email}</td>
            <td><a href="customer_show.action?customer.id=${customer.id}">详细</a></td>
        </tr>

    </c:forEach>
    <tfoot>
    <tr>

        <c:if test="${1<page.currentPageNo}">
            <td><a href="customer_page.action?pageNo=${page.currentPageNo-1}&size=${size}">《 上一页</a></td>
        </c:if>

        <td> ${page.currentPageNo}</td>
        <c:if test="${page.totalPageCount>page.currentPageNo}">
            <td><a href="customer_page.action?pageNo=${page.currentPageNo+1}&size=${size}">下一页 》</a></td>
        </c:if>

    </tr>
    </tfoot>
</table>
<table border="1px">
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

    <s:iterator value="customerList" status="st">
        <tr>
            <td><s:property value="#st.count"/></td>
            <td><s:property value="name"/></td>
            <td><s:property value="contact"/></td>
            <td><s:property value="telephone"/></td>
            <td><s:property value="email"/></td>
            <td><a href="customer_show.action?customer.id=<s:property value='id'/>">详细</a></td>
        </tr>

    </s:iterator>
</table>


</body>
</html>
