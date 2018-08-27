<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ctg" uri="customtags"%>
<jsp:include page="client_navbar.jsp" />
<div class="container-fluid">
	<div class="row">
		<jsp:include page="client_menu.jsp" />
		<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
		<div
			class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
			<h1 class="h2">Change client password</h1>
			<div class="btn-toolbar mb-2 mb-md-0">CHANGE PASSWORD</div>
		</div>
		<div class="container">
			<form action="ServletController" method="post">
				<input type="hidden" name="clientId" value="${client.id}" />
				<div class="form-row">
					<div class="col-md-3 mb-3 input-group-sm">
						<label for="current_password"><i class="fas fa-key"></i>
							Current password</label> <input type="password" class="form-control"
							id="current_password" placeholder="Current password"
							name="current_password" required>
					</div>
					<div class="col-md-4 mb-3 input-group-sm">
						<label for="new_password"><i class="fas fa-key"></i> New
							password</label> <input type="password" class="form-control"
							id="new_password" placeholder="New password" name="new_password"
							required>
					</div>
					<div class="col-md-4 mb-3 input-group-sm">
						<label for="veriry_new_password"><i class="fas fa-key"></i>
							Verify</label> <input type="password" class="form-control"
							id="veriry_new_password" placeholder="Verify"
							name="veriry_new_password" required>
					</div>
					<div class="col-md-1 mb-3 input-group-sm">
						<div style="margin-top: 31px;"></div>
						<button
							class="btn btn-success form-control my-sm-0 my-2 btn-sm custom_button"
							id="save_btn" type="submit" name="command"
							value="update_password">
							<i class="fas fa-save"></i> Save
						</button>
					</div>
				</div>
			</form>
			<p>${messageErrorSavePassword}</p>
		</div>
		</main>
	</div>
</div>
<ctg:footer />
