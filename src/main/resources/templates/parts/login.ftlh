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
                    <input type="text" id="username" name="username"
                           class="form-control ${(usernameError??)?string('is-invalid', '')}"
                           value="<#if user??>${user.username}</#if>"
                           placeholder="Username"
                           pattern="[a-zA-Z0-9]{4,12}">
                    <#--                    <p class="help-block">Username should be at least 4 characters</p>-->
                    <#if usernameError??>
                        <div class="invalid-feedback">
                            ${usernameError}
                        </div>
                    </#if>
                </div>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <div>
                    <input type="password" id="password" name="password"
                           class="form-control ${(passwordError??)?string('is-invalid', '')}"
                           placeholder="Password" pattern="[a-zA-Z0-9_-]{4,12}">
                    <#--                    <p class="help-block">Password should be at least 4 characters</p>-->
                    <#if passwordError??>
                        <div class="invalid-feedback">
                            ${passwordError}
                        </div>
                    </#if>
                </div>
            </div>
            <#if path == "/registration">
                <div class="form-group">
                    <label for="password1">Password</label>
                    <div>
                        <input type="password" id="password1" name="password1"
                               class="form-control ${(password1Error??)?string('is-invalid', '')}"
                               placeholder="Confirm password" pattern="[a-zA-Z0-9_-]{4,12}">
                        <#--                    <p class="help-block">Password should be at least 4 characters</p>-->
                        <#if password1Error??>
                            <div class="invalid-feedback">
                                ${password1Error}
                            </div>
                        </#if>
                    </div>
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <div>
                        <input type="email" id="email" name="email"
                               class="form-control ${(emailError??)?string('is-invalid', '')}"
                               value="<#if user??>${user.email}</#if>" placeholder="Your email">
                        <#if emailError??>
                            <div class="invalid-feedback">
                                ${emailError}
                            </div>
                        </#if>
                    </div>
                </div>
            </#if>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <#if path != "/registration">
                <a href="/registration" class="btn btn-success">Create an account</a>
            </#if>
            <button type="submit" class="btn btn-primary pull-right">Sing in</button>
        </form>

    </div>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit" class="btn btn-primary mr-3">Sign out</button>
    </form>
</#macro>