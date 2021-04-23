<#import "parts/common.ftlh" as c>
<#import "/spring.ftl" as spring>
<@c.page>
    <@spring.message "message.useredit"/>
    <form action="/users/edit/" method="post">
        <table class="table">
            <thead>
            <tr>
                <th scope="col"><@spring.message "tablehead.selected"/></th>
                <th scope="col"><@spring.message "tablehead.roles"/></th>
            </tr>
            </thead>
            <tbody>
            <#list roles as role>
                <tr>
                    <th><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}/></th>
                    <td>${role}</td>
                </tr>
            </#list>
            </tbody>
        </table>
        <input type="hidden" name="userId" value="${user.id}"/>
<#--        <input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
        <button type="submit" class="btn btn-outline-primary"><@spring.message "button.save"/></button>
    </form>
</@c.page>