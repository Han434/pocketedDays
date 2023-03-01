<%@include file="taglib.jsp"%>
<%@include file="head.jsp"%>
<html>
<header>
    <%@include file="viewMenu.jsp"%>
</header>
<body>
    <div class="container">
        <h2>Row View</h2>
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
            <table class="table table-striped table-dark table-hover">
                <thead>
                <tr>
                    <th scope="col">No</th>
                    <th scope="col">Row Id</th>
                    <th scope="col">Created Date</th>
                    <th scope="col">Row Description</th>
                    <th scope="col">Quantity</th>
                    <th scope="col">Cost Per Item</th>
                    <th scope="col">Expense Type</th>
                    <th scope="col">Tag</th>
                    <th scope="col">View Rows</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="row" items="${rows}" varStatus="status">
                    <tr>
                        <th scope="row">${status.count}</th>
                        <td class="m-3">${row.rowId}</td>
                        <td class="m-3">${row.createdDate}</td>
                        <td class="m-3">${row.rowDescription}</td>
                        <td class="m-3">${row.quantity}</td>
                        <td class="m-3">${row.costPerItem}</td>
                        <td class="m-3">${row.rowType}</td>
                        <td class="m-3">${row.tag}</td>
                        <td class="m-3"><a href="viewRowInDetails?rowId=${row.rowId}">Click</a></td>
                    </tr>
                </c:forEach>
                <tr>
                    <th scope="row"></th>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>
                        <c:choose>
                            <c:when test="${empty rows}">
                                No row is found for this sheet.
                            </c:when>
                            <c:otherwise>
                                Found rows for this sheet are above.
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a class="btn bg-success" href="createRow?submit=Add">Add New Row</a>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>