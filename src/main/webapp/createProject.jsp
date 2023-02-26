<%@include file="taglib.jsp"%>
<%@include file="head.jsp"%>
<html>
<header>
  <%@include file="projectMenu.jsp"%>
</header>
<div class="container bg-secondary">
  <body>

  <div class="container-fluid">
    <h2>Create Project</h2>
    <form action="createProject">
      <div class="form-group">
        <label for="name">Project Name:</label>
        <input type="text" class="form-control" id="name" name="projectName" aria-describedby="emailHelp" placeholder="Enter Project Name">
      </div>
      <div class="form-group">
        <label for="exampleInputPassword1">Project Password</label>
        <input type="password" class="form-control" name="projectPassword" id="exampleInputPassword1" placeholder="Password">
      </div>
      <div class="form-group">
        <label for="creatorId">Creator Id</label>
        <input type="number" class="form-control" name="creatorId" id="creatorId" placeholder="123">
      </div>
      <div class="form-group">
        <label for="description">Project Description</label>
        <textarea class="form-control" name="projectDescription" id="description" placeholder="This project is for...." rows="3"></textarea>
      </div>
      <input class="btn bg-primary" type="submit" name="submit" value="Add New" />
    </form>
  </body>
</div>
</html>