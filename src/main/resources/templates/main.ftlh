<#import "parts/common.ftlh" as c>
<@c.page>
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