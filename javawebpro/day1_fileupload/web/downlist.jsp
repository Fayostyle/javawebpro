<%--
  Created by IntelliJ IDEA.
  User: HuangPan
  Date: 2017/5/25
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>下载列表</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
</head>
<body>
    <table border="0" align="center" width="80%">
        <tr>
            <th>文件序号</th>
            <th>文件名</th>
            <th>操作</th>
        </tr>
        <c:forEach var="en" items="${requestScope.fileNames}" varStatus="vs">
            <tr>
                <td>${vs.count}</td>
                <td>${en.value}</td>
                <td>
                    <!--构建一个地址 -->
                    <c:url var="url" value="fileServlet">
                        <c:param name="method" value="down"></c:param>
                        <c:param name="fileName" value="${en.key}"></c:param>
                    </c:url>
                    <a href="${url}">下载</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
