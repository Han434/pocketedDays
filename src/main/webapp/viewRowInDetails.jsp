<%@include file="taglib.jsp"%>
<%@include file="head.jsp"%>
<html>
<header>
    <%@include file="viewMenu.jsp"%>
</header>
<body class="bg-dark">
<div class="container mt-3 p-3 bgs-b rounded text-white">
    <div class="row rounded-top">
        <div class="text-center">
            <h2>Row View In Details</h2>
        </div>
        <div class="row">
            <div class="col-6">
                <h3>Project Name: ${project.projectName}</h3>
            </div>
            <div class="col-6">
                <h3>Sheet Name: ${sheet.sheetDescription}</h3>
            </div>
        </div>
        <div class="row">
            <div class="col-6">
                <h3>Sheet Type: ${sheet.sheetType}</h3>
            </div>
            <div class="col-6">

            </div>
        </div>
    </div>
    <div class="row">
        <table class="table table-striped table-dark table-hover">
            <thead>
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Value</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th scope="row">Row Description</th>
                <td class="m-3">${row.rowDescription}</td>
            </tr>
            <tr>
                <th scope="col">Created Date</th>
                <td class="m-3">${row.updatedDate}</td>
            </tr>
            <tr>
                <th scope="col">Quantity</th>
                <td class="m-3">${row.quantity}</td>
            </tr>
            <tr>
                <th scope="col">Cost Per Item</th>
                <td class="m-3">${row.getFormattedCurrency(row.costPerItem)}</td>
            </tr>
            <tr>
                <th scope="col">Total</th>
                <td class="m-3">${row.getFormattedCurrency(row.calculateTotal())}</td>
            </tr>
            <tr>
                <th scope="col">Row Type</th>
                <td class="m-3">${row.rowType}</td>
            </tr>
            <tr>
                <th scope="col">Tag</th>
                <td class="m-3">${row.tag}</td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="row">
        <button type="button" class="btn btn-danger ml-3 text-dark col-3" data-bs-toggle="modal" data-bs-target="#deleteRow">
            Delete Row
        </button>
        <div class="modal fade text-white" id="deleteRow">
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
                            <li class="list-group-item list-group-item-action list-group-item-dark">Row Id: ${row.rowId}</li>
                            <li class="list-group-item list-group-item-action list-group-item-dark">Row Description: ${row.rowDescription}</li>
                            <li class="list-group-item list-group-item-action list-group-item-dark">Quantity: ${row.quantity}</li>
                            <li class="list-group-item list-group-item-action list-group-item-dark">Cost Per Item: ${row.costPerItem}</li>
                            <li class="list-group-item list-group-item-action list-group-item-dark">Row Type: ${row.rowType}</li>
                            <li class="list-group-item list-group-item-action list-group-item-dark">Tag: ${row.tag}</li>
                        </div>
                        <p class="font-italic">Note. Clicking the button below will delete this row permanently.</p>
                    </div>
                    <!-- Modal footer -->
                    <div class="modal-footer bg-dark">
                        <a class="btn bg-danger col-3 mx-3" href="deleteRow?rowId=${row.rowId}">Delete Row</a>
                        <button type="button" class="btn btn-success" data-bs-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <a class="btn bg-success col-3 mx-3" href="editRow?submit=EditRow&rowId=${row.rowId}">Edit Row</a>
        <a class="text-white btn bg-secondary col-3 mx-3" href="viewRow?sheetId=${sheetId}">Go Back</a>
    </div>
</div>
</body>
</html>