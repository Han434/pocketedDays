<%@include file="taglib.jsp"%>
<%@include file="head.jsp"%>
<html>
<header>
  <%@include file="viewMenu.jsp"%>
</header>
<div class="container bg-secondary">
  <body>

  <div class="container-fluid">
    <h2>Sheet View</h2>
    <div class="row">
      <h3>${sheetType}</h3>
    </div>
    <div class="row">
      <table class="border border-success" style="background-color:darkseagreen;" width: 100%>
        <tr style="border-bottom:2px solid; background-color:darkslategrey; color: antiquewhite;">
          <td class="m-3">Sheet Id</td>
          <td class="m-3">Project Id</td>
          <td class="m-3">Sheet Description</td>
          <td class="m-3">Created Date</td>
          <td class="m-3">Organization</td>
          <td class="m-3">File Path</td>
          <td class="m-3">Note</td>
          <td class="m-3">View Rows</td>
        </tr>
        <c:forEach var="sheet" items="${sheets}">
          <tr class="border-bottom">
            <td class="m-3">${sheet.sheetId}</td>
            <td class="m-3">${sheet.projectId}</td>
            <td class="m-3">${sheet.sheetDescription}</td>
            <td class="m-3">${sheet.createdDate}</td>
            <td class="m-3">${sheet.organization}</td>
            <td class="m-3">${sheet.filePath}</td>
            <td class="m-3">${sheet.note}</td>
            <td class="m-3"><a href="viewRow?sheetId=${sheet.sheetId}">Click</a></td>
          </tr>
        </c:forEach>
      </table>
      <form action="createSheet">
        <input class="btn bg-primary" type="submit" name="submit" value="Add" />
      </form>
    </div>
  </div>

  </body>
</div>
</html>