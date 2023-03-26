<%@ page import="com.pocketedDays.entity.Row" %>
<%@include file="taglib.jsp"%>
<%@include file="head.jsp"%>
<html>
<header>
    <%@include file="viewMenu.jsp"%>
</header>
<body class="bg-dark">
    <div class="container-fluid bg-dark text-white">
        <div class="row rounded-top" style="background-image: url('images/sheetBackground.jpg'); background-size: cover; background-repeat: no-repeat; height: 25vh">
            <div class="text-center">
                <h2>Row View</h2>
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
                    <button type="button" class="btn btn-danger ml-3 text-dark" data-bs-toggle="modal" data-bs-target="#deleteSheet">
                        Delete Sheet
                    </button>
                    <div class="modal fade text-white" id="deleteSheet">
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
                                        <li class="list-group-item list-group-item-action list-group-item-dark">Sheet Id: ${sheet.sheetId}</li>
                                        <li class="list-group-item list-group-item-action list-group-item-dark">Sheet Description: ${sheet.sheetDescription}</li>
                                        <li class="list-group-item list-group-item-action list-group-item-dark">Organization: ${sheet.organization}</li>
                                        <li class="list-group-item list-group-item-action list-group-item-dark">Sheet Type: ${sheet.sheetType}</li>
                                    </div>
                                    <p class="font-italic">Note. Clicking the button below will delete this sheet permanently.</p>
                                </div>
                                <!-- Modal footer -->
                                <div class="modal-footer bg-dark">
                                    <a class="btn bg-danger" href="deleteSheet">Delete Sheet</a>
                                    <button type="button" class="btn btn-success" data-bs-dismiss="modal">Close</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <table class="table table-striped table-dark table-hover">
                <thead>
                <tr>
                    <th scope="col">No</th>
                    <th scope="col">Created Date</th>
                    <th scope="col">Row Description</th>
                    <th scope="col">Quantity</th>
                    <th scope="col">Cost Per Item</th>
                    <th scope="col">Total</th>
                    <th scope="col">Expense Type</th>
                    <th scope="col">Tag</th>
                    <th scope="col">Details</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="row" items="${rows}" varStatus="status">
                    <tr>
                        <th scope="row">${status.count}</th>
                        <td class="m-3">${row.updatedDate}</td>
                        <td class="m-3">${row.rowDescription}</td>
                        <td class="m-3">${row.quantity}</td>
                        <td class="m-3">${row.getFormattedCurrency(row.costPerItem)}</td>
                        <td class="m-3">${row.getFormattedCurrency(row.calculateTotal())}</td>
                        <td class="m-3">${row.rowType}</td>
                        <td class="m-3">${row.tag}</td>
                        <td class="m-3"><a class="text-white btn bg-success" href="viewRowInDetails?rowId=${row.rowId}">View</a></td>
                    </tr>
                </c:forEach>
                <tr>
                    <th scope="row"></th>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>Total: ${sheet.getFormattedCurrency(sheet.calculateTotal())}</td>
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