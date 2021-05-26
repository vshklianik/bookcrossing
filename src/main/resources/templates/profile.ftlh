<#import "parts/common.ftlh" as c>
<#import "/spring.ftl" as spring>
<@c.page>
    <h5>${user.username}</h5>
    ${message?if_exists}
    <div class="form-group col-sm-5">
        <form method="post">
            <#--            <div class="form-group">-->
            <#--                <label for="firstName"><@spring.message "label.firstname"/></label>-->
            <#--                <div>-->
            <#--                    <input type="text" id="firstName" name="firstName" class="form-control" placeholder="<@spring.message "placeholder.firstname"/>" value="${user.firstName!''}"/>-->
            <#--                </div>-->
            <#--            </div>-->
            <#--            <div class="form-group">-->
            <#--                <label for="lastName"><@spring.message "label.lastname"/></label>-->
            <#--                <div>-->
            <#--                    <input type="text" id="lastName" name="lastName" class="form-control" placeholder="<@spring.message "placeholder.lastname"/>" value="${user.lastName!''}"/>-->
            <#--                </div>-->
            <#--            </div>-->
            <#--            <div class="form-group">-->
            <#--                <label for="email"><@spring.message "label.email"/></label>-->
            <#--                <div>-->
            <#--                    <input type="email" id="email" name="email" class="form-control" placeholder="<@spring.message "placeholder.email"/>" value="${user.email!''}"/>-->
            <#--                </div>-->
            <#--            </div>-->
            <#--            <div class="form-group">-->
            <#--                <label for="phone"><@spring.message "label.phonenumber"/></label>-->
            <#--                <div>-->
            <#--                    <input type="text" id="phoneNumber" name="phoneNumber" class="form-control" placeholder="<@spring.message "placeholder.phonenumber"/>" value="${user.phoneNumber!''}"/>-->
            <#--                </div>-->
            <#--            </div>-->
            <#--            <div class="form-group">-->
            <#--                <label for="password"><@spring.message "label.password"/></label>-->
            <#--                <div>-->
            <#--                    <input type="password" id="password" name="password" class="form-control" -->
            <#--                           placeholder="<@spring.message "placeholder.password"/>" pattern="[a-zA-Z0-9_-]{4,12}"/>-->
            <#--                </div>-->
            <#--            </div>-->
            <div class="form-group">
                <label for="firstName"><@spring.message "label.firstname"/></label>
                <div>
                    <input type="text" id="firstName" name="firstName"
                           class="form-control ${(firstNameError??)?string('is-invalid', '')}"
                           value="<#if user??>${user.firstName}</#if>"
                           placeholder="<@spring.message "placeholder.firstname"/>"/>
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
                <label for="phone"><@spring.message "label.phonenumber"/></label>
                <div>
                    <input type="text" id="phoneNumber" name="phoneNumber"
                           class="form-control ${(phoneNumberError??)?string('is-invalid', '')}"
                           placeholder="<@spring.message "placeholder.phonenumber"/>"
                           value="${user.phoneNumber!}"/>
                    <#if phoneNumberError??>
                        <div class="invalid-feedback">
                            <@spring.message "${phoneNumberError}"/>
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
            <input type="hidden" name="username" value="${user.username}"/>
            <#--            <input type="hidden" name="_csrf" value="${_csrf.token}" />-->
            <button type="submit" class="btn btn-primary"><@spring.message "button.save"/></button>
        </form>
    </div>
</@c.page>