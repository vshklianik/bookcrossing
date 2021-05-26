<#import "/spring.ftl" as spring>
<a class="btn btn-primary" data-toggle="collapse" href="#collapseAddBook" role="button" aria-expanded="false"
   aria-controls="collapseExample">
    <@spring.message "button.addbook"/>
</a>
<div class="collapse <#if (bookError)?exists>show</#if>" id="collapseAddBook">
    <div class="form-group mt-2">
        <form action="/mybooks" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" class="form-control ${(bookNameError??)?string('is-invalid', '')}"
                       value="<#if book??>${bookError.bookName}</#if>" name="bookName"
                       placeholder="<@spring.message "label.bookname"/>"/>
                <#if bookNameError??>
                    <div class="invalid-feedback">
                        <@spring.message "${bookNameError}"/>
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <input type="text" class="form-control ${(authorNameError??)?string('is-invalid', '')}"
                       value="<#if book??>${bookError.authorName}</#if>" name="authorName"
                       placeholder="<@spring.message "label.authorname"/>"/>
                <#if authorNameError??>
                    <div class="invalid-feedback">
                        <@spring.message "${authorNameError}"/>
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" class="custom-file-input" name="file" id="customFile"
                           accept=".jpg,.gif,.png, .bmp"/>
                    <label class="custom-file-label" for="customFile"><@spring.message "label.selectpicture"/></label>
                </div>
                <#--                <input type="hidden" name="_csrf" value="${_csrf.token}" />-->
            </div>
            <#include "genresAdd.ftlh">
            <div class="form-group">
                <button type="submit" class="btn btn-outline-primary"><@spring.message "button.save"/></button>
            </div>
        </form>
    </div>
</div>
