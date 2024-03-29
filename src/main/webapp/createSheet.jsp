<%@include file="taglib.jsp"%>
<%@include file="head.jsp"%>
<html>
<header>
    <%@include file="viewMenu.jsp"%>
</header>
<body class="bg-dark">
    <div class="container mt-3 p-3 bgs-b rounded text-white">
        <h2 class="text-center">Create Sheet</h2>
        <form action="createSheet" enctype='multipart/form-data' method="post" class="was-validated">
            <div class="row my-3">
                <div class="col">
                    <div class="form-group">
                        <label for="sheetName">Sheet Description:</label>
                        <input pattern=".*" type="text" class="form-control" id="sheetName" name="sheetDescription" placeholder="Enter Sheet Description" required>
                        <div class="valid-feedback">Looks good!</div>
                        <div class="invalid-feedback">Please fill out this field!</div>
                    </div>
                </div>
                <div class="col">
                    <div class="form-group">
                        <label for="organization">Organization</label>
                        <input pattern=".*" type="text" class="form-control" name="organization" id="organization" placeholder="Enter Organization" required>
                        <div class="valid-feedback">Looks good!</div>
                        <div class="invalid-feedback">Please fill out this field!</div>
                    </div>
                </div>
            </div>
            <div class="my-3">
                <label class="custom-label" for="customFile">File Path</label>
                <input type="file" class="form-control" id="customFile" name="filePath" required>
                <div class="valid-feedback">Looks good!</div>
                <div class="invalid-feedback">Certain file type</div>
            </div>
            <div class="form-group my-3">
                <label for="note">Note</label>
                <textarea class="form-control" name="note" id="note" placeholder="Something you want to write down related to the sheet." rows="3" required></textarea>
                <div class="valid-feedback">Looks good!</div>
                <div class="invalid-feedback">Please fill out this field!</div>
            </div>
            <div class="form-group d-none">
                <label for="sheetType">Sheet Type</label>
                <input type="text" value="${sheetType}" class="form-control" name="sheetType" id="sheetType">
            </div>
            <div class="my-3">
                <input class="btn bg-success text-white mx-3" type="submit" name="submit" value="Create" />
                <a href="sheet?sheetType=Revenue" class="btn bg-dark text-white mx-3">Cancle</a>
            </div>
        </form>
    </div>
    </body>
<%@include file="footer.jsp"%>
</html>