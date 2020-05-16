<#import "parts/common.ftlh" as c>
<@c.page>
    <#include "parts/bookEdit.ftlh" />

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

        <#else>
            No adverts
        </#list>
    </div>
</@c.page>