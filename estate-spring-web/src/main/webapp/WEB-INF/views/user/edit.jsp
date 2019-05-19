<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="userUrl" value="/api/user"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Thêm tòa nhà</a>
                </li>
            </ul>
            <!-- /.breadcrumb -->
        </div>
        <form:form id="formBuilding" commandName="model">
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
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label no-padding-right">user name</label>
                                                <div class="col-sm-9">
                                                    <form:input path="userName" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label no-padding-right">password</label>
                                                <div class="col-sm-9">
                                                   <%-- <form:password path="password" cssClass="form-control" />--%>
                                                    <input type="password" class="form-control" name="password" value="${model.password}" >
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label no-padding-right">full name</label>
                                                <div class="col-sm-9">
                                                    <form:input path="fullName" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label no-padding-right">Role</label>
                                                <div class="col-sm-9">
                                                    <div class="form-group" id="checkboxer">
                                                        <c:forEach var="item" items="${roles}">
                                                            <div class="form-check form-check-flat">
                                                                <label class="form-check-label">
                                                                    <input type="checkbox" class="form-check-input"
                                                                           value="${item.key}"
                                                                    <c:forEach var="item1" items="${model.roles}">
                                                                           <c:if test="${item.key == item1.code}">checked</c:if>
                                                                    </c:forEach> >
                                                                        ${item.value}
                                                                    <i class="input-helper"></i></label>
                                                            </div>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label no-padding-right"></label>
                                                <div class="col-sm-9">
                                                    <input type="button" class="btn btn-success mr-2" id="btnSubmit"
                                                           value="submit">
                                                </div>
                                            </div>
                                            <form:hidden path="id"/>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form:form>
    </div>
</div>
<script>
    $('#btnSubmit').click(function (e) {
        e.preventDefault();
        var data = {};
        var dataArray = $('#formBuilding').serializeArray();
        $.each(dataArray, function (i, v) {
            data[v.name] = v.value;
        })
        var category = $('#checkboxer :checkbox:checked').map(function () {
            return $(this).val();
        }).get();
        data["typeArray"] = category;
        var id = $('#id').val();
        if(id == "") {
            addUser(data);
        } else {
            updateUser(data);
        }
    });

    function addUser(data) {
        $.ajax({
            url: "${userUrl}",
            method: "post",
            contentType: "application/json",
            data: JSON.stringify(data),
            dataType: "json",
            success: function (result) {
              // window.location.href = "<c:url value="/admin/building/edit?message=insert_success&alert=success"/>";
            alert(1);
            },
            error: function (jqXHR, exception) {
                var result = jqXHR;
               // window.location.href = "<c:url value="/admin/building/edit?message=error_system&alert=danger"/>";
                alert(2);
            }
        });
    }
    function updateUser(data) {
        $.ajax({
            url: "${userUrl}",
            method: "put",
            contentType: "application/json",
            data: JSON.stringify(data),
            dataType: "json",
            success: function (result) {
                //window.location.href = "<c:url value="/admin/building/edit?message=insert_success&alert=success"/>";
                alert(1);
            },
            error: function (jqXHR, exception) {
                var result = jqXHR;
               // window.location.href = "<c:url value="/admin/building/edit?message=error_system&alert=danger"/>";
                alert(2);
            }
        });
    }

</script>
</body>
</html>
