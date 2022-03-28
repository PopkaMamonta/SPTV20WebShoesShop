<%-- 
    Document   : menu
    Created on : 25.03.2022, 8:38:18
    Author     : user
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">
      <img src="/img/brand/bootstrap-solid.svg" width="30" height="30" class="d-inline-block align-top" alt="" loading="lazy">
      BootsShop
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="addModel">Добавить обувь</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" aria-current="page" href="#">фф</a>
        </li>
      </ul>
      <ul class="navbar-nav mb-2 mb-lg-0">
        <c:if test="${authPerson eq null}">
            <li class="nav-item">
                <a class="nav-link <c:if test="${activeShowLogin eq true}">active</c:if>" aria-current="page" href="showLogin">Выход</a>
            </li>
        </c:if>
            
        <c:if test="${authPerson ne null}">
            <li class="nav-item">
                <a class="nav-link <c:if test="${activeLogout eq true}">active</c:if>" aria-current="page" href="logout">Выход</a>
            </li>
        </c:if>

      </ul>
    </div>
    
    <form class="d-flex">
      <input class="form-control mr-2" type="search" placeholder="Search" aria-label="Search">
      <button class="btn btn-outline-success" type="submit">Search</button>
    </form>
  </div>
</nav>