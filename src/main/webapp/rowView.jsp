<%@include file="taglib.jsp"%>
<%@include file="head.jsp"%>
<html>
<header>
    <%@include file="viewMenu.jsp"%>
</header>
<div class="container bg-secondary">
    <body>

    <div class="container-fluid">
        <h2>Row View</h2>
        <div class="row">
            <table class="border border-success" style="background-color:darkseagreen;" width: 100%>
                <tr style="border-bottom:2px solid; background-color:darkslategrey; color: antiquewhite;">
                    <td class="m-3">Row Id</td>
                    <td class="m-3">Sheet Id</td>
                    <td class="m-3">Row Creator Id</td>
                    <td class="m-3">Row Description</td>
                    <td class="m-3">Quantity</td>
                    <td class="m-3">Cost Per Item</td>
                    <td class="m-3">Expense Type</td>
                    <td class="m-3">Tag</td>
                    <td class="m-3">View Rows</td>
                </tr>
                <c:forEach var="row" items="${rows}">
                    <tr class="border-bottom">
                        <td class="m-3">${row.rowId}</td>
                        <td class="m-3">${row.sheetId}</td>
                        <td class="m-3">${row.createdDate}</td>
                        <td class="m-3">${row.rowDescription}</td>
                        <td class="m-3">${row.quantity}</td>
                        <td class="m-3">${row.costPerItem}</td>
                        <td class="m-3">${row.type}</td>
                        <td class="m-3">${row.tag}</td>
                        <td class="m-3"><a href="viewRow?sheetId=${row.sheetId}">Click</a></td>
                    </tr>
                </c:forEach>
            </table>
            <form action="createRow">
                <input class="btn bg-primary" type="submit" name="submit" value="Add" />
            </form>
        </div>
    </div>

    </body>
</div>
</html>