<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<c:url value="/admin/user/list" var="userList"/>
<c:url var="userUrl" value="/api/user"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Danh sách tòa nhà</title>
</head>

<body>
<div class="main-content">
    <form:form action="${userList}" id="formSubmit" method="get" commandName="model">

        <div class="main-content-inner">
            <div class="breadcrumbs ace-save-state" id="breadcrumbs">
                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="#">Trang chủ</a>
                    </li>
                </ul>
                <!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <c:if test="${not empty message}">
                    <div class="alert alert-${alert}">
                            ${message}
                    </div>
                </c:if>
                <div class="row">
                    <div class="col-xs-12">
                        <div class="widget-box table-filter">
                            <div class="widget-body">
                                <div class="widget-main">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right">Tên người
                                                dùng</label>
                                            <div class="col-sm-9">
                                                    <%--<input type="text" id="name" name="name" class="form-control" value="${model.name}"/>--%>
                                                <form:input path="userName" cssClass="form-control"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right">Tên đầy đủ</label>
                                            <div class="col-sm-9">
                                                    <%--<input type="text" id="street" name="street" class="form-control" value="${model.street}"/>--%>
                                                <form:input path="fullName" cssClass="form-control"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right">Role</label>
                                            <div class="col-sm-9">
                                                <c:forEach var="type" items="${rall}">
                                                    <input type="checkbox" class="form-check-input" name="typeArray"
                                                    <c:forEach var="item1" items="${model.typeArray}">
                                                           <c:if test="${item1 == type.key}">checked</c:if>
                                                    </c:forEach>
                                                           value="${type.key}">
                                                    <label> ${type.value}</label>
                                                </c:forEach>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right"></label>
                                            <div class="col-sm-9">
                                                <button id="btnSearch" type="button" class="btn btn-sm btn-success">
                                                    Tìm kiếm
                                                    <i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="table-btn-controls">
                                <div class="pull-right tableTools-container">
                                    <div class="dt-buttons btn-overlap btn-group">
                                        <a flag="info"
                                           class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
                                           data-toggle="tooltip"
                                           title='Thêm tòa nhà' href='<c:url value="/admin/user/edit"/>'>
															<span>
																<i class="fa fa-plus-circle bigger-110 purple"></i>
															</span>
                                        </a>
                                        <button id="btnDelete" type="button" disabled
                                                class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
                                                data-toggle="tooltip" title='Xóa tòa nhà'>
																<span>
																	<i class="fa fa-trash-o bigger-110 pink"></i>
																</span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="table-responsive">
                                    <table class="table table-bordered" id="buildingTable">
                                        <thead>
                                        <tr>
                                            <th><input type="checkbox" class="check-box-element" id="checkAll"/></th>
                                            <th>Tên người dùng</th>
                                            <th>Tên đầy đủ</th>
                                            <th></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="item" items="${model.listResult}">
                                            <tr>
                                                <td><input type="checkbox" name="checkList" class="check-box-element"
                                                           id="checkbox_${item.id}" value="${item.id}"/></td>
                                                <td>${item.userName}</td>
                                                <td>${item.fullName}</td>
                                                <td>
                                                        <%--<c:url var="editURL" value="/admin-new">
                                                            <c:param name="type" value="edit"/>
                                                            <c:param name="id" value="${item.id}"/>
                                                        </c:url>--%>
                                                    <button class="btn btn-xs btn-primary btn-edit"
                                                            data-toggle="tooltip" type="button" title='Giao tòa nhà'
                                                            id="btnAssignBuilding" buildingId="${item.id}">
                                                        <i class="fa fa-tasks"></i>
                                                    </button>
                                                    <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
                                                       title="Cập nhật tòa nhà"
                                                       href='<c:url value="/admin/user/edit?id=${item.id}"/>'><i
                                                            class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                    <ul class="pagination" id="pagination"></ul>
                                    <input type="hidden" value="" id="page" name="page"/>
                                    <input type="hidden" value=" " id="crudaction" name="crudaction">
                                    <input type="hidden" value="" id="sortName" name="sortName"/>
                                    <input type="hidden" value="" id="sortBy" name="sortBy"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form:form>
</div>
<!-- /.main-content -->

<script>

    var totalPages = ${model.totalPage};
    var currentPage = ${model.page};
    $(function () {
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: totalPages,
            visiblePages: 5,
            startPage: currentPage,
            onPageClick: function (event, page) {
                if (currentPage != page) {
                    $('#page').val(page);
                    $("#sortName").val("id");
                    $("#sortBy").val("desc");
                    $('#formSubmit').submit();
                }
            }
        });
    });

    $('#btnSearch').click(function () {
        $('#page').val(1);
        $("#sortName").val("id");
        $("#sortBy").val("desc");
        $('#formSubmit').submit();
    });
    //delete building
    $('#btnDelete').click(function (e) {
        e.preventDefault();
        var dataArray = $(' tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        $.ajax({
            url: "${userUrl}",
            method: "delete",
            contentType: "application/json",
            data: JSON.stringify(dataArray),
            dataType: "json",
            success: function (result) {
                // window.location.href = "<c:url value="/admin/building/list?sortName=id&sortBy=desc&message=delete_success&alert=success"/>";
                alert(1);
            },
            error: function (jqXHR, exception) {
                var result = jqXHR;
                //window.location.href = "<c:url value="/admin/building/list?sortName=id&sortBy=desc&message=error_system&alert=danger"/>";
                alert(2);
            }
        });
    });
</script>
</body>

</html>