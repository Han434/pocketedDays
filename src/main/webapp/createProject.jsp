<%@include file="taglib.jsp"%>
<%@include file="head.jsp"%>
<html>
<header>
  <%@include file="menu.jsp"%>
</header>
<body>
  <div class="container mt-3">
    <div class="form-box">
      <h2 class="text-center">Create Project</h2>
      <form action="createProject" method="post">
        <div class="row my-3">
          <div class="col">
            <div class="form-group">
              <label for="name">Project Name:</label>
              <input type="text" class="form-control" id="name" name="projectName" aria-describedby="emailHelp" placeholder="Enter Project Name" required>
            </div>
          </div>
          <div class="col">
            <div class="form-group">
              <label for="exampleInputPassword1">Project Password</label>
              <input type="password" class="form-control" name="projectPassword" id="exampleInputPassword1" placeholder="Password" required>
            </div>
          </div>
        </div>
        <div class="form-group my-3">
          <label for="description">Project Description</label>
          <textarea class="form-control" name="projectDescription" id="description" placeholder="This project is for...." rows="3" required></textarea>
        </div>
        <div class="form-group my-3">
          <input class="btn bg-success text-white mx-3" type="submit" name="submit" value="Add New Project" />
          <a href="workspace" class="btn bg-dark text-white addHover mx-3">Cancle</a>
        </div>
      </form>
    </div>
  </div>
</body>
</html>