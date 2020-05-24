<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<#import "/spring.ftl" as spring>
<@c.page>
    <#if message??>
        <div class="alert alert-${message}" role="alert">
            <@spring.message "message.deletebook"/>
        </div>
    </#if>

    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/books" class="form-inline">
                <input type="text" class="form-control" name="filter" value="${filter?if_exists}" placeholder="<@spring.message "label.findbygenre"/>">
                <button type="submit" class="btn btn-outline-primary ml-2"><@spring.message "button.search"/></button>
            </form>
        </div>
    </div>
    <#include "parts/bookList.ftlh">
</@c.page>