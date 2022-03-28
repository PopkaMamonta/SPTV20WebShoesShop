<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="w-100 text-center my-5">Новая книга</h1>
<div class="w-100 d-flex justify-content-center">
    <div class="card border-0 p-5 m-4" style="width: 30rem;">
            <form class="row g-3" action="createModel" method="POST">
              <div class="col-12">
                <label for="model" class="form-label">Модель</label>
                <input type="text" class="form-control" id="model" placeholder="">
              </div>
              <div class="col-md-6">
                <label for="brand" class="form-label">Брэнд</label>
                <input type="text" class="form-control" id="brand" placeholder="">
              </div>
              <div class="col-md-4">
                <label for="price" class="form-label">Цена</label>
                <input type="text" class="form-control" id="price" placeholder="">
              </div>
              <div class="col-md-4">
                <label for="size" class="form-label">Размер</label>
                <input type="text" class="form-control" id="size" placeholder="">
              </div>
              <div class="col-md-6">
                <label for="quantity" class="form-label">Количество</label>
                <input type="text" class="form-control" id="quantity" placeholder="">
              </div>
              <div class="col-12">
                <button type="submit" class="btn btn-primary">Добавить обувь</button>
              </div>
            </form>
    </div>
</div>