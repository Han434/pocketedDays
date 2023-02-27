<%@include file="taglib.jsp"%>
<%@include file="head.jsp"%>
<html>
<header>
    <%@include file="viewMenu.jsp"%>
</header>
<body>
    <div class="container bg-secondary">
        <h2>Create Sheet</h2>
        <form action="createSheet">
            <div class="form-group">
                <label for="name">Sheet Description:</label>
                <input type="text" class="form-control" id="name" name="sheetDescription" aria-describedby="emailHelp" placeholder="Enter Sheet Description">
            </div>
            <div class="form-group">
                <label for="organization">Organization</label>
                <input type="text" class="form-control" name="organization" id="organization" placeholder="Organization">
            </div>
            <div class="form-group">
                <label for="filePath">File Path</label>
                <input type="text" class="form-control" name="filePath" id="filePath" placeholder="abc/de.txt">
            </div>
            <div class="form-group">
                <label for="note">Note</label>
                <textarea class="form-control" name="note" id="note" placeholder="This sheet is for...." rows="3"></textarea>
            </div>
            <div class="form-group d-none">
                <label for="sheetType">Sheet Type</label>
                <input type="text" value="${sheetType}" class="form-control" name="sheetType" id="sheetType" placeholder="Expense">
            </div>
            <input class="btn bg-success text-white" type="submit" name="submit" value="Add New Sheet" />
            <a href="workspace" class="btn bg-dark text-white">Cancle</a>
        </form>
    </div>
    </body>
</html>