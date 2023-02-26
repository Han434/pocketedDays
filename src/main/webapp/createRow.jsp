<%@include file="taglib.jsp"%>
<%@include file="head.jsp"%>
<html>
<header>
  <%@include file="viewMenu.jsp"%>
</header>
<div class="container bg-secondary">
  <body>

  <div class="container-fluid">
    <h2>Create Row</h2>
    <form action="createRow">
      <div class="form-group">
        <label for="name">Row Description:</label>
        <input type="text" class="form-control" id="name" name="rowDescription" aria-describedby="emailHelp" placeholder="Enter Row Description">
      </div>
      <div class="form-group">
        <label for="sheetId">Sheet Id</label>
        <input type="number" class="form-control" name="sheetId" id="sheetId" placeholder="123">
      </div>
      <div class="form-group">
        <label for="rowCreatorId">Row Creator Id</label>
        <input type="number" class="form-control" name="rowCreatorId" id="rowCreatorId" placeholder="123">
      </div>
      <div class="form-group">
        <label for="quantity">Quantity</label>
        <input type="number" class="form-control" name="quantity" id="quantity" placeholder="123">
      </div>
      <div class="form-group">
        <label for="costPerItem">Cost Per Item</label>
        <input type="number" class="form-control" name="costPerItem" id="costPerItem" placeholder="123">
      </div>
      <div class="form-group">
        <label for="type">Type</label>
        <input type="text" class="form-control" name="type" id="type" placeholder="abc">
      </div>
      <div class="form-group">
        <label for="tag">Tag</label>
        <input type="text" class="form-control" name="tag" id="tag" placeholder="abc">
      </div>
      <input class="btn bg-primary" type="submit" name="submit" value="Add New" />
    </form>
  </div>
  </body>

</div>
</html>