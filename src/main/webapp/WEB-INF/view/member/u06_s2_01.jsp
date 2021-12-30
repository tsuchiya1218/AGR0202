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
    <title>メールアドレス変更画面</title>
</head>
<body>
	<jsp:include page="../common/member/header.jsp"/>
    <main>
        <div class="main_box">
            <div class="item_con">
                <div class="subtitle">
                    <h2>メールアドレス変更</h2>
                </div>
                <div class="items">
                    <form action="MemberController?action=u06_s2_01" method="post">
                        <div class="items_text">
                            <p>新しいメールアドレスを入力してください。</p>
                            <input type="email" name="email" placeholder="変更するメールアドレス">
                        </div>
                        <div class="items_text">
                            <p>パスワードを入力してください。</p>
                            <input type="password" name="pw">
                        </div>
                        <input type="button" onclick="location.href='MemberController?view=u06_01'" value="戻る">
                        <button type="button" onclick="isSubmit(form)">変更</button>
                    </form>
                </div>
            </div>
        </div>
    </main>
    <jsp:include page="../common/footer.jsp"/>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/member/u06.js"></script>
</html>