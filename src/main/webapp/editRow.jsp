<%@include file="taglib.jsp"%>
<%@include file="head.jsp"%>
<html>
<header>
    <%@include file="projectMenu.jsp"%>
</header>
<body class="bg-dark">
<div class="container mt-3 p-3 bgs-b rounded text-white">
    <div class="form-box">
        <h2 class="text-center">Edit Row</h2>
        <form action="editRow" method="post" class="was-validated">
            <div class="form-group my-3">
                <label for="rowName">Row Description:</label>
                <input pattern=".*" type="text" value="${row.rowDescription}" class="form-control" id="rowName" name="rowDescription" placeholder="${row.rowDescription}">
                <div class="valid-feedback">Looks good!</div>
                <div class="invalid-feedback">Please fill out this field!</div>
            </div>
            <div class="row my-3">
                <div class="col">
                    <div class="form-group">
                        <label for="quantity">Quantity</label>
                        <input pattern="(?!-\d*$).*" value="${row.quantity}" class="form-control" name="quantity" id="quantity" placeholder="${row.quantity}">
                        <div class="valid-feedback">Looks good!</div>
                        <div class="invalid-feedback">Please do not enter negative numbers!</div>
                    </div>
                </div>
                <div class="col">
                    <div class="form-group">
                        <label for="costPerItem">Cost per item:</label>
                        <input pattern="(?!-\d*$).*" value="${row.costPerItem}" class="form-control" name="costPerItem" id="costPerItem" placeholder="${row.costPerItem}">
                        <div class="valid-feedback">Looks good!</div>
                        <div class="invalid-feedback">Please do not enter negative numbers!</div>
                    </div>
                </div>
            </div>
            <div class="row my-3">
                <div class="col">
                    <div class="my-3">
                        <label class="mr-sm-2" for="inlineFormCustomSelect">Type</label>
                        <select name="rowType" class="form-select mr-sm-2" id="inlineFormCustomSelect">
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
                        <input pattern=".*" value="${row.tag}" type="text" class="form-control" name="tag" id="tag" placeholder="${row.tag}">
                        <div class="valid-feedback">Looks good!</div>
                        <div class="invalid-feedback">Please fill out this field.</div>
                    </div>
                </div>
            </div>
            <div class="form-group my-3 d-none">
                <label for="name">Row Id:</label>
                <input type="text" value="${row.rowId}" class="form-control" id="name" name="rowId" aria-describedby="emailHelp" placeholder="${row.rowId}">
            </div>
            <div class="form-group my-3">
                <input class="btn bg-success text-white mx-3" type="submit" name="submit" value="Edit"/>
                <a class="btn bg-dark text-white mx-3" href="viewRow?sheetId=${sheetId}">Cancle</a>
            </div>
        </form>
    </div>
</div>
</body>
<%@include file="footer.jsp"%>
</html>