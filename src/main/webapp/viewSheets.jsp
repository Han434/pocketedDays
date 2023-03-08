<%@include file="taglib.jsp"%>
<%@include file="head.jsp"%>
<html>
<header>
  <%@include file="viewMenu.jsp"%>
</header>
<body>
  <div class="container">
    <h2>Sheet View</h2>
    <div class="row">
      <div class="col-6">
        <h2>${viewTypeForHeader} View</h2>
      </div>
      <div class="col-6">
        <h3>Project Name: ${project.projectName}</h3>
      </div>
    </div>
    <div class="row">
      <table class="table table-striped table-dark table-hover">
        <thead>
        <tr>
          <th scope="col">No</th>
          <th scope="col">Sheet Id</th>
          <th scope="col">Created Date</th>
          <th scope="col">Sheet Description</th>
          <th scope="col">Organization</th>
          <th scope="col">File Path</th>
          <th scope="col">Total</th>
          <th scope="col">Note</th>
          <th scope="col">Details</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="sheet" items="${sheets}" varStatus="status">
          <tr>
            <th scope="row">${status.count}</th>
            <td>${sheet.sheetId}</td>
            <td>${sheet.updatedDate}</td>
            <td>${sheet.sheetDescription}</td>
            <td>${sheet.organization}</td>
            <td>${sheet.filePath}</td>
            <td>${sheetTotals[sheet.sheetId]}</td>
            <td>${sheet.note}</td>
            <td><a class="text-white btn bg-success" href="viewRow?sheetId=${sheet.sheetId}">View</a></td>
          </tr>
        </c:forEach>
        <tr>
          <th scope="row"></th>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td>Total: ${projectTotal}</td>
          <td>
            <c:choose>
              <c:when test="${empty sheets}">
                No sheet is found for this project.
              </c:when>
              <c:otherwise>
                Found sheets for this project are above.
              </c:otherwise>
            </c:choose>
          </td>
          <td><a class="btn bg-success" href="createSheet?submit=Add New Sheet">Add New Sheet</a></td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</body>
</html>