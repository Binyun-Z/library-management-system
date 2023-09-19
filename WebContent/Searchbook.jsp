<%@ page import="Dao.BookDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Entity.Book" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Searchbook.css">
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/searchbook.js"></script>
<%
    request.setCharacterEncoding("utf-8");
    if(session.getAttribute("adminname") == null){
        response.sendRedirect("/Library/index.jsp");
    }
%>
<title>Searchbook</title>
</head>
<body>
<h1 align="center">欢迎进入图书馆管理系统</h1>
<jsp:include page="nav.html"/>
<div class="search">
    <div class="title">请输入搜索内容</div>
    <form action="BookAction?action=Searchbook" onsubmit="return errorsubmit()" method="post">
        <div>
            <input type="text" id="searchinput" name="searchinput">
            <span id="searchcheck" class="error"></span>
        </div>
        <div class="button">
            <button type="submit" class="btn btn-success" onclick="searchcheck()">搜索</button>
            <button type="reset" class="btn btn-default">清空</button>
        </div>
     </form>
</div>
<table class="table">
    <thead>
    <tr>
        <th>书本编号</th>
        <th>书名</th>
        <th>作者</th>
        <th>出版社</th>
        <th>库存总量</th>
        <th>借出数量</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <%   
    	ArrayList<Book> booklist = (ArrayList<Book>)session.getAttribute("searchbooklist");
        if(booklist!=null && booklist.size() > 0)
        {
            for(int i = 0; i < booklist.size(); i++)
            {
                Book b = booklist.get(i);
    %>
    <tr>
        <td><%=b.getId()%></td>
        <td><a href="BookAction?action=querybookbyid&id=<%=b.getId()%>&next=check"><%=b.getName()%></a> </td>
        <td><%=b.getAuthor()%></td>
        <td><%=b.getPublisher()%></td>
        <td><%=b.getStore()%></td>
        <td><%=b.getLend()%></td>
        <td><a href="BookAction?action=querybookbyid&id=<%=b.getId()%>&next=edit">编辑或删除</a></td>
    </tr>
    <%
            }
        }
    %>
    </tbody>
</table>
</body>
</html>