<#import "parts/common.ftlh" as c>
<#import "/spring.ftl" as spring>
<@c.page>
<#--    <@spring.message "message.useredit"/>-->
    <form action="/mybooks/edit/" method="post">
        <div class="form-group mt-3">
            <label for="username" ><@spring.message "label.editusername"/></label>
            <input type="text" class="form-control" name="username" value="${user.username}" placeholder="<@spring.message "label.username"/>"/>
        </div>
        <#list roles as role>
            <div class="mb-3">
                <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}/>${role}</label>
            </div>
        </#list>
        <input type="hidden" name="userId" value="${user.id}"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-outline-primary"><@spring.message "button.save"/></button>
    </form>
</@c.page>