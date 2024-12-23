<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Todo.GG - 로그인</title>
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
            max-width: 480px;
            margin: 40px auto;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            overflow: hidden;
        }

        .login-header {
            background: #5383e8;
            padding: 20px;
            color: white;
            text-align: center;
        }

        .login-header h2 {
            font-size: 24px;
            font-weight: bold;
        }

        .login-content {
            padding: 32px;
        }

        .error-message {
            background-color: #fff2f2;
            border: 1px solid #ffb8b8;
            color: #ff4747;
            padding: 12px;
            border-radius: 4px;
            margin-bottom: 20px;
            font-size: 14px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 8px;
            color: #242929;
            font-weight: 500;
            font-size: 14px;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 12px;
            border: 1px solid #dbe0e4;
            border-radius: 4px;
            font-size: 14px;
            transition: border-color 0.2s, box-shadow 0.2s;
        }

        input[type="text"]:focus,
        input[type="password"]:focus {
            border-color: #5383e8;
            box-shadow: 0 0 0 2px rgba(83, 131, 232, 0.2);
            outline: none;
        }

        button[type="submit"] {
            width: 100%;
            padding: 12px;
            background-color: #5383e8;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            font-weight: 500;
            cursor: pointer;
            transition: background-color 0.2s;
        }

        button[type="submit"]:hover {
            background-color: #4171d6;
        }

        @media (max-width: 768px) {
            .container {
                margin: 20px auto;
                max-width: 100%;
            }

            .login-content {
                padding: 20px;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="login-header">
            <h2>로그인</h2>
        </div>
        
        <div class="login-content">
            <% if(request.getAttribute("msg") != null) { %>
                <div class="error-message">
                    <%= request.getAttribute("msg") %>
                </div>
            <% } %>

            <form action="${pageContext.request.contextPath}/login/login.do" method="post">
                <div class="form-group">
                    <label for="id">아이디</label>
                    <input type="text" id="id" name="id" required>
                </div>
                
                <div class="form-group">
                    <label for="password">비밀번호</label>
                    <input type="password" id="password" name="password" required>
                </div>
                
                <button type="submit">로그인</button>
            </form>
        </div>
    </div>
</body>
</html>