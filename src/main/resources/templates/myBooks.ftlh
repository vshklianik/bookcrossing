<#import "parts/common.ftlh" as c>
<@c.page>
    <#include "parts/bookAdd.ftlh" />

    <div class="card-columns">
        <#list books as book>

            <div class="card my-3">
                <div>
                    <#if book.filename??>
                        <img src="/img/${book.filename}" class="card-img-top">
                    </#if>
                </div>
                <div class="card-title">
                    <h5>${book.bookName}</h5>
                </div>
                <div class="card-body m-2">
                    <span>${book.genre}</span><br>
                    <span>${book.authorName}</span><br>
                        <a class="btn btn-primary" href="/myBooks/edit/${book.id}">Edit</a>
                        <a class="btn btn-danger" href="/myBooks/delete/${book.id}">Delete</a>
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