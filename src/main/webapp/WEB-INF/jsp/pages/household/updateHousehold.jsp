<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    <%@include file="style/household.css"%>
</style>
<template:pageTemplate title="UpdateHousehold">

    <div class="container my-5">
        <h2 class="mb-5">Update Household</h2>
        <div class="row pl-5">
            <div class="col-md-6">
                <form action="/households/update/" method="post">
                    <input name="id" type="hidden" value="${household.id}">
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label for="representative" class="col-form-label">Representative</label>
                            <input type="text" name="representative" id="representative" class="form-control"
                                   value="${household.representative}" placeholder="Representative">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="phone" class="col-form-label">Phone</label>
                            <input type="text" name="phone" id="phone" class="form-control" value="${household.phone}"
                                   placeholder="Phone">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="email" class="col-form-label">Email</label>
                            <input type="email" name="email" id="email" class="form-control" value="${household.email}"
                                   placeholder="Email">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="address" class="col-form-label">Contact address</label>
                            <input type="text" name="address" id="address" class="form-control"
                                   value="${household.address}" placeholder="Address">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="numberOfPeople" class="col-form-label">People</label>
                            <input type="number" name="numberOfPeople" id="numberOfPeople"
                                   value="${household.numberOfPeople}" class="form-control"
                                   placeholder="People">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="numberOfChildren" class="col-form-label">Children</label>
                            <input type="number" name="numberOfChildren" id="numberOfChildren"
                                   value="${household.numberOfChildren}" class="form-control"
                                   placeholder="Children">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="numberOfVegans" class="col-form-label">Vegans</label>
                            <input type="number" name="numberOfVegans" id="numberOfVegans"
                                   value="${household.numberOfVegans}" class="form-control"
                                   placeholder="Vegans">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="numberOfNonVegans" class="col-form-label">Non Vegans</label>
                            <input type="number" name="numberOfNonVegans" id="numberOfNonVegans"
                                   value="${household.numberOfNonVegans}" class="form-control"
                                   placeholder="Non Vegans">
                        </div>
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
