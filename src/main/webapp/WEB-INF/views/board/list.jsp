<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../includes/header.jsp"%>

<main>
	<div class="modal" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Alert</h5>
					<button type="button" class="btn-close" data-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<p>Modal body text goes here..</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<div class="container-fluid">
		<h1 class="mt-4">Board</h1>
		<ol class="breadcrumb mb-4">
			<li class="breadcrumb-item"><a href="index.html">Dashboard</a></li>
			<li class="breadcrumb-item active">Board</li>
		</ol>
		<div class="card mb-4">
			<div class="card-body">
				DataTables is a third party plugin that is used to generate the demo
				table below. For more information about DataTables, please visit the
				<a target="_blank" href="https://datatables.net/">official
					DataTables documentation</a> .
			</div>
		</div>
		<div class="card mb-4">
			<div class="card-header">
				<i class="fas fa-table mr-1"></i> List <a
					class="btn btn-outline-info btn-sm" href="/board/register"
					style="float: right;">Register</a>
			</div>
			<div class="card-body">
				<div class="table-responsive">
					<table class="table table-bordered" id="dataTable" width="100%"
						cellspacing="0">
						<thead>
							<tr>
								<th>Bno</th>
								<th>Title</th>
								<th>Writer</th>
								<th>RegDate</th>
								<th>Updatedate</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th>Bno</th>
								<th>Title</th>
								<th>Writer</th>
								<th>RegDate</th>
								<th>Updatedate</th>
							</tr>
						</tfoot>
						<tbody>
							<c:forEach items="${list }" var="board">
								<tr>
									<td><c:out value=" ${board.bno }"></c:out></td>
									<td><a href="/board/get?bno=${board.bno }"> <c:out
												value="${board.title }"></c:out></a></td>
									<td><c:out value="${board.writer }"></c:out></td>
									<td><fmt:formatDate value="${board.regdate }"
											pattern="yy/MM/dd" /></td>
									<td><fmt:formatDate value="${board.updatedate }"
											pattern="yy/MM/dd" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</main>

<script type="text/javascript">
	$(document).ready(function() {
		var result = '<c:out value="${result }"></c:out>';
		
		showModal(result);
		
		function showModal(result){
			
			if(result === "success"){
				$('.modal-body').html("Success Remove!");
				$('.modal').modal('show');
				history.pushState(null, null, location.href);
				window.onpopstate = function(){
					$('.modal-body').html("This content has been removed.");
					$('.modal').modal('show');
					history.go(1);
					history.replace(null,null,null);
				};
			}
		}
	});
</script>

<%@ include file="../includes/footer.jsp"%>
