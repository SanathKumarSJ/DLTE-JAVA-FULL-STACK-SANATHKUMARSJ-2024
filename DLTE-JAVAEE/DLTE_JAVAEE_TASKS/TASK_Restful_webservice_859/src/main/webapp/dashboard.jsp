<%--
  Created by IntelliJ IDEA.
  User: xxsjjjjs
  Date: 4/24/2024
  Time: 3:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Account</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body>
    <% response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
    if(session.getAttribute("username")!=null){ %>
    <nav class="navbar navbar-expand-lg" style="background:linear-gradient(70deg,Blue,Yellow);">
        <div class="container-fluid">
            <a class="navbar-brand text-danger display-6 text-uppercase" style="font-weight: bolder ; "  href="#">MyBank</a>

            <a class="navbar-toggler bg-light" type="a" data-bs-toggle="collapse" data-bs-target="#myBankMenu">

                <span class="navbar-toggler-icon"></span>
            </a>

            <div class="collapse navbar-collapse" id="myBankMenu">
                <ul class="navbar-nav ms-auto mb-1 ml-1 mt-6 mb-lg-0">
                    <li class="nav-item">
                        <p class="text-primary display-7">Hi,<%=session.getAttribute("username")%></p>
                    </li>
                    <li class="nav-item">
                        <a href="view" class="btn btn-outline-light rounded-5 mb-1 me-2"><span class="bi bi-list-columns"></span> View</a>
                    </li>
                    <li class="nav-item">
                        <a href="addAccount.jsp" class="btn btn-outline-light rounded-5 mb-1 me-2"><span class="bi bi-cloud-plus-fill"></span> Add New</a>
                    </li>
                    <li class="nav-item">
                        <a href="logout" class="btn btn-outline-light rounded-5 mb-1 me-2"><span class="bi bi-door-open"></span> Logout</a>
                    </li>
                    <li>
                        <form action="viewByUsername" method="get" class="mb-1">
                            <input type="text" placeholder="view by name" name="name" />
                            <input type="submit" value="view">
                        </form>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <% }
    else {
        response.sendRedirect("index.jsp");
    }%>
</body>
</html>
