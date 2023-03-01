<%@include file="taglib.jsp"%>
<%@include file="head.jsp"%>
<html>
<header>
    <%@include file="projectMenu.jsp"%>
</header>
<body>
<div class="container mt-3">
    <div class="form-box">
        <h2 class="text-center">Edit Row</h2>
        <form action="editRow">
            <div class="form-group my-3">
                <label for="name">Row Description:</label>
                <input type="text" value="${row.rowDescription}" class="form-control" id="name" name="rowDescription" aria-describedby="emailHelp" placeholder="${row.rowDescription}">
            </div>
            <div class="row my-3">
                <div class="col">
                    <div class="form-group">
                        <label for="exampleInputPassword1">Quantity</label>
                        <input type="number" value="${row.quantity}" class="form-control" name="quantity" id="exampleInputPassword1" placeholder="${row.quantity}">
                    </div>
                </div>
                <div class="col">
                    <div class="form-group">
                        <label for="exampleInputPassword2">Cost per item:</label>
                        <input type="number" value="${row.costPerItem}" class="form-control" name="costPerItem" id="exampleInputPassword2" placeholder="${row.costPerItem}">
                    </div>
                </div>
            </div>
            <div class="row my-3">
                <div class="col">
                    <div class="my-3">
                        <label class="mr-sm-2" for="inlineFormCustomSelect">Type</label>
                        <select value="${row.rowType}" class="custom-select mr-sm-2" id="inlineFormCustomSelect">
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
                        <input value="${row.tag}" type="text" class="form-control" name="tag" id="tag" placeholder="${row.tag}">
                    </div>
                </div>
            </div>
            <div class="form-group my-3 d-none">
                <label for="name">Row Id:</label>
                <input type="text" value="${row.rowId}" class="form-control" id="name" name="rowId" aria-describedby="emailHelp" placeholder="${row.rowId}">
            </div>
            <div class="form-group my-3">
                <input class="btn bg-success text-white mx-3" type="submit" name="submit" value="Edit Row"/>
                <a class="btn bg-dark text-white mx-3" href="viewRow?sheetId=${sheetId}">Cancle</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>