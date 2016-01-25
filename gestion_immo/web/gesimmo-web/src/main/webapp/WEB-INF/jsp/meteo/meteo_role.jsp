
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="col-lg-3 col-md-6">
    <div class="panel panel-default">
        <div class="panel-heading">
            <div class="row">
                <div class="col-xs-3">
                    <i class="fa fa-comments fa-5x"></i>
                </div>
                <div class="col-xs-9 text-right">
                    <div class="huge">${nbr_user_tot}</div>
                    <div><spring:message code="label.utilisateur.all" /></div>
                </div>
            </div>
        </div>  
        <a href="${pageContext.request.contextPath}/userslist">
            <div class="panel-footer">
                <span class="pull-left"><spring:message code="label.div.meteo.details" /></span>

                <div class="clearfix"></div>
            </div>
        </a>
    </div>
</div>

<div class="col-lg-3 col-md-6">
    <div class="panel panel-green" style="border-color: #5cb85c;">
        <div class="panel-heading">
            <div class="row">
                <div class="col-xs-3">
                    <i class="fa fa-comments fa-5x"></i>
                </div>
                <div class="col-xs-9 text-right">
                    <div class="huge">${nbr_super_admin}</div>
                    <div><spring:message code="label.utilisateur.role.superadmin" /></div>
                </div>
            </div>
        </div>
        <a href="userslist_by_role?nameRole=ROLE_SUPER_ADMIN">
            <div class="panel-footer ">
                <span class="pull-left"><spring:message code="label.div.meteo.details" /></span>
                <div class="clearfix"></div>
            </div>
        </a>
    </div>
</div>

<div class="col-lg-3 col-md-6">
    <div class="panel panel-red" style="border-color: #d9534f;">
        <div class="panel-heading">
            <div class="row">
                <div class="col-xs-3">
                    <i class="fa fa-comments fa-5x"></i>     
                </div>

                <div class="col-xs-9 text-right">
                    <div class="huge">${nbr_admin}</div>
                    <div><spring:message code="label.utilisateur.role.admin" /></div>
                </div>
            </div>
        </div>
        <a href="userslist_by_role?nameRole=ROLE_ADMIN">
            <div class="panel-footer">
                <span class="pull-left"><spring:message code="label.div.meteo.details" /></span>
                <div class="clearfix"></div>
            </div>
        </a>
    </div>
</div>

<div class="col-lg-3 col-md-6">
    <div class="panel panel-yellow" style=" border-color: #f0ad4e;">
        <div class="panel-heading">
            <div class="row">
                <div class="col-xs-3">
                    <i class="fa fa-comments fa-5x"></i>     
                </div>
                <div class="col-xs-9 text-right">
                    <div class="huge">${nbr_user}</div>
                    <div><spring:message code="label.utilisateur.role.user" /></div>
                </div>
            </div>
        </div>
        <a href="userslist_by_role?nameRole=ROLE_USER">
            <div class="panel-footer">
                <span class="pull-left"><spring:message code="label.div.meteo.details" /></span>
                <div class="clearfix"></div>
            </div>
        </a>
    </div>
</div>

