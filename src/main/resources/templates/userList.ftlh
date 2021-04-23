<#import "parts/common.ftlh" as c>
<#import "/spring.ftl" as spring>
<@c.page>
    <#if message??>
        <div class="alert alert-${message}" role="alert">
            <@spring.message "message.deletebook"/>
        </div>
    </#if>
    <@spring.message "message.userlist"/>
    <table class="table">
        <thead>
        <tr>
            <th scope="col"><@spring.message "tablehead.id"/></th>
            <th scope="col"><@spring.message "tablehead.username"/></th>
            <th scope="col"><@spring.message "tablehead.roles"/></th>
            <th scope="col"></th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <th scope="row">${user.id}</th>
                <td>${user.username}</td>
                <td><#list user.roles as role>${role}<#sep>, </#list></td>
                <td><a class="btn btn-primary" href="/users/edit/${user.id}"><@spring.message "button.edit"/></a></td>
                <td><a class="btn btn-danger" href="/users/delete/${user.id}"><@spring.message "button.delete"/></a></td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>