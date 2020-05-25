<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<#import "/spring.ftl" as spring>
<@c.page>
    <#if message??>
        <div class="alert alert-${message}" role="alert">
            <@spring.message "message.deletebook"/>
        </div>
    </#if>
    <a class="btn btn-primary" data-toggle="collapse" href="#collapseAddBook" role="button" aria-expanded="false" aria-controls="collapseExample">
        <@spring.message "button.filter"/>
    </a>
    <div class="collapse <#if book??>show</#if>" id="collapseAddBook">
        <div class="form-group col-md-6">
            <form method="get" action="/books">
                <#include "parts/genresAdd.ftlh">
                <button type="submit" class="btn btn-outline-primary ml-2"><@spring.message "button.search"/></button>
            </form>
        </div>
    </div>
    <#include "parts/bookList.ftlh">
</@c.page>