<#import "/spring.ftl" as spring>
<div class="card-columns" id="books-list">
    <#list books as book>
        <div class="card my-3">
            <div>
                <#if book.filename??>
                    <img src="/img/${book.filename}" class="card-img-top"/>
                </#if>
            </div>
            <div class="card-title">
                <h5>${book.bookName}</h5>
            </div>
            <div class="card-body m-2">
                <p>${book.authorName}</p>
                <p>
                    <#list book.genre as genre>
                        <@spring.message "genre.${genre}"/><#if genre_has_next>, </#if>
                    </#list>
                </p>
                <#if springMacroRequestContext.getRequestUri()?contains('/books')>
                    <#if isAdmin>
                        <a href="/books/${book.id}" class="btn btn-primary"><@spring.message "button.moreinfo"/></a>
                        <a href="/books/delete/${book.id}" class="btn btn-danger"><@spring.message "button.delete"/></a>
                    <#else >
                        <a href="/books/${book.id}" class="btn btn-primary stretched-link"><@spring.message "button.moreinfo"/></a>
                    </#if>
                <#elseif springMacroRequestContext.getRequestUri() == "/mybooks">
                    <a class="btn btn-primary" href="/mybooks/edit/${book.id}"><@spring.message "button.edit"/></a>
                    <a class="btn btn-danger" href="/mybooks/delete/${book.id}"><@spring.message "button.delete"/></a>
                </#if>
            </div>
            <div class="card-footer text-muted">
                <p>${book.getAuthorUsername()}</p>
            </div>
        </div>
    <#else>
        <@spring.message "message.nobooks"/>
    </#list>
</div>