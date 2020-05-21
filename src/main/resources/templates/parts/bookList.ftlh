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
                <p>${book.authorName}</p>
                <p>${(book.getListOfGenre())?join(", ")}</p>
                <#if springMacroRequestContext.getRequestUri() == "/books">
                    <#if isAdmin>
                        <a href="/books/${book.id}" class="btn btn-primary">More info</a>
                        <a href="/books/delete/${book.id}" class="btn btn-danger">Delete</a>
                    <#else >
                        <a href="/books/${book.id}" class="btn btn-primary stretched-link">More info</a>
                    </#if>
                <#elseif springMacroRequestContext.getRequestUri() == "/mybooks">
                    <a class="btn btn-primary" href="/mybooks/edit/${book.id}">Edit</a>
                    <a class="btn btn-danger" href="/mybooks/delete/${book.id}">Delete</a>
                </#if>
            </div>
            <div class="card-footer text-muted">
                <p>${book.getAuthorUsername()}</p>
            </div>
        </div>
    <#else>
        No books
    </#list>
</div>