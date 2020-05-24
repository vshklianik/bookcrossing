<#import "parts/common.ftlh" as c>
<#import "/spring.ftl" as spring>
<@c.page>
    <h5>${user.username}</h5>
    ${message?if_exists}
    <div class="form-group col-sm-5">
        <form method="post">
            <div class="form-group">
                <label for="email"><@spring.message "label.email"/></label>
                <div>
                    <input type="email" id="email" name="email" class="form-control" placeholder="<@spring.message "placeholder.email"/>" value="${user.email!''}">
                </div>
            </div>
            <div class="form-group">
                <label for="phone"><@spring.message "label.phonenumber"/></label>
                <div>
                    <input type="text" id="phoneNumber" name="phoneNumber" class="form-control" placeholder="<@spring.message "placeholder.phonenumber"/>" value="${user.phoneNumber!''}">
                </div>
            </div>
            <div class="form-group">
                <label for="password"><@spring.message "label.password"/></label>
                <div>
                    <input type="password" id="password" name="password" class="form-control" placeholder="<@spring.message "placeholder.password"/>">
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit" class="btn btn-primary"><@spring.message "button.save"/></button>
        </form>
    </div>
</@c.page>