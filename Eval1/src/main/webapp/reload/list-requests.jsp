<%@ page import="custom.springutils.util.ListResponse" %>
<%@ page import="java.util.List" %>
<%@ page import="com.eval1.models.duration.Duration" %>
<%@ page import="com.eval1.models.duration.DurationFilter" %>
<%@ page import="com.eval1.models.reload.ReloadRequest" %>
<%@ page import="com.eval1.models.reload.ReloadFilter" %>
<%@ page import="com.eval1.models.Status" %>
<%@ page import="com.eval1.models.reload.ReloadRequest" %>
<%@ page import="com.eval1.models.reload.ReloadFilter" %>

<%@include file="../includes/layouts/default/top.jsp"%>
<%
    List<ReloadRequest> requests = (List<ReloadRequest>) request.getAttribute("requests");
    Integer requiredPages = (Integer) request.getAttribute("requiredPages");
    Integer pageNumber = (Integer) request.getAttribute("page");
    ReloadFilter reloadFilter = (ReloadFilter) request.getAttribute("reloadFilter");
    String filters = "";
    if (reloadFilter != null) {
        filters = reloadFilter.getFilterConditions();
    }
%>
<%
    List<Status> status = (List<Status>) request.getAttribute("status");
    AppUser appUser = (AppUser) session.getAttribute("appUser");
%>
<head>
    <title>Demandes de rechargement</title>
</head>
<!--begin::main-->
<div class="d-flex flex-column flex-column-fluid">
    <!--begin::toolbar-->
    <div class="app-toolbar py-3 py-lg-6">
        <div class="app-container container-xxl d-flex flex-stack">
            <div class="page-title d-flex flex-column justify-content-center flex-wrap me-3">
                <h1 class="page-heading d-flex text-dark fw-bold fs-3 flex-column justify-content-center my-0">
                    Demandes de rechargement
                </h1>
                <ul class="breadcrumb breadcrumb-separatorless fw-semibold fs-7 my-0 pt-1">
                    <li class="breadcrumb-item text-muted">
                        Demandes de rechargement
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
                        <a href="${pageContext.request.contextPath}/reload/request/create" class="btn btn-success">
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
                                        <% if (reloadFilter != null && reloadFilter.getMineq_amount() != null) { %>
                                           value="<%=reloadFilter.getMineq_amount()%>"
                                        <% } %>
                                    >
                                </div>
                                <div class="col-sm-6 mb-5">
                                    <input type="text" name="maxeq_amount" class="form-control" placeholder="Montant maximum..."
                                        <% if (reloadFilter != null && reloadFilter.getMaxeq_amount() != null) { %>
                                           value="<%=reloadFilter.getMaxeq_amount()%>"
                                        <% } %>
                                    >
                                </div>
                            </div>

                            <div class="row">
                                <% if (appUser.isAdmin()) { %>
                                <div class="col-sm-6 mb-5">
                                    <input type="text" name="clientName" class="form-control" placeholder="Client"
                                        <% if (reloadFilter != null && reloadFilter.getClientName() != null) { %>
                                           value="<%=reloadFilter.getClientName().replace("%", "")%>"
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
                                                <% if (reloadFilter != null && reloadFilter.getStatusId() != null && reloadFilter.getStatusId().equals(status1.getId())) { %>
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
                            <th>Status</th>
                        </tr>
                        </thead>
                        <tbody>
                        <% for(ReloadRequest reloadRequest : requests) { %>
                        <tr>
                            <td><%=reloadRequest.getId()%></td>
                            <td><%=reloadRequest.getClient().getName()%></td>
                            <td><%=reloadRequest.getAmount()%> Ar</td>
                            <td><%=reloadRequest.getDate()%></td>
                            <td style="color: <%=reloadRequest.getStatus().getColor()%>"><%=reloadRequest.getStatus().getName()%></td>

                            <% if (appUser.isAdmin()) { %>
                                <% if (reloadRequest.getStatus().getId() == 0L) { %>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/reload/requests/update/<%= reloadRequest.getId() %>" >
                                            <i class="la la-pencil text-warning fs-2x"></i>
                                        </a>
                                    </td>
                                    <td>
                                        <a href="#" onclick="onDeleteButtonClicked(<%= reloadRequest.getId() %>, '<%= reloadRequest.getClient().getName() %>', '${pageContext.request.contextPath}/reload/refuse/<%=reloadRequest.getId()%>', '')"
                                           data-bs-target="#delete-modal" data-bs-toggle="modal">
                                            <i class="la la-close text-danger fs-2x"></i>
                                        </a>
                                    </td>
                                <% } else if (reloadRequest.getStatus().getId() == 10L) { %>
                                    <td>
                                        <a href="#" onclick="onRestoreButtonClicked(<%= reloadRequest.getId() %>, '<%= reloadRequest.getClient().getName() %>', '${pageContext.request.contextPath}/reload/restore/<%=reloadRequest.getId()%>')"
                                           data-bs-target="#restore-modal" data-bs-toggle="modal">
                                            <i class="la la-undo text-primary fs-2x"></i>
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
                        ><a href="${pageContext.request.contextPath}/reload/requests?<%=filters%>&page=<%=pageNumber-1%>" class="page-link"><i class="previous"></i></a></li>
                        <% for (int i = 1 ; i <= requiredPages ; i++) { %>
                        <li
                                <% if (pageNumber == i) { %>
                                class="page-item active"
                                <% } else { %>
                                class="page-item"
                                <% } %>
                        ><a href="${pageContext.request.contextPath}/reload/requests?<%=filters%>&page=<%=i%>" class="page-link"><%=i%></a></li>
                        <% } %>
                        <li
                                <% if (pageNumber == requiredPages) { %>
                                class="page-item next disabled"
                                <% } else { %>
                                class="page-item next"
                                <% } %>
                        ><a href="${pageContext.request.contextPath}/reload/requests?<%=filters%>&page=<%=pageNumber+1%>"  class="page-link"><i class="next"></i></a></li>
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
<%@include file="../includes/restore.jsp"%>

<!--end::main-->
<script src="${pageContext.request.contextPath}/assets/custom/elementDelete.js"></script>
<script src="${pageContext.request.contextPath}/assets/custom/elementRestore.js"></script>

<%@include file="../includes/layouts/default/bottom.jsp"%>
