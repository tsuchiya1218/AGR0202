<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/common.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/member/u05.css">
    <title>退会完了画面</title>
</head>
<body>
	<jsp:include page="../common/member/header.jsp"/>
    <main>
        <div class="main_con">
            <div class="subtitle">
                <h2>退会手続き完了</h2>
            </div>
            <p>
                会員の退会手続きが完了しました<br>
                退会手続き完了メールを送信しましたので、内容をご確認ください。<br>
                ご利用ありがとうございました。<br>
                またの機会のご利用を、心よりお待ちしております。</p>
            </p>
        </div>
    </main>
    <jsp:include page="../common/footer.jsp"/>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common.js"></script>

</html>