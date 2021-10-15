<#import "parts/common.ftlh" as c>
<#import "/spring.ftl" as spring>
<@c.page>
    <#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>
        <div class="alert alert-danger col-sm-5" role="alert">
            ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}
        </div>
    </#if>
    <#if messageSuccess??>
        <div class="alert alert-success col-sm-5" role="alert">
            ${messageSuccess}
        </div>
    </#if>
    <#if message??>
        <div class="alert alert-${messageType} col-sm-5" role="alert">
            ${message}
        </div>
    </#if>

<#--    <div align="center">-->
<#--        <a href="/login/google">Войти через Google</a>-->
<#--    </div>-->

    <div class="form-group col-sm-5">
        <form action="/login" method="post">
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
            <#--            <input type="hidden" name="_csrf" value="${_csrf.token}" />-->

            <a href="/registration" class="btn btn-success"><@spring.message "button.registration"/></a>


            <button type="submit" class="btn btn-primary pull-right"><@spring.message "button.singin"/></button>

        </form>
    </div>
</@c.page>
