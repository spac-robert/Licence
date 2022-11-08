<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    <%@include file="style/product.css"%>
</style>
<template:pageTemplate title="UpdateHousehold">

    <div class="container my-5">
        <h2 class="mb-5">Update Product</h2>
        <div class="row pl-5">
            <div class="col-md-6">
                <form action="/products/update/" method="post">
                    <input name="id" type="hidden" value="${product.id}">
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label for="name" class="col-form-label">Name</label>
                            <input type="text" name="name" id="name" class="form-control"
                                   value="${product.name}" placeholder="Name">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="stock" class="col-form-label">Stock</label>
                            <input type="number" name="stock" id="stock" class="form-control" value="${product.stock}"
                                   placeholder="Stock">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="stock" class="col-form-label">Price</label>
                            <input type="number" name="price" id="price" class="form-control" value="${product.price}"
                                   placeholder="Price">
                        </div>
                        <label for="name" class="col-form-label">Category
                            <select name="productCategory">
                                <c:forEach items="${categories}" var="entry">
                                    <option value="${entry}">${entry}</option>
                                </c:forEach>
                            </select>
                        </label>
                    </div>
                    <div class="row">
                        <div class="col-md-6 mt-5">
                            <input type="submit" class="btn btn-primary" value="Update Household">
                        </div>
                    </div>
                    <c:if test="${errorSize > 0}">
                        <div class="border-error">
                            <c:forEach items="${bindingResultMsg}" var="entry">
                                <p class="error-msg">${entry}</p>
                            </c:forEach>
                        </div>
                    </c:if>
                </form>
            </div>
        </div>
    </div>

</template:pageTemplate>
