<#import "parts/common.ftlh" as c>
<@c.page>
    <div>
        <form method="post">
            <input type="text" name="bookName" placeholder="Введите название книги">
            <input type="text" name="authorName" placeholder="Введите имя автора">
            <input type="text" name="genre" placeholder="Введите название жанра">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit">Добавить</button>
        </form>
    </div>

    <div>
        <form method="get" action="main">
            <input type="text" name="filter" value="${filter!""}" placeholder="фильтр">
            <button type="submit">Найти</button>
        </form>
    </div>

    <#list adverts as advert>
        <div>
            <b>${advert.id}</b>
            <span style="color: green">${advert.bookName}</span>
            <span style="color: green">${advert.authorName}</span>
            <span style="color: green">${advert.genre}</span>
            <span style="color: green">${advert.getAuthorUsername()}</span>
        </div>
        <#else>
        No adverts
    </#list>
</@c.page>