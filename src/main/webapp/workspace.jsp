<%@include file="taglib.jsp"%>
<%@include file="head.jsp"%>
<html>
<header>
    <%@include file="menu.jsp"%>
</header>
<div class="container bg-secondary">
    <body>

    <div class="container-fluid">
        <h2>Workspace</h2>
        <div class="row">
            <c:forEach var="project" items="${projects}">
                <div class="card col-3 px-0 py-0 mx-2">
                    <div class="card-header bgs-b text-light">
                        <p>Projec Name: ${project.projectName}</p>
                    </div>
                    <div class="card-body bg-dark">
                        <a class="mx-auto text-light" href="projectHome?projectId=${project.projectId}">
                            <img class="w-25" src="images/viewProject.png">
                            Click to View the Project
                        </a>
                    </div>
                    <div class="card-footer bgs-b text-light">
                        <p>Creator Id: ${project.creatorId}</p>
                        <p>Creator Date: ${project.createdDate}</p>
                    </div>
                </div>
            </c:forEach>
            <div class="card col-3 px-0 py-0 mx-2">
                <div class="card-header bgs-b text-light">
                    <p>Add new Project</p>
                </div>
                <div class="card-body bg-dark">
                    <a class="mx-auto text-light" href="createProject?submit=NoAdding">
                        <img class="w-25" src="images/addNew.png">
                        Click to Add New Project
                    </a>
                </div>
                <div class="card-footer bgs-b text-light">
                    <p>Note. To create new project click the above plus sign.</p>
                </div>
            </div>
        </div>
    </div>

    </body>
</div>
</html>