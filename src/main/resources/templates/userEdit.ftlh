<#import "parts/common.ftlh" as c>
<@c.page>
    User editor

    <form action="/users/edit" method="post">
        <div class="mt-3">
            <label for="username" >Edit username</label>
            <input type="text" name="username" value="${user.username}">
        </div>
        <#list roles as role>
            <div class="mb-3">
                <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label>
            </div>
        </#list>
        <input type="hidden" name="userId" value="${user.id}">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-outline-primary">Save</button>
    </form>
</@c.page>