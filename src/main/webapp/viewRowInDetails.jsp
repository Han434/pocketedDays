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
        <div class="col-6">
            <h3>Sheet Name: ${sheet.sheetDescription}</h3>
        </div>
        <div class="col-6">

        </div>
    </div>
    <div class="row">
        <div class="col-6">
            <h3>Sheet Type: ${sheet.sheetType}</h3>
        </div>
        <div class="col-4">
            <h3>Project Name: ${project.projectName}</h3>
        </div>
        <div class="col-2">
            <a class="btn bg-danger" href="deleteSheet">Delete Sheet</a>
        </div>
    </div>
    <div class="row">
        <div class="col-6">
            <h3>${row.rowDescription}</h3>
        </div>
        <div class="col-6">
            <h3>${row.createdDate}</h3>
        </div>
    </div>
    <div class="row">
        <p class="col-12">${row.quantity}</p>
        <p class="col-12">${row.costPerItem}</p>
        <p class="col-12">${row.rowType}</p>
        <p class="col-12">${row.tag}</p>
    </div>
    <div class="row">
        <a class="btn bg-success" href="deleteRow?rowId=${row.rowId}">Delete Row</a>
        <a class="btn bg-success" href="editRow?submit=Edit&rowId=${row.rowId}">Edit Row</a>
    </div>
</div>
</body>
</html>