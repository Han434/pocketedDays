<%@include file="taglib.jsp"%>
<%@include file="head.jsp"%>
<html>
<header>
    <%@include file="viewMenu.jsp"%>
</header>
<body>
<div class="container">
    <h2>Row View In Details</h2>
    <div class="row">
        <div class="col-12">
            <h3>Sheet Name: ${sheet.sheetDescription}</h3>
        </div>
    </div>
    <div class="row">
        <div class="col-6">
            <h3>Sheet Type: ${sheet.sheetType}</h3>
        </div>
        <div class="col-6">
            <h3>Project Name: ${project.projectName}</h3>
        </div>
    </div>
    <div class="row">
        <div class="col-6">
            <p>Row Description</p>
        </div>
        <div class="col-6">
            <p>${row.rowDescription}</p>
        </div>
    </div>
    <div class="row">
        <div class="col-6">
            <p>Created Date</p>
        </div>
        <div class="col-6">
            <p>${row.updatedDate}</p>
        </div>
    </div>
    <div class="row">
        <div class="col-6">
            <p>Quantity:</p>
        </div>
        <div class="col-6">
            <p>${row.quantity}</p>
        </div>
    </div>
    <div class="row">
        <div class="col-6">
            <p>Cost Per Item:</p>
        </div>
        <div class="col-6">
            <p>${row.costPerItem}</p>
        </div>
    </div>
    <div class="row">
        <div class="col-6">
            <p>Row Type:</p>
        </div>
        <div class="col-6">
            <p>${row.rowType}</p>
        </div>
    </div>
    <div class="row">
        <div class="col-6">
            <p> Tag:</p>
        </div>
        <div class="col-6">
            <p>${row.tag}</p>
        </div>
    </div>
    <div class="row">
        <a class="btn bg-success col-3 mx-3" href="deleteRow?rowId=${row.rowId}">Delete Row</a>
        <a class="btn bg-success col-3 mx-3" href="editRow?submit=Edit&rowId=${row.rowId}">Edit Row</a>
        <a class="text-white btn bg-secondary col-3 mx-3" href="viewRow?sheetId=${sheetId}">Go Back</a>
    </div>
</div>
</body>
</html>