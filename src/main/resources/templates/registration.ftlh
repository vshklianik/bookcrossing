<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<@c.page>
Add new user
${message!"<none>"}
<@l.login "/registration" />
</@c.page>