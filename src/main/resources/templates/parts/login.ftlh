<style>
    .form-control[type=text]:valid {
        color: green
    }

    .form-control[type=text]:invalid {
        color: red
    }
</style>
<#macro login path>
    <div class="form-group col-sm-5">
        <form action="${path}" method="post">
            <div class="form-group">
                <label for="username">Username</label>
                <div>
                    <input type="text" id="username" name="username" class="form-control" placeholder="Username"
                           pattern="[a-zA-Z0-9]{4,12}">
                    <p class="help-block">Username should be at least 4 characters</p>
                </div>
            </div>
            <#if path == "/registration">
                <div class="form-group">
                    <label for="email">Email</label>
                    <div>
                        <input type="email" id="email" name="email" class="form-control" placeholder="Your email">
                    </div>
                </div>
            </#if>
            <div class="form-group">
                <label for="password">Password</label>
                <div>
                    <input type="password" id="password" name="password" class="form-control" placeholder="Password"
                           pattern="[a-zA-Z0-9_-]{4,12}">
                    <p class="help-block">Password should be at least 4 characters</p>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit" class="btn btn-primary">Sing in</button>
        </form>
        <#if path != "/registration">
            <a href="/registration" class="alert-info">Create an account</a>
        </#if>
    </div>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit" class="btn btn-primary mr-3">Sign out</button>
    </form>
</#macro>