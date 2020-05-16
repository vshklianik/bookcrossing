<a class="btn btn-primary" data-toggle="collapse" href="#collapseAddBook" role="button" aria-expanded="false" aria-controls="collapseExample">
    Save book
</a>
<div class="collapse <#if book??>show</#if>" id="collapseAddBook">
    <div class="form-group mt-2">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" class="form-control ${(bookNameError??)?string('is-invalid', '')}"
                       value="<#if book??>${book.bookName}</#if>" name="bookName" placeholder="Enter book name">
                <#if bookNameError??>
                    <div class="invalid-feedback">
                        ${bookNameError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <input type="text" class="form-control ${(authorNameError??)?string('is-invalid', '')}"
                       value="<#if book??>${book.authorName}</#if>" name="authorName" placeholder="Enter author name">
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
                    <label class="custom-file-label" for="customFile">Select picture</label>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-outline-primary">Save</button>
            </div>
        </form>
    </div>
</div>
