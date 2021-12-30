<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/common.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/member/u01.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/member/u01_03_04.css">
    <title>会員認証完了画面</title>
</head>
<body>
    <jsp:include page="../common/header.jsp"/>
    <main>
        <div class="contents">
            <div class="registFinish">
                <p>登録が完了しました。ログインしてから利用可能です。</p>
            </div>
            <section class="finishBtn">
                <input type="button" onclick="location.href='NonMemberController?view=u02'" value="ログイン画面へ">
            </section>
        </div>
    </main>
    <jsp:include page="../common/footer.jsp"/>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common.js"></script>

</html>