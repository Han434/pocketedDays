<%@include file="taglib.jsp"%>
<%@include file="head.jsp"%>
<html>
    <header>
        <%@include file="menu.jsp"%>
    </header>
    <body class="bg-dark">
        <div class="container text-white bgs-b mt-3 rounded p5">
            <div class="row p-3">
                <h2 class="text-center">
                    Welcome
                </h2>
            </div>
            <div class="row mt-3 p-3">
                <div class="col-5">
                    <h3>What is "Pocketed Days"?</h3>
                    <p>
                        Pocketed Days is a web application that is created to help business or individual to help with task based structure. At the moment the application can only keep track the financial records. But more are on the way. The application is created with the foundation which is a backbone to hold more concept and services.
                    </p>
                </div>
                <div class="col-7">
                    <img class="rounded" src="images/welcomeBackground.jpg" width="100%">
                </div>
            </div>
            <div class="row p-3">
                <div class="col-7">
                    <h3>Why created "Pocketed Days"?</h3>
                    <p>
                        I decided to develop this application while using Notion. What I want is I want to creat productivity software like notion as the chance is given by the class. However, I believe my limited knowledge and experience is something that is holding me to achieve that. That is the reason why I developed this application. Which at the moment, I can work on idea that I have and add to this application. I hope you find helpful using this.
                    </p>
                </div>
                <div class="col-5">
                    <h2>Extra Links</h2>
                    <div class="m-2">
                        <a class="btn rounded bg-success" target="_blank" href="report/rows/">Our Web Service</a>
                    </div>
                    <div class="m-2">
                        <a class="btn rounded bg-success" target="_blank" href="https://github.com/Han434/pocketedDays">View code on GitHub</a>
                    </div>
                    <div class="m-2">
                        <a class="btn rounded bg-success" target="_blank" href="http://paulawaite.com/education/java113/IndividualProject.html">Check Out the Instruction</a>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <c:choose>
        <c:when test="${not empty quoteDao}">
            <%@include file="footer.jsp"%>
        </c:when>
        <c:otherwise>
            <footer class="text-center bgs-b mt-3 p-2 text-white">
            </footer>
        </c:otherwise>
    </c:choose>
</html>