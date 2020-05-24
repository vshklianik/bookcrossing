<#import "/spring.ftl" as spring>
<div name="genre">
    <#if genres??>
        <#list genres as genre>
            <div class="mb-3">
                <#if book??>
                    <label><input type="checkbox" name="${genre}" value="${genre}"} ${(book.genre)?seq_contains(genre)?string("checked", "")}><@spring.message "genre.${genre}"/></label>
                <#else>
                    <label><input type="checkbox" name="${genre}" value="${genre}"}><@spring.message "genre.${genre}"/></label>
                </#if>
            </div>
        </#list>
    </#if>
</div>