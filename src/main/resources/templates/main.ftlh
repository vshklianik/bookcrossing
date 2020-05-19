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
            <form method="get" action="/main" class="form-inline">
                <input type="text" class="form-control" name="filter" value="${filter?if_exists}" placeholder="Find by genre">
                <button type="submit" class="btn btn-outline-primary ml-2">Search</button>
            </form>
        </div>
    </div>

    <div class="card-columns">
        <#list books as book>
            <div class="card my-3">
                <div>
                    <#if book.filename??>
                        <img src="/img/${book.filename}" class="card-img-top">
                    </#if>
                </div>
                <div class="m-2">
                    <div><span>${book.bookName}</span></div>
                    <div><span>${book.authorName}</span></div>
                    <div><span>${book.genre}</span></div>
                    <#if isAdmin>
                        <a href="/main/${book.id}" class="btn btn-primary">More info</a>
                        <a href="/main/delete/${book.id}" class="btn btn-danger">Delete</a>
                        <#else >
                            <a href="/main/${book.id}" class="btn btn-primary stretched-link">More info</a>
                    </#if>


                </div>
                <div class="card-footer text-muted">
                    <span>${book.getAuthorUsername()}</span>
                </div>
            </div>
<#--        <#else>-->
<#--            No adverts-->
        </#list>
    </div>
</@c.page>