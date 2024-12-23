<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Todo.GG - 회원가입</title>
    <style>
        /* 기본 리셋 및 글꼴 설정 - 전체 사이트와의 일관성 유지 */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: -apple-system, "Segoe UI", Helvetica, Arial, sans-serif;
        }

        /* 배경 및 기본 텍스트 스타일 설정 */
        body {
            background-color: #ebeef1;
            color: #242929;
            line-height: 1.5;
            padding: 20px;
        }

        /* 메인 컨테이너 스타일링 - 시각적 일관성을 위해 로그인 폼과 유사하게 구성 */
        .container {
            max-width: 480px;
            margin: 40px auto;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            overflow: hidden;
        }

        /* 헤더 섹션 스타일링 - 기존 디자인 시스템의 파란색 사용 */
        .signup-header {
            background: #5383e8;
            padding: 20px;
            color: white;
            text-align: center;
        }

        .signup-header h2 {
            font-size: 24px;
            font-weight: bold;
        }

        /* 메인 콘텐츠 영역 스타일링 */
        .signup-content {
            padding: 32px;
        }

        /* 폼 그룹 스타일링 - 각 입력 필드 세트의 구조화 */
        .form-group {
            margin-bottom: 24px;
            position: relative;
        }

        /* 라벨 스타일링 - 사용자 친화적인 텍스트 스타일 */
        label {
            display: block;
            margin-bottom: 8px;
            color: #242929;
            font-weight: 500;
            font-size: 14px;
        }

        /* 입력 필드 스타일링 - 일관된 시각적 스타일 유지 */
        input[type="text"],
        input[type="password"],
        input[type="email"] {
            width: 100%;
            padding: 12px;
            border: 1px solid #dbe0e4;
            border-radius: 4px;
            font-size: 14px;
            transition: border-color 0.2s, box-shadow 0.2s;
        }

        /* 입력 필드 포커스 효과 - 사용자 상호작용 피드백 */
        input[type="text"]:focus,
        input[type="password"]:focus,
        input[type="email"]:focus {
            border-color: #5383e8;
            box-shadow: 0 0 0 2px rgba(83, 131, 232, 0.2);
            outline: none;
        }

        /* 입력 필드 유효성 표시 스타일 */
        input:required:valid {
            border-color: #00b881;
        }

        /* 제출 버튼 스타일링 - 눈에 띄는 주요 액션 버튼 */
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

        /* 에러 메시지 스타일링 */
        .error-message {
            background-color: #fff2f2;
            border: 1px solid #ffb8b8;
            color: #ff4747;
            padding: 12px;
            border-radius: 4px;
            margin-bottom: 20px;
            font-size: 14px;
        }

        /* 반응형 디자인을 위한 미디어 쿼리 */
        @media (max-width: 768px) {
            .container {
                margin: 20px auto;
                max-width: 100%;
            }

            .signup-content {
                padding: 20px;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="signup-header">
            <h2>회원 가입</h2>
        </div>
        
        <div class="signup-content">
            <form action="${pageContext.request.contextPath}/signUp/signUpForm.do" method="post">
                <!-- 아이디 입력 필드 -->
                <div class="form-group">
                    <label for="id">아이디</label>
                    <input type="text" id="id" name="id" required
                           placeholder="사용하실 아이디를 입력해주세요">
                </div>
                
                <!-- 비밀번호 입력 필드 -->
                <div class="form-group">
                    <label for="password">비밀번호</label>
                    <input type="password" id="password" name="password" required
                           placeholder="비밀번호를 입력해주세요">
                </div>
                
                <!-- 닉네임 입력 필드 -->
                <div class="form-group">
                    <label for="nickname">닉네임</label>
                    <input type="text" id="nickname" name="name" required
                           placeholder="사용하실 닉네임을 입력해주세요">
                </div>
                
                <!-- 이메일 입력 필드 -->
                <div class="form-group">
                    <label for="email">이메일</label>
                    <input type="email" id="email" name="email" required
                           placeholder="이메일 주소를 입력해주세요">
                </div>
                
                <!-- 회원가입 버튼 -->
                <button type="submit">가입하기</button>
            </form>
        </div>
    </div>
</body>
</html>