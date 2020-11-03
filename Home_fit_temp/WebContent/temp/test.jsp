<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>방문할때마다 변하는 이미지</title>
<script type="text/javascript">
	var imgArray = new Array();
	imgArray[0] = "../template/img/product/product1.png";
	imgArray[1] = "../template/img/product/product2.png";
	imgArray[2] = "../template/img/product/product3.png"
	imgArray[3] = "../template/img/product/product4.png";
	
	function showImage(){
		var imgNum = Math.round(Math.random()*3);
		var objImg = document.getElementById("introImg");
		objImg.src = imgArray[imgNum];
	}
</script>
</head>
<body onload = "showImage()">
	<img id = "introImg" border="0">
</body>
</html>