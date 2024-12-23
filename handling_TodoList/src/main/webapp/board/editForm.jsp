<%@page import="vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정</title>
</head>
<body>
    <div align="center">
        <br>
        <hr>
        <h1>게시글 수정</h1>
        <hr>
        <br>
        <form method="post" action="${pageContext.request.contextPath}/board/board.do">
            <input type="hidden" name="action" value="edit">
            <input type="hidden" name="boardId" value="${boardVO.boardId}">
            <input type="hidden" name="memberId" value="${boardVO.memberId}">
            <table border="1" style="width: 80%">
                <tr>
                    <th width="23%">제목</th>
                    <td>
                        <input type="text" name="title" value="${boardVO.title}">
                    </td>
                </tr>
                <tr>
                    <th>작성자</th>
                    <td>${boardVO.memberId}</td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td>
                        <textarea rows="7" cols="80" name="content">${boardVO.content}</textarea>
                    </td>
                </tr>
            </table>
            <br>
            <button type="submit">수정</button>
            <button type="button" onclick="history.back()">취소</button>
        </form>
    </div>
</body>
</html>