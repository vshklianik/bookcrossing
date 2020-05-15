<#import "parts/common.ftlh" as c>
<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/main" class="form-inline">
                <input type="text" class="form-control" name="filter" value="${filter?if_exists}" placeholder="Поиск по тегу">
                <button type="submit" class="btn btn-outline-primary ml-2">Найти</button>
            </form>
        </div>
    </div>

    <a class="btn btn-primary" data-toggle="collapse" href="#collapseAddBook" role="button" aria-expanded="false" aria-controls="collapseExample">
        Add book
    </a>
    <div class="collapse <#if advert??>show</#if>" id="collapseAddBook">
        <div class="form-group mt-2">
            <form method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text" class="form-control ${(bookNameError??)?string('is-invalid', '')}"
                           value="<#if advert??>${advert.bookName}</#if>" name="bookName" placeholder="Введите название книги">
                    <#if bookNameError??>
                         <div class="invalid-feedback">
                            ${bookNameError}
                         </div>
                    </#if>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control ${(authorNameError??)?string('is-invalid', '')}"
                           value="<#if advert??>${advert.authorName}</#if>" name="authorName" placeholder="Введите имя автора">
                    <#if authorNameError??>
                        <div class="invalid-feedback">
                            ${authorNameError}
                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="genre" placeholder="Тэг">
                </div>
                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" class="custom-file-input" name="file" id="customFile">
                        <label class="custom-file-label" for="customFile">Выберите картинку</label>
                    </div>
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-outline-primary">Добавить</button>
                </div>
            </form>
        </div>
    </div>

    <div class="card-columns">
        <#list adverts as advert>
            <div class="card my-3">
                <div>
                    <#if advert.filename??>
                        <img src="/img/${advert.filename}" class="card-img-top">
                    </#if>
                </div>
                <div class="m-2">
                    <div><span>${advert.bookName}</span></div>
                    <div><span>${advert.authorName}</span></div>
                    <div><span>${advert.genre}</span></div>
                </div>
                <div class="card-footer text-muted">
                    <span>${advert.getAuthorUsername()}</span>
                </div>
            </div>
<#--        <#else>-->
<#--            No adverts-->
        </#list>
    </div>
</@c.page>