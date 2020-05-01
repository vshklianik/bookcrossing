<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<@c.page>
    Login page
    <@l.login "/login"/>
    <a href="/registration" class="alert-info">Add new user</a>
</@c.page>