<%@include file="taglib.jsp"%>
<%@include file="head.jsp"%>
<html>
<header>
    <%@include file="viewMenu.jsp"%>
</header>
<body>
    <div class="container">
        <h2>Create Sheet</h2>
        <form action="createSheet" enctype='multipart/form-data' method="post">
            <div class="row my-3">
                <div class="col">
                    <div class="form-group">
                        <label for="name">Sheet Description:</label>
                        <input type="text" class="form-control" id="name" name="sheetDescription" aria-describedby="emailHelp" placeholder="Enter Sheet Description" required>
                    </div>
                </div>
                <div class="col">
                    <div class="form-group">
                        <label for="organization">Organization</label>
                        <input type="text" class="form-control" name="organization" id="organization" placeholder="Organization" required>
                    </div>
                </div>
            </div>
            <div class="my-3">
                <label class="custom-label" for="customFile">File Path</label>
                <input type="file" class="form-control" id="customFile" name="filePath" placeholder="abc/de.png" required>
            </div>
            <div class="form-group my-3">
                <label for="note">Note</label>
                <textarea class="form-control" name="note" id="note" placeholder="This sheet is for...." rows="3" required></textarea>
            </div>
            <div class="form-group d-none">
                <label for="sheetType">Sheet Type</label>
                <input type="text" value="${sheetType}" class="form-control" name="sheetType" id="sheetType" placeholder="Expense">
            </div>
            <div class="my-3">
                <input class="btn bg-success text-white mx-3" type="submit" name="submit" value="Add New" />
                <a href="sheet?sheetType=Revenue" class="btn bg-dark text-white mx-3">Cancle</a>
            </div>
        </form>
    </div>
    </body>
</html>