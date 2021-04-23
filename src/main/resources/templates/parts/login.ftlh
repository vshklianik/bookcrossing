<#macro login path>
    <#import "/spring.ftl" as spring>
    <div class="form-group col-sm-5">
        <form action="${path}" method="post">
            ${path}
            <div class="form-group">
                <label for="username"><@spring.message "label.username"/></label>
                <div>
                    <input type="text" id="username" name="username"
                           class="form-control ${(usernameError??)?string('is-invalid', '')}"
                           value="<#if user??>${user.username}</#if>"
                           placeholder="<@spring.message "label.username"/>"/>
                    <#if usernameError??>
                        <div class="invalid-feedback">
                            <@spring.message "${usernameError}"/>
                        </div>
                    </#if>
                </div>
            </div>
            <#if path == "/registration">
                <div class="form-group">
                    <label for="firstName"><@spring.message "label.firstname"/></label>
                    <div>
                        <input type="text" id="firstName" name="firstName"
                               class="form-control ${(firstNameError??)?string('is-invalid', '')}"
                               value="<#if user??>${user.firstName}</#if>"
                               placeholder="<@spring.message "placeholder.firstname"/>" />
                        <#if firstNameError??>
                            <div class="invalid-feedback">
                                <@spring.message "${firstNameError}"/>
                            </div>
                        </#if>
                    </div>
                </div>
                <div class="form-group">
                    <label for="lastName"><@spring.message "label.lastname"/></label>
                    <div>
                        <input type="text" id="lastName" name="lastName"
                               class="form-control ${(lastNameError??)?string('is-invalid', '')}"
                               value="<#if user??>${user.lastName}</#if>"
                               placeholder="<@spring.message "placeholder.lastname"/>"/>
                        <#if lastNameError??>
                            <div class="invalid-feedback">
                                <@spring.message "${lastNameError}"/>
                            </div>
                        </#if>
                    </div>
                </div>
                <div class="form-group">
                    <label for="email"><@spring.message "label.email"/></label>
                    <div>
                        <input type="email" id="email" name="email"
                               class="form-control ${(emailError??)?string('is-invalid', '')}"
                               value="<#if user??>${user.email}</#if>"
                               placeholder="<@spring.message "label.email"/>"/>
                        <#if emailError??>
                            <div class="invalid-feedback">
                                <@spring.message "${emailError}"/>
                            </div>
                        </#if>
                    </div>
                </div>
            </#if>
            <div class="form-group">
                <label for="password"><@spring.message "label.password"/></label>
                <div>
                    <input type="password" id="password" name="password"
                           class="form-control ${(passwordError??)?string('is-invalid', '')}"
                           placeholder="<@spring.message "label.password"/>" pattern="[a-zA-Z0-9_-]{4,12}"/>
                    <#if passwordError??>
                        <div class="invalid-feedback">
                            <@spring.message "${passwordError}"/>
                        </div>
                    </#if>
                </div>
            </div>
            <#if path == "/registration">
                <div class="form-group">
                    <label for="password1"><@spring.message "label.confirmpassword"/></label>
                    <div>
                        <input type="password" id="password1" name="password1"
                               class="form-control ${(password1Error??)?string('is-invalid', '')}"
                               placeholder="<@spring.message "label.confirmpassword"/>" pattern="[a-zA-Z0-9_-]{4,12}"/>
                        <#if password1Error??>
                            <div class="invalid-feedback">
                                <@spring.message "${password1Error}"/>
                            </div>
                        </#if>
                    </div>
                </div>
            </#if>
<#--            <input type="hidden" name="_csrf" value="${_csrf.token}" />-->
            <#if path != "/registration">
                <a href="/registration" class="btn btn-success"><@spring.message "button.registration"/></a>
                <#else>
                <button type="submit" class="btn btn-primary"><@spring.message "button.singup"/></button>
            </#if>
            <#if path == "/login">
                <button type="submit" class="btn btn-primary pull-right"><@spring.message "button.singin"/></button>
            </#if>
        </form>
    </div>
</#macro>

<#macro logout>
    <#import "/spring.ftl" as spring>
    <form action="/logout" method="post">
<#--        <input type="hidden" name="_csrf" value="${_csrf.token}" />-->
        <button type="submit" class="btn btn-primary mr-3"><@spring.message "button.singout"/></button>
    </form>
</#macro>