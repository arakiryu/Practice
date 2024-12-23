<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Todo.GG - 당신의 일정관리 파트너</title>
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
        }

        .top-banner {
            background: #28344e;
            color: white;
            text-align: center;
            padding: 8px;
            font-size: 14px;
        }

        .navbar {
            background: #5383e8;
            padding: 0 20px;
            height: 60px;
            display: flex;
            align-items: center;
            justify-content: space-between;
        }

        .logo {
            color: white;
            font-size: 28px;
            font-weight: bold;
            text-decoration: none;
        }

        .login-section {
            margin-left: auto;
        }

        .login-section a {
            color: white;
            text-decoration: none;
            margin-left: 15px;
            font-size: 14px;
            opacity: 0.9;
            transition: opacity 0.2s;
        }

        .login-section a:hover {
            opacity: 1;
        }

        .welcome-msg {
            color: white;
            margin-right: 15px;
            font-size: 14px;
            opacity: 0.9;
        }

        .features-section {
            max-width: 1080px;
            margin: 40px auto;
            padding: 0 20px;
            display: grid;
            grid-template-columns: repeat(2, 1fr);
            gap: 24px;
        }

        .feature-card {
            background: white;
            padding: 24px;
            border-radius: 4px;
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
        }

        .feature-icon {
            font-size: 32px;
            margin-bottom: 16px;
        }

        .feature-title {
            font-size: 20px;
            font-weight: bold;
            margin-bottom: 12px;
            color: #5383e8;
        }

        .feature-description {
            color: #666;
            font-size: 15px;
            line-height: 1.6;
            margin-bottom: 16px;
        }

        .feature-button {
            display: inline-block;
            background: #5383e8;
            color: white;
            padding: 10px 20px;
            border-radius: 4px;
            text-decoration: none;
            font-size: 14px;
            font-weight: 500;
            transition: background-color 0.2s;
        }

        .feature-button:hover {
            background: #4171d6;
        }

        .board-preview {
            margin-top: 16px;
        }

        .board-item {
            padding: 16px;
            background: #f8f9fa;
            border-radius: 4px;
            margin-bottom: 12px;
            transition: background-color 0.2s;
        }

        .board-item:hover {
            background: #ebeef1;
        }

        .post-title {
            font-size: 15px;
            font-weight: 500;
            margin-bottom: 8px;
            color: #5383e8;
        }

        .post-info {
            font-size: 13px;
            color: #666;
        }

        .post-info span {
            margin-right: 12px;
        }

        @media (max-width: 768px) {
            .features-section {
                grid-template-columns: 1fr;
            }
        }
        
        
        
        
    </style>
</head>
<body>
    <div class="top-banner">🎉 Todo.GG가 새롭게 업데이트 되었습니다!</div>

    <nav class="navbar">
        <a href="#" class="logo">Todo.GG</a>
        <div class="login-section">
            <c:choose>
                <c:when test="${empty loginMember}">
                    <a href="${pageContext.request.contextPath}/login/loginForm.jsp">로그인</a>
                    <a href="${pageContext.request.contextPath}/signUp/signUpForm.jsp">회원가입</a>
                </c:when>
                <c:otherwise>
                    <span class="welcome-msg">${loginMember.name}님 환영합니다</span>
                    <a href="${pageContext.request.contextPath}/mypage/mypage.jsp">마이페이지</a>
                    <a href="${pageContext.request.contextPath}/login/logout.do">로그아웃</a>
                </c:otherwise>
            </c:choose>
        </div>
    </nav>

    <section class="features-section">
        <div class="feature-card">
            <div class="feature-icon">📋</div>
            <h3 class="feature-title">일정 관리</h3>
            <p class="feature-description">간편하게 일정을 등록하고 관리하세요. 한눈에 보는 대시보드로 모든 일정을 효율적으로 파악할 수 있습니다.</p>
            <c:choose>
                <c:when test="${empty loginMember}">
                    <a href="${pageContext.request.contextPath}/signUp/signUpForm.jsp" class="feature-button">시작하기</a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/todo/todo.do?action=todoListMain" class="feature-button">일정 관리하기</a>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="feature-card">
            <div class="feature-icon">💬</div>
            <h3 class="feature-title">커뮤니티</h3>
            <p class="feature-description">다른 사용자들과 함께 일정 관리 경험을 공유하세요.</p>
            <div class="board-preview">
                <div class="board-item">
                    <div class="post-title">[오늘의 성공] 한 달 만에 아침 6시 기상 습관 만들기 성공!</div>
                    <div class="post-info">
                        <span>작성자: 열정러너</span>
                        <span>조회수: 127</span>
                    </div>
                </div>
            </div>
            <a href="${pageContext.request.contextPath}/board/board.do?action=pageing" class="feature-button">커뮤니티 입장</a>
        </div>
    </section>
</body>
</html>