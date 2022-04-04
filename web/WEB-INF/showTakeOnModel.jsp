<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="w-100 text-center my-5">Каталог</h1>
<div class="w-100 d-flex justify-content-center">
    <div class="card" style="width: 18rem;">
      <div class="card-body">
        <h5 class="card-title">${entry.key.name}</h5>
        <h6 class="card-subtitle">${entry.key.brand}</h6>
        <p class="card-text">${entry.key.quantity} пар</p>
        <p class="card-text">${entry.key.size}</p>
        <p class="card-text">${entry.key.price}</p>
        <p class="card-text"><a href="takeOnModel?modelId=${entry.key.id}">Купить</a></p>
      </div>
    </div>
</div>