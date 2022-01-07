<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/common.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/doctor/u05.css">
    <title>退会手続き</title>
</head>
<body>
	<jsp:include page="../common/doctor/header.jsp"/>
    <main>
        <div class="main_con">
            <form action="DoctorController?action=u05" method="POST">
                <div class="subtitle">
                    <h2>退会手続き</h2>
                </div>
                <p>
                	会員を退会された場合、登録されたすべての情報が削除されます。<br>
                    削除した情報は復元することは出来ません。<br>
                    退会手続きを進める場合は、パスワードを入力し、「退会」ボタンをクリックしてください。
                </p>
                    <div class="items">
                        <div class="items_title">
                            パスワード
                        </div>
                        <input type="password" name="pw" id="pw"><br>
                </div>
                <button type="button" onclick="history.back();">戻る</button>
                <button type="button" onclick="isSubmit(form)">退会</button>    
            </form>
        </div>
    </main>
    <jsp:include page="../common/footer.jsp"/>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/doctor/u05.js"></script>
</html>