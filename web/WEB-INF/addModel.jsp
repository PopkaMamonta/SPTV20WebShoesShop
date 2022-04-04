<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="w-100 text-center my-5">Добавление модели</h1>
<div class="w-100 d-flex justify-content-center">
    <div class="card border-0 p-5 m-4" style="width: 30rem;">
            <form action="createModel" method="POST">
              <div class="mb-3">
                <label for="model" class="form-label">Модель</label>
                <input type="text" class="form-control" name="model" id="model" placeholder="">
              </div>
              <div class="mb-3">
                <label for="brand" class="form-label">Брэнд</label>
                <input type="text" class="form-control" name="brand" id="brand" placeholder="">
              </div>
              <div class="mb-3">
                <label for="price" class="form-label">Цена</label>
                <input type="text" class="form-control" name="price" id="price" placeholder="">
              </div>
              <div class="mb-3">
                <label for="size" class="form-label">Размер</label>
                <input type="text" class="form-control" name="size" id="size" placeholder="">
              </div>
              <div class=mb-3">
                <label for="quantity" class="form-label">Количество</label>
                <input type="text" class="form-control" name="quantity" id="quantity" placeholder="">
              </div>
            <div class="mb-3">
            <label for="coverId" class="form-label">Картинка</label>
            <h3 class="w-100 text-center my-5">Загрузить файл</h3>
                <div class="row mb-3">
                  <label for="file" class="form-label">Выберите локальный файл</label>
                  <input class="form-control" type="file" name="file" id="file">
                </div>
                <div class=" row mb-3">
                  <label for="description" class="form-label">Описание</label>
                  <input class="form-control" type="text" name="description" id="description">
                </div>
            </div>
              <div class="mb-3">
                <button type="submit" class="btn btn-primary">Добавить обувь</button>
              </div>
            </form>
    </div>
</div>