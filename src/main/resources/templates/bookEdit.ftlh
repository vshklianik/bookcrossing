<#import "parts/common.ftlh" as c>
<#import "/spring.ftl" as spring>
<@c.page>
    <div class="form-group mt-2">
        <form action="/mybooks/edit/" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" class="form-control ${(bookNameError??)?string('is-invalid', '')}"
                       value="<#if bookError??>${book.bookName}</#if>" name="bookName" placeholder="<@spring.message "label.bookname"/>"/>
                <#if bookNameError??>
                    <div class="invalid-feedback">
                        <@spring.message "${bookNameError}"/>
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <input type="text" class="form-control ${(authorNameError??)?string('is-invalid', '')}"
                       value="<#if bookError??>${book.authorName}</#if>" name="authorName" placeholder="<@spring.message "label.authorname"/>"/>
                <#if authorNameError??>
                    <div class="invalid-feedback">
                        <@spring.message "${authorNameError}"/>
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" class="custom-file-input" name="file" id="customFile"/>
                    <label class="custom-file-label" for="customFile"><@spring.message "label.selectpicture"/></label>
                </div>
                <input type="hidden" name="id" value="${book.id}" />
                <input type="hidden" name="filename" value="<#if book.filename??>${book.filename}</#if>" />
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
            </div>
            <div class="form-group">
                <#include "parts/genresAdd.ftlh">
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-outline-primary"><@spring.message "button.save"/></button>
            </div>
        </form>
    </div>
</@c.page>