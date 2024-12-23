<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Todo.GG - 커뮤니티</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: -apple-system, "Segoe UI", Helvetica, Arial, sans-serif;
        }

        body {
            background-color: #ebeef1;
            color: #242929;
            line-height: 1.5;
            padding: 20px;
        }

        .container {
            max-width: 1080px;
            margin: 0 auto;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            overflow: hidden;
        }

        .board-header {
            background: #5383e8;
            padding: 20px;
            color: white;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .header-title {
            font-size: 24px;
            font-weight: bold;
        }

        .login-panel {
            background: #28344e;
            color: white;
            padding: 15px 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            font-size: 14px;
        }

        .write-button {
            background-color: #5383e8;
            color: white;
            border: none;
            padding: 8px 16px;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
            font-weight: 500;
            font-size: 14px;
            transition: background-color 0.2s;
        }

        .write-button:hover {
            background-color: #4171d6;
        }

        .board-content {
            padding: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th {
            background-color: #f8f9fa;
            color: #4171d6;
            font-weight: 600;
            padding: 12px;
            text-align: left;
            border-top: 1px solid #ebeef1;
            border-bottom: 1px solid #ebeef1;
            font-size: 12px;
            text-transform: uppercase;
        }

        td {
            padding: 16px 12px;
            border-bottom: 1px solid #ebeef1;
            font-size: 14px;
        }

        tr:hover {
            background-color: #f8f9fa;
        }

        .title-link {
            color: #28344e;
            text-decoration: none;
            font-weight: 500;
        }

        .title-link:hover {
            color: #5383e8;
            text-decoration: underline;
        }

        .member-id {
            color: #666;
            font-weight: 500;
        }

        .date, .views {
            color: #666;
            font-size: 13px;
        }

        .no-posts {
            text-align: center;
            padding: 40px;
            color: #666;
        }

        /* 페이지네이션 스타일링 */
        .pagination {
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
            gap: 8px;
        }

        .pagination a {
            display: flex;
            align-items: center;
            justify-content: center;
            min-width: 32px;
            height: 32px;
            padding: 0 12px;
            border-radius: 4px;
            border: 1px solid #ebeef1;
            background-color: white;
            color: #28344e;
            font-size: 14px;
            text-decoration: none;
            transition: all 0.2s ease;
        }

        .pagination a:hover {
            background-color: #f8f9fa;
            border-color: #5383e8;
            color: #5383e8;
        }

        .pagination a.active {
            background-color: #5383e8;
            border-color: #5383e8;
            color: white;
        }

        .nav-button {
            font-weight: 500;
        }

        /* 반응형 디자인을 위한 미디어 쿼리 */
        @media (max-width: 768px) {
            .container {
                margin: 0;
                border-radius: 0;
            }
            
            .board-header {
                padding: 15px;
            }

            .header-title {
                font-size: 20px;
            }

            th, td {
                padding: 8px;
            }

            .views-column, .date-column {
                display: none;
            }

            .pagination {
                flex-wrap: wrap;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="board-header">
            <h1 class="header-title">커뮤니티</h1>
        </div>

        <div class="login-panel">
            <div class="login-info">
                <c:choose>
                    <c:when test="${empty loginMember}">
                        로그인이 필요합니다
                    </c:when>
                    <c:otherwise>
                        <strong>${loginMember.name}</strong>님 환영합니다
                    </c:otherwise>
                </c:choose>
            </div>
            <c:if test="${not empty loginMember}">
                <a href="${pageContext.request.contextPath}/board/board.do?action=writeForm" 
                   class="write-button">글쓰기</a>
            </c:if>
        </div>

        <div class="board-content">
            <table>
                <tr>
                    <th width="8%">번호</th>
                    <th width="52%">제목</th>
                    <th width="15%">작성자</th>
                    <th width="15%" class="date-column">작성일</th>
                    <th width="10%" class="views-column">조회수</th>
                </tr>
                <c:forEach var="board" items="${pageBoardList}">
                    <tr>
                        <td>${board.boardId}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/board/board.do?action=view&boardId=${board.boardId}"
                               class="title-link">
                                ${board.title}
                            </a>
                        </td>
                        <td class="member-id">${board.memberId}</td>
                        <td class="date date-column">${board.date}</td>
                        <td class="views views-column">${board.views}</td>
                    </tr>
                </c:forEach>
            </table>

            <div class="pagination">
                <c:if test="${currentPage > 1}">
                    <a href="${pageContext.request.contextPath}/board/board.do?action=pageing&page=${currentPage - 1}"
                       class="nav-button">&laquo; 이전</a>
                </c:if>
                
                <c:forEach begin="1" end="${totalPages}" var="pageNum">
                    <a href="${pageContext.request.contextPath}/board/board.do?action=pageing&page=${pageNum}"
                       class="${pageNum == currentPage ? 'active' : ''}">${pageNum}</a>
                </c:forEach>
                
                <c:if test="${currentPage < totalPages}">
                    <a href="${pageContext.request.contextPath}/board/board.do?action=pageing&page=${currentPage + 1}"
                       class="nav-button">다음 &raquo;</a>
                </c:if>
            </div>
        </div>
    </div>
</body>
</html>