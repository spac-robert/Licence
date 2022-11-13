<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    <%@include file="style/product.css"%>
</style>
<template:pageTemplate title="Add Product">

    <div class="container my-5">
        <h2 class="mb-5">New Product</h2>
        <div class="row">
            <div class="col-md-6">
                <form action="/products/save/" method="post">
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label for="name" class="col-form-label">Name</label>
                            <input type="text" name="name" id="name" class="form-control"
                                   placeholder="Name">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="name">Category
                                <select name="productCategory">
                                    <c:forEach items="${categories}" var="entry">
                                        <option value="${entry}">${entry}</option>
                                    </c:forEach>
                                </select>
                            </label>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="stock" class="col-form-label">Stock</label>
                            <input type="number" name="stock" id="stock" class="form-control" placeholder="Stock">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="price" class="col-form-label">Price</label>
                            <input type="number" name="price" id="price" class="form-control" placeholder="Price">
                        </div>
                            <%--                        <div class="form-group col-md-6">--%>
                            <%--                            <label for="currency" class="col-form-label">Currency</label>--%>
                            <%--                            <input type="text" name="currency" id="currency" class="form-control" placeholder="Currency">--%>
                            <%--                        </div>--%>
                        <div class="form-group col-md-6">
                            <label for="name">Currency
                                <select name="currency">
                                    <c:forEach items="${currency}" var="entry">
                                        <option value="${entry}">${entry}</option>
                                    </c:forEach>
                                </select>
                            </label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 mt-5">
                            <input type="submit" class="btn btn-primary" value="Add Product">
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
    </body>
    </html>

</template:pageTemplate>
