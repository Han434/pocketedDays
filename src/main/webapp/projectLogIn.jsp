<%@include file="taglib.jsp"%>
<%@include file="head.jsp"%>
<html>
<header>
  <%@include file="menu.jsp"%>
</header>
<body class="bg-dark">
<div class="container mt-3 p-3 bgs-b rounded text-white">
  <div class="form-box">
    <h2 class="text-center">Log In to Project</h2>
    <form action="projectLogIn" method="post" class="was-validated">
      <div class="row my-3">
        <div class="col">
          <div class="form-group">
            <label for="projectId">Project Id:</label>
            <input pattern=".*" type="number" class="form-control" id="projectId" name="projectId" placeholder="Enter Project Id" required>
            <div class="valid-feedback">Looks good!</div>
            <div class="invalid-feedback">Please fill out this field!</div>
          </div>
        </div>
        <div class="col">
          <div class="form-group">
            <label for="projectPassword">Project Password</label>
            <input minlength="8" type="password" class="form-control" name="projectPassword" id="projectPassword" placeholder="Enter Password" required>
            <div class="valid-feedback">Looks good!</div>
            <div class="invalid-feedback">Password must be at least 8 characters!</div>
          </div>
        </div>
      </div>
      <div class="form-group my-3">
        <input class="btn bg-success text-white mx-3" type="submit" name="submit" value="Log In to the Project" />
        <a href="workspace" class="btn bg-secondary text-white addHover mx-3">Cancle</a>
      </div>
    </form>
  </div>
</div>
</body>
<%@include file="footer.jsp"%>
</html>