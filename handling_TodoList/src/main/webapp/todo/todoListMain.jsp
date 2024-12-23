<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Todo.GG - 할 일 관리</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <link href='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.10.2/fullcalendar.min.css' rel='stylesheet' />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js'></script>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.10.2/fullcalendar.min.js'></script>
    
    <style>
    @import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap');

    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        font-family: 'Noto Sans KR', -apple-system, BlinkMacSystemFont,
            system-ui, Roboto, 'Helvetica Neue', 'Segoe UI', 'Apple SD Gothic Neo',
            'Malgun Gothic', sans-serif;
        letter-spacing: -0.02em;
    }

    body {
        display: flex;
        background-color: #f5f6f7;
        min-height: 100vh;
        color: #242929;
    }

    .sidebar {
        width: 280px;
        background: white;
        padding: 24px;
        border-right: 1px solid #e0e3e5;
        position: fixed;
        height: 100vh;
        overflow-y: auto;
        box-shadow: 2px 0 8px rgba(0, 0, 0, 0.05);
    }

    .sidebar-header {
        padding: 16px 0;
        border-bottom: 1px solid #e0e3e5;
        margin-bottom: 24px;
    }

    .sidebar-header h1 {
        font-size: 28px;
        color: #5383e8;
        font-weight: 700;
    }

    .sidebar-add-btn {
        width: 100%;
        padding: 12px;
        margin: 0 0 20px 0;
        background: none;
        border: 1px dashed #5383e8;
        border-radius: 8px;
        color: #5383e8;
        cursor: pointer;
        display: flex;
        align-items: center;
        gap: 8px;
        font-size: 14px;
        transition: all 0.2s ease;
    }

    .sidebar-add-btn:hover {
        background: #f0f4fe;
    }

    .sidebar ul {
        list-style: none;
    }

    .sidebar li {
        padding: 14px 18px;
        margin: 6px 0;
        border-radius: 8px;
        cursor: pointer;
        color: #424242;
        font-weight: 500;
        display: flex;
        align-items: center;
        gap: 14px;
        transition: all 0.2s ease;
    }

    .sidebar li:hover {
        background-color: #f0f4fe;
        color: #5383e8;
    }

    .sidebar li.active {
        background-color: #5383e8;
        color: white;
    }

    .container {
        flex: 1;
        margin-left: 280px;
        padding: 40px;
        max-width: 1200px;
    }

    .header {
        margin-bottom: 32px;
        padding-bottom: 20px;
        border-bottom: 1px solid #e0e3e5;
    }

    .header-title {
        font-size: 32px;
        font-weight: 700;
        color: #242929;
    }

    .todo-section {
        display: none;
    }

    .todo-section.active {
        display: block;
    }

    .todo-list {
        display: flex;
        flex-direction: column;
        gap: 8px;
    }

    .todo-item {
        background: white;
        padding: 12px 16px;
        border-radius: 8px;
        display: flex;
        align-items: center;
        gap: 12px;
        transition: all 0.2s ease;
        border: 1px solid #e0e3e5;
        position: relative;
    }

    .todo-item:hover {
        box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
    }

    .todo-checkbox {
        width: 20px;
        height: 20px;
        border: 2px solid #5383e8;
        border-radius: 4px;
        cursor: pointer;
        transition: all 0.2s ease;
        flex-shrink: 0;
    }

    .todo-content {
        flex: 1;
        min-width: 0;
    }

    .todo-title {
        font-size: 14px;
        font-weight: 500;
        margin-bottom: 4px;
        display: flex;
        align-items: center;
        gap: 8px;
    }

    .recurring-badge {
        background-color: #5383e8;
        color: white;
        padding: 2px 8px;
        border-radius: 12px;
        font-size: 12px;
    }

    .todo-detail {
        font-size: 12px;
        color: #666;
        margin-bottom: 4px;
    }

    .todo-meta {
        font-size: 12px;
        color: #999;
    }

    .todo-date {
        display: flex;
        align-items: center;
        gap: 4px;
    }

    .todo-actions {
        display: flex;
        gap: 8px;
    }

    .action-button {
        padding: 4px;
        background: none;
        border: none;
        color: #999;
        cursor: pointer;
        transition: all 0.2s ease;
        border-radius: 4px;
        font-size: 14px;
    }

    .action-button:hover {
        color: #5383e8;
        background-color: #f0f4fe;
    }

    .date-picker-container {
        background: white;
        border-radius: 12px;
        padding: 20px;
        margin-bottom: 24px;
        border: 1px solid #e0e3e5;
    }

    .date-range {
        display: flex;
        gap: 16px;
        align-items: flex-end;
    }

    .date-input-group {
        flex: 1;
    }

    .date-input-group label {
        display: block;
        margin-bottom: 8px;
        color: #424242;
        font-weight: 500;
        font-size: 14px;
    }

    .date-input-group input[type="date"] {
        width: 100%;
        padding: 10px;
        border: 1px solid #e0e3e5;
        border-radius: 8px;
        font-size: 14px;
    }

    .todo-form {
        position: fixed;
        right: -400px;
        top: 0;
        width: 400px;
        height: 100vh;
        background: white;
        padding: 24px;
        box-shadow: -4px 0 12px rgba(0, 0, 0, 0.1);
        transition: all 0.3s ease;
        z-index: 1000;
        overflow-y: auto;
    }

    .todo-form.active {
        right: 0;
    }

    .todo-form h2 {
        font-size: 20px;
        font-weight: 700;
        margin-bottom: 24px;
        padding-bottom: 16px;
        border-bottom: 1px solid #e0e3e5;
    }

    .form-group {
        margin-bottom: 20px;
    }

    .form-group label {
        display: block;
        margin-bottom: 8px;
        color: #424242;
        font-weight: 500;
        font-size: 14px;
    }

    .form-group input, .form-group textarea {
        width: 100%;
        padding: 10px;
        border: 1px solid #e0e3e5;
        border-radius: 8px;
        font-size: 14px;
    }

    .form-group input:focus, .form-group textarea:focus {
        outline: none;
        border-color: #5383e8;
        box-shadow: 0 0 0 3px rgba(83, 131, 232, 0.1);
    }

    .btn {
        padding: 10px 20px;
        border-radius: 8px;
        border: none;
        cursor: pointer;
        font-weight: 500;
        font-size: 14px;
        transition: all 0.2s ease;
    }

    .btn-primary {
        background: #5383e8;
        color: white;
    }

    .btn-primary:hover {
        background: #4263eb;
    }

    .slide-overlay {
        position: fixed;
        top: 0;
        left: 0;
        width: 100vw;
        height: 100vh;
        background: rgba(0, 0, 0, 0.5);
        opacity: 0;
        visibility: hidden;
        transition: all 0.3s ease;
        z-index: 999;
    }

    .slide-overlay.active {
        opacity: 1;
        visibility: visible;
    }

    .empty-state {
        text-align: center;
        padding: 48px 0;
        color: #666;
    }

    .empty-state i {
        font-size: 48px;
        color: #ccc;
        margin-bottom: 16px;
    }

    .empty-state p {
        font-size: 14px;
    }

    .todo-checkbox.checked {
        background-color: #5383e8;
        border-color: #5383e8;
        position: relative;
    }

    .todo-checkbox.checked:after {
        content: '✓';
        color: white;
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        font-size: 12px;
    }

    .visually-hidden {
        position: absolute;
        width: 1px;
        height: 1px;
        padding: 0;
        margin: -1px;
        overflow: hidden;
        clip: rect(0,0,0,0);
        border: 0;
    }

    #calendar {
        max-width: 900px;
        margin: 0 auto;
    }

    .fc-event {
        cursor: pointer;
    }

    .recurring {
        border-left: 3px solid #5383e8;
    }

    </style>
</head>
<body>
    <!-- 오버레이 -->
    <div class="slide-overlay"></div>

    <!-- 사이드바 -->
    <div class="sidebar">
        <div class="sidebar-header">
            <h1>Todo.GG</h1>
        </div>
        <button class="sidebar-add-btn">
            <i class="fas fa-plus"></i>작업 추가
        </button>
        <ul>
            <li data-section="today" class="active"><i class="fas fa-calendar-day"></i>오늘 일정</li>
            <li data-section="planned"><i class="fas fa-calendar"></i>계획된 일정</li>
            <li data-section="completed"><i class="fas fa-check-circle"></i>완료된 일정</li>
            <li data-section="calendar"><i class="fas fa-calendar-alt"></i>캘린더</li>
        </ul>
    </div>

    <!-- 메인 컨텐츠 -->
    <div class="container">
        <!-- 오늘 일정 섹션 -->
        <div id="today-section" class="todo-section active">
            <div class="header">
                <h1 class="header-title">오늘 일정</h1>
            </div>
            <div class="todo-list">
                <!-- 여기에 오늘의 할 일 항목들이 동적으로 추가됩니다 -->
            </div>
        </div>

        <!-- 계획된 일정 섹션 -->
        <div id="planned-section" class="todo-section">
            <div class="header">
                <h1 class="header-title">계획된 일정</h1>
            </div>
            <div class="date-picker-container">
                <form id="plannedDateRangeForm" class="date-range">
                    <div class="date-input-group">
                        <label for="plannedStartDate">시작 날짜</label>
                        <input type="date" id="plannedStartDate" name="startDate" required>
                    </div>
                    <div class="date-input-group">
                        <label for="plannedEndDate">종료 날짜</label>
                        <input type="date" id="plannedEndDate" name="endDate" required>
                    </div>
                    <button type="submit" class="btn btn-primary">조회</button>
                </form>
            </div>
            <div class="todo-list">
                <!-- 여기에 계획된 일정 항목들이 동적으로 추가됩니다 -->
            </div>
        </div>

        <!-- 완료된 일정 섹션 -->
        <div id="completed-section" class="todo-section">
            <div class="header">
            <h1 class="header-title">완료된 일정</h1>
            </div>
            <div class="date-picker-container">
                <form id="completedDateRangeForm" class="date-range">
                    <div class="date-input-group">
                        <label for="completedStartDate">시작 날짜</label>
                        <input type="date" id="completedStartDate" name="startDate" required>
                    </div>
                    <div class="date-input-group">
                        <label for="completedEndDate">종료 날짜</label>
                        <input type="date" id="completedEndDate" name="endDate" required>
                    </div>
                    <button type="submit" class="btn btn-primary">조회</button>
                </form>
            </div>
            <div class="todo-list">
                <!-- 여기에 완료된 일정 항목들이 동적으로 추가됩니다 -->
            </div>
        </div>

        <!-- 캘린더 섹션 -->
        <div id="calendar-section" class="todo-section">
            <div class="header">
                <h1 class="header-title">캘린더</h1>
            </div>
            <div id="calendar"></div>
        </div>
    </div>

    <!-- 할일 추가 및 수정 폼 -->
    <div class="todo-form">
        <button class="close-btn">
            <i class="fas fa-times"></i>
        </button>
        <h2>새 일정 추가</h2>
        <form id="todoForm">
            <div class="form-group">
                <label for="todoTitle">제목</label>
                <input type="text" id="todoTitle" name="title" required>
            </div>
            <div class="form-group">
                <label for="todoDetail">내용</label>
                <textarea id="todoDetail" name="detail" rows="4"></textarea>
            </div>
            <div class="form-group">
                <label for="todoStartDate">시작일</label>
                <input type="date" id="todoStartDate" name="startDate" required>
            </div>
            <div class="form-group">
                <label for="todoEndDate">마감일</label>
                <input type="date" id="todoEndDate" name="endDate" required>
            </div>
            <div class="form-group">
                <label class="checkbox-label">
                    <input type="checkbox" id="isRecurring" name="isRecurring" value="Y">
                    반복 일정
                </label>
            </div>
            <div id="recurringOptions" style="display: none;" class="form-group">
                <label for="repeatInterval">반복 간격</label>
                <div class="repeat-interval-container">
                    <input type="number" 
                           id="repeatInterval" 
                           name="repeatInterval" 
                           min="1" 
                           value="1" 
                           class="repeat-input">
                    <span>일 마다</span>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">추가하기</button>
        </form>
    </div>

    <script>
        var contextPath = '${pageContext.request.contextPath}';
        $(document).ready(function() {
            let currentSection = 'today';
            let currentDate = new Date();
            let isCalendarInitialized = false;

            // 초기화
            loadTodayTodos();
            initializeDateInputs();

            // 사이드바 섹션 전환
            $('.sidebar li').on('click', function(e) {
                const section = $(this).data('section');
                switchSection(section);
            });

            // 작업 추가 버튼 클릭 시 폼 열기
            $('.sidebar-add-btn').click(function() {
                initNewTodoForm();
                openTodoForm();
            });

            // 폼 닫기 버튼 및 오버레이 클릭 시 폼 닫기
            $('.close-btn, .slide-overlay').click(function() {
                closeTodoForm();
            });

            // 캘린더 초기화
            function initializeCalendar() {
                if (isCalendarInitialized) return;
                $('#calendar').fullCalendar({
                    header: {
                        left: 'prev,next today',
                        center: 'title',
                        right: 'month,agendaWeek,agendaDay'
                    },
                    editable: true,
                    eventLimit: true,
                    events: function(start, end, timezone, callback) {
                    	console.log('Calendar Request Start Date:', start.format('YYYY-MM-DD'));
                        console.log('Calendar Request End Date:', end.format('YYYY-MM-DD'));
                    	
                        $.ajax({
                            url: contextPath + '/todo/todo.do',
                            type: 'GET',
                            data: {
                                action: 'calendarMonth',
                                startDate: start.format('YYYY-MM-DD'),
                                endDate: end.format('YYYY-MM-DD')
                            },
                            dataType: 'json',
                            success: function(response) {
                            	console.log('Calendar Events Received:', response);
                                console.log('Total Events Count:', response.length);
                                callback(response); // JSON 데이터를 직접 사용
                            }
                        });
                    },
                    eventRender: function(event, element) {
                        if (event.className.includes('recurring')) {
                            element.find('.fc-title').prepend('<i class="fas fa-redo-alt mr-1"></i>');
                        }
                    }
                });
                isCalendarInitialized = true;
            }

            function updateCalendar() {
                $('#calendar').fullCalendar('refetchEvents');
            }

            // 섹션 전환 함수
            function switchSection(section) {
                currentSection = section;
                $('.sidebar li').removeClass('active');
                $('.sidebar li[data-section="' + section + '"]').addClass('active');
                $('.todo-section').removeClass('active');
                $('#' + section + '-section').addClass('active');

                switch (section) {
                    case 'today':
                        loadTodayTodos();
                        break;
                    case 'planned':
                        loadPlannedTodos();
                        break;
                    case 'completed':
                        loadCompletedTodos();
                        break;
                    case 'calendar':
                        if (!isCalendarInitialized) {
                            initializeCalendar();
                        }
                        updateCalendar();
                        break;
                }
            }

            // 오늘 일정 불러오기
            function loadTodayTodos() {
                $.ajax({
                    url: contextPath + '/todo/todo.do',
                    type: 'GET',
                    data: { action: 'todayList' },
                    success: function(response) {
                        $('#today-section .todo-list').empty().append(response);
                        bindTodoEvents();
                        updateEmptyState('#today-section .todo-list');
                    }
                });
            }

            // 계획된 일정 불러오기
            function loadPlannedTodos() {
                $.ajax({
                    url: contextPath + '/todo/todo.do',
                    type: 'GET',
                    data: { action: 'plannedList' },
                    success: function(response) {
                        $('#planned-section .todo-list').empty().append(response);
                        bindTodoEvents();
                        updateEmptyState('#planned-section .todo-list');
                    }
                });
            }

            // 완료된 일정 불러오기
            function loadCompletedTodos() {
                $.ajax({
                    url: contextPath + '/todo/todo.do',
                    type: 'GET',
                    data: { action: 'completedList' },
                    success: function(response) {
                        $('#completed-section .todo-list').empty().append(response);
                        bindTodoEvents();
                        updateEmptyState('#completed-section .todo-list');
                    }
                });
            }

            // 이벤트 바인딩 함수
            function bindTodoEvents() {
                // 할 일 완료 체크
                $('.todo-checkbox').off('click').on('click', function() {
                    $(this).addClass('checked');
                    const todoId = $(this).closest('.todo-item').find('.visually-hidden').text();
                    const $todoItem = $(this).closest('.todo-item');

                    setTimeout(() => {
                        $todoItem.fadeOut(300, function() {
                            completeTodo(todoId);
                        });
                    }, 300);
                });

                // 할 일 편집
                $('.action-button[title="편집"]').off('click').on('click', function() {
                    const todoItem = $(this).closest('.todo-item');
                    const todoId = todoItem.find('.visually-hidden').text();
                    const title = todoItem.find('.todo-title').contents().filter(function() {
                        return this.nodeType === 3;
                    }).text().trim();
                    const detail = todoItem.find('.todo-detail').text().trim();
                    const startDate = todoItem.find('.todo-date').data('start-date');
                    const endDate = todoItem.find('.todo-date').data('end-date');
                    const isRecurring = todoItem.find('.recurring-badge').length > 0;

                    initEditForm(todoId, title, detail, startDate, endDate, isRecurring);
                    openTodoForm();
                });

                // 할 일 삭제
                $('.action-button[title="삭제"]').off('click').on('click', function() {
                    if (!confirm('정말 삭제하시겠습니까?')) return;
                    const todoItem = $(this).closest('.todo-item');
                    const todoId = todoItem.find('.visually-hidden').text();
                    const isCompleted = todoItem.closest('#completed-section').length > 0;

                    todoItem.fadeOut(300, function() {
                        deleteTodo(todoId, isCompleted);
                    });
                });

                // 완료된 일정 되돌리기
                $('.revert-todo').off('click').on('click', function() {
                    const todoId = $(this).closest('.todo-item').find('.visually-hidden').text();
                    const $todoItem = $(this).closest('.todo-item');

                    $todoItem.fadeOut(300, function() {
                        revertTodo(todoId);
                    });
                });
            }

            // 새 할 일 폼 초기화
            function initNewTodoForm() {
                $('.todo-form h2').text('새 일정 추가');
                $('.todo-form .btn-primary').text('추가하기');
                $('.todo-form form')[0].reset();
                initializeDateInputs();

                $('#isRecurring').change(function() {
                    $('#recurringOptions').toggle(this.checked);
                    if (!this.checked) {
                        $('#repeatInterval').val(1);
                    }
                });

                $('.todo-form form').off('submit').on('submit', function(e) {
                    e.preventDefault();
                    
                    let isRecurring = $('#isRecurring').is(':checked') ? 'Y' : 'N';
                    let repeatInterval = $('#repeatInterval').val() || 1;

                    $.ajax({
                        url: contextPath + '/todo/todo.do',
                        type: 'POST',
                        data: {
                            action: 'todoInsertForm',
                            title: $('#todoTitle').val(),
                            detail: $('#todoDetail').val(),
                            startDate: $('#todoStartDate').val(),
                            endDate: $('#todoEndDate').val(),
                            isRecurring: isRecurring,
                            repeatInterval: repeatInterval
                        },
                        success: function(response) {
                            closeTodoForm();
                            refreshCurrentSection();
                            if (currentSection === 'calendar') {
                                updateCalendar();
                            }
                        },
                        error: function(xhr, status, error) {
                            console.error('Error:', error);
                        }
                    });
                });
            }

            // 할 일 편집 폼 초기화
            function initEditForm(todoId, title, detail, startDate, endDate, isRecurring) {
                $('.todo-form h2').text('일정 수정');
                $('.todo-form .btn-primary').text('수정하기');

                $('#todoTitle').val(title);
                $('#todoDetail').val(detail);
                $('#todoStartDate').val(startDate);
                $('#todoEndDate').val(endDate);
                $('#isRecurring').prop('checked', isRecurring);

                $('.todo-form form').off('submit').on('submit', function(e) {
                    e.preventDefault();
                    
                    let isRecurring = $('#isRecurring').is(':checked') ? 'Y' : 'N';
                    let repeatInterval = isRecurring === 'Y' ? ($('#repeatInterval').val() || 1) : 0;

                    $.ajax({
                        url: contextPath + '/todo/todo.do',
                        type: 'POST',
                        data: {
                            action: 'todoEdit',
                            todoId: todoId,
                            title: $('#todoTitle').val(),
                            detail: $('#todoDetail').val(),
                            startDate: $('#todoStartDate').val(),
                            endDate: $('#todoEndDate').val(),
                            isRecurring: isRecurring,
                            repeatInterval: repeatInterval
                        },
                        success: function(response) {
                            closeTodoForm();
                            refreshCurrentSection();
                            if (currentSection === 'calendar') {
                                updateCalendar();
                            }
                        },
                        error: function(xhr, status, error) {
                            console.error('수정 실패:', error);
                        }
                    });
                });
            }

            // 할 일 완료 처리
            function completeTodo(todoId) {
                $.ajax({
                    url: contextPath + '/todo/todo.do',
                    type: 'POST',
                    data: {
                        action: 'completeTodo',
                        todoId: todoId
                    },
                    success: function() {
                        refreshCurrentSection();
                        if (currentSection === 'calendar') {
                            updateCalendar();
                        }
                    }
                });
            }

            // 할 일 삭제 처리
            function deleteTodo(todoId, isCompleted) {
                $.ajax({
                    url: contextPath + '/todo/todo.do',
                    type: 'POST',
                    data: {
                        action: 'todoDelete',
                        todoId: todoId,
                        isCompleted: isCompleted
                    },
                    success: function() {
                    	refreshCurrentSection();
                        if (currentSection === 'calendar') {
                            updateCalendar();
                        }
                    }
                });
            }

            // 완료된 일정 되돌리기 처리
            function revertTodo(todoId) {
                $.ajax({
                    url: contextPath + '/todo/todo.do',
                    type: 'POST',
                    data: {
                        action: 'revertTodo',
                        todoId: todoId
                    },
                    success: function() {
                        refreshCurrentSection();
                        if (currentSection === 'calendar') {
                            updateCalendar();
                        }
                    }
                });
            }

            // 폼 열기
            function openTodoForm() {
                $('.todo-form').addClass('active');
                $('.slide-overlay').addClass('active');
            }

            // 폼 닫기
            function closeTodoForm() {
                $('.todo-form').removeClass('active');
                $('.slide-overlay').removeClass('active');
                setTimeout(initNewTodoForm, 300);
            }

            // 현재 섹션 새로고침
            function refreshCurrentSection() {
                switch (currentSection) {
                    case 'today':
                        loadTodayTodos();
                        break;
                    case 'planned':
                        loadPlannedTodos();
                        break;
                    case 'completed':
                        loadCompletedTodos();
                        break;
                    case 'calendar':
                        updateCalendar();
                        break;
                }
            }

            // 빈 상태 업데이트 함수
            function updateEmptyState(selector) {
                if ($(selector).children('.todo-item').length === 0) {
                    $(selector).html(`
                        <div class="empty-state">
                            <i class="far fa-clipboard"></i>
                            <p>등록된 일정이 없습니다.</p>
                        </div>
                    `);
                }
            }

            // 날짜 입력 초기화 함수
            function initializeDateInputs() {
                const today = new Date().toISOString().split('T')[0];
                $('input[name="startDate"]').val(today);
                $('input[name="endDate"]').val(today);
                $('input[type="date"]').attr('min', today);
            }

            // 계획된 일정 및 완료된 일정의 날짜 범위 폼 제출 처리
            $('#plannedDateRangeForm, #completedDateRangeForm').submit(function(e) {
                e.preventDefault();
                const startDate = $(this).find('[name="startDate"]').val();
                const endDate = $(this).find('[name="endDate"]').val();

                if (new Date(startDate) > new Date(endDate)) {
                    alert('종료 날짜는 시작 날짜보다 늦어야 합니다.');
                    return;
                }

                const action = $(this).attr('id') === 'plannedDateRangeForm' ? 'selectDate' : 'selectCompletedDate';
                const targetSection = $(this).attr('id') === 'plannedDateRangeForm' ? '#planned-section .todo-list' : '#completed-section .todo-list';

                $.ajax({
                    url: contextPath + '/todo/todo.do',
                    type: 'GET',
                    data: {
                        action: action,
                        startDate: startDate,
                        endDate: endDate
                    },
                    success: function(response) {
                        $(targetSection).empty().append(response);
                        bindTodoEvents();
                        updateEmptyState(targetSection);
                    }
                });
            });
        });
    </script>
</body>
</html>
              
                    