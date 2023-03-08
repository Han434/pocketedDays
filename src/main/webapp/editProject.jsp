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
    <form action="editProject" method="post" class="was-validated">
        <div class="row my-3">
            <div class="col">
                <div class="form-group">
                    <label for="projectName">Project Name:</label>
                    <input pattern=".*" type="text" value="${project.projectName}" class="form-control" id="projectName" name="projectName" placeholder="${project.projectName}">
                    <div class="valid-feedback">Looks good!</div>
                    <div class="invalid-feedback">Please fill out this field!</div>
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <label for="projectPassword">Project Password</label>
                    <input minlength="8" type="password" class="form-control" name="projectPassword" id="projectPassword" placeholder="${project.projectPassword}">
                    <div class="valid-feedback">Looks good!</div>
                    <div class="invalid-feedback">Password must be at least 8 characters!</div>
                </div>
            </div>
        </div>
        <div class="form-group my-3">
            <label for="description">Project Description</label>
            <textarea class="form-control" name="projectDescription" id="description" placeholder="${project.projectDescription}" rows="3">${project.projectDescription}</textarea>
            <div class="valid-feedback">Looks good!</div>
            <div class="invalid-feedback">Please fill out this field!</div>
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