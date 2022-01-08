<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/admin/m1_02.css">
    <title>病院登録画面</title>
</head>
<body id="body">
    <jsp:include page="../../view/common/admin/header.jsp"/>
    <main>
        <div class="main_box">
            <section class="item_con">
                <div class="item_box">
                    <div class="subtitle">
                        <h2>病院登録</h2>
                    </div>
                    <form action="AdminController?action=m03_01" method="post">
                        <div class="items">
                            <label>
                                <span class="items_title">メールアドレス</span>
                                <input type="email" name="email" placeholder="メールアドレス">
                            </label>
                        </div>
                        <div class="items">
                            <label>
                                <span class="items_title">パスワード</span>
                                <input type="password" name="password">
                            </label>
                        </div>
                        <div class="items">
                            <label>
                                <span class="items_title">病院名</span>
                                <input type="text" name="name" placeholder="COCO病院">
                            </label>
                        </div>
                        <div class="items">
                            <label class="tel">
                                <span class="items_title">電話番号</span>
                                <input type="number" name="tel" placeholder="070" min="0" maxlength="4" oninput="maxLengthCheck(this)">-
                                <input type="number" name="tel" placeholder="0123" min="0" maxlength="4" oninput="maxLengthCheck(this)">-
                                <input type="number" name="tel" placeholder="5678" min="0" maxlength="4" oninput="maxLengthCheck(this)">
                            </label>
                        </div>
                        <div class="items">
                            <label>
                                <span class="items_title">住所</span>
                                <input type="text" name="address" placeholder="東京都新宿区百人町">
                            </label>
                        </div>
                        <div class="btn_box">
                            <button type="button" onclick="history.back();">戻る</button>
                            <button type="button" class="add_btn" onclick="isSubmit(this.form);" >確定</button>
                        </div>
                    </form>
                </div>
            </section>
        </div>
    </main>
    <jsp:include page="../../view/common/footer.jsp"/>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/admin/admin_common.js"></script>
</html>