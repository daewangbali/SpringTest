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
		<h1 class="mt-4">Test Board</h1>
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
				<form action="/board/list" method="get" id="searchForm" style="margin: 0 0 10px">
					<div class="input-group" style="position:absolute ;  width : 15px 0" >
						<select class="custom-select col-md-1 amountNum" id="inputGroupSelect04">
							<option ${page.cri.amount eq 10? 'selected':'' } value="10">10</option>
							<option ${page.cri.amount eq 20? 'selected':'' } value="20">20</option>
							<option ${page.cri.amount eq 50? 'selected':'' } value="50">50</option>
							<option ${page.cri.amount eq 100? 'selected':'' } value="100">100</option>
						</select>
						<div class="input-group-append">
							<button class="btn btn-outline-secondary getAmount" type="button">Button</button>
						</div>
					</div>
					<div class="input-group" style="width : 15px 0 ; justify-content: flex-end;">
							<select class="custom-select col-md-1" name="type"
								id="inputGroupSelect04">
								<option ${page.cri.type == null? 'selected':'' } value="">---</option>
								<option ${page.cri.type == 'T'? 'selected':'' } value="T">제목</option>
								<option ${page.cri.type == 'C'? 'selected':'' } value="C">내용</option>
								<option ${page.cri.type == 'W'? 'selected':'' } value="W">작성자</option>
								<option ${page.cri.type == 'TC'? 'selected':'' } value="TC">제목+내용</option>
								<option ${page.cri.type == 'CW'? 'selected':'' } value="CW">내용+작성자</option>
								<option ${page.cri.type == 'TW'? 'selected':'' } value="TW">제목+작성자</option>
								<option ${page.cri.type == 'TCW'? 'selected':'' } value="TCW">전체</option>
							</select>
							<div class="input-group-append">
								<input type="text" name="keyword" value="${page.cri.keyword }">
								<input type="hidden" name="pageNum" value="${page.cri.pageNum }">
								<input type="hidden" name="amount" value="${page.cri.amount }">
								<button class="btn btn-outline-secondary getSearch"
									type="button">Button</button>
							</div>
						</div>
					</form>
					<table class="table table-bordered" id="" width="100%"
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
									<td><a class="move" href="${board.bno }"> <c:out
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

					<div style="float: right;">
						<nav aria-label="Page navigation example">
							<ul class="pagination">
								<c:if test="${page.prev }">
									<li class="page-item"><a class="page-link"
										href="${page.startPage - 1 }">Prev</a></li>
								</c:if>
								<c:forEach begin="${page.startPage }" end="${page.endPage }"
									var="num">
									<li
										class="page-item ${page.cri.pageNum == num ? 'active' : ''}"><a
										class="page-link" href="${num }">${num }</a></li>
								</c:forEach>
								<c:if test="${page.next }">
									<li class="page-item"><a class="page-link"
										href="${page.endPage + 1 }">Next</a></li>
								</c:if>
							</ul>
						</nav>
					</div>
					<form id="actionForm" method="get">
						<input type="hidden" name="pageNum" value="${page.cri.pageNum }">
						<input type="hidden" name="amount" value="${page.cri.amount }">
						<input type="hidden" name="type" value="${page.cri.type }">
						<input type="hidden" name="keyword" value="${page.cri.keyword }">
					</form>
				</div>
			</div>
		</div>
	</div>
</main>

<script type="text/javascript">
	$(document).ready(function() {
		var actionForm = $("#actionForm");

		var result = '<c:out value="${result }"></c:out>';

		showModal(result);

		function showModal(result) {

			if (result === "success") {
				$('.modal-body').html("Success Remove!");
				$('.modal').modal('show');
				history.pushState(null, null, location.href);
				window.onpopstate = function() {
					$('.modal-body').html(
							"This content has been removed.");
					$('.modal').modal('show');
					history.go(1);
					history.replace(null, null, null);
				};
			}
		}

		$(".page-link").on("click", function(e) {
			e.preventDefault();

			var target = $(this).attr("href");

			console.log(target);

			/*현재위치를 가고싶은 위치로 바꾸겠다 */

			actionForm.find("input[name=pageNum]").val(target);
			actionForm.attr("action", "/board/list").submit();

		});

		$(".move")
				.on(
						"click",
						function(e) {
							e.preventDefault();

							var target = $(this).attr("href");

							console.log(target);

							/*현재위치를 가고싶은 위치로 바꾸겠다 */
							actionForm
									.append("<input type='hidden' name='bno' value='" + target + "'>");
							actionForm.attr("action",
									"/board/get").submit();

						});

		$(".getAmount").on("click", function(e) {

			e.preventDefault();

			var target = $(".amountNum").val();

			console.log(target);

			/*현재위치를 가고싶은 위치로 바꾸겠다 */
			actionForm.find("input[name=amount]").val(target);
			actionForm.attr("action", "/board/list").submit();

		});
		
		var searchForm = $("#searchForm");
		
		$(".getSearch").on("click", function(e){
			e.preventDefault();
			
			var result = $(".getType").val();
			console.log(result);
			
			searchForm.append("<input type='hidden' name='type value='" + result +">");
			searchForm.find("input[name='pageNum']").val(1);
			
			searchForm.submit();
		});
		
		
	});
</script>

<%@ include file="../includes/footer.jsp"%>
