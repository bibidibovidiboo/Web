<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*,java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>  
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../template/vendors/owl-carousel/owl.carousel.min.css">
 <script type="text/javascript">

  </script>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="http://fonts.googleapis.com/earlyaccess/nanumpenscript.css" rel="stylesheet">
</head> 
<body>			
	<section class="blog_area single-post-area py-80px section-margin--small">
			<div class="container">
					<div class="row">
							<div class="col-lg-8 posts-list">
									<div class="single-post row">
											<div class="col-lg-12">
													<div class="feature-img">
															<img class="img-fluid" src="/Home_fit/challenge_poster/${vo.poster}" alt="">
													</div>
											</div>
											<div class="col-lg-3  col-md-3">
													<div class="blog_info text-right">
															<div class="post_tag">
																	<a href="#">Food,</a>
																	<a class="active" href="#">Technology,</a>
																	<a href="#">Politics,</a>
																	<a href="#">Lifestyle</a>
															</div>
															<ul class="blog_meta list">
																	<li>
																			<a href="#">방장: ${vo.id_leader }
																					<i class="lnr lnr-user"></i>
																			</a>
																	</li>
																	<li>
																			<a href="#">등록일: ${vo.regdate }
																					<i class="lnr lnr-calendar-full"></i>
																			</a>
																	</li>
																	<li>
																			<a href="#">도전 시작일: ${vo.db_start_day }
																					<i class="lnr lnr-eye"></i>
																			</a>
																	</li>
																	<li>
																			<a href="#">도전 종료일: ${vo.end_day }
																					<i class="lnr lnr-bubble"></i>
																			</a>
																	</li>
															</ul>
															<ul class="social-links">
																	<li>
																			<a href="#">
																					<i class="fab fa-facebook-f"></i>
																			</a>
																	</li>
																	<li>
																			<a href="#">
																				<i class="fab fa-twitter"></i>																				
																			</a>
																	</li>
																	<li>
																			<a href="#">
																				<i class="fab fa-github"></i>																				
																			</a>
																	</li>
																	<li>
																			<a href="#">
														 						<i class="fab fa-behance"></i>																				
													  						</a>
													  				</li>
													  		</ul>
													</div>
											</div>	 
											<div class="col-lg-9 col-md-9 blog_details">
													<h2>${vo.title }</h2>
													<p class="excert">
													 		[도전 내용] 	
													</p>
													<p>
													 			${vo.content }
													</p>
													<p>
													 		
													</p>
											</div>	 
											<div class="col-lg-12">
													<div class="quotes">
													 		MCSE boot camps have its supporters and its detractors. Some people do not understand why you should have to spend money
													 		on boot camp when you can get the MCSE study materials yourself at a fraction of the camp
													 		price. However, who has the willpower to actually sit through a self-imposed MCSE training.
													</div>
													
													<strong><h5>인증  현재 참여율은 ${percent} %입니다.</h5></strong>
													<c:forEach var="Certifiedvo" items="${Certifiedvo }">
													<div class="owl-carousel owl-theme" id="bestSellerCarousel">
												          <div class="card text-center card-product">
												            <div class="card-product__img">
<%-- 												            	<img src="/Home_fit/challenge_poster/${Certifiedvo.poster}" alt=""> --%>
												              <img class="img-fluid" src="/Home_fit/challenge_poster/${Certifiedvo.poster}" alt="">
												              <ul class="card-product__imgOverlay">
												                <li><button><i class="ti-search"></i></button></li>
												                <li><button><i class="ti-shopping-cart"></i></button></li>
												                <li><button><i class="ti-heart"></i></button></li>
												              </ul>
												            </div>
												            <div class="card-body">
												              <p>Accessories</p>

												             <p href="single-product.html" class="card-product__title">아이디: ${Certifiedvo.challenge_id}</p>
												              <p class="product__price">등록일: ${Certifiedvo.certified_date }</p>
												            </div>
												          </div>
														</div>											
<!-- 												밑에는  무슨 부분인지 모르겠음   필요없을거 같아서 주석 2020-10-24 [5:16] -->
<!-- 													<div class="row"> -->
<!-- 															<div class="col-6"> -->
<%-- 																	<img class="img-fluid" src="/Home_fit/challenge_poster/${Certifiedvo.poster}" alt=""> --%>
<!-- 																	<p>   -->
<%-- 																		  ${Certifiedvo.challenge_id } --%>
<!-- 																	</p> -->
<!-- 															</div>	  -->
<!-- <!-- 															<div class="col-6"> --> 
<%-- <%-- 																	<img class="img-fluid" src="/Home_fit/challenge_poster/${Certifiedvo.poster}" alt=""> --%>
<!-- <!-- 																	<p>   --> 
<%-- <%-- 																		  ${Certifiedvo.challenge_id } --%> 
<!-- <!-- 																	</p> --> 
<!-- <!-- 															</div> --> 
<!-- 															<div class="col-lg-12 mt-4"> -->
<!-- 															</div> -->
<!-- 													</div>		   -->
													</c:forEach>  
													<p> 		  
																[ 도전 글귀 ]MCSE boot camps have its supporters and its detractors. Some people do not understand why you should have to spend money
																on boot camp when you can get the MCSE study materials yourself at a fraction of
																the camp price. However, who has the willpower.
													</p>
													<p align="center">
													
													<!-- 
														Certifieid_count는 사용자가 인증한 횟수다 ! 테스트를위해서 일단은 주석 처리!!!!!!!
													
													 -->
<%-- 													<c:if test="${certifeid_count==0 }">	 --%>
													<c:if test="${count==1 || count==3}">
														<a href="../challenge/Certified.do?challenge_no=${ vo.challenge_no}" class="button button-postComment button--active" >인증하기</a>
													</c:if>								
<%-- 													</c:if>					 --%>
<%-- 													<c:if test="${certifeid_count>=1}"> --%>
<!-- 														<span class="btn btn-la btn-danger" >오늘은  이미 인증 하셨습니다.</span> -->
<%-- 													</c:if> --%>
													</p>
											</div>
									</div>
									<div class="navigation-area">
											<div class="row">
													<div class="col-lg-6 col-md-6 col-12 nav-left flex-row ㄴ justify-content-start align-items-center">
															<div class="thumb">
																	<a href="#">
																			<img class="img-fluid" src="img/blog/prev.jpg" alt="">
																	</a>
															</div>
															<div class="arrow">
																	<a href="#">
																			<span class="lnr text-white lnr-arrow-left"></span>
																	</a>
															</div>
															<div class="detials">
																	<p>Prev Post</p>
																	<a href="#">
																			<h4>Space The Final Frontier</h4>
																	</a>
															</div>
													</div>
													<div class="col-lg-6 col-md-6 col-12 nav-right flex-row d-flex justify-content-end align-items-center">
															<div class="detials">
																	<p>Next Post</p>
																	<a href="#">
																			<h4>Telescopes 101</h4>
																	</a>
															</div>
															<div class="arrow">
																	<a href="#">
																			<span class="lnr text-white lnr-arrow-right"></span>
																	</a>
															</div>
															<div class="thumb">
																	<a href="#">
																			<img class="img-fluid" src="img/blog/next.jpg" alt="">
																	</a>
															</div>
													</div>
											</div>
									</div>
									<div class="comments-area">
											<h4>05 Comments</h4>
											<div class="comment-list">
													<div class="single-comment justify-content-between d-flex">
															<div class="user justify-content-between d-flex">
																	<div class="thumb">
																			<img src="img/blog/c1.jpg" alt="">
																	</div>
																	<div class="desc">
																			<h5>
																					<a href="#">Emilly Blunt</a>
																			</h5>
																			<p class="date">December 4, 2017 at 3:12 pm </p>
																			<p class="comment">
																					Never say goodbye till the end comes!
																			</p>
																	</div>
															</div>
															<div class="reply-btn">
																	<a href="#" class="btn-reply text-uppercase">reply</a>
															</div>
													</div>
											</div>
											<div class="comment-list left-padding">
													<div class="single-comment justify-content-between d-flex">
															<div class="user justify-content-between d-flex">
																	<div class="thumb">
																			<img src="img/blog/c2.jpg" alt="">
																	</div>
																	<div class="desc">
																			<h5>
																					<a href="#">Elsie Cunningham</a>
																			</h5>
																			<p class="date">December 4, 2017 at 3:12 pm </p>
																			<p class="comment">
																					Never say goodbye till the end comes!
																			</p>
																	</div>
															</div>
															<div class="reply-btn">
																	<a href="#" class="btn-reply text-uppercase">reply</a>
															</div>
													</div>
											</div>
											<div class="comment-list left-padding">
													<div class="single-comment justify-content-between d-flex">
															<div class="user justify-content-between d-flex">
																	<div class="thumb">
																			<img src="img/blog/c3.jpg" alt="">
																	</div>
																	<div class="desc">
																			<h5>
																					<a href="#">Annie Stephens</a>
																			</h5>
																			<p class="date">December 4, 2017 at 3:12 pm </p>
																			<p class="comment">
																					Never say goodbye till the end comes!
																			</p>
																	</div>
															</div>
															<div class="reply-btn">
																	<a href="#" class="btn-reply text-uppercase">reply</a>
															</div>
													</div>
											</div>
											<div class="comment-list">
													<div class="single-comment justify-content-between d-flex">
															<div class="user justify-content-between d-flex">
																	<div class="thumb">
																			<img src="img/blog/c4.jpg" alt="">
																	</div>
																	<div class="desc">
																			<h5>
																					<a href="#">Maria Luna</a>
																			</h5>
																			<p class="date">December 4, 2017 at 3:12 pm </p>
																			<p class="comment">
																					Never say goodbye till the end comes!
																			</p>
																	</div>
															</div>
															<div class="reply-btn">
																	<a href="#" class="btn-reply text-uppercase">reply</a>
															</div>
													</div>
											</div>
											<div class="comment-list">
													<div class="single-comment justify-content-between d-flex">
															<div class="user justify-content-between d-flex">
																	<div class="thumb">
																			<img src="img/blog/c5.jpg" alt="">
																	</div>
																	<div class="desc">
																			<h5>
																					<a href="#">Ina Hayes</a>
																			</h5>
																			<p class="date">December 4, 2017 at 3:12 pm </p>
																			<p class="comment">
																					Never say goodbye till the end comes!
																			</p>
																	</div>
															</div>
															<div class="reply-btn">
																	<a href="#" class="btn-reply text-uppercase">reply</a>
															</div>
													</div>
											</div>
									</div>
									<div class="comment-form">
											<h4>Leave a Reply</h4>
											<form>
													<div class="form-group form-inline">
															<div class="form-group col-lg-6 col-md-6 name">
																	<input type="text" class="form-control" id="name" placeholder="Enter Name" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Enter Name'">
															</div>
															<div class="form-group col-lg-6 col-md-6 email">
																	<input type="email" class="form-control" id="email" placeholder="Enter email address" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Enter email address'">
															</div>
													</div>
													<div class="form-group">
															<input type="text" class="form-control" id="subject" placeholder="Subject" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Subject'">
													</div>
													<div class="form-group">
															<textarea class="form-control mb-10" rows="5" name="message" placeholder="Messege" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Messege'"
																	required=""></textarea>
													</div>
													<c:if test="${count==1 || count==3}">
													<a href="../challenge/participation.do?challenge_no=${vo.challenge_no }" class="button button-postComment button--active">이미 참여 중인 도전입니다.</a>
													</c:if>
													<c:if test="${count==0}">
													<a href="../challenge/participation.do?challenge_no=${vo.challenge_no }" class="button button-postComment button--active">참여 하기</a>
													</c:if>
													
											</form>
									</div>
							</div>
							<div class="col-lg-4">
									<div class="blog_right_sidebar">
											<aside class="single_sidebar_widget search_widget">
													<div class="input-group">
															<input type="text" class="form-control" placeholder="Search Posts">
															<span class="input-group-btn">
																	<button class="btn btn-default" type="button">
																			<i class="lnr lnr-magnifier"></i>
																	</button>
															</span>
													</div>
													<!-- /input-group -->
													<p>
													
													<strong><h4 align="center">참여 인증 랭킹</h4></strong>
											</aside>
											<aside class="single_sidebar_widget author_widget">
							<table>
									<td>
										<table cellpadding="10">
								         	<tr>
								         		<td>
		 	          							<img src="../challenge/rank_1.png" width=80 height=40>
		 	          							</td>
		 	          						</tr>
		 	          						<tr>
		 	          							<td>
		 	          							<img src="../challenge/rank_2.png" width=80 height=40>
		 	          							</td>
		 	          						</tr>
		 	          						<tr>
		 	          							<td>
		 	          							<img src="../challenge/rank_3.png" width=80 height=40>
		 	          							</td>
		 	          						</tr>
										</table>
									</td>
									<td>		
										<table cellpadding="10">
											<td>
				             					<c:forEach var="vo" items="${rank_list}">
												<tr>
													<td>
											       ${vo.challenge_id } (횟수 :${vo.certified_no})
											       	<td>
										 		</tr>
				             					</c:forEach>
				             					<c:if test="${rank_list.size()<3}">
				             						<tr>
				             						<c:forEach var="i" begin="${rank_list.size()}" end="2">
				             							<td>
															순위없음
				             							</td>
				             						</c:forEach>
				             						</tr>
				             					</c:if>
				             				<td>
								 		</table>	
			             			</td>
							 	</table>
<!-- 													<h4>Charlie Barber</h4> -->
<!-- 													<p>Senior blog writer</p> -->
<!-- 													<div class="social_icon"> -->
<!--                               <a href="#"> -->
<!--                                   <i class="fab fa-facebook-f"></i> -->
<!--                               </a> -->
<!--                               <a href="#"> -->
<!--                                   <i class="fab fa-twitter"></i> -->
<!--                               </a> -        ->
<!--                               <a href="#"> -->
<!--                                   <i class="fab fa-github"></i> -->
<!--                               </a> -->
<!--                               <a href="#"> -->
<!--                                 <i class="fab fa-behance"></i> -->
<!--                               </a> -->
<!--                           </div> -->
<!-- 													<p>Boot camps have its supporters andit sdetractors. Some people do not understand why you should -->
<!-- 															have to spend money on boot camp when you can get. Boot camps have itssuppor ters andits -->
<!-- 															detractors. -->
<!-- 													</p> -->
													<div class="br"></div>
											</aside>
											<aside class="single_sidebar_widget popular_post_widget">
													<h3 class="widget_title">Popular Posts</h3>
											<!-- 방 참여 중인 사람 보여주려는 부분 -->	
												<li class="breadcrumb-item active" aria-current="page">현재 참여중인 인원</li>
												<p>
										<div style="overflow-y:auto;height:200px">		
											<c:forEach var="people_list" items="${ people_list}">											
													<div class="media post_item">
															<div class="media-body">
																	<a href="blog-details.html">
																			<h3 >${people_list.challenge_id }</h3>
																	</a>
																	<p>02 Hours ago</p>
															</div>
													</div>
												</c:forEach>	
										</div>
													<div class="br"></div>
											</aside>
											<aside class="single_sidebar_widget ads_widget">
													<a href="#">
															<img class="img-fluid" src="img/blog/add.jpg" alt="">
													</a>
													<div class="br"></div>
											</aside>
											<aside class="single_sidebar_widget post_category_widget">
													<h4 class="widget_title">Post Catgories</h4>
													<ul class="list cat-list">
															<li>
																	<a href="#" class="d-flex justify-content-between">
																			<p>Technology</p>
																			<p>37</p>
																	</a>
															</li>
															<li>
																	<a href="#" class="d-flex justify-content-between">
																			<p>Lifestyle</p>
																			<p>24</p>
																	</a>
															</li>
															<li>
																	<a href="#" class="d-flex justify-content-between">
																			<p>Fashion</p>
																			<p>59</p>
																	</a>
															</li>
															<li>
																	<a href="#" class="d-flex justify-content-between">
																			<p>Art</p>
																			<p>29</p>
																	</a>
															</li>
															<li>
																	<a href="#" class="d-flex justify-content-between">
																			<p>Food</p>
																			<p>15</p>
																	</a>
															</li>
															<li>
																	<a href="#" class="d-flex justify-content-between">
																			<p>Architecture</p>
																			<p>09</p>
																	</a>
															</li>
															<li>
																	<a href="#" class="d-flex justify-content-between">
																			<p>Adventure</p>
																			<p>44</p>
																	</a>
															</li>
													</ul>
													<div class="br"></div>
											</aside>
											<aside class="single-sidebar-widget newsletter_widget">
													<h4 class="widget_title">Newsletter</h4>
													<p>
															Here, I focus on a range of items and features that we use in life without giving them a second thought.
													</p>
													<div class="form-group d-flex flex-row">
															<div class="input-group">
																	<div class="input-group-prepend">
																			<div class="input-group-text">
																					<i class="fa fa-envelope" aria-hidden="true"></i>
																			</div>
																	</div>
																	<input type="text" class="form-control" id="inlineFormInputGroup" placeholder="Enter email address" onfocus="this.placeholder = ''"
																			onblur="this.placeholder = 'Enter email'">
															</div>
															<a href="#" class="bbtns">Subcribe</a>
													</div>
													<p class="text-bottom">You can unsubscribe at any time</p>
													<div class="br"></div>
											</aside>
											<aside class="single-sidebar-widget tag_cloud_widget">
											<c:if test="${count==3 && compare<0}">
												<p align="center">
													<a href="../challenge/challenge_room_update.do?challenge_no=${vo.challenge_no }" class="button button-postComment button--active">도전 수정하기</a>
												</p>
											</c:if>
											<c:if test="${count==3 && compare>=0}">
												<p align="center">
													<a href="" class="btn btn-sm btn-danger">도전 수정 불가</a>
													<h4 align="center">도전이 이미 시작되었습니다.</h4>
												</p>
											</c:if>
											<c:if test="${count==3 && compare<0}">
												<p align="center">
													<a href="../challenge/challenge_room_delete.do?challenge_no=${vo.challenge_no }&poster=${vo.poster}" class="button button-postComment button--active">도전 삭제하기</a>
												</p>
											</c:if>
											<c:if test="${count==3 && compare>=0}">
												<p align="center">
													<a href="" class="btn btn-sm btn-danger">도전 삭제 불가</a>
													<h4 align="center">도전이 이미 시작되었습니다.</h4>
												</p>
											</c:if>
											</aside>
									</div>
							</div>
					</div>
			</div>
	</section>
	<!--================Blog Area =================-->
  

  <!--================Instagram Area =================-->
  <section class="instagram_area">
    <div class="container box_1620">
      <div class="insta_btn">
        <a class="btn theme_btn" href="#">Follow us on instagram</a>
      </div>
      <div class="instagram_image row m0">
        <a href="#"><img src="img/instagram/ins-1.jpg" alt=""></a>
        <a href="#"><img src="img/instagram/ins-2.jpg" alt=""></a>
        <a href="#"><img src="img/instagram/ins-3.jpg" alt=""></a>
        <a href="#"><img src="img/instagram/ins-4.jpg" alt=""></a>
        <a href="#"><img src="img/instagram/ins-5.jpg" alt=""></a>
        <a href="#"><img src="img/instagram/ins-6.jpg" alt=""></a>
      </div>
    </div>
  </section>
</body>			
</html>