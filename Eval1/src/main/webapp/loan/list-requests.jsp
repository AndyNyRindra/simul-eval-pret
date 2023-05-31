<%@ page import="custom.springutils.util.ListResponse" %>
<%@ page import="java.util.List" %>
<%@ page import="com.eval1.models.duration.Duration" %>
<%@ page import="com.eval1.models.duration.DurationFilter" %>
<%@ page import="com.eval1.models.loan.LoanRequest" %>
<%@ page import="com.eval1.models.loan.LoanFilter" %>
<%@ page import="com.eval1.models.Status" %>
<%@ page import="com.eval1.models.appUser.AppUser" %>

<%
    List<LoanRequest> requests = (List<LoanRequest>) request.getAttribute("requests");
    Integer requiredPages = (Integer) request.getAttribute("requiredPages");
    Integer pageNumber = (Integer) request.getAttribute("page");
    LoanFilter loanFilter = (LoanFilter) request.getAttribute("loanFilter");
    String filters = "";
    if (loanFilter != null) {
        filters = loanFilter.getFilterConditions();
    }
%>
<%
    List<Status> status = (List<Status>) request.getAttribute("status");
    AppUser appUser = (AppUser) session.getAttribute("appUser");
%>
<% if (appUser.isAdmin()) { %>
    <%@include file="../includes/layouts/default/top.jsp"%>
<% } else { %>
    <%@include file="../includes/layouts/default/top-client.jsp"%>
<% } %>
<head>
    <title>Demandes de pret</title>
</head>
<!--begin::main-->
<div class="d-flex flex-column flex-column-fluid">
    <!--begin::toolbar-->
    <div class="app-toolbar py-3 py-lg-6">
        <div class="app-container container-xxl d-flex flex-stack">
            <div class="page-title d-flex flex-column justify-content-center flex-wrap me-3">
                <h1 class="page-heading d-flex text-dark fw-bold fs-3 flex-column justify-content-center my-0">
                    Demandes de pret
                </h1>
                <ul class="breadcrumb breadcrumb-separatorless fw-semibold fs-7 my-0 pt-1">
                    <li class="breadcrumb-item text-muted">
                        Demandes de pret
                    </li>
                    <li class="breadcrumb-item">
                        <span class="bullet bg-gray-400 w-5px h-2px"></span>
                    </li>
                    <li class="breadcrumb-item text-muted">
                        Liste
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!--end::toolbar-->
    <!--begin::content-->
    <div class="app-content flex-column-fluid">
        <div class="app-container container-xxl">
            <!--begin::card-->
            <div class="card card-flush">
                <!--begin::card header-->
                <div class="card-header align-items-center py-0 gap-2">
                    <div class="card-toolbar flex-row-fluid justify-content-end gap-5" data-select2-id="select2-data-123-mzxj">
                        <!--begin::Add product-->
                        <a href="${pageContext.request.contextPath}/loan/request/create" class="btn btn-success">
                            Ajouter une demande
                        </a>
                        <!--end::Add product-->
                    </div>
                </div>
                <!--end::card header-->
                <!--begin::card body-->
                <div class="card-body pt-0">
                    <div class="accordion-body">
                        <form method="get">
                            <div class="row">
                                <div class="col-sm-6 mb-5">
                                    <input type="text" name="mineq_amount" class="form-control" placeholder="Montant minimum..."
                                        <% if (loanFilter != null && loanFilter.getMineq_amount() != null) { %>
                                           value="<%=loanFilter.getMineq_amount()%>"
                                        <% } %>
                                    >
                                </div>
                                <div class="col-sm-6 mb-5">
                                    <input type="text" name="maxeq_amount" class="form-control" placeholder="Montant maximum..."
                                        <% if (loanFilter != null && loanFilter.getMaxeq_amount() != null) { %>
                                           value="<%=loanFilter.getMaxeq_amount()%>"
                                        <% } %>
                                    >
                                </div>
                            </div>

                            <div class="row">
                                <% if (appUser.isAdmin()) { %>
                                <div class="col-sm-6 mb-5">
                                    <input type="text" name="clientName" class="form-control" placeholder="Client"
                                        <% if (loanFilter != null && loanFilter.getClientName() != null) { %>
                                           value="<%=loanFilter.getClientName().replace("%", "")%>"
                                        <% } %>
                                    >
                                </div>
                                <% } %>
                                <div class="col-sm-6 mb-5">
                                    <select name="statusId" class="form-select"
                                            data-control="select2" data-placeholder="Status"
                                            data-allow-clear="true" >
                                        <option value="" >--Status--</option>
                                        <% for (Status status1 : status
                                        ) { %>
                                        <option value="<%= status1.getId() %>"
                                        <% if (loanFilter != null && loanFilter.getStatusId() != null && loanFilter.getStatusId().equals(status1.getId())) { %>
                                                selected
                                                <% } %>
                                        > <%= status1.getName() %></option>
                                        <% } %>
                                    </select>
                                </div>
                            </div>


                            <button class="btn btn-primary" type="submit">
                                Filtrer
                            </button>

                        </form>
                    </div>
                    <!--begin::table-->
                    <table class="table table-row-bordered gy-5" id="scenes-list">
                        <thead>
                        <tr class="fw-semibold fs-6 text-muted">
                            <th>Id</th>
                            <th>Client</th>
                            <th>Montant</th>
                            <th>Date</th>
                            <th>Durée</th>
                            <th>Taux</th>
                            <th>Status</th>
                        </tr>
                        </thead>
                        <tbody>
                        <% for(LoanRequest loanRequest : requests) { %>
                        <tr>
                            <td><%=loanRequest.getId()%></td>
                            <td><%=loanRequest.getClient().getName()%></td>
                            <td><%=loanRequest.getAmount()%> Ar</td>
                            <td><%=loanRequest.getDate()%></td>
                            <td>
                                <%= loanRequest.getDuration().getDuration() %> Mois
                            </td>
                            <td><%=loanRequest.getRate()%> %</td>
                            <td style="color: <%=loanRequest.getStatus().getColor()%>"><%=loanRequest.getStatus().getName()%></td>

                            <% if (appUser.isAdmin()) { %>
                                <% if (loanRequest.getStatus().getId() == 0L) { %>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/loan/requests/update/<%= loanRequest.getId() %>" >
                                            <i class="la la-pencil text-warning fs-2x"></i>
                                        </a>
                                    </td>
                                    <td>
                                        <a href="#" onclick="onDeleteButtonClicked(<%= loanRequest.getId() %>, '<%= loanRequest.getClient().getName() %>', '${pageContext.request.contextPath}/loan/refuse/<%=loanRequest.getId()%>', '')"
                                           data-bs-target="#delete-modal" data-bs-toggle="modal">
                                            <i class="la la-close text-danger fs-2x"></i>
                                        </a>
                                    </td>
                                <% } %>
                            <% } %>
                        </tr>
                        <% } %>
                        </tbody>
                    </table>
                    <ul class="pagination">
                        <li
                                <% if (pageNumber == 1) { %>
                                class="page-item previous disabled"
                                <% } else { %>
                                class="page-item previous"
                                <% } %>
                        ><a href="${pageContext.request.contextPath}/loan/requests?<%=filters%>&page=<%=pageNumber-1%>" class="page-link"><i class="previous"></i></a></li>
                        <% for (int i = 1 ; i <= requiredPages ; i++) { %>
                        <li
                                <% if (pageNumber == i) { %>
                                class="page-item active"
                                <% } else { %>
                                class="page-item"
                                <% } %>
                        ><a href="${pageContext.request.contextPath}/loan/requests?<%=filters%>&page=<%=i%>" class="page-link"><%=i%></a></li>
                        <% } %>
                        <li
                                <% if (pageNumber == requiredPages) { %>
                                class="page-item next disabled"
                                <% } else { %>
                                class="page-item next"
                                <% } %>
                        ><a href="${pageContext.request.contextPath}/loan/requests?<%=filters%>&page=<%=pageNumber+1%>"  class="page-link"><i class="next"></i></a></li>
                    </ul>

                </div>
                <!--end::card body-->
            </div>
            <!--end::card-->
        </div>
    </div>
    <!--end:content-->
</div>
<%@include file="../includes/refuse.jsp"%>

<!--end::main-->
<script src="${pageContext.request.contextPath}/assets/custom/elementDelete.js"></script>
<%@include file="../includes/layouts/default/bottom.jsp"%>
