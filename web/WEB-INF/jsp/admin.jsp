<%-- 
    Document   : admin
    Created on : 27.04.2014, 11:26:48
    Author     : Florin
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ taglib uri="/WEB-INF/tlds/carrentaltaglib.tld" prefix="crtag" %> 

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!--i18n settings-->
<c:set var="language" value="${not empty param.language ? param.language :
                               not empty language ? language :
                               pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.bionic_university.carrental.i18n.text" />

<!DOCTYPE html>
<html>

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title><fmt:message key="index.title" /></title>

        <link href="styles/carrentalstyles.css" rel="stylesheet"/>

        <!-- Core CSS - Include with every page -->
        <link href="css/bootstrap.min.css" rel="stylesheet"/>
        <link href="font-awesome/css/font-awesome.css" rel="stylesheet"/>

        <!-- Page-Level Plugin CSS - Dashboard -->
        <link href="css/plugins/morris/morris-0.4.3.min.css" rel="stylesheet"/>
        <link href="css/plugins/timeline/timeline.css" rel="stylesheet"/>

        <!-- SB Admin CSS - Include with every page -->
        <link href="css/sb-admin.css" rel="stylesheet"/>

    </head>

    <body>

        <crtag:loadOrderList/>

        <div id="wrapper">

            <nav class="navbar navbar-default navbar-fixed-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="index.jsp">
                        <fmt:message key="index.title" />
                    </a>
                </div>
                <!-- /.navbar-header -->
                <ul class="nav navbar-top-links navbar-right">
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="fa fa-globe fa-fw"></i>  <i class="fa fa-caret-down"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="?language=uk_UA">UA</a></li>
                            <li><a href="?language=en_US">EN</a></li>
                        </ul>
                        <!-- /.dropdown-language -->
                    </li>
                    <!-- /.dropdown -->
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <c:if test="${empty sessionScope.userName}">
                                <li>
                                    <a href="login.jspx">
                                        <i class="fa fa-sign-in fa-fw"></i>
                                        <fmt:message key="header.button.login" />
                                    </a>
                                </li>
                                <li class="divider"></li>
                                <li>
                                    <a href="registration.jspx">
                                        <i class="fa fa-plus fa-fw"></i>
                                        <fmt:message key="header.button.registration" />
                                    </a>
                                </li>
                            </c:if>
                            <c:if test="${!empty sessionScope.userName}">
                                <li>
                                    <a href="#">
                                        <div>
                                            <i class="fa fa-user fa-fw"></i>
                                            <c:out value="${sessionScope.userName}"/>
                                        </div>
                                    </a>
                                </li>
                                <li class="divider"></li>
                                <li>

                                    <form name="logoutForm" method="POST" action="CarRentalServlet">
                                        <input type="hidden" name="command" value="logout"/>
                                        <a href="" onclick="parentNode.submit();
                                                return false;">
                                            <i class="fa fa-sign-out fa-fw"></i>
                                            <fmt:message key="header.button.logout" />
                                        </a>
                                    </form>
                                </li>
                            </c:if>
                        </ul>
                        <!-- /.dropdown-user -->
                    </li>
                    <!-- /.dropdown -->
                </ul>
                <!-- /.navbar-top-links -->

                <div class="navbar-default navbar-static-side" role="navigation">
                    <div class="sidebar-collapse">
                        <ul class="nav" id="side-menu">
                            <c:if test="${!empty sessionScope.userName}">
                                <li>
                                    <form name="makeOrderButton" method="post" action="CarRentalServlet">
                                        <input type="hidden" name="command" value="makeOrderButton"/>
                                        <a href="" onclick="parentNode.submit();
                                                return false;">
                                            <i class="fa fa-shopping-cart fa-fw"></i>
                                            <fmt:message key="index.button.makeOrder" />
                                        </a>
                                    </form>
                                </li>
                                <c:if test="${sessionScope.userTypeID == 1}">
                                    <li>
                                        <form name="adminZoneButton" method="post" action="CarRentalServlet">
                                            <input type="hidden" name="command" value="adminZoneButton"/>
                                            <a href="" onclick="parentNode.submit();
                                                    return false;">
                                                <i class="fa fa-wrench fa-fw"></i>
                                                <fmt:message key="index.button.adminZone" />
                                            </a>
                                        </form>
                                    </li>
                                </c:if>
                            </c:if>
                        </ul>
                        <!-- /#side-menu -->
                    </div>
                    <!-- /.sidebar-collapse -->
                </div>
                <!-- /.navbar-static-side -->
            </nav>

            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header"><fmt:message key="admin.pageheader" /></h1>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-info">
                            <div class="panel-heading">
                                <fmt:message key="admin.paneltitle.table" />
                            </div>
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <form name="selectOrder" method="post" action="CarRentalServlet">
                                        <input type="hidden" name="command" value="selectOrder" />
                                        <fmt:message key="admin.button.selectOrder" var="selectOrderButtonValue" />
                                        <button type="submit" class="btn btn-outline btn-info btn-lg btn-block">
                                            <i class="fa fa-dot-circle-o"></i>
                                            <fmt:message key="admin.button.selectOrder" />
                                        </button>
                                        <table class="table table-striped table-bordered table-hover">
                                            <thead>
                                                <tr>
                                                    <th><fmt:message key="table.order.choice" /></th>
                                                    <th><fmt:message key="table.order.vehicle" /></th>
                                                    <th><fmt:message key="table.order.user" /></th>
                                                    <th><fmt:message key="table.order.passport" /></th>		
                                                    <th><fmt:message key="table.order.pickUp" /></th>
                                                    <th><fmt:message key="table.order.dropOff" /></th>
                                                    <th><fmt:message key="table.order.rentCost" /></th>
                                                    <th><fmt:message key="table.order.processed" /></th>
                                                    <th><fmt:message key="table.order.rejected" /></th>
                                                    <th><fmt:message key="table.order.rejectDesc" /></th>
                                                    <th><fmt:message key="table.order.picked" /></th>
                                                    <th><fmt:message key="table.order.returned" /></th>
                                                    <th><fmt:message key="table.order.damaged" /></th>
                                                    <th><fmt:message key="table.order.damageDesc" /></th>
                                                    <th><fmt:message key="table.order.damageCost" /></th>
                                                    <th><fmt:message key="table.order.paid" /></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:if test="${empty requestScope.orderID}">
                                                    <c:forEach items="${orderList}" var="order">
                                                        <tr>
                                                            <td><input type="radio" name="orderChoice" value="${order.orderID}" checked="" /></td>
                                                            <td><c:out value="${order.vehicle.getDescription()}" /></td>
                                                            <td><c:out value="${order.user.login}" /></td>
                                                            <td><c:out value="${order.passport.getDescription()}" /></td>
                                                            <td><c:out value="${order.pickUpDate}" /></td>
                                                            <td><c:out value="${order.dropOffDate}" /></td>
                                                            <td><c:out value="${order.rentCost}" /></td>
                                                            <td>
                                                                <c:if test="${order.processed}">
                                                                    <input type="checkbox" name="processed" disabled="" checked="" />
                                                                </c:if>
                                                                <c:if test="${not order.processed}">
                                                                    <input type="checkbox" name="processed" disabled="" />
                                                                </c:if>
                                                            </td>
                                                            <td>
                                                                <c:if test="${order.rejected}">
                                                                    <input type="checkbox" name="rejected" disabled="" checked="" />
                                                                </c:if>
                                                                <c:if test="${not order.rejected}">
                                                                    <input type="checkbox" name="rejected" disabled="" />
                                                                </c:if>
                                                            </td>
                                                            <td><c:out value="${order.rejectDesc}" /></td>
                                                            <td>
                                                                <c:if test="${order.picked}">
                                                                    <input type="checkbox" name="picked" disabled="" checked="" />
                                                                </c:if>
                                                                <c:if test="${not order.picked}">
                                                                    <input type="checkbox" name="picked" disabled="" />
                                                                </c:if>
                                                            </td>
                                                            <td>
                                                                <c:if test="${order.returned}">
                                                                    <input type="checkbox" name="returned" disabled="" checked="" />
                                                                </c:if>
                                                                <c:if test="${not order.returned}">
                                                                    <input type="checkbox" name="returned" disabled="" />
                                                                </c:if>
                                                            </td>
                                                            <td>
                                                                <c:if test="${order.damaged}">
                                                                    <input type="checkbox" name="damaged" disabled="" checked="" />
                                                                </c:if>
                                                                <c:if test="${not order.damaged}">
                                                                    <input type="checkbox" name="damaged" disabled="" />
                                                                </c:if>
                                                            </td>
                                                            <td><c:out value="${order.damageDesc}" /></td>
                                                            <td><c:out value="${order.damageCost}" /></td>
                                                            <td>
                                                                <c:if test="${order.paid}">
                                                                    <input type="checkbox" name="paid" disabled="" checked="" />
                                                                </c:if>
                                                                <c:if test="${not order.paid}">
                                                                    <input type="checkbox" name="paid" disabled="" />
                                                                </c:if>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </c:if>
                                                <c:if test="${!empty requestScope.orderID}">
                                                    <tr>
                                                        <td><input type="radio" name="orderChoice" value="${orderList[requestScope.orderID-1].orderID}" checked="" /></td>
                                                        <td><c:out value="${orderList[requestScope.orderID-1].vehicle.getDescription()}" /></td>
                                                        <td><c:out value="${orderList[requestScope.orderID-1].user.login}" /></td>
                                                        <td><c:out value="${orderList[requestScope.orderID-1].passport.getDescription()}" /></td>
                                                        <td><c:out value="${orderList[requestScope.orderID-1].pickUpDate}" /></td>
                                                        <td><c:out value="${orderList[requestScope.orderID-1].dropOffDate}" /></td>
                                                        <td><c:out value="${orderList[requestScope.orderID-1].rentCost}" /></td>
                                                        <td>
                                                            <c:if test="${orderList[requestScope.orderID-1].processed}">
                                                                <input type="checkbox" name="processed" disabled="" checked="" />
                                                            </c:if>
                                                            <c:if test="${not orderList[requestScope.orderID-1].processed}">
                                                                <input type="checkbox" name="processed" disabled="" />
                                                            </c:if>
                                                        </td>
                                                        <td>
                                                            <c:if test="${orderList[requestScope.orderID-1].rejected}">
                                                                <input type="checkbox" name="rejected" disabled="" checked="" />
                                                            </c:if>
                                                            <c:if test="${not orderList[requestScope.orderID-1].rejected}">
                                                                <input type="checkbox" name="rejected" disabled="" />
                                                            </c:if>
                                                        </td>
                                                        <td><c:out value="${orderList[requestScope.orderID-1].rejectDesc}" /></td>
                                                        <td>
                                                            <c:if test="${orderList[requestScope.orderID-1].picked}">
                                                                <input type="checkbox" name="picked" disabled="" checked="" />
                                                            </c:if>
                                                            <c:if test="${not orderList[requestScope.orderID-1].picked}">
                                                                <input type="checkbox" name="picked" disabled="" />
                                                            </c:if>
                                                        </td>
                                                        <td>
                                                            <c:if test="${orderList[requestScope.orderID-1].returned}">
                                                                <input type="checkbox" name="returned" disabled="" checked="" />
                                                            </c:if>
                                                            <c:if test="${not orderList[requestScope.orderID-1].returned}">
                                                                <input type="checkbox" name="returned" disabled="" />
                                                            </c:if>
                                                        </td>
                                                        <td>
                                                            <c:if test="${orderList[requestScope.orderID-1].damaged}">
                                                                <input type="checkbox" name="damaged" disabled="" checked="" />
                                                            </c:if>
                                                            <c:if test="${not orderList[requestScope.orderID-1].damaged}">
                                                                <input type="checkbox" name="damaged" disabled="" />
                                                            </c:if>
                                                        </td>
                                                        <td><c:out value="${orderList[requestScope.orderID-1].damageDesc}" /></td>
                                                        <td><c:out value="${orderList[requestScope.orderID-1].damageCost}" /></td>
                                                        <td>
                                                            <c:if test="${orderList[requestScope.orderID-1].paid}">
                                                                <input type="checkbox" name="paid" disabled="" checked="" />
                                                            </c:if>
                                                            <c:if test="${not orderList[requestScope.orderID-1].paid}">
                                                                <input type="checkbox" name="paid" disabled="" />
                                                            </c:if>
                                                        </td>
                                                    </tr>
                                                </c:if>
                                            </tbody>
                                        </table>
                                    </form>
                                    <form name="selectOrder" method="post" action="CarRentalServlet">
                                        <input type="hidden" name="command" value="loadOrderList" />
                                        <fmt:message key="admin.button.selectOrder" var="selectOrderButtonValue" />
                                        <button type="submit" class="btn btn-outline btn-info btn-lg btn-block">
                                            <c:if test="${empty requestScope.orderID}">
                                                <i class="fa fa-refresh"></i>
                                                <fmt:message key="admin.button.refresh" />
                                            </c:if>
                                            <c:if test="${!empty requestScope.orderID}">
                                                <i class="fa fa-expand"></i>
                                                <fmt:message key="admin.button.showAll" />
                                            </c:if>
                                        </button>
                                    </form>
                                </div>
                                <!-- /.table-responsive -->

                            </div>
                            <!-- /.panel-body -->
                        </div>
                        <!-- /.panel -->
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
                <c:if test="${!empty requestScope.orderID}">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-info">
                                <div class="panel-heading">
                                    <fmt:message key="admin.paneltitle.controlPanel" />
                                </div>
                                <div class="panel-body">
                                    <div class="row">
                                        <!--controls here-->
                                        <div class="col-lg-2">
                                            <div class="panel panel-default">
                                                <div class="panel-heading">
                                                    <fmt:message key="admin.paneltitle.confirm" />
                                                </div>
                                                <div class="panel-body">
                                                    <form name="confirmOrderButton" method="post" action="CarRentalServlet">
                                                        <input type="hidden" name="command" value="confirmOrder" />
                                                        <input type="hidden" name="orderID" value="${orderID}" />
                                                        <button type="submit" class="btn btn-outline btn-success btn-block">
                                                            <i class="fa fa-check-square-o"></i>
                                                        </button>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- /.col-lg-2 (nested) -->
                                        <div class="col-lg-3">
                                            <div class="panel panel-default">
                                                <div class="panel-heading">
                                                    <fmt:message key="admin.paneltitle.reject" />
                                                </div>
                                                <div class="panel-body">
                                                    <form name="rejectOrderButton" method="post" action="CarRentalServlet">
                                                        <input type="hidden" name="command" value="rejectOrder" />
                                                        <input type="hidden" name="orderID" value="${orderID}" />
                                                        <div class="form-group">
                                                            <button type="submit" class="btn btn-outline btn-warning btn-block">
                                                                <i class="fa fa-minus-square-o"></i>
                                                            </button> 
                                                        </div>
                                                        <div class="form-group">
                                                            <fmt:message key="admin.field.reject" var="rejectDescFieldValue" />
                                                            <input class="form-control" name="rejectDesc" placeholder="${rejectDescFieldValue}">
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- /.col-lg-3 (nested) -->
                                        <div class="col-lg-1">
                                            <div class="panel panel-default">
                                                <div class="panel-heading">
                                                    <fmt:message key="admin.paneltitle.give" />
                                                </div>
                                                <div class="panel-body">
                                                    <form name="giveVehicleButton" method="post" action="CarRentalServlet">
                                                        <input type="hidden" name="command" value="giveVehicle" />
                                                        <input type="hidden" name="orderID" value="${orderID}" />
                                                        <button type="submit" class="btn btn-outline btn-info btn-block">
                                                            <i class="fa fa-level-up"></i>
                                                        </button>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- /.col-lg-1 (nested) -->
                                        <div class="col-lg-1">
                                            <div class="panel panel-default">
                                                <div class="panel-heading">
                                                    <fmt:message key="admin.paneltitle.return" />
                                                </div>
                                                <div class="panel-body">
                                                    <form name="returnVehicleButton" method="post" action="CarRentalServlet">
                                                        <input type="hidden" name="command" value="returnVehicle" />
                                                        <input type="hidden" name="orderID" value="${orderID}" />
                                                        <button type="submit" class="btn btn-outline btn-info btn-block">
                                                            <i class="fa fa-level-down"></i>
                                                        </button>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- /.col-lg-1 (nested) -->
                                        <div class="col-lg-3">
                                            <div class="panel panel-default">
                                                <div class="panel-heading">
                                                    <fmt:message key="admin.paneltitle.returnDamaged" />
                                                </div>
                                                <div class="panel-body">
                                                    <form name="returnDamagedVehicleButton" method="post" action="CarRentalServlet">
                                                        <input type="hidden" name="command" value="returnDamagedVehicle" />
                                                        <input type="hidden" name="orderID" value="${orderID}" />
                                                        <div class="form-group">
                                                            <button type="submit" class="btn btn-outline btn-warning btn-block">
                                                                <i class="fa fa-warning"></i>
                                                            </button> 
                                                        </div>
                                                        <div class="form-group">
                                                            <fmt:message key="admin.field.damageDesc" var="damageDescField" />
                                                            <input class="form-control" name="damageDesc" placeholder="${damageDescField}">
                                                        </div>
                                                        <div class="form-group input-group">
                                                            <span class="input-group-addon"><i class="fa fa-money"></i>
                                                            </span>
                                                            <fmt:message key="admin.field.damageCost" var="damageCostField" />
                                                            <input class="form-control" type="number" name="damageCost" min="0" step="0.01" placeholder="${damageCostField}" required="" />
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- /.col-lg-3 (nested) -->
                                        <div class="col-lg-1">
                                            <div class="panel panel-default">
                                                <div class="panel-heading">
                                                    <fmt:message key="admin.paneltitle.setPaid" />
                                                </div>
                                                <div class="panel-body">
                                                    <form name="confirmPaymentButton" method="post" action="CarRentalServlet">
                                                        <input type="hidden" name="command" value="confirmPayment" />
                                                        <input type="hidden" name="orderID" value="${orderID}" />
                                                        <button type="submit" class="btn btn-outline btn-success btn-block">
                                                            <i class="fa fa-money"></i>
                                                        </button>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- /.col-lg-1 (nested) -->
                                        <div class="col-lg-1">
                                            <div class="panel panel-default">
                                                <div class="panel-heading">
                                                    <fmt:message key="admin.paneltitle.reset" />
                                                </div>
                                                <div class="panel-body">
                                                    <form name="resetOrderButton" method="post" action="CarRentalServlet">
                                                        <input type="hidden" name="command" value="resetOrder" />
                                                        <input type="hidden" name="orderID" value="${orderID}" />
                                                        <button type="submit" class="btn btn-outline btn-danger btn-block">
                                                            <i class="fa fa-eraser"></i>
                                                        </button>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- /.col-lg-1 (nested) -->
                                    </div>
                                    <!-- /.row -->
                                </div>
                                <!-- /.panel-body -->
                            </div>
                            <!-- /.panel -->
                        </div>
                        <!-- /.col-lg-12 -->
                    </div>
                    <!-- /.row -->
                </c:if>
            </div>
            <!-- /#page-wrapper -->

        </div>
        <!-- /#wrapper -->

        <!-- Core Scripts - Include with every page -->
        <script src="js/jquery-1.10.2.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>

        <!-- Page-Level Plugin Scripts - Dashboard -->
        <script src="js/plugins/morris/raphael-2.1.0.min.js"></script>
        <script src="js/plugins/morris/morris.js"></script>

        <!-- SB Admin Scripts - Include with every page -->
        <script src="js/sb-admin.js"></script>

    </body>

</html>