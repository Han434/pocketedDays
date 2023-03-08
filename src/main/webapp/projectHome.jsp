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
          <button type="button" class="btn btn-danger ml-3 text-dark" data-bs-toggle="modal" data-bs-target="#deleteProject">
              Delete Project
          </button>
          <div class="modal fade" id="deleteProject">
              <div class="modal-dialog modal-dialog-centered">
                  <div class="modal-content">
                      <!-- Modal Header -->
                      <div class="modal-header bg-dark">
                          <h4 class="modal-title">Warning!</h4>
                          <button type="button" class="btn-close bg-light" data-bs-dismiss="modal"></button>
                      </div>
                      <!-- Modal body -->
                      <div class="modal-body bgs-b">
                          <h5>Review</h5>
                          <div class="list-group bg-dark">
                              <li class="list-group-item list-group-item-action list-group-item-dark">Project Id: ${project.projectId}</li>
                              <li class="list-group-item list-group-item-action list-group-item-dark">Project Name: ${project.projectName}</li>
                              <li class="list-group-item list-group-item-action list-group-item-dark">Project Password: ${project.projectPassword}</li>
                          </div>
                          <p class="font-italic">Note. Clicking the button below will delete this project permanently.</p>
                      </div>
                      <!-- Modal footer -->
                      <div class="modal-footer bg-dark">
                          <a href="deleteProject" class="btn bg-danger mr-3">Delete Project</a>
                          <button type="button" class="btn btn-success" data-bs-dismiss="modal">Close</button>
                      </div>
                  </div>
              </div>
          </div>
          <a href="removeTeamMember?projectId=${project.projectId}" class="btn bg-danger ml-3">Remove a team member</a>
        <a href="editProject?submit=NoEdit" class="btn bg-primary ml-3">Edit Project</a>
      </div>
    </div>
  </div>
  </body>
</html>