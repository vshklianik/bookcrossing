<#import  "parts/common.ftlh" as c>
<#import "/spring.ftl" as spring>
<@c.page>
    <h5>Hello, Guest!</h5>
    <div>
        <p><@spring.message "message.welcome"/></p>
    </div>
</@c.page>