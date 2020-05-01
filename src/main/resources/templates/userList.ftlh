<#import "parts/common.ftlh" as c>

<@c.page>
    List of users
    <table>
        <thead>
        <th>Username</th>
        <th>Roles</th>
        <th></th>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td>${user.username}</td>
                <td><#list user.roles as role>${role}<#sep>, </#list></td>
                <td><a href="/users/${user.id}">edit</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>