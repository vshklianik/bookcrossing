<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<#import "/spring.ftl" as spring>
<@c.page>
    <@spring.message "message.registration"/>
    ${message?if_exists}
    <@l.login "/registration" />
</@c.page>