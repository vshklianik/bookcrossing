<#import "parts/common.ftlh" as c>
<@c.page>
<#--    <div class="card my-3">-->
<#--        <div>-->
<#--            <#if book.filename??>-->
<#--                <img src="/img/${book.filename}" class="card-img-top">-->
<#--            </#if>-->
<#--        </div>-->
<#--        <div class="m-2">-->
<#--            <div><span>${book.bookName}</span></div>-->
<#--            <div><span>${book.authorName}</span></div>-->
<#--            <div><span>${book.genre}</span></div>-->
<#--        </div>-->
<#--        <div class="card-footer text-muted">-->
<#--            <span>${userInfo.getEmail()}</span><br>-->
<#--            <span>${userInfo.getPhoneNumber()}</span><br>-->
<#--            <span>${book.getAuthorUsername()}</span>-->
<#--        </div>-->
<#--    </div>-->

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
                    <p class="card-text">${book.getGenre()}</p>
                    <p class="card-text">${book.getAuthorName()}</p
                    <#if userInfo??>
                        <#if userInfo.getEmail()??>
                            <p class="card-text"><small class="text-muted">${userInfo.getEmail()}</small></p>
                        </#if>
                        <#if userInfo.getPhoneNumber()??>
                            <p class="card-text"><small class="text-muted">${userInfo.getPhoneNumber()}</small></p>
                        </#if>
                        <p class="card-text"><small class="text-muted">${book.getAuthorUsername()}</small></p>
                    </#if>
                    <p class="card-text"><small class="text-muted"></small></p>
                </div>
            </div>
        </div>
    </div>
</@c.page>