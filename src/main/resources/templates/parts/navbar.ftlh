<#include "security.ftlh">
<#import "login.ftlh" as l>
<div>
    <nav class="navbar navbar-expand-lg navbar-light" id="nav">
        <a class="navbar-brand" href="#">BookCrossing</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="/">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/main">Books</a>
                </li>
                <#if user??>
                    <li class="nav-item">
                        <a class="nav-link" href="/myBooks">My books</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/users/profile">Profile</a>
                    </li>
                </#if>
                <#if isAdmin>
                    <li class="nav-item">
                        <a class="nav-link" href="/users">Users</a>
                    </li>
                </#if>
            </ul>

            
            <div class="navbar-text mr-3">${name}</div>
            <#if user??>
                <@l.logout />
                <#else >
                    <div class="mr-3">
                        <a href="/login" class="btn btn-primary">Sign in</a>
                    </div>
            </#if>

            <form class="form-inline my-2 my-lg-0">
                <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>
        </div>
    </nav>
</div>