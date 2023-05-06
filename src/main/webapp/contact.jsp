<%@include file="head.jsp"%>
<html>
<header>
    <%@include file="menu.jsp"%>
</header>
<body class="bg-dark">
<div class="container text-white bgs-b mt-3 rounded p5">
    <h2>Contact the developer</h2>
    <label for="email">Email address:</label>
    <a href = "mailto:mhan3@madisoncollege.edu" id="email">mhan3@madisoncollege.edu</a>
</div>
</body>
<c:choose>
    <c:when test="${not empty quoteDao}">
        <%@include file="footer.jsp"%>
    </c:when>
    <c:otherwise>
        <footer class="text-center bgs-b mt-3 p-2 text-white">
        </footer>
    </c:otherwise>
</c:choose>
</html>