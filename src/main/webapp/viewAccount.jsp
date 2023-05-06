<%@include file="taglib.jsp"%>
<%@include file="head.jsp"%>
<html>
<header>
    <%@include file="menu.jsp"%>
</header>
<body class="bg-dark">
<div class="container mt-3 p-3 bgs-b rounded text-white">
    <div class="row rounded-top">
        <div class="text-center m-3">
            <h2>View Account Information</h2>
        </div>
    </div>
    <div class="row m-3">
        <table class="table table-striped table-dark table-hover">
            <thead>
            <tr>
                <th scope="col">Title</th>
                <th scope="col">Information</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th scope="row">User Name</th>
                <td class="m-3">${user.userName}</td>
            </tr>
            <tr>
                <th scope="col">Birthday</th>
                <td class="m-3">${user.dateOfBirth}</td>
            </tr>
            <tr>
                <th scope="col">Gender</th>
                <td class="m-3">${user.gender}</td>
            </tr>
            <tr>
                <th scope="col">First Name</th>
                <td class="m-3">${user.firstName}</td>
            </tr>
            <tr>
                <th scope="col">Last Name</th>
                <td class="m-3">${user.lastName}</td>
            </tr>
            <tr>
                <th scope="col">Email</th>
                <td class="m-3">${user.email}</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</body>
<%@include file="footer.jsp"%>
</html>