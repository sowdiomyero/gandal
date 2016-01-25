<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty programme.activiteEtats}">
    <div class="table-responsive" >
        <table id="table_etats_activites" class="table table-bordred table-striped  "   border="0">
            <thead>
                <tr  style="font-weight: bold;  background-color: #CCD4D9">
                    <td>Date </td>
                    <td>Etats </td>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${programme.activiteEtats}" var="activiteEtat" >
                    <tr>
                        <td><fmt:formatDate type="both" value="${activiteEtat.date}" /></td>
                <td> ${activiteEtat.etat.nom}</td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</c:if>
<c:if test="${ empty programme.activiteEtats}">
    <h4>Aucun etat</h4>
</c:if>