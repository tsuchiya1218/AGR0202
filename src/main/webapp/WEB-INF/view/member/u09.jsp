<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/member/u09.css">
    <title>会員QRコード画面</title>
</head>
<body id="body">
    <jsp:include page="../common/member/header.jsp"/>
    <main>
        <div class="main_box">
            <section class="main_item">
                <div class="item_box">
                    <div class="subtitle"><h2>My QRコード</h2></div>
                    <img src="${pageContext.request.contextPath}/static/img/qr_code/${member.m_qr_num}.png" alt="My QRコード">
                </div>
                <p>${member.m_qr_num }</p>
                <div class="qr_btn_box">
                    <form action="MemberController?action=u09" method="post">
                        <button type="submit" class="qr_btn">ダウンロード</button>
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