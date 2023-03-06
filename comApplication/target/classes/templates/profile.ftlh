<#import  "parts/common.ftlh" as c>

<@c.page>
    <h5><${username}/h5>
    <form method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Пароль: </label>
            <div class="col-sm-5">
                <input class="form-control" type="password" name="password" placeholder="Пароль"/>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-primary" type="submit"> Сохранить</button>
    </form>
</@c.page>