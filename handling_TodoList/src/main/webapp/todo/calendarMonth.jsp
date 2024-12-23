<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="todo" items="${todoData}">
    <div class="todo-data" 
         data-date="${todo.startDate}" 
         data-title="${todo.title}" 
         data-recurring="${todo.isRecurring}"
         data-todo-id="${todo.todoId}">
    </div>
    
    
</c:forEach>

<body>
console.log(data-date)

</body>