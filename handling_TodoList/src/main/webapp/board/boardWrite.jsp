<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Todo.GG - 글쓰기</title>
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

        .write-header {
            background: #5383e8;
            padding: 20px;
            color: white;
        }

        .header-title {
            font-size: 24px;
            font-weight: bold;
        }

        .write-content {
            padding: 30px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-label {
            display: block;
            margin-bottom: 8px;
            font-weight: 600;
            color: #28344e;
        }

        .form-control {
            width: 100%;
            padding: 12px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 16px;
            transition: border-color 0.2s;
        }

        .form-control:focus {
            outline: none;
            border-color: #5383e8;
            box-shadow: 0 0 0 2px rgba(83, 131, 232, 0.1);
        }

        textarea.form-control {
            min-height: 300px;
            resize: vertical;
        }

        .author-info {
            background: #f8f9fa;
            padding: 12px;
            border-radius: 4px;
            color: #666;
            margin-bottom: 20px;
        }

        .button-group {
            display: flex;
            gap: 10px;
            margin-top: 30px;
        }

        .submit-button {
            background-color: #5383e8;
            color: white;
            border: none;
            padding: 12px 24px;
            border-radius: 4px;
            cursor: pointer;
            font-weight: 500;
            font-size: 16px;
            transition: background-color 0.2s;
        }

        .submit-button:hover {
            background-color: #4171d6;
        }

        .cancel-button {
            background-color: #e9ecef;
            color: #495057;
            border: none;
            padding: 12px 24px;
            border-radius: 4px;
            cursor: pointer;
            font-weight: 500;
            font-size: 16px;
            transition: all 0.2s;
        }

        .cancel-button:hover {
            background-color: #dee2e6;
            color: #212529;
        }

        @media (max-width: 768px) {
            .container {
                margin: 0;
                border-radius: 0;
            }
            
            .write-header {
                padding: 15px;
            }

            .write-content {
                padding: 20px;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="write-header">
            <h1 class="header-title">새 글 작성</h1>
        </div>

        <div class="write-content">
            <form action="${pageContext.request.contextPath}/board/board.do?action=write" method="POST">
                <div class="author-info">
                    작성자: <strong><c:out value="${loginMember.name}" /></strong>
                </div>

                <div class="form-group">
                    <label class="form-label" for="title">제목</label>
                    <input type="text" id="title" name="title" class="form-control" required 
                           placeholder="제목을 입력해주세요">
                </div>

                <div class="form-group">
                    <label class="form-label" for="content">내용</label>
                    <textarea id="content" name="content" class="form-control" required
                              placeholder="내용을 입력해주세요"></textarea>
                </div>

                <div class="button-group">
                    <button type="submit" class="submit-button">등록하기</button>
                    <button type="button" class="cancel-button" 
                            onclick="location.href='${pageContext.request.contextPath}/board/board.do?action=list'">
                        취소
                    </button>
                </div>
            </form>
        </div>
    </div>

    <script>
        // 폼 제출 전 유효성 검사
        document.querySelector('form').addEventListener('submit', function(e) {
            const title = document.getElementById('title').value.trim();
            const content = document.getElementById('content').value.trim();

            if (!title) {
                e.preventDefault();
                alert('제목을 입력해주세요.');
                document.getElementById('title').focus();
                return;
            }

            if (!content) {
                e.preventDefault();
                alert('내용을 입력해주세요.');
                document.getElementById('content').focus();
                return;
            }
        });
    </script>
</body>
</html>