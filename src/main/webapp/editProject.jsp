<%@include file="taglib.jsp"%>
<%@include file="head.jsp"%>
<html>
<header>
    <%@include file="projectMenu.jsp"%>
</header>
<body>
<div class="container mt-3">
<div class="form-box">
    <h2 class="text-center">Edit Project</h2>
    <form action="editProject">
        <div class="row my-3">
            <div class="col">
                <div class="form-group">
                    <label for="name">Project Name:</label>
                    <input type="text" value="${project.projectName}" class="form-control" id="name" name="projectName" aria-describedby="emailHelp" placeholder="${project.projectName}">
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <label for="exampleInputPassword1">Project Password</label>
                    <input type="password" class="form-control" name="projectPassword" id="exampleInputPassword1" placeholder="${project.projectPassword}">
                </div>
            </div>
        </div>
        <div class="form-group my-3">
            <label for="description">Project Description</label>
            <textarea class="form-control" value="${project.projectDescription}" name="projectDescription" id="description" placeholder="${project.projectDescription}" rows="3"></textarea>
        </div>
        <div class="form-group my-3">
            <input class="btn bg-success text-white mx-3" type="submit" name="submit" value="Edit Project"/>
            <a class="btn bg-dark text-white mx-3" href="projectHome?projectId=${project.projectId}">Cancle</a>
        </div>
    </form>
</div>
</div>
</body>
</html>