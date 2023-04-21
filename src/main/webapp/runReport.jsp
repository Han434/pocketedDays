<%@include file="taglib.jsp"%>
<%@include file="head.jsp"%>
<html>
<header>
    <%@include file="viewMenu.jsp"%>
</header>
<body class="bg-dark">
<div class="container-fluid rounded bgs-b text-white">
    <div class="row pt-0">
        <div class="col-12" style="background-image: url('images/projectBackground.jpg'); background-size: cover; background-repeat: no-repeat; height: 25vh">
            <div class="rounded-bottom text-white p-3 mx-auto">
                <div class="text-center">
                    <h2>Report</h2>
                </div>
                <div class="row mt-3 align-items-end justify-content-end">
                    <div class="col-3">
                        <h3>${project.projectName}</h3>
                        <button class="btn bg-success text-white" id="download-button">Download as PDF</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="invoice">
        <h1>Revenue</h1>
        <table class="table table-bordered table-dark table-hover">
            <thead>
            <tr>
                <th scope="col">No</th>
                <th scope="col">Created Date</th>
                <th scope="col">Row Description</th>
                <th scope="col">Quantity</th>
                <th scope="col">Cost Per Item</th>
                <th scope="col">Total</th>
                <th scope="col">Type</th>
                <th scope="col">Tag</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="listOfRowsForRevenue" items="${listOfListOfRowsForRevenue}">
                <c:forEach var="row" items="${listOfRowsForRevenue}" varStatus="status">
                    <tr>
                        <th scope="row">${status.count}</th>
                        <td class="m-3">${row.updatedDate}</td>
                        <td class="m-3">${row.rowDescription}</td>
                        <td class="m-3">${row.quantity}</td>
                        <td class="m-3">${row.getFormattedCurrency(row.costPerItem)}</td>
                        <td class="m-3">${row.getFormattedCurrency(row.calculateTotal())}</td>
                        <td class="m-3">${row.rowType}</td>
                        <td class="m-3">${row.tag}</td>
                    </tr>
                </c:forEach>
            </c:forEach>
            <tr>
                <th scope="row"></th>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td>Total: ${project.getFormattedCurrency(project.calculateTotalRevenue())}</td>
                <td></td>
                <td></td>
            </tr>
            </tbody>
        </table>

        <h1>Expense</h1>
        <table class="table table-bordered table-dark table-hover">
            <thead>
            <tr>
                <th scope="col">No</th>
                <th scope="col">Created Date</th>
                <th scope="col">Row Description</th>
                <th scope="col">Quantity</th>
                <th scope="col">Cost Per Item</th>
                <th scope="col">Total</th>
                <th scope="col">Type</th>
                <th scope="col">Tag</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="listOfRowsForExpense" items="${listOfListOfRowsForExpense}">
                <c:forEach var="row" items="${listOfRowsForExpense}" varStatus="status">
                    <tr>
                        <th scope="row">${status.count}</th>
                        <td class="m-3">${row.updatedDate}</td>
                        <td class="m-3">${row.rowDescription}</td>
                        <td class="m-3">${row.quantity}</td>
                        <td class="m-3">${row.getFormattedCurrency(row.costPerItem)}</td>
                        <td class="m-3">${row.getFormattedCurrency(row.calculateTotal())}</td>
                        <td class="m-3">${row.rowType}</td>
                        <td class="m-3">${row.tag}</td>
                    </tr>
                </c:forEach>
            </c:forEach>
            <tr>
                <th scope="row"></th>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td>Total: ${project.getFormattedCurrency(project.calculateTotalExpense())}</td>
                <td></td>
                <td></td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</body>
<script>
    const button = document.getElementById('download-button');

    function generatePDF() {
        // Choose the element that your content will be rendered to.
        const element = document.getElementById('invoice');
        // Choose the element and save the PDF for your user.
        html2pdf().from(element).save();
    }

    button.addEventListener('click', generatePDF);
</script>
</html>