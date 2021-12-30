<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/common.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/member/u03.css">
    <title>パスワード再設定完了</title>
</head>
<body>
    <jsp:include page="../common/header.jsp"/>
    <main>
        <div class="main_con">
            <div class="subtitle">
                <h2>パスワード再設定完了</h2>
            </div>
            <p>
            	パスワードの再設定が完了しました。<br>
           		改めてログインしてください。
        	</p>
            <hr>
            <button type="button" onclick="location.href='NonMemberController?view=u02'">ログイン</button>
        </div>
    </main>
    <jsp:include page="../common/footer.jsp"/>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common.js"></script>

</html>