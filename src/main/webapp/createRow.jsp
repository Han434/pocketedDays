<%@include file="taglib.jsp"%>
<%@include file="head.jsp"%>
<html>
<header>
  <%@include file="viewMenu.jsp"%>
</header>
<body class="bg-dark">
<div class="container mt-3 p-3 bgs-b rounded text-white">
    <h2 class="text-center">Create Row</h2>
    <form action="createRow" method="post" class="was-validated">
      <div class="form-row">
        <div class="form-group my-3">
          <label for="rowName">Row Description:</label>
          <input pattern=".*" type="text" class="form-control" id="rowName" name="rowDescription" placeholder="Enter Row Description" required>
          <div class="valid-feedback">Looks good!</div>
          <div class="invalid-feedback">Please fill out this field!</div>
        </div>
      </div>
      <div class="row my-3">
        <div class="col">
          <div class="form-group">
            <label for="quantity">Quantity</label>
            <input pattern="(?!-\d*$).*" class="form-control" name="quantity" id="quantity" placeholder="123" required>
            <div class="valid-feedback">Looks good!</div>
            <div class="invalid-feedback">Please do not enter negative numbers!</div>
          </div>
        </div>
        <div class="col">
          <div class="form-group">
            <label for="costPerItem">Cost Per Item</label>
            <input pattern="(?!-\d*$).*" class="form-control" name="costPerItem" id="costPerItem" placeholder="123" required>
            <div class="valid-feedback">Looks good!</div>
            <div class="invalid-feedback">Please do not enter negative numbers!</div>
          </div>
        </div>
      </div>
      <div class="row my-3">
        <div class="col">
          <div class="my-3">
            <label class="mr-sm-2" for="inlineFormCustomSelect">Type</label>
            <select class="form-select mr-sm-2" id="inlineFormCustomSelect" name="rowType" required>
              <option selected value="Service">Service</option>
              <option value="Product">Product</option>
              <option value="Donation">Donation</option>
            </select>
            <div class="valid-feedback">Looks good!</div>
            <div class="invalid-feedback">Please select something!</div>
          </div>
        </div>
        <div class="col">
          <div class="form-group my-3">
            <label for="tag">Tag</label>
            <input pattern=".*" type="text" class="form-control" name="tag" id="tag" placeholder="abc" required>
            <div class="valid-feedback">Looks good!</div>
            <div class="invalid-feedback">Please fill out this field.</div>
          </div>
        </div>
      </div>
      <div class="my-3">
        <input class="btn bg-success" type="submit" name="submit" value="Create" />
        <a class="text-white btn bg-secondary" href="viewRow?sheetId=${sheetId}">Cancle</a>
      </div>
    </form>
    </div>
  </body>
</html>