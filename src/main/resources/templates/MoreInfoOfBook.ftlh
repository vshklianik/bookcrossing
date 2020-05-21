<#import "parts/common.ftlh" as c>
<@c.page>
    <div class="card mb-3" style="max-width: 1080px; max-height: 540px">
        <div class="row no-gutters">
            <div class="col-md-4">
                <#if book.filename??>
                <img src="/img/${book.filename}" class="card-img">
                </#if>
            </div>
            <div class="col-md-8">
                <div class="card-body">
                    <h5 class="card-title">${book.bookName}</h5>
                    <p class="card-text"><span class="text-muted">Genre: </span>${book.getListOfGenre()?join(", ")}</p>
                    <p class="card-text"><span class="text-muted">Author name: </span>${book.getAuthorName()}</p>
                    <#if userInfo??>
                        <#if userInfo.getEmail()??>
                            <p class="card-text"><span class="text-muted">Email: </span>${userInfo.getEmail()}</p>
                        </#if>
                        <#if userInfo.getPhoneNumber()??>
                            <p class="card-text"><span class="text-muted">Phone number: </span>${userInfo.getPhoneNumber()}</p>
                        </#if>
                    </#if>
                    <p class="card-text"><span class="text-muted">User: </span>${book.getAuthorUsername()}</p>
                </div>
            </div>
        </div>
    </div>
</@c.page>