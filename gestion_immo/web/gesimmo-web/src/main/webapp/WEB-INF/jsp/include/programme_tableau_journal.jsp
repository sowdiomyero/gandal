<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty journals}">
    <div class="table-responsive" >
        <table id="table_journal" class="table table-bordred table-striped  "   border="0">
            <thead>
                <tr  style="font-weight: bold;  background-color: #CCD4D9">
                    <td>Date </td>
                    <td>Libelle </td>
                    <td>Details </td>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${journals}" var="journal" >
                    <tr>
                        <td><fmt:formatDate type="date" value="${journal.dateCreation}" /></td>
                <td> ${journal.libelle}</td>
                <td>
                    <div data-placement="top" data-toggle="toolt" class="col-lg-1">
                        <button title="Visualiser journal" class="showJournal btn btn-info btn-xs"
                                data-id="${journal.idJournal}"  type="button" >

                            <span class="glyphicon glyphicon-eye-open" ></span>
                        </button>
                    </div>
                </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</c:if>
<c:if test="${ empty journals}">
    <h4>Aucun journal</h4>
</c:if>