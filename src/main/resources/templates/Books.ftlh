<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<@c.page>
    <#if message??>
        <div class="alert alert-${message}" role="alert">
            Book delete is successfully
        </div>
    </#if>

    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/books" class="form-inline">
                <input type="text" class="form-control" name="filter" value="${filter?if_exists}" placeholder="Find by genre">
                <button type="submit" class="btn btn-outline-primary ml-2">Search</button>
            </form>
        </div>
    </div>
    <#include "parts/bookList.ftlh">
</@c.page>