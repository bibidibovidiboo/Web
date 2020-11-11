<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
// $(function(){
// 		$.ajax({
// 			type:'post',
// 			url:'../shop/purchase_list.do',
// 			success:function(result){
// 			$('#purchase_list').html(result);
// 			}
// 	})
// });
</script>
</head>
<body>
 
	<!-- ================ start banner area ================= -->	
	<section class="blog-banner-area" id="category">
		<div class="container h-100">
			<div class="blog-banner">
				<div class="text-center">
					<h1>구매내역</h1>
					<nav aria-label="breadcrumb" class="banner-breadcrumb">
            <ol class="breadcrumb">
              <li class="breadcrumb-item"><a href="#">G반</a></li>
              <li class="breadcrumb-item active" aria-current="page">김한비</li>
            </ol>
          </nav>
				</div>
			</div>
    </div>
	</section>
	<!-- ================ end banner area ================= -->
  
  <!--================Order Details Area =================-->
  <section class="order_details section-margin--small">
    <div class="container">
      <p class="text-center billing-alert">Thank you. Your order has been received.</p>
      
      
<!--       	<div id="purchase_list"></div> -->
      	<div class="order_details_table">
        	<h2 align="center">주문내역</h2>
        <div class="table-responsive">
        <div class="order_details_table">
        	<h5 align="center"></h5>
        <div class="table-responsive">
          <table class="table">
            <thead>
              <tr>
              	<th style="width:10%"></th>
              	<th style="width:20%">이미지</th>
                <th style="width:30%">제품</th>
                <th style="width:10%">수량</th>
                <th style="width:20%">가격</th>
                <th style="width:10%"></th>
              </tr>
            </thead>
            <tbody>

	
			<c:forEach var="vo" items="${list }">
              <tr>
              	<td style="width:5%">
                  <!-- 여백 -->
                </td>
              	<td style="width:20%">
                  <img src="${vo.svo.poster }" width="50" height="50">
                </td>
                <td style="width:40%">
                  ${vo.svo.title }
                </td>
                <td style="width:10%">
                  <h5>${vo.count }</h5>
                </td>
                <td style="width:20%">
                  <p>${vo.total}</p>
                </td>
                <td style="width:5%">
                  <!-- 여백 -->
                </td>
              </tr>
            </c:forEach>
            
			
              <tr>
                <td style="width:5%">
                  <h4></h4>
                </td>
                <td style="width:20%">
                  <h5></h5>
                </td>
                <td style="width:40%">
                  <h4></h4>
                </td>
                <td style="width:10%">
                  <h4>합계:</h4>
                </td>
                <td style="width:20%">
<%--                   <h4>${vo.purchase_total }</h4> --%>
					<h4>${p_total }원</h4>
                </td>
                <td style="width:5%">
                  <h4></h4>
                </td>
              </tr>
              
            </tbody>
          </table>
        </div>
      </div>
      
      
    </div>
  </section>
</body>
</html>
