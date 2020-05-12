<#import "parts/common.ftlh" as c>

<@c.page>
    <h5>${username}</h5>
    ${message?if_exists}
    <div class="form-group col-sm-5">
        <form method="post">
            <div class="form-group">
                <label for="email">Email</label>
                <div>
                    <input type="email" id="email" name="email" class="form-control" placeholder="Your email" value="${email!''}">
                </div>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <div>
                    <input type="password" id="password" name="password" class="form-control" placeholder="Password">
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit" class="btn btn-primary">Save</button>
        </form>
    </div>
</@c.page>