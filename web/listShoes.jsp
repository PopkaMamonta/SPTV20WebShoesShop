<%-- 
    Document   : listShoes
    Created on : 25.03.2022, 10:17:04
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="w-100 text-center my-5">Список обуви</h1>
<div class="w-100 d-flex justify-content-center">
  <c:forEach var="entry" items="${mapModels}">
    <div class="card" style="width: 18rem;">
    <img src="insertFile/${entry.value.fileName}" class="card-img-top" >
      <div class="card-body">
        <h5 class="card-title">${entry.key.model}</h5>
        <h6 class="card-subtitle">${entry.key.brand}</h6>
        <p class="card-text">${entry.key.quantity} пар</p>
        <p class="card-text">${entry.key.size}</p>
        <p class="card-text">${entry.key.price}</p>
      </div>
    </div>
  </c:forEach>
</div>

