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
    <title>医者登録画面</title>
</head>
<body id="body">
    <jsp:include page="../../view/common/admin/header.jsp"/>
    <main>
        <div class="main_box">
            <section class="item_con">
                <div class="item_box">
                    <div class="subtitle">
                        <h2>医者登録</h2>
                    </div>
                    <form action="AdminController?action=m01_01" method="post">
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
                                <span class="items_title">名前(姓)</span>
                                <input type="text" name="frist_name" id="frist_name" placeholder="福井">
                            </label>
                        </div>
                        <div class="items">
                            <label>
                                <span class="items_title">名前(名)</span>
                                <input type="text" name="last_name" id="last_name" placeholder="岬">
                            </label>
                        </div>
                        <div class="items">
                            <label>
                                <span class="items_title">ふりがな(せい)</span>
                                <input type="text" name="frist_kana" id="frist_kana" placeholder="ふくい">
                            </label>
                        </div>
                        <div class="items">
                            <label>
                                <span class="items_title">ふりがな(めい)</span>
                                <input type="text" name="last_kana" id="last_kana" placeholder="みさき">
                            </label>
                        </div>
                        <div class="items">
                            <label class="brith">
                                <span class="items_title">生年月日</span>
                                <input type="number" name="birth" placeholder="1987" maxlength="4" oninput="maxLengthCheck(this)">-
                                <input type="number" name="birth" placeholder="06" maxlength="2" oninput="maxLengthCheck(this)">-
                                <input type="number" name="birth" placeholder="23" maxlength="2" oninput="maxLengthCheck(this)">
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
                                <span class="items_title">性別</span>
                            </label>
                            <label><input type="radio" name="gender" value="男性" checked>男性</label>
                            <label><input type="radio" name="gender" value="女性">女性</label>
                        </div>
                        <div class="items">
                            <label>
                                <span class="items_title">診療科</span>
                                <input type="text" name="department" placeholder="内科">
                            </label>
                        </div>
                        <div class="items">
                            <label>
                                <span class="items_title">所属病院名</span>
                                <input type="text" name="hospital_name" placeholder="XX病院">
                            </label>
                        </div>
                        <div class="btn_box">
                            <button type="button" onclick="history.back();">戻る</button>
                            <button type="button" class="add_btn" onclick="addSubmit(this.form);" >確認</button>
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