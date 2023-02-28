<%@include file="taglib.jsp"%>
<%@include file="head.jsp"%>
<html>
<header>
  <%@include file="viewMenu.jsp"%>
</header>
  <body>
    <div class="container bg-secondary">
    <h2>Create Row</h2>
    <form action="createRow">
      <div class="form-row">
        <div class="form-group my-3">
          <label for="name">Row Description:</label>
          <input type="text" class="form-control" id="name" name="rowDescription" aria-describedby="emailHelp" placeholder="Enter Row Description">
        </div>
      </div>
      <div class="row my-3">
        <div class="col">
          <div class="form-group">
            <label for="quantity">Quantity</label>
            <input type="number" class="form-control" name="quantity" id="quantity" placeholder="123">
          </div>
        </div>
        <div class="col">
          <div class="form-group">
            <label for="costPerItem">Cost Per Item</label>
            <input type="number" class="form-control" name="costPerItem" id="costPerItem" placeholder="123">
          </div>
        </div>
      </div>
      <div class="row my-3">
        <div class="col">
          <div class="my-3">
            <label class="mr-sm-2" for="inlineFormCustomSelect">Type</label>
            <select class="custom-select mr-sm-2" id="inlineFormCustomSelect">
              <option selected>Choose...</option>
              <option value="service">Service</option>
              <option value="product">Product</option>
              <option value="donation">Donation</option>
            </select>
          </div>
        </div>
        <div class="col">
          <div class="form-group my-3">
            <label for="tag">Tag</label>
            <input type="text" class="form-control" name="tag" id="tag" placeholder="abc">
          </div>
        </div>
      </div>
      <div class="my-3">
        <input class="btn bg-primary" type="submit" name="submit" value="Add New" />
      </div>
    </form>
    </div>
  </body>
</html>