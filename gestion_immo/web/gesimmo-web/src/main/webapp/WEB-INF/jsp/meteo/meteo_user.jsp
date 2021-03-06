<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 
<div class="col-lg-3 col-md-6">
    <div class="panel panel-default">
        <div class="panel-heading">
            <div class="row">
                <div class="col-xs-3">
                    <i class="fa fa-comments fa-5x"></i>
                </div>
                <div class="col-xs-9 text-right">
                    <div class="huge"><c:out value="${nbUsers}"></c:out></div>
                    <div>Utilisateurs</div>
                </div>
            </div>
        </div>  
        <a href="${pageContext.request.contextPath}/userslist">
            <div class="panel-footer">
                <span class="pull-left">Voir tous les utilisateurs</span>

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
                    <div class="huge"><c:out value="${nbUsersActif}"></c:out></div>
                    <div>Utilisateurs actifs</div>
                </div>
            </div>
        </div>
        <a href="${pageContext.request.contextPath}/actifuserslist">
            <div class="panel-footer ">
                <span class="pull-left">Voir les utilisateurs actifs</span>
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
                    <div class="huge"><c:out value="${nbUsersInactif}"></c:out></div>
                    <div>Utilisateurs inactifs</div>
                </div>
            </div>
        </div>
        <a href="${pageContext.request.contextPath}/inactifuserslist">
            <div class="panel-footer">
                <span class="pull-left">Voir les utilisateurs inactifs</span>
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
                    <div class="huge">0</div>
                    <div>Utilisateurs surveillÚs</div>
                </div>
            </div>
        </div>
        <a href="#">
            <div class="panel-footer">
                <span class="pull-left">Voir les utilisateurs surveillÚs</span>
                <div class="clearfix"></div>
            </div>
        </a>
    </div>
</div>



