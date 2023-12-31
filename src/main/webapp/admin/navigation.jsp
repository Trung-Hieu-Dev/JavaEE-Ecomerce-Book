<div class="container py-5">
	<div class="d-flex justify-content-between align-items-center">
		<a href="<%= pageContext.getServletContext().getInitParameter("baseUrl")%>/admin">
			<img alt="logo" src="<%= pageContext.getServletContext().getInitParameter("baseUrl")%>/img/logo.png">
		</a>
		

		<div class="d-flex justify-content-end vw-50">
			Welcome, Admin
			<span class="mx-3"> | </span>
			<input type="button" value="Logout" class="btn btn-outline-primary ms-4">
		</div>
	</div>

	<div class="d-flex justify-content-center">
		<a href="manage_user" class="fw-bold">Users</a>
		<span class="mx-3"> | </span>
		<a href="manage_category" class="fw-bold">Categories</a>
		<span class="mx-3"> | </span>
		<a href="#" class="fw-bold">Books</a>
		<span class="mx-3"> | </span>
		<a href="#" class="fw-bold">Customers</a>
		<span class="mx-3"> | </span>
		<a href="#" class="fw-bold">Orders</a>
	</div>
</div>