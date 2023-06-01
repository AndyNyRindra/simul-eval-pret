<%@ page import="custom.springutils.util.ListResponse" %>
<%@ page import="java.util.List" %>
<%@ page import="com.eval1.models.duration.Duration" %>
<%@ page import="com.eval1.models.duration.DurationFilter" %>
<%@ page import="com.eval1.models.maxAmount.MaxAmount" %>
<%@ page import="com.eval1.models.maxAmount.MaxAmountFilter" %>
<%@ page import="com.eval1.models.VTotalRembourses" %>
<%@ page import="com.eval1.models.MonthYearFilter" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.eval1.models.VBenefice" %>

<%@include file="../includes/layouts/default/top.jsp"%>
<%
    List<VBenefice> benefices = (List<VBenefice>) request.getAttribute("benefices");
    Integer requiredPages = (Integer) request.getAttribute("requiredPages");
    Integer pageNumber = (Integer) request.getAttribute("page");
    MonthYearFilter monthYearFilter = (MonthYearFilter) request.getAttribute("monthYearFilter");
    String filters = "";
    if (monthYearFilter != null) {
        filters = monthYearFilter.getFilterConditions();
    }
    HashMap<Integer, String> months = (HashMap<Integer, String>) request.getAttribute("months");
%>
<head>
    <title>Bénéfices par mois</title>
</head>
<!--begin::main-->
<div class="d-flex flex-column flex-column-fluid">
    <!--begin::toolbar-->
    <div class="app-toolbar py-3 py-lg-6">
        <div class="app-container container-xxl d-flex flex-stack">
            <div class="page-title d-flex flex-column justify-content-center flex-wrap me-3">
                <h1 class="page-heading d-flex text-dark fw-bold fs-3 flex-column justify-content-center my-0">
                    Bénéfices par mois
                </h1>
                <ul class="breadcrumb breadcrumb-separatorless fw-semibold fs-7 my-0 pt-1">
                    <li class="breadcrumb-item text-muted">
                        Bénéfices par mois
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
                        <%--                        <a href="${pageContext.request.contextPath}/stats/totalRembourses/create" class="btn btn-success">--%>
                        <%--                            Nouveau Bénéfices par mois--%>
                        <%--                        </a>--%>
                        <!--end::Add product-->
                    </div>
                </div>
                <!--end::card header-->
                <!--begin::card body-->
                <div class="card-body pt-0">
                    <div class="accordion-body">
                        <form method="get">
                            <div class="mb-5">
                                <label>Mois :</label>
                                <select name="mois" class="form-select"
                                        data-control="select2" data-placeholder="Mois"
                                        data-allow-clear="true" >
                                    <option value="" >--Mois--</option>
                                    <% for(Integer i : months.keySet()) { %>
                                    <option value="<%= i %>" <%= monthYearFilter != null && monthYearFilter.getMois() == i ? "selected" : "" %>><%= months.get(i) %></option>
                                    <% } %>
                                </select>
                            </div>
                            <div class="mb-5">
                                <input id="year" type="number" name="annee" class="form-control" placeholder="Année..."
                                    <% if (monthYearFilter != null && monthYearFilter.getAnnee() != null) { %>
                                       value="<%=monthYearFilter.getAnnee()%>"
                                    <% } %>
                                >
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
                            <th>Mois</th>
                            <th>Année</th>
                            <th>Total Empruntés</th>
                            <th>Total Remboursés</th>
                            <th>Bénéfice</th>
                        </tr>
                        </thead>
                        <tbody>
                        <% for(VBenefice benefice : benefices) { %>
                        <tr>
                            <td>
                                <%= benefice.getMois() %>
                            </td>
                            <td>
                                <%= benefice.getAnnee() %>
                            </td>
                            <td>
                                <%= benefice.getTotalempruntes() %> Ar
                            </td>
                            <td>
                                <%= benefice.getTotalrembourses() %> Ar
                            </td>
                            <td>
                                <%= benefice.getBenefice() %> Ar
                            </td>
                            <%--                            <td>--%>
                            <%--                                <a href="${pageContext.request.contextPath}/stats/totalRembourses/update/<%= maxAmount.getId() %>" >--%>
                            <%--                                    <i class="la la-pencil text-warning fs-2x"></i>--%>
                            <%--                                </a>--%>
                            <%--                            </td>--%>
                            <%--                            <td>--%>
                            <%--                                <a href="#" onclick="onDeleteButtonClicked(<%= maxAmount.getId() %>, '<%= maxAmount.getDuration() %>', '${pageContext.request.contextPath}/maxAmounts/delete/<%=maxAmount.getId()%>', 'la durée')"--%>
                            <%--                                   data-bs-target="#delete-modal" data-bs-toggle="modal">--%>
                            <%--                                    <i class="la la-trash text-danger fs-2x"></i>--%>
                            <%--                                </a>--%>
                            <%--                            </td>--%>
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
                        ><a href="${pageContext.request.contextPath}/stats/benefices?<%=filters%>&page=<%=pageNumber-1%>" class="page-link"><i class="previous"></i></a></li>
                        <% for (int i = 1 ; i <= requiredPages ; i++) { %>
                        <li
                                <% if (pageNumber == i) { %>
                                class="page-item active"
                                <% } else { %>
                                class="page-item"
                                <% } %>
                        ><a href="${pageContext.request.contextPath}/stats/benefices?<%=filters%>&page=<%=i%>" class="page-link"><%=i%></a></li>
                        <% } %>
                        <li
                                <% if (pageNumber == requiredPages) { %>
                                class="page-item next disabled"
                                <% } else { %>
                                class="page-item next"
                                <% } %>
                        ><a href="${pageContext.request.contextPath}/stats/benefices?<%=filters%>&page=<%=pageNumber+1%>"  class="page-link"><i class="next"></i></a></li>
                    </ul>

                </div>
                <!--end::card body-->
            </div>
            <!--end::card-->
        </div>
    </div>
    <!--end:content-->
</div>
<%@include file="../includes/delete.jsp"%>

<!--end::main-->
<script src="${pageContext.request.contextPath}/assets/custom/elementDelete.js"></script>
<%@include file="../includes/layouts/default/bottom.jsp"%>
