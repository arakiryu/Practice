<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="../css/board-styles.css">

<meta charset="UTF-8">
<title>게시글 상세보기</title>
<style>
    /* 기본 스타일 재설정과 글로벌 스타일 */

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

/* 메인 컨테이너 스타일링 */
.container {
    max-width: 1080px;
    margin: 0 auto;
    background-color: white;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    overflow: hidden;
}

/* 게시글 헤더 영역 */
.board-header {
    background: #5383e8;
    padding: 24px;
    color: white;
}

.board-header h3 {
    font-size: 24px;
    font-weight: 600;
    margin-bottom: 16px;
}

.board-header p {
    color: rgba(255, 255, 255, 0.8);
    font-size: 14px;
    margin: 4px 0;
}

/* 게시글 본문 영역 */
.board-content {
    padding: 32px;
    min-height: 200px;
}

.board-content > p {
    font-size: 15px;
    line-height: 1.8;
    color: #242929;
}

/* 버튼 그룹 스타일링 */
.button-group {
    padding: 16px 24px;
    border-top: 1px solid #ebeef1;
    display: flex;
    justify-content: space-between;
    background: #f8f9fa;
}

.btn {
    padding: 8px 16px;
    border-radius: 4px;
    border: none;
    cursor: pointer;
    font-weight: 500;
    font-size: 14px;
    transition: all 0.2s;
}

.btn-primary {
    background-color: #5383e8;
    color: white;
}

.btn-primary:hover {
    background-color: #4171d6;
}

.btn-cancel {
    background-color: #e84057;
    color: white;
}

.btn-cancel:hover {
    background-color: #d63648;
}

/* 댓글 섹션 스타일링 */
.comments-section {
    padding: 24px;
    border-top: 1px solid #ebeef1;
}

.comments-section h3 {
    color: #242929;
    margin-bottom: 20px;
    font-size: 18px;
    font-weight: 600;
}

/* 댓글 폼 스타일링 */
.comment-form {
    margin-bottom: 24px;
}

.comment-form textarea {
    width: 100%;
    min-height: 100px;
    padding: 12px;
    border: 1px solid #dbe0e4;
    border-radius: 4px;
    margin-bottom: 12px;
    resize: vertical;
    font-size: 14px;
}

.comment-form textarea:focus {
    outline: none;
    border-color: #5383e8;
}

/* 댓글 목록 스타일링 */
.comment-item {
    padding: 16px;
    border-bottom: 1px solid #ebeef1;
}

.comment-header {
    display: flex;
    justify-content: space-between;
    margin-bottom: 8px;
}

.comment-header .author {
    font-weight: 600;
    color: #242929;
}

.comment-header .date {
    color: #758592;
    font-size: 13px;
}

.comment-content {
    color: #242929;
    font-size: 14px;
    line-height: 1.6;
    margin: 8px 0;
}

/* 답글 스타일링 */
.comment-item[style*="margin-left"] {
    border-left: 2px solid #5383e8;
    background-color: #f8f9fa;
}

/* 댓글 액션 버튼 스타일링 */
.comment-actions {
    margin-top: 8px;
}

.comment-actions a {
    color: #5383e8;
    font-size: 13px;
    margin-right: 16px;
    cursor: pointer;
    text-decoration: none;
}

.comment-actions a:hover {
    color: #4171d6;
    text-decoration: underline;
}

/* 답글/수정 폼 스타일링 */
.reply-form, .edit-form {
    margin-top: 12px;
    padding: 16px;
    background-color: #f8f9fa;
    border-radius: 4px;
}

.form-buttons {
    display: flex;
    gap: 8px;
    margin-top: 12px;
}

/* 에러 메시지 스타일링 */
.error-message {
    padding: 12px 24px;
    margin: 16px;
    background-color: #fff3f5;
    color: #e84057;
    border-radius: 4px;
    font-size: 14px;
}

/* 반응형 디자인 */
@media (max-width: 768px) {
    body {
        padding: 12px;
    }
    
    .container {
        border-radius: 0;
    }
    
    .board-header {
        padding: 20px;
    }

    .board-content {
        padding: 20px;
    }

    .button-group {
        flex-direction: column;
        gap: 8px;
    }
    
    .button-group button {
        width: 100%;
    }
}
</style>
</head>
<body>
   <div class="container">
        <!-- 에러 메시지 표시 -->
        <c:if test="${not empty msg}">
            <div class="error-message">${msg}</div>
        </c:if>

        <!-- 게시글 헤더 -->
        <div class="board-header">
            <h3>${board.title}</h3>
            <p>작성자: ${board.memberId}</p>
            <p>작성일: ${board.date}</p>
        </div>

        <!-- 게시글 본문 -->
        <div class="board-content">
            <p>${board.content}</p>
        </div>

        <!-- 버튼 그룹 -->
        <div class="button-group">
            <div class="left-buttons">
                <button class="btn" onclick="location.href='${pageContext.request.contextPath}/board/board.do?action=list'">
                    목록으로
                </button>
            </div>
            <div class="right-buttons">
                <c:if test="${loginMember.id eq board.memberId}">
                    <button class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/board/board.do?action=editForm&boardId=${board.boardId}'">
                        수정
                    </button>
                    <button class="btn btn-cancel" onclick="deleteBoard()">삭제</button>
                </c:if>
            </div>
        </div>

        <!-- 댓글 섹션 -->
        <div class="comments-section">
            <h3>댓글</h3>
            
            <!-- 댓글 작성 폼 -->
            <div class="comment-form">
                <form action="${pageContext.request.contextPath}/comment/comment.do" method="post">
                    <input type="hidden" name="action" value="write">
                    <input type="hidden" name="boardId" value="${board.boardId}">
                    <textarea name="content" placeholder="댓글을 입력하세요"></textarea>
                    <button type="submit" class="btn btn-primary">댓글 작성</button>
                </form>
            </div>

            <!-- 댓글 목록 -->
            <div class="comment-list">
                <c:forEach items="${cmtList}" var="comment">
                    <div class="comment-item" style="margin-left: ${comment.depth * 20}px">
                        <div class="comment-header">
                            <span class="author">${comment.member_id}</span>
                            <span class="date">${comment.created_At}</span>
                        </div>
                        <div class="comment-content">
                            ${comment.content}
                        </div>
                        <div class="comment-actions">
                            <a href="javascript:void(0)" onclick="showReplyForm(${comment.cmtNo})">답글</a>
                            
                            <c:if test="${loginMember.id eq comment.member_id}">
                                <a href="javascript:void(0)" onclick="showEditForm(${comment.cmtNo})">수정</a>
                                <a href="javascript:void(0)" onclick="if(confirm('정말 삭제하시겠습니까?')) deleteComment(${comment.cmtNo})">삭제</a>
                            </c:if>
                        </div>

                        <!-- 답글 작성 폼 -->
                        <div id="replyForm${comment.cmtNo}" class="reply-form" style="display: none;">
                            <form action="${pageContext.request.contextPath}/comment/comment.do" method="post">
                                <input type="hidden" name="action" value="reply">
                                <input type="hidden" name="boardId" value="${board.boardId}">
                                <input type="hidden" name="parent" value="${comment.cmtNo}">
                                <textarea name="content" placeholder="답글을 입력하세요"></textarea>
                                <div class="form-buttons">
                                    <button type="submit" class="btn btn-primary">답글 작성</button>
                                    <button type="button" class="btn btn-cancel" onclick="hideReplyForm(${comment.cmtNo})">취소</button>
                                </div>
                            </form>
                        </div>

                        <!-- 수정 폼 -->
                        <div id="editForm${comment.cmtNo}" class="edit-form" style="display: none;">
                            <form action="${pageContext.request.contextPath}/comment/comment.do" method="post">
                                <input type="hidden" name="action" value="update">
                                <input type="hidden" name="boardId" value="${board.boardId}">
                                <input type="hidden" name="cmtNo" value="${comment.cmtNo}">
                                <textarea name="content">${comment.content}</textarea>
                                <div class="form-buttons">
                                    <button type="submit" class="btn btn-primary">수정하기</button>
                                    <button type="button" class="btn btn-cancel" onclick="hideEditForm(${comment.cmtNo})">취소</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>

    <script>
        function deleteBoard() {
            if (confirm('정말 삭제하시겠습니까?')) {
                location.href = '${pageContext.request.contextPath}/board/board.do?action=delete&boardId=${board.boardId}';
            }
        }

        function showReplyForm(commentId) {
            hideAllForms();
            document.getElementById('replyForm' + commentId).style.display = 'block';
        }

        function hideReplyForm(commentId) {
            document.getElementById('replyForm' + commentId).style.display = 'none';
        }

        function showEditForm(commentId) {
            hideAllForms();
            document.getElementById('editForm' + commentId).style.display = 'block';
        }

        function hideEditForm(commentId) {
            document.getElementById('editForm' + commentId).style.display = 'none';
        }

        function hideAllForms() {
            // 모든 답글, 수정 폼을 숨김
            const replyForms = document.querySelectorAll('[id^="replyForm"]');
            const editForms = document.querySelectorAll('[id^="editForm"]');
            
            replyForms.forEach(form => form.style.display = 'none');
            editForms.forEach(form => form.style.display = 'none');
        }

        function deleteComment(commentId) {
            if (confirm('댓글을 삭제하시겠습니까?')) {
                location.href = '${pageContext.request.contextPath}/comment/comment.do?action=delete&cmtNo=' + commentId + '&boardId=${board.boardId}';
            }
        }
    </script>
</body>
</html>