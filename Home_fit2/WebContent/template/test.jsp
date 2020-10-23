<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Aroma Shop - Home</title>
	<link rel="icon" href="img/Fevicon.png" type="image/png">
  <link rel="stylesheet" href="vendors/bootstrap/bootstrap.min.css">
  <link rel="stylesheet" href="vendors/fontawesome/css/all.min.css">
	<link rel="stylesheet" href="vendors/themify-icons/themify-icons.css">
  <link rel="stylesheet" href="vendors/nice-select/nice-select.css">
  <link rel="stylesheet" href="vendors/owl-carousel/owl.theme.default.min.css">
  <link rel="stylesheet" href="vendors/owl-carousel/owl.carousel.min.css">
  <link rel="stylesheet" href="css/style.css">
  <script src="vendors/jquery/jquery-3.2.1.min.js"></script>
  <script src="vendors/bootstrap/bootstrap.bundle.min.js"></script>
  <script src="vendors/skrollr.min.js"></script>
  <script src="vendors/owl-carousel/owl.carousel.min.js"></script>
  <script src="vendors/nice-select/jquery.nice-select.min.js"></script>
  <script src="vendors/jquery.ajaxchimp.min.js"></script>
  <script src="vendors/mail-script.js"></script>
  <script src="js/main.js"></script>
  <script type="text/javascript">
  $(document).ready(function(){
      var owl = $('.owl-custom');
      
      owl.owlCarousel({
          items: 5, // 아이템수
          loop: false,       // 루프
          dots: false,      // 닷츠
          rewind: false // 반복    
      });    
  });
  </script>
  </style>
</head>
<body>
  <!-- ================ Best Selling item  carousel ================= --> 
    <section class="section-margin calc-60px">
      <div class="container">
        <div class="section-intro pb-60px">
          <p>Popular Item in the market</p>
          <h2>Best <span class="section-intro__style">Sellers</span></h2>
        </div>
			<div class="owl-carousel owl-theme owl-custom" id="custom-carousel">
				<div class="card text-center card-product">
					<div class="card-product__img">
						<img class="img-fluid" src="img/product/product1.png" alt="">
						<ul class="card-product__imgOverlay">
							<li><button>
									<i class="ti-search"></i>
								</button></li>
							<li><button>
									<i class="ti-shopping-cart"></i>
								</button></li>
							<li><button>
									<i class="ti-heart"></i>
								</button></li>
						</ul>
					</div>
					<div class="card-body">
						<p>Accessories</p>
						<h4 class="card-product__title">
							<a href="single-product.html">Quartz Belt Watch</a>
						</h4>
						<p class="card-product__price">$150.00</p>
					</div>
				</div>

				<div class="card text-center card-product">
					<div class="card-product__img">
						<img class="img-fluid" src="img/product/product2.png" alt="">
						<ul class="card-product__imgOverlay">
							<li><button>
									<i class="ti-search"></i>
								</button></li>
							<li><button>
									<i class="ti-shopping-cart"></i>
								</button></li>
							<li><button>
									<i class="ti-heart"></i>
								</button></li>
						</ul>
					</div>
					<div class="card-body">
						<p>Beauty</p>
						<h4 class="card-product__title">
							<a href="single-product.html">Women Freshwash</a>
						</h4>
						<p class="card-product__price">$150.00</p>
					</div>
				</div>

				<div class="card text-center card-product">
					<div class="card-product__img">
						<img class="img-fluid" src="img/product/product3.png" alt="">
						<ul class="card-product__imgOverlay">
							<li><button>
									<i class="ti-search"></i>
								</button></li>
							<li><button>
									<i class="ti-shopping-cart"></i>
								</button></li>
							<li><button>
									<i class="ti-heart"></i>
								</button></li>
						</ul>
					</div>
					<div class="card-body">
						<p>Decor</p>
						<h4 class="card-product__title">
							<a href="single-product.html">Room Flash Light</a>
						</h4>
						<p class="card-product__price">$150.00</p>
					</div>
				</div>

				<div class="card text-center card-product">
					<div class="card-product__img">
						<img class="img-fluid" src="img/product/product4.png" alt="">
						<ul class="card-product__imgOverlay">
							<li><button>
									<i class="ti-search"></i>
								</button></li>
							<li><button>
									<i class="ti-shopping-cart"></i>
								</button></li>
							<li><button>
									<i class="ti-heart"></i>
								</button></li>
						</ul>
					</div>
					<div class="card-body">
						<p>Decor</p>
						<h4 class="card-product__title">
							<a href="single-product.html">Room Flash Light</a>
						</h4>
						<p class="card-product__price">$150.00</p>
					</div>
				</div>

				
		</div>
      </div>
    </section>
    <!-- ================ Best Selling item  carousel end ================= --> 
</body>
</html>