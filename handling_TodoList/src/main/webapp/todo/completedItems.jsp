<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach items="${completedList}" var="todo">
    <div class="todo-item completed">
        <div class="todo-content">
            <div class="todo-title">
                <c:out value="${todo.title}" />
                <span class="completed-badge">완료됨</span>
            </div>
            <div class="todo-detail">${todo.detail}</div>
            <div class="todo-meta">
                <span class="visually-hidden">${todo.todoId}</span>
                <span class="todo-date">
                    <i class="far fa-calendar-check"></i>
                    완료일: ${todo.completedAt}
                </span>
                <span class="todo-original-dates">
                    <i class="far fa-clock"></i>
                    원래 일정: ${todo.startDate} ~ ${todo.endDate}
                </span>
            </div>
        </div>
        <div class="todo-actions">
            <button class="action-button revert-todo" title="되돌리기" onclick="revertTodo(${todo.todoId})">
                <i class="fas fa-undo"></i>
            </button>
            <button class="action-button" title="삭제" onclick="deleteTodo(${todo.todoId})">
                <i class="fas fa-trash"></i>
            </button>
        </div>
    </div>
</c:forEach>