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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/member/u03.css">
    <title>パスワード再設定</title>
</head>
<body>
	<jsp:include page="../common/header.jsp"/>
    <main>
        <form action="NonMemberController?action=u03_03" method="POST">
        	<div class="main_con">
	            <div class="subtitle">
	                <h2>パスワード再設定</h2>
	            </div>
	            <p>
	            	新しいパスワードを入力し、「確定」ボタンを押してください<br>
	                パスワードは英数字のみ、８文字以上です。
                </p>
	            <div class="items">
	            <input type="hidden" name="email" value="${requestScope.email }"> 
	                <label>
	                    <div class="items_title">
	                    	<span>パスワード</span>
	                    </div>
	                    <input type="password" id="pw" name="pw">
	                </label>
	            </div>
	            <div class="items">
	                <label>
	                    <div class="items_title">
	                        <span>パスワード(確認用)</span>
	                    </div>
	                    <input type="password" id="pw_check" name="pw_check">
	                </label>
	            </div>
                <button type="button" onclick="isSubmit(form)">確定</button>
            </div>
        </form>
    </main>
    <jsp:include page="../common/footer.jsp"/>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/member/u03.js"></script>

</html>