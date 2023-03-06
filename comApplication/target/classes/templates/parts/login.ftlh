<#macro login path isRegisterForm>
<form action="${path}" method="post" xmlns="http://www.w3.org/1999/html">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Имя прользователя :</label>
        <div class="col-sm-5">
            <input class="form-control" type="text" name="username" placeholder="Имя пользователя"/>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Пароль: </label>
        <div class="col-sm-5">
            <input class="form-control" type="password" name="password" placeholder="Пароль"/>
        </div>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <#if !isRegisterForm> <a href="/registration">Зарегистрироваться</a></#if>
    <button class="btn btn-primary" type="submit"><#if isRegisterForm> Зарегистрироваться<#else>Войти</#if></button>
</form>
</#macro>

<#macro logout>
<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button class="btn btn-primary" type="submit"/>Выйти</button>
</form>
</#macro>