<%@include file="taglib.jsp"%>
<%@include file="head.jsp"%>
<html>
<header>
  <%@include file="projectMenu.jsp"%>
</header>
<div class="container bg-secondary">
  <body>

  <div class="container-fluid">
    <h2>Project Home</h2>
    <div class="row">
      <h3>${project.projectName}</h3>
    </div>
    <div class="row">
      <h3>Project Members</h3>
      <ul>
          <li>${project.projectId}</li>
      </ul>
    </div>
    <div class="row">
      <h3>Project Description</h3>
      <p>${project.projectDescription}</p>
    </div>
    <div class="row">
      <h3>Danger Zone</h3>
      <a href="deleteProject?projectId=${project.projectId}">Delete Project</a>
      <a href="editProject?projectId=${project.projectId}&submit=NoEdit">Edit Project</a>
      <a href="removeTeamMember?projectId=${project.projectId}">Remove a team member</a>
    </div>
  </div>

  </body>
</div>
</html>