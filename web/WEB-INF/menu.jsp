<%-- 
    Document   : menu
    Created on : 25.03.2022, 8:38:18
    Author     : user
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="listShoes">
      <img src="pics/sneakers.png" width="30" height="30" class="d-inline-block align-top" alt="">
      BootsShop
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
        <c:choose>
          <c:when test="${topRole eq 'ADMINISTRATOR'}">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item"> <a class='nav-link <c:if test="${activeAddModel eq true}">active</c:if>' aria-current="page" href="addModel">Добавить обувь</a> </li>
                <li class="nav-item"> <a class='nav-link <c:if test="${activeShowTakeOnModel eq true}">active</c:if>' aria-current="page" href="showTakeOnModel">Каталог</a> </li>
                <li class="nav-item"> <a class='nav-link <c:if test="${activeAdminPanel eq true}">active</c:if>' aria-current="page" href="adminPanel">Админская панель</a> </li>
            </ul>
          </c:when>
          <c:when test="${topRole eq 'MANAGER'}">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item"> <a class='nav-link <c:if test="${activeAddModel eq true}">active</c:if>' aria-current="page" href="addModel">Добавить обувь</a> </li>
                <li class="nav-item"> <a class='nav-link <c:if test="${activeShowTakeOnModel eq true}">active</c:if>' aria-current="page" href="showTakeOnModel">Каталог</a> </li>
            </ul>
          </c:when>
          <c:when test="${topRole eq 'USER'}">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item"> <a class='nav-link <c:if test="${activeShowTakeOnModel eq true}">active</c:if>' aria-current="page" href="showTakeOnModel">Каталог</a> </li>
            </ul>
          </c:when>
          <c:when test="${topRole eq NULL}">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item"> <a class='nav-link <c:if test="${activeListShoes eq true}">active</c:if>' aria-current="page" href="listShoes">Список обуви</a> </li>
            </ul>
          </c:when>
        </c:choose>
      <ul class="navbar-nav mb-2 mb-lg-0">
        <c:if test="${authPerson eq null}">
            <li class="nav-item">
                <a class="nav-link <c:if test="${activeShowLogin eq true}">active</c:if>" aria-current="page" href="showLogin">Вход</a>
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
</nav>