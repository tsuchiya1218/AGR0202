<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/pharmacy/u10_01_pharmacy.css">
    <title>会員検索画面</title>
</head>
<body id="body">
	<jsp:include page="../common/pharmacy/header.jsp"/>
    <main>
        <div class="main_box">
            <section class="item_con">
                <div class="item_box">
                    <div class="subtitle">
                        <h2>会員検索</h2>
                    </div>
                    <form action="PharmacyController?action=u10" method="post">
                        <div class="items">
                            <span class="items_text">
                                検索する会員のQRコード画像をアップロードし、検索ボタンを押してください。
                            </span><br>
                            <input type="number" name="m_qr_num" placeholder="ユーザーQRコード番号" autocomplete="off" maxlength="16" oninput="maxLengthCheck(this)">
                        </div>
                        <div class="items_btn">
                            <button type="button" onclick="history.back();">戻る</button>
                            <button type="submit" class="submit_btn">検索</button>
                        </div>
                    </form>
                </div>
            </section>
        </div>
    </main>
    <jsp:include page="../common/footer.jsp"/>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common.js"></script>
</html>