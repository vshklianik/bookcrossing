<#macro login path>
    <#import "/spring.ftl" as spring>
    <div class="form-group col-sm-5">
        <form action="${path}" method="post">
            <div class="form-group">
                <label for="username"><@spring.message "label.username"/></label>
                <div>
                    <input type="text" id="username" name="username"
                           class="form-control ${(usernameError??)?string('is-invalid', '')}"
                           value="<#if user??>${user.username}</#if>"
                           placeholder="<@spring.message "label.username"/>"
                           pattern="[a-zA-Z0-9]{4,12}"/>
                    <#--                    <p class="help-block">Username should be at least 4 characters</p>-->
                    <#if usernameError??>
                        <div class="invalid-feedback">
                            ${usernameError}
                        </div>
                    </#if>
                </div>
            </div>
            <div class="form-group">
                <label for="password"><@spring.message "label.password"/></label>
                <div>
                    <input type="password" id="password" name="password"
                           class="form-control ${(passwordError??)?string('is-invalid', '')}"
                           placeholder="<@spring.message "label.password"/>" pattern="[a-zA-Z0-9_-]{4,12}"/>
                    <#if passwordError??>
                        <div class="invalid-feedback">
                            ${passwordError}
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
                                ${password1Error}
                            </div>
                        </#if>
                    </div>
                </div>
                <div class="form-group">
                    <label for="email"><@spring.message "label.email"/></label>
                    <div>
                        <input type="email" id="email" name="email"
                               class="form-control ${(emailError??)?string('is-invalid', '')}"
                               value="<#if user??>${user.email}</#if>" placeholder="<@spring.message "label.email"/>"/>
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
                <a href="/registration" class="btn btn-success"><@spring.message "button.registration"/></a>
            </#if>
            <button type="submit" class="btn btn-primary pull-right"><@spring.message "button.singin"/></button>
        </form>
    </div>
</#macro>

<#macro logout>
    <#import "/spring.ftl" as spring>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit" class="btn btn-primary mr-3"><@spring.message "button.singout"/></button>
    </form>
</#macro>