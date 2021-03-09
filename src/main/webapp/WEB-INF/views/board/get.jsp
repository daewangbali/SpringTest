<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../includes/header.jsp"%>

<main>
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
				<i class="fas fa-table mr-1"></i> Detail
			</div>

			<div class="card-body">
				<div class="table-responsive">
					<div class="form-group col-md-12">
						<div class="mb-3">
							<label for="title" class="form-label">Title</label> <input
								type="text" class="form-control border border-dark" id="title"
								name="title" value="<c:out value='${board.title }'></c:out>"
								readonly="readonly">
						</div>
					</div>
				</div>
				<div class="form-group col-md-12">
					<div class="mb-3 row">
						<label for="writer" class="col-sm-2 col-form-label">Writer</label>
						<div class="col-sm-10">
							<input type="text" readonly="readonly"
								class="form-control-plaintext rounded" id="writer" name="writer"
								value="<c:out value='${board.writer }'></c:out>">
						</div>
					</div>
				</div>
				<div class="form-group col-md-12">
					<div class="mb-3">
						<label for="content" class="form-label">Content</label>
						<textarea class="form-control border border-dark" id="content"
							name="content" rows="3" readonly="readonly"> <c:out
								value="${board.content }"></c:out></textarea>
					</div>
				</div>
				<c:if test="${board.filename != null }">
					<div class="form-group col-md-12">
						<c:out value="${board.filename }"></c:out>
						<button type="button" id="download" class="btn btn-outline-warning"
							style="float: right;">Download</button>
						<div class="mb-3"></div>
					</div>
				</c:if>
				<form id="actionForm">
					<input type="hidden" id="bno" name="bno" value="${board.bno }">
					<input type="hidden" name="pageNum" value="${cri.pageNum }">
					<input type="hidden" name="amount" value="${cri.amount }">
					<input type="hidden" name="type" value="${cri.type }"> <input
						type="hidden" name="keyword" value="${cri.keyword }">
					<button class="btn btn-outline-danger" id="btn1"
						style="float: right;">Remove</button>
					<button class="btn btn-outline-dark" id="listBtn"
						style="float: right;">Back to List</button>
					<button class="btn btn-outline-primary" id="modifyBtn"
						style="float: right;">Modify</button>

				</form>
			</div>
			<div class="input-group">
				<input type="text" class="input-group-text"
					placeholder="Enter Writer" id="comment_writer">
				<textarea class="form-control" placeholder="Enter Comment"
					id="comment_content"></textarea>
				<button type="button" class="btn btn-outline-info"
					id="comment_register">Submit</button>
			</div>
		</div>
	</div>
	<div id="comment_list"></div>
</main>
<div class="modal" tabindex="-1" id="board_modal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Modal title</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body" id="board_modal_body">
				<p>Modal body text goes here.</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal"
					id="btn2">Remove</button>
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
	$(document).ready(function() {
		var actionForm = $("#actionForm");

		$('#btn1').click(function(e) {
			e.preventDefault();//이벤트 자동발생 막아줌
			$('#board_modal_body').html("Are you sure you want to remove?");
			$('#board_modal').modal('show');
		});

		$('#btn2').click(function(e) {
			actionForm.attr("action", "/board/remove").attr("method", "post");
			actionForm.submit();
		});

		$("#listBtn").on("click", function(e) {

			e.preventDefault();

			actionForm.find("input[name=bno]").remove();
			actionForm.attr("action", "/board/list");
			actionForm.attr("method", "get");
			actionForm.submit();

		});

		$("#modifyBtn").on("click", function(e) {

			e.preventDefault();

			actionForm.attr("action", "/board/modify");
			actionForm.attr("method", "get");
			actionForm.submit();

		});

		comment_list();

		function comment_list() {
			$.ajax({
				type : "GET",
				url : "/board/comment/comment_get_list?bno=${board.bno}",
				success : function(result) {
					$("#comment_list").html(result);
				},
				error : function(req, text) {
					alert(text + " : " + req.status);
				}
			});
		}

		$("#comment_register").on("click", function(e) {
			e.preventDefault();
			console.log(".................");

			var comment = new Object();
			comment.content = $("#comment_content").val();
			comment.writer = $("#comment_writer").val();

			if (comment.content === "" || comment.writer === "") {
				alert("댓글을 입력하세요.");
				return;
			}
			comment.bno = "<c:out value='${board.bno} '></c:out>";

			console.log(comment.bno);

			$.ajax({
				data : JSON.stringify(comment),
				contentType : "application/json; charset=utf-8",
				type : "POST",
				url : "/board/comment/comment_register",
				success : function() {
					comment.content = $("#comment_content").val("");
					comment.writer = $("#comment_writer").val("");
					comment_list();
				},
				error : function(req, text) {
					alert(text + " : " + req.status);
				}
			});
		});
		
		$("#download").on("click", function(){
			location = '/board/download?bno=${board.bno}';
		});

	});
</script>

<%@ include file="../includes/footer.jsp"%>
