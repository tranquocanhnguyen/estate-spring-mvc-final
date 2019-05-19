<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingUrl" value="/api/building"/>
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
                                                <label class="col-sm-3 control-label no-padding-right">Name</label>
                                                <div class="col-sm-9">
                                                    <form:input path="name" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label no-padding-right">province</label>
                                                <div class="col-sm-9">
                                                    <form:input path="province" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label no-padding-right">street</label>
                                                <div class="col-sm-9">
                                                    <form:input path="street" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label no-padding-right">structure</label>
                                                <div class="col-sm-9">
                                                    <form:input path="structure" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label no-padding-right">numberOfBasement</label>
                                                <div class="col-sm-9">
                                                    <form:input path="numberOfBasement" cssClass="form-control"
                                                                type="number"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label no-padding-right">buildingArea</label>
                                                <div class="col-sm-9">
                                                    <form:input path="buildingArea" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label no-padding-right">direction</label>
                                                <div class="col-sm-9">
                                                    <form:input path="direction" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label no-padding-right">level</label>
                                                <div class="col-sm-9">
                                                    <form:input path="level" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label no-padding-right">feesBrokerage</label>
                                                <div class="col-sm-9">
                                                    <form:input path="feesBrokerage" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label no-padding-right">rentArea</label>
                                                <div class="col-sm-9">
                                                    <form:input path="rentArea" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label no-padding-right">areaDescription</label>
                                                <div class="col-sm-9">
                                                    <form:input path="areaDescription" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label no-padding-right">costRent</label>
                                                <div class="col-sm-9">
                                                    <form:input path="costRent" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label no-padding-right">costDescription</label>
                                                <div class="col-sm-9">
                                                    <form:input path="costDescription" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label no-padding-right">serviceCost</label>
                                                <div class="col-sm-9">
                                                    <form:input path="serviceCost" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label no-padding-right">carCost</label>
                                                <div class="col-sm-9">
                                                    <form:input path="carCost" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label no-padding-right">motorbikeCost</label>
                                                <div class="col-sm-9">
                                                    <form:input path="motorbikeCost" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label no-padding-right">overtimeCost</label>
                                                <div class="col-sm-9">
                                                    <form:input path="overtimeCost" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label no-padding-right">electricityCost</label>
                                                <div class="col-sm-9">
                                                    <form:input path="electricityCost" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label no-padding-right">deposit</label>
                                                <div class="col-sm-9">
                                                    <form:input path="deposit" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label no-padding-right">timeContract</label>
                                                <div class="col-sm-9">
                                                    <form:input path="timeContract" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label no-padding-right">timeDecorator</label>
                                                <div class="col-sm-9">
                                                    <form:input path="timeDecorator" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label no-padding-right">managerName</label>
                                                <div class="col-sm-9">
                                                    <form:input path="managerName" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label no-padding-right">managerPhone</label>
                                                <div class="col-sm-9">
                                                    <form:input path="managerPhone" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label no-padding-right">commission</label>
                                                <div class="col-sm-9">
                                                    <form:input path="commission" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label no-padding-right">district</label>
                                                <div class="col-sm-9">
                                                    <form:input path="district" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label no-padding-right">note</label>
                                                <div class="col-sm-9">
                                                    <form:input path="note" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label no-padding-right">link</label>
                                                <div class="col-sm-9">
                                                    <form:input path="link" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label no-padding-right">image</label>
                                                <div class="col-sm-9">
                                                    <form:input path="image" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label no-padding-right">areaFree</label>
                                                <div class="col-sm-9">
                                                    <form:input path="areaFree" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label no-padding-right">buildingTypes</label>
                                                <div class="col-sm-9">
                                                        <%-- <form:checkboxes items="${typeArray}" path="buildingTypes" />--%>
                                                    <div class="form-group" id="checkboxer">
                                                        <c:forEach var="item" items="${typeArray}">
                                                            <div class="form-check form-check-flat">
                                                                <label class="form-check-label">
                                                                    <input type="checkbox" class="form-check-input"
                                                                           value="${item.key}"
                                                                    <c:forEach var="item1" items="${model.type}">
                                                                           <c:if test="${item.key == item1}">checked</c:if>
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
        data["buildingTypes"] = category;
        var id = $('#id').val();
        if(id == "") {
            addBuilding(data);
        } else {
            updateBuilding(data);
        }
    });

    function addBuilding(data) {
        $.ajax({
            url: "${buildingUrl}",
            method: "post",
            contentType: "application/json",
            data: JSON.stringify(data),
            dataType: "json",
            success: function (result) {
                window.location.href = "<c:url value="/admin/building/edit?message=insert_success&alert=success"/>";
            },
            error: function (jqXHR, exception) {
                var result = jqXHR;
                window.location.href = "<c:url value="/admin/building/edit?message=error_system&alert=danger"/>";
            }
        });
    }
    function updateBuilding(data) {
        $.ajax({
            url: "${buildingUrl}",
            method: "put",
            contentType: "application/json",
            data: JSON.stringify(data),
            dataType: "json",
            success: function (result) {
                window.location.href = "<c:url value="/admin/building/edit?message=insert_success&alert=success"/>";
            },
            error: function (jqXHR, exception) {
                var result = jqXHR;
                window.location.href = "<c:url value="/admin/building/edit?message=error_system&alert=danger"/>";
            }
        });
    }

</script>
</body>
</html>
