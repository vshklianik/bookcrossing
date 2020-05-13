<#import "parts/common.ftlh" as c>
<@c.page>
    User editor

    <form action="/users" method="post">
        <div class="mt-3">
            <label for="username" >Edit username</label>
            <input type="text" name="username" value="${user.username}">
        </div>
        <#list roles as role>
            <div class="custom-control custom-checkbox mb-3">
                <input type="checkbox" class="custom-control-input" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>
                <label class="custom-control-label" for="${role}">${role}</label>
            </div>
        </#list>
        <input type="hidden" name="userId" value="${user.id}">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-outline-primary">Save</button>
    </form>
</@c.page>