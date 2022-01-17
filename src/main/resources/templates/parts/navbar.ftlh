<#include "security.ftlh">
<#import "login.ftlh" as l>
<#import "/spring.ftl" as spring>
<div>
    <nav class="navbar navbar-expand-lg navbar-light" id="nav">
        <a class="navbar-brand" href="#">BookCrossing</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="/"><@spring.message "navbar.item.home"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/books"><@spring.message "navbar.item.books"/></a>
                </li>
                <#if user?? && user.password?exists && user.password != ''>
                    <li class="nav-item">
                        <a class="nav-link" href="/mybooks"><@spring.message "navbar.item.mybooks"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/users/profile"><@spring.message "navbar.item.profile"/></a>
                    </li>
                </#if>
                <#if isAdmin>
                    <li class="nav-item">
                        <a class="nav-link" href="/users"><@spring.message "navbar.item.users"/></a>
                    </li>
                </#if>
            </ul>

            <div class="nav-item mr-3">
                <select id="locales">
                    <option value=""></option>
                    <option value="en" ><@spring.message "lang.en"/></option>
                    <option value="ru" ><@spring.message "lang.ru"/></option>
                </select>
            </div>

            <div class="navbar-text mr-3" id="username">${name}</div>
            <#if user?? && user.password?exists && user.password != ''>
                <@l.logout />
            <#else >
                <div class="mr-3">
                    <a href="/login" class="btn btn-primary"><@spring.message "button.singin"/></a>
                </div>
            </#if>

            <form action="/books" class="form-inline my-2 my-lg-0">
                <input class="form-control mr-sm-2" type="search" name="searchText" placeholder="<@spring.message "label.search"/>" aria-label="Search"/>
<#--                <input type="hidden" name="_csrf" value="${_csrf.token}" />-->
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><@spring.message "button.search"/></button>
            </form>
        </div>
    </nav>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                $("#locales").change(function () {
                    var selectedOption = $('#locales').val();
                    if (selectedOption != ''){
                        window.location.replace("${springMacroRequestContext.getRequestUri()}?lang=" + selectedOption);
                    }
                });
            });
        </script>
</div>