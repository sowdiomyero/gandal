<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="sec"  uri="http://www.springframework.org/security/tags"%>

    <sec:authorize access="isAuthenticated()">
        <div class="container">
            <div class="row">
                <h3 class="text-left" style="font-weight: bold;">
                    GESTION DES CLIENTS E-SECURED</h3>
                <div class="container-fluid">
                    <div class="row">
                        <div class="tabbable">
                            <ul class="nav nav-pills">
                                <li class="active"><a href="#panel_transactions" data-toggle="tab" style="font-weight: bold;">Historique Transactions <span class="badge">${nb_transactions}</span></a></li>
                                <li ><a href="#panel_echoue" data-toggle="tab" style="font-weight: bold;">Transactions Echouées <span class="badge">${nb_nv_transactions}</span></a></li>
                            </ul>
                            <div class="tab-content">
                                <div id="panel_transactions" class="tab-pane active">
                                    <div>
                                        <h4 style="font-weight: bold;">Liste des Transactions</h4>
                                        <div style="padding-bottom: 5px; margin-bottom: 5px">
                                            <span class="label label-default" style="margin: 5px; padding: 5px; color:#ac2925"><a href="${pageContext.request.contextPath}/download/rapport.csv">Download CSV</a> </span>
                                            <span class="label label-default" style="margin: 5px; padding: 5px; color:#ac2925"><a href="${pageContext.request.contextPath}/download/rapport.pdf">Download PDF</a> </span>
                                        </div>
                                        <div class="table-responsive" >
                                            <table id="mytable" class=" tableauTransactions display"  border="0">
                                                <thead>
                                                   <tr>
                                                   <th colspan="3" align="center" style=" font-weight: bold; text-align: center; background-color: #5EB6DD;">Client</th>

                                                    <th colspan="2" style="font-weight: bold; text-align: center;background-color: #C4E6F5;" >Commercant</th>

                                                    <th colspan="2" style="font-weight: bold; text-align: center;background-color: #5EB6DD;">Transaction</th>

                                                    <th  style="font-weight: bold;text-align: center;background-color: #C4E6F5;">Action</th>
                                                    </tr>

                                                    <tr  style="background-color: green;">
                                                        <td style="font-weight: bold; background-color: skyblue">Nom </td>
                                                        <td style="font-weight: bold; background-color: skyblue">Prénom </td>
                                                        <td style="font-weight: bold; background-color: skyblue">Numero Carte </td>
                                                        <td style="font-weight: bold; background-color: skyblue">Nom </td>
                                                        <td style="font-weight: bold; background-color: skyblue">Site Web</td>

                                                        <td style="font-weight: bold; background-color: skyblue">Montant</td>
                                                        <td style="font-weight: bold; background-color: skyblue">Date Transaction</td>

                                                        <td style="font-weight: bold; background-color: skyblue">Visualiser</td>
                                                    </tr> 
                                                </thead>
                                                <tbody>

                                                        <tr>
                                                            <td>SOW</td>
                                                            <td>Diom Yero</td>
                                                            <td>123-456-789</td>
                                                            <td>EBAY</td>
                                                            <td>www.ebay.com</td>
                                                            <td>150?</td>
                                                            <td>21/10/2015</td>
                                                            <td>                                                          
                                                                <button title="Visualiser transaction" style="background:#5EB6DD;" class="detailTransaction btn btn-primary btn-xs" data-id="1"  data-title="Edit"
                                                                        data-toggle="modal">
                                                                    <span class="glyphicon glyphicon-eye-open"></span>
                                                                </button>
                                                            </td>
                                                            </tr>

                                            </table>
                                        </div>
                                    </div>
                                </div>

                                <!-- Tab Transactions ayant Echouees -->
                                <div id="panel_echoue" class="tab-pane">
                                    <div >
                                        <h4 style="font-weight: bold;">Transactions ayant Echouées </h4>
                                        <div style="padding-bottom: 5px; margin-bottom: 5px">
                                            <span class="label label-success" style="margin: 5px; padding: 5px; color:#ac2925"><a href="${pageContext.request.contextPath}/download/rapport_failed.csv">Download CSV</a> </span>
                                            <span class="label label-default" style=" font-weight: bold; margin: 5px; padding: 5px; color:#ac2925 "><a href="${pageContext.request.contextPath}/download/rapport_failed.pdf">Download PDF</a> </span>
                                        </div>
                                        <div class="table-responsive" >
                                            <table id="mytable_nv" class=" tableauTransactions table table-bordred table-striped"  border="0">
                                                <thead>
                                                    <tr>
                                                        <th colspan="3" align="center" style=" font-weight: bold; text-align: center; background-color: #5EB6DD;">Client</th>

                                                        <th colspan="2" style="font-weight: bold; text-align: center;background-color: #C4E6F5;" >Commercant</th>

                                                        <th colspan="2" style="font-weight: bold; text-align: center;background-color: #5EB6DD;">Transaction</th>

                                                        <th  style="font-weight: bold; text-align: center;background-color: #C4E6F5;">Action</th>
                                                    </tr>
                                                    <tr  style="background-color: green;">
                                                        <td style="font-weight: bold; background-color: skyblue">Nom </td>
                                                        <td style="font-weight: bold; background-color: skyblue">Prénom </td>
                                                        <td style="font-weight: bold; background-color: skyblue">Numero Carte </td>
                                                        <td style="font-weight: bold; background-color: skyblue">Nom </td>
                                                        <td style="font-weight: bold; background-color: skyblue">Site Web</td>

                                                        <td style="font-weight: bold; background-color: skyblue">Montant</td>
                                                        <td style="font-weight: bold; background-color: skyblue">Date Transaction</td>

                                                        <td style="font-weight: bold; background-color: skyblue">Visualiser</td>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                <tr>
                                                    <td>DIALLO</td>
                                                    <td>Alhassane</td>
                                                    <td>123-456-789</td>
                                                    <td>EBAY</td>
                                                    <td>www.ebay.com</td>
                                                    <td>5000?</td>
                                                    <td>21/10/2015</td>
                                                    <td>
                                                        <button title="Visualiser transaction" style="background:#5EB6DD;" class="detailTransaction btn btn-primary btn-xs" data-id="1"  data-title="Edit"
                                                                data-toggle="modal">
                                                            <span class="glyphicon glyphicon-eye-open"></span>
                                                        </button>
                                                    </td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div><!-- /.tab-content -->
                        </div><!-- /.tabbable -->
                    </div>
                </div>
                <!-- #######################################################  -->

            </div>
            <!-- container-->
        </sec:authorize>


    </div>

