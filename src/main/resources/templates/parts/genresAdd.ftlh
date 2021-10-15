<#import "/spring.ftl" as spring>
<div name="genre">
    <#if genreError??>
        <div class="alert alert-danger col-sm-5" role="alert">
            <@spring.message "${genreError}"/>
        </div>
    </#if>
    <#if genres??>
        <#list genres as genre>
            <div class="my-3">
                <#if book?? && book.genre?exists>
                    <label><input type="checkbox" name="${genre}" value="${genre}"} ${(book.genre)?seq_contains(genre)?string("checked", "")}/> <@spring.message "genre.${genre}"/></label>
                <#else>
                    <label><input type="checkbox" name="${genre}" value="${genre}"}/> <@spring.message "genre.${genre}"/></label>
                </#if>
            </div>
        </#list>
    </#if>
</div>