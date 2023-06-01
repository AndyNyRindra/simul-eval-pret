<%@ page import="java.util.List" %>
<%@ page import="com.eval1.models.loan.LoanRequest" %>
<%@ page import="com.eval1.models.loan.LoanRequestFilter" %>
<%@ page import="com.eval1.models.Status" %>
<%@ page import="com.eval1.models.appUser.AppUser" %>
<%@ page import="com.eval1.models.loan.Loan" %>
<%@ page import="com.eval1.models.loan.LoanFilter" %>

<%
    List<Loan> requests = (List<Loan>) request.getAttribute("requests");
    Integer requiredPages = (Integer) request.getAttribute("requiredPages");
    Integer pageNumber = (Integer) request.getAttribute("page");
    LoanFilter loanFilter = (LoanFilter) request.getAttribute("loanFilter");
    String filters = "";
    if (loanFilter != null) {
        filters = loanFilter.getFilterConditions();
    }
%>
<%
    AppUser appUser = (AppUser) session.getAttribute("appUser");
%>
<% if (appUser.isAdmin()) { %>
<%@include file="../includes/layouts/default/top.jsp"%>
<% } else { %>
<%@include file="../includes/layouts/default/top-client.jsp"%>
<% } %>
<head>
    <title>Prets</title>
</head>
<!--begin::main-->
<div class="d-flex flex-column flex-column-fluid">
    <!--begin::toolbar-->
    <div class="app-toolbar py-3 py-lg-6">
        <div class="app-container container-xxl d-flex flex-stack">
            <div class="page-title d-flex flex-column justify-content-center flex-wrap me-3">
                <h1 class="page-heading d-flex text-dark fw-bold fs-3 flex-column justify-content-center my-0">
                    Prets
                </h1>
                <ul class="breadcrumb breadcrumb-separatorless fw-semibold fs-7 my-0 pt-1">
                    <li class="breadcrumb-item text-muted">
                        Prets
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
                        <% if(!appUser.isAdmin()) { %>
                        <a href="${pageContext.request.contextPath}/loan/request/create" class="btn btn-success">
                            Ajouter une demande
                        </a>
                        <% } %>
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
                                <div class="mb-5">
                                    <input type="text" name="clientName" class="form-control" placeholder="Client"
                                        <% if (loanFilter != null && loanFilter.getClientName() != null) { %>
                                           value="<%=loanFilter.getClientName().replace("%", "")%>"
                                        <% } %>
                                    >
                                </div>
                                <% } %>

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

                        </tr>
                        </thead>
                        <tbody>
                        <% for(Loan loan : requests) { %>
                        <tr>
                            <td><%=loan.getId()%></td>
                            <td><%=loan.getRequest().getClient().getName()%></td>
                            <td><%=loan.getRequest().getAmount()%> Ar</td>
                            <td><%=loan.getDate()%></td>
                            <td>
                                <%= loan.getRequest().getDuration().getDuration() %> Mois
                            </td>
                            <td><%=loan.getRequest().getRate()%> %</td>

<%--                            <td style="color: <%=loan.getStatus().getColor()%>"><%=loan.getStatus().getName()%></td>--%>

                            <td>
                                <a href="${pageContext.request.contextPath}/loan/<%= loan.getId() %>/reimbursements" >
                                    <i class="la la-eye text-primary fs-2x"></i>
                                </a>
                            </td>

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
                        ><a href="${pageContext.request.contextPath}/loan?<%=filters%>&page=<%=pageNumber-1%>" class="page-link"><i class="previous"></i></a></li>
                        <% for (int i = 1 ; i <= requiredPages ; i++) { %>
                        <li
                                <% if (pageNumber == i) { %>
                                class="page-item active"
                                <% } else { %>
                                class="page-item"
                                <% } %>
                        ><a href="${pageContext.request.contextPath}/loan?<%=filters%>&page=<%=i%>" class="page-link"><%=i%></a></li>
                        <% } %>
                        <li
                                <% if (pageNumber == requiredPages) { %>
                                class="page-item next disabled"
                                <% } else { %>
                                class="page-item next"
                                <% } %>
                        ><a href="${pageContext.request.contextPath}/loan?<%=filters%>&page=<%=pageNumber+1%>"  class="page-link"><i class="next"></i></a></li>
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

<div class="modal fade" tabindex="-1" id="accept-modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title">Accepter</h3>

                <!--begin::Close-->
                <div class="btn btn-icon btn-sm btn-active-light-primary ms-2" data-bs-dismiss="modal" aria-label="Close">
                    <i class="fa-solid fa-xmark fs-1"></i>
                </div>
                <!--end::Close-->
            </div>

            <form method="post" id="accept-form">
                <div class="modal-body">
                    <div class="mb-5">
                        <label>Accepté le :</label>
                        <input type="date" name="date" class="form-control" required
                        >
                    </div>

                    <div class="mb-5">
                        <label>Début du remboursement :</label>
                        <input type="date" name="startReimbursement" class="form-control" required
                        >
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="reset" class="btn btn-light" data-bs-dismiss="modal">Annuler</button>
                    <button type="submit" class="btn btn-success" id="accept-url">Accepter</button>
                </div>
            </form>

            <script>
                const form = document.getElementById('accept-form');

                form.addEventListener('submit', function(evnt) {
                    evnt.preventDefault();
                    const formData = new FormData(form);
                    send(formData, form.action, "${pageContext.request.contextPath}/loan/requests")
                });
            </script>



        </div>
    </div>
</div>

<!--end::main-->
<script src="${pageContext.request.contextPath}/assets/custom/elementDelete.js"></script>
<script src="${pageContext.request.contextPath}/assets/custom/elementAccept.js"></script>

<%@include file="../includes/layouts/default/bottom.jsp"%>
