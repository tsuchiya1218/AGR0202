<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/common.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/member/u06_s2.css">
    <title>メールアドレス変更完了</title>
</head>
<body>
	<jsp:include page="../common/header.jsp"/>
    <main>
        <div class="main_box02">
            <div class="item_con">
                <div class="subtitle">
                    <h2>メールアドレス変更完了</h2>
                </div>
                <p>
                	ご入力いただいた<strong>新しいメールアドレスに認証URL</strong>を送信しました。<br>
                	メールに記載のＵＲＬからアクセスし、メールアドレスの変更を完了してください。
                </p> 
                <a href="NonMemberController?view=u02">ログイン画面へ</a>
                <hr>
            </div>
        </div>
    </main>
    <jsp:include page="../common/footer.jsp"/>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/member/u06.js"></script>

</html>