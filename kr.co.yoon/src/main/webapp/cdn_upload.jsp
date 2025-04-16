<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 
CDN : 콘텐츠 전송 네트워크 서비스
	이미지, 동영상, 문서파일 같은 것만 가능
	(.html, .jsp, .htm, .js, .css 이런건 절대 안올라감)
	E-러닝, 스트리밍같은 분야에 사용 
	쇼핑몰 상품판매 
 -->
<title>Cdn Server에 파일전송</title>
</head>
<body>


<form id="frm" method="post" action="./cdn_uploadok.do" enctype="multipart/form-data">
CDN 파일전송 : <input type="file" name="mfile">
<input type="button" value="전송" onclick="fileok()">
</form>


</body>
<script>
function fileok(){
	frm.submit();
}
</script>
</html>