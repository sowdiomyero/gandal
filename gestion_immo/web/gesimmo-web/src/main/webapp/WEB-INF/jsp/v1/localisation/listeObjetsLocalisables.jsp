<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<br>

<div class=" div-list">
    
    <c:forEach items="${localisations}" var="localisation" varStatus="indexes"> 
                <div class="row div-item ${indexes.index == 0 ? 'selected-div' : ''}" style="border-bottom: 2px solid #EEE; margin: 3px;" data-id="${localisation.idLocalisation}" name="localisationItem" onclick="loadLocalisationItem(${localisation.idLocalisation});"> 
<!--                   <a href="#" data-id="${localisation.idLocalisation}" > -->
                        <div >
                        
                            <c:if test="${localisation.getDType() == 'SITE'}">
                                <img src="${pageContext.request.contextPath}/img/markers/hotel_0star.png"  style="height: auto; width: auto; max-height: 25px; max-width: 25px"/>
                             </c:if> 
                             <c:if test="${localisation.getDType() == 'BATIMENT'}">
                                 <img src="${pageContext.request.contextPath}/img/markers/villa.png" style="height: auto; width: auto; max-height: 25px; max-width: 25px" />
                             </c:if> 
                        
                         
                                 <span > ${localisation.nomLocalisable} - ${localisation.idLocalisation}</span>
                        
                    </div>
                    <div  name="desc"> <span >${localisation.description}</span></div>    
<!--                    </a>-->
                </div>
                                       
            </c:forEach>
    
            

</div>


