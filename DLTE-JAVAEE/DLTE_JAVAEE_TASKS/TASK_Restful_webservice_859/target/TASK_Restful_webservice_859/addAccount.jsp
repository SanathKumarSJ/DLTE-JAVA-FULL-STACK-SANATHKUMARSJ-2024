<%--
  Created by IntelliJ IDEA.
  User: xxsjjjjs
  Date: 4/23/2024
  Time: 5:45 PM
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


<%
    String info = (String)request.getAttribute("info");
    String error = (String)request.getAttribute("error");
%>
<div class="container">
    <div class="row justify-content-center" style="height: 100vh">
        <% if(info!=null&&info!=""){ %>
        <h1 class="text-center text-success"><%=info%></h1>
        <%}%>
        <% if(error!=null&&error!=""){ %>
        <h1 class="text-center text-danger"><%=error%></h1>
        <%}%>
        <form action="add" method="post" class="col-lg-4 col-md-8 col-12 p-5 rounded-5 shadow-lg align-self-center">
            <div class="form group">
                <label >UserName</label>
                <input type="text" name="name" placeholder="Username" class="form-control" />
            </div>
            <div class="row justify-content-between">
                <div class="col-lg-6 col-12">
                    <label >Password</label>
                    <input type="password" name="password" placeholder="Password" class="form-control" />
                </div>
            <div class="row justify-content-between">
                <div class="col-lg-6 col-12">
                    <label >Email</label>
                    <input type="text" name="email" placeholder="E-mail id" class="form-control" />
                </div>
                <div class="row justify-content-between">
                    <div class="col-lg-6 col-12">
                        <label >Address</label>
                        <input type="text" name="address" placeholder="user address" class="form-control" />
                    </div>
                <div class="col-lg-6 col-12">
                    <label >Contact</label>
                    <input type="number" name="contact" placeholder="Contact" class="form-control" />
                </div>
            </div>
            <div class="row justify-content-between">
                <div class="col-lg-6 col-12">
                    <label >Balance</label>
                    <input type="number" name="balance" placeholder="Balance" class="form-control" />
                </div>
            </div>
            <div class="row justify-content-around mt-3">
                <div class="col-2">
                    <button type="submit" class="btn btn-outline-info"><h1 class="bi bi-check-circle-fill"></h1></button>
                </div>

                <div class="col-2">
                    <button type="reset" class="btn btn-outline-secondary"><h1 class="bi bi-x-lg"></h1></button>
                </div>
            </div>
        </form>
    </div>
</div>
<% }
else {
    response.sendRedirect("index.jsp");
}%>
</body>
</html>
