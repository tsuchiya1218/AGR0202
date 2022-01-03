<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/common.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/member/u02.css">
    <title>ログイン画面</title>
</head>
<body>
	<jsp:include page="../common/header.jsp"/>
    <main>
        <div class="main_box" id="main_box">
            <form action="NonMemberController?action=u02" method="POST">
                <h2 class="subtitle">ログイン</h2>
                <div class="form-item">
                    <p class="formLabel">Email</p>
                    <input type="email" name="email" id="email" class="form-style" autocomplete="off"/>
                    </div>
                    <div class="form-item">
                    <p class="formLabel">Password</p>
                    <input type="password" name="password" id="password" class="form-style" />
                    <p><a href="NonMemberController?view=u03_01" class="forgot_pw"><small>パスワードを忘れた</small></a></p>  
                    </div>
                    <div class="form-item">
                    <p class="pull-left"><a href="NonMemberController?view=u01_01" class="singup_link"><small>会員登録</small></a></p>
                    <input type="submit" class="login pull-right" value="ログイン">
                    <div class="clear-fix"></div>
                    </div>
            </form>
        </div>
    </main>
    <jsp:include page="../common/footer.jsp"/>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/member/u02.js"></script>
</html>