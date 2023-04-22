<%@include file="head.jsp"%>
<html>
<header>
    <%@include file="menu.jsp"%>
</header>
<body class="bg-dark">
<div class="container text-white bgs-b mt-3 rounded p3">
        <h2>Help Us Improve</h2>
    <form action="helpUsImprove" method="get" class="was-validated">
        <div class="form-group my-3">
            <label for="description">Feedback Description</label>
            <textarea class="form-control" name="feedbackDescription" id="description" placeholder="Please enter something you would like to suggest." rows="3" required></textarea>
            <div class="valid-feedback">Looks good!</div>
            <div class="invalid-feedback">Please fill out this field!</div>
        </div>
        <button type="submit" class="btn btn-primary" name="submit" value="Submit">Submit</button>
    </form>
    </div>
</body>
</html>