<%@include file="taglib.jsp"%>
<%@include file="head.jsp"%>
<html>
<header>
    <%@include file="menu.jsp"%>
</header>
<body>
    <div class="container mt-3">
        <h2>Workspace</h2>
        <div class="row">
            <c:forEach var="project" items="${projects}">
                <div class="card col-3 px-0 py-0 m-3">
                    <div class="card-header bgs-b text-light">
                        <p>Projec Name: ${project.projectName}</p>
                    </div>
                    <div class="card-body bg-dark">
                        <a class="mx-auto text-light btn" href="projectHome?projectId=${project.projectId}">
                            <img class="w-25" src="images/viewProject.png">
                        </a>
                    </div>
                    <div class="card-footer bgs-b text-light">
                        <p class="mr-auto">Created by ${project.creatorId} on ${project.createdDate}.</p>
                    </div>
                </div>
            </c:forEach>
            <div class="card col-3 px-0 py-0 m-3">
                <div class="card-header bgs-b text-light">
                    <p>Add new Project</p>
                </div>
                <div class="card-body bg-dark">
                    <a class="mx-auto text-light btn" href="createProject?submit=NoAdding">
                        <img class="w-25" src="images/addNew.png">
                    </a>
                </div>
                <div class="card-footer bgs-b text-light">
                    <p></p>
                </div>
            </div>
        </div>
    </div>
</body>
</html>