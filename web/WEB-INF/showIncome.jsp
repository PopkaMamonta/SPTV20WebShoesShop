<%-- 
    Document   : showIncome
    Created on : 06.04.2022, 18:45:54
    Author     : angel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="w-100 text-center my-5">Доходы</h1>
<div class="w-100 d-flex justify-content-center"  style="margin: 15px">
    <div class="card" style="width: 18rem;">
      <div class="card-body">
          <div class="mb-3">
              
            <h1 class="card-subtitle">Доходы за все время: </h1>
            <h2 class="card-subtitle" name="income">${income} EUR</h2>
          </div>
      </div>
    </div>
</div>