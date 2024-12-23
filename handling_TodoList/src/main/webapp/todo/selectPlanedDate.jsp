<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
    <c:when test="${empty plannedList}">
        <div class="empty-state">
            <i class="far fa-calendar-times"></i>
            <p>선택하신 기간에 해당하는 일정이 없습니다.</p>
            <p class="date-range-info">조회 기간: ${param.startDate} ~ ${param.endDate}</p>
        </div>
    </c:when>
    <c:otherwise>
        <c:forEach items="${plannedList}" var="todo">
            <div class="todo-item">
                <div class="todo-checkbox"></div>
                <div class="todo-content">
                    <div class="todo-title">
                        <c:out value="${todo.title}" />
                        <c:if test="${todo.isRecurring eq 'Y'}">
                            <span class="recurring-badge">반복</span>
                        </c:if>
                    </div>
                    <div class="todo-detail">${todo.detail}</div>
                    <div class="todo-meta">
                        <span class="visually-hidden">${todo.todoId}</span>
                        <span class="todo-date">
                            <i class="far fa-calendar"></i>${todo.startDate} ~ ${todo.endDate}
                        </span>
                    </div>
                </div>
                <div class="todo-actions">
                    <button class="action-button" title="편집">
                        <i class="fas fa-edit"></i>
                    </button>
                    <button class="action-button" title="삭제">
                        <i class="fas fa-trash"></i>
                    </button>
                </div>
            </div>
        </c:forEach>
    </c:otherwise>
</c:choose>