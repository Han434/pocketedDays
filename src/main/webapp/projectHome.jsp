<%@include file="taglib.jsp"%>
<%@include file="head.jsp"%>
<html>
<header>
  <%@include file="projectMenu.jsp"%>
</header>
<body>
    <div class="container mt-3">
    <div class="row" style="height: 10em;">
      <div class="col-7">
        <h2>Project Home</h2>
      </div>
      <div class="col-5 bgs-b text-white" style="border-radius: 0 0 0 20px;">
        <h3>Project Name: ${project.projectName}</h3>
          <h3>Project Password: ${project.projectPassword}</h3>
      </div>
    </div>

    <div class="row">
      <div class="col-12">
        <h3>Project Members</h3>
        <ul>
          <li>${project.projectId}</li>
        </ul>
      </div>
    </div>

    <div class="row">
        <div class="col-12">
            <h3>Project Description</h3>
            <p>${project.projectDescription}</p>
        </div>
    </div>

    <div class="row bgs-b p-2 text-white">
      <h3 class="col-12 m-2">Danger Zone</h3>
        <p>Please becareful with these buttons.</p>
      <div class="col-12">
        <a href="deleteProject" class="btn bg-danger ml-3">Delete Project</a>
          <a href="removeTeamMember?projectId=${project.projectId}" class="btn bg-danger ml-3">Remove a team member</a>
        <a href="editProject?submit=NoEdit" class="btn bg-dark ml-3">Edit Project</a>
      </div>
    </div>
  </div>
  </body>
</html>