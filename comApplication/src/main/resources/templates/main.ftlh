<#import  "parts/common.ftlh" as c>
<@c.page>
<div class="from-row">
    <div class="form-group col-md-6">
        <form class="form-inline" method="get" action="/main">
            <input class="form-control" type="text" name="filter" value="${filter?ifExists}" placeholder="Поиск по тегу">
            <button type="submit" class="btn btn-primary ml-2">Найти</button>
        </form>
    </div>
</div>

<a class="btn btn-primary" data-toggle="collapse" href="#collapseRequest" role="button" aria-expanded="false" aria-controls="collapseRequest">
    Добавить объявление
</a>

<div class="collapse" id="collapseRequest">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input class="form-control" type="text" name="text" placeholder="Опишите вашу проблему" />
            </div>
            <div class="form-group">
                <input class="form-control" type="text" name="tag" placeholder="Тэг">
            </div>
            <div class="custom-file">
                <input type="file" name="file" id="customFile">
                <label class="custom-file-label" for="customFile">Выбрать файл</label>
            </div>
            <div class="form-group">
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
            </div>
            <div class="form-group">
                <button class="btn btn-primary" type="submit">Добавить</button>
            </div>
        </form>
    </div>
</div>

<div class="card-columns">
<#list requests as request>
    <div class="card my-3">
        <#if request.filename??>
            <img src="/img/${request.filename}" class="card-img-top">
        </#if>
        <div class="m-2">
            <span>${request.text}</span>
            <i>${request.tag}</i>
        </div>
        <div class="card-footer text-muted">
            ${request.username}
        </div>

    </div>
<#else>
    Не найдено постов с таким тегом
</#list>
</div>
</@c.page>
