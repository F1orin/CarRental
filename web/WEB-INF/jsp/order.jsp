<%-- 
    Document   : order
    Created on : 27.04.2014, 9:50:12
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

        <crtag:loadVehicleList/>

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
                        <h1 class="page-header"><fmt:message key="order.pageheader" /></h1>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <fmt:message key="order.paneltitle" />
                            </div>
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-lg-6">
                                        <form role="form" name="calculateCost" method="post" action="CarRentalServlet">
                                            <input type="hidden" name="command" value="calculateCost"/>
                                            <h2><fmt:message key="order.label.selectVehicle" /></h2>
                                            <div class="form-group">
                                                <div class="table-responsive">
                                                    <table class="table table-striped table-bordered table-hover">
                                                        <thead>
                                                            <tr>
                                                                <th><fmt:message key="table.vehicle.choice" /></th>
                                                                <th><fmt:message key="table.vehicle.make" /></th>
                                                                <th><fmt:message key="table.vehicle.model" /></th>		
                                                                <th><fmt:message key="table.vehicle.gearbox" /></th>
                                                                <th><fmt:message key="table.vehicle.airConditioner" /></th>
                                                                <th><fmt:message key="table.vehicle.seats" /></th>
                                                                <th><fmt:message key="table.vehicle.price" /></th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:if test="${empty requestScope.vehicleID}">
                                                                <c:forEach items="${vehicleList}" var="vehicle">
                                                                    <tr>
                                                                        <td><input type="radio" name="vehicleChoice" value="${vehicle.vehicleID}" checked="" /></td>
                                                                        <td><c:out value="${vehicle.make}" /></td>
                                                                        <td><c:out value="${vehicle.model}" /></td>
                                                                        <td>
                                                                            <c:if test="${vehicle.autoGearbox}">
                                                                                <input type="checkbox" name="autoGearbox" disabled="" checked="" />
                                                                            </c:if>
                                                                            <c:if test="${not vehicle.autoGearbox}">
                                                                                <input type="checkbox" name="autoGearbox" disabled="" />
                                                                            </c:if>
                                                                        </td>
                                                                        <td>
                                                                            <c:if test="${vehicle.airConditioner}">
                                                                                <input type="checkbox" name="airConditioner" disabled="" checked="" />
                                                                            </c:if>
                                                                            <c:if test="${not vehicle.airConditioner}">
                                                                                <input type="checkbox" name="airConditioner" disabled="" />
                                                                            </c:if>
                                                                        </td>
                                                                        <td><c:out value="${vehicle.seats}" /></td>
                                                                        <td><c:out value="${vehicle.dailyPrice}" /></td>
                                                                    </tr>
                                                                </c:forEach>
                                                            </c:if>
                                                            <c:if test="${!empty requestScope.vehicleID}">
                                                                <tr>
                                                                    <td><input type="radio" name="vehicleChoice" value="${vehicleList[requestScope.vehicleID-1].vehicleID}" checked="" /></td>
                                                                    <td><c:out value="${vehicleList[requestScope.vehicleID-1].make}" /></td>
                                                                    <td><c:out value="${vehicleList[requestScope.vehicleID-1].model}" /></td>
                                                                    <td>
                                                                        <c:if test="${vehicleList[requestScope.vehicleID-1].autoGearbox}">
                                                                            <input type="checkbox" name="autoGearbox" disabled="" checked="" />
                                                                        </c:if>
                                                                        <c:if test="${not vehicleList[requestScope.vehicleID-1].autoGearbox}">
                                                                            <input type="checkbox" name="autoGearbox" disabled="" />
                                                                        </c:if>
                                                                    </td>
                                                                    <td>
                                                                        <c:if test="${vehicleList[requestScope.vehicleID-1].airConditioner}">
                                                                            <input type="checkbox" name="airConditioner" disabled="" checked="" />
                                                                        </c:if>
                                                                        <c:if test="${not vehicleList[requestScope.vehicleID-1].airConditioner}">
                                                                            <input type="checkbox" name="airConditioner" disabled="" />
                                                                        </c:if>
                                                                    </td>
                                                                    <td><c:out value="${vehicleList[requestScope.vehicleID-1].seats}" /></td>
                                                                    <td><c:out value="${vehicleList[requestScope.vehicleID-1].dailyPrice}" /></td>
                                                                </tr>
                                                            </c:if>
                                                        </tbody>
                                                    </table>
                                                </div>
                                                <!-- /.table-responsive -->
                                            </div>
                                            <h2><fmt:message key="order.label.chooseDates" /></h2>             
                                            <div class="form-group">
                                                <label><fmt:message key="order.label.pickUp" /></label>
                                                <input class="form-control" type="datetime-local" name="pickUpDate" min="1970-01-01T00:01" max="2038-01-19T03:14" value="${pickUpDate}" required="" />
                                            </div>
                                            <div class="form-group">
                                                <label><fmt:message key="order.label.dropOff" /></label>
                                                <input class="form-control" type="datetime-local" name="dropOffDate" min="1970-01-01T00:01" max="2038-01-19T03:14" value="${dropOffDate}" required="" />
                                            </div>
                                            <h2><fmt:message key="order.label.calculateCost" /></h2>
                                            <div class="form-group input-group">
                                                <span class="input-group-addon"><i class="fa fa-money"></i>
                                                </span>
                                                <input type="text" name="rentCost" class="form-control" value="${rentCost}" disabled="" >
                                            </div>
                                            <button type="submit" class="btn btn-default"><fmt:message key="order.button.calculateCost" /></button>
                                        </form>
                                    </div>
                                    <!-- /.col-lg-6 (nested) -->
                                    <div class="col-lg-6">
                                        <h2><fmt:message key="order.label.fillPassport" /></h2>
                                        <form role="form" name="orderForm" method="POST" action="CarRentalServlet">
                                            <input type="hidden" name="command" value="createOrder"/>
                                            <input type="hidden" name="vehicleID" value="${vehicleID}"/>
                                            <input type="hidden" name="pickUpDate" value="${pickUpDate}"/>
                                            <input type="hidden" name="dropOffDate" value="${dropOffDate}"/>
                                            <input type="hidden" name="rentCost" value="${rentCost}"/>
                                            <label><fmt:message key="order.label.fullName" /></label>
                                            <div class="form-group">
                                                <fmt:message key="order.label.lastName" var="lastName" />
                                                <input type="text" class="form-control" name="lastName" placeholder="${lastName}" required="">
                                            </div>
                                            <div class="form-group">
                                                <fmt:message key="order.label.firstName" var="firstName" />
                                                <input type="text" class="form-control" name="firstName" placeholder="${firstName}" required="">
                                            </div>
                                            <div class="form-group">
                                                <fmt:message key="order.label.patronymic" var="patronymic" />
                                                <input type="text" class="form-control" name="patronymic" placeholder="${patronymic}">
                                            </div>
                                            <label><fmt:message key="order.label.birthday" /></label>
                                            <div class="form-group">
                                                <input type="date" class="form-control" name="birthday" min="1000-01-01" max="9999-12-31" required="">
                                            </div>
                                            <label><fmt:message key="order.label.pData" /></label>
                                            <div class="form-group">
                                                <fmt:message key="order.label.pSeries" var="pSeries" />
                                                <input type="text" class="form-control" name="pSeries" maxlength="2" placeholder="${pSeries}" required="">
                                            </div>
                                            <div class="form-group">
                                                <fmt:message key="order.label.pNumber" var="pNumber" />
                                                <input type="text" class="form-control" name="pNumber" maxlength="6" placeholder="${pNumber}" required="">
                                            </div>
                                            <div class="form-group">
                                                <fmt:message key="order.label.whoIssued" var="whoIssued" />
                                                <textarea class="form-control" rows="2" name="whoIssued" placeholder="${whoIssued}" required="" ></textarea>
                                            </div>
                                            <div class="form-group">
                                                <p class="help-block"><fmt:message key="order.label.whenIssued" /></p>
                                                <input type="date" class="form-control" name="whenIssued" min="1000-01-01" max="9999-12-31" required="">
                                            </div>
                                            <button type="submit" class="btn btn-primary"><fmt:message key="order.button.createOrder" /></button>
                                        </form>
                                    </div>
                                    <!-- /.col-lg-6 (nested) -->
                                </div>
                                <!-- /.row (nested) -->
                            </div>
                            <!-- /.panel-body -->
                        </div>
                        <!-- /.panel -->
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
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