<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/pharmacy/u14.css">
    <title>薬情報登録画面</title>
</head>
<body id="body">
    <jsp:include page="../common/pharmacy/header.jsp"/>
    <main>
        <div class="main_box">
            <section class="item_con">
                <div class="item_box">
                    <div class="subtitle">
                        <h2>薬情報登録</h2>
                    </div>
                    <form action="PharmacyController?action=u14" method="POST" enctype="multipart/form-data">
                        <div class="items">
                            <span class="items_title">写真</span><br>
							<img id="preview" src="" width="200"><br>
                            <input type="file" id="img_file" name="drug_img">
                        </div>
                        <div class="items">
                            <label>
                                <span class="items_title">薬名</span>
                                <input type="text" name="drug_name" placeholder="リリカOD" maxlength="50" autocomplete="off" oninput="maxLengthCheck(this)" required>
                            </label>
                        </div>
                        <div class="items">
                            <label>
                                <span class="items_title">種類</span>
                                <select name="drug_type">
                                    <option value="錠剤" selected>錠剤</option>
                                    <option value="カプセル剤" >カプセル剤</option>
                                    <option value="散剤(粉薬)" >散剤(粉薬)</option>
                                    <option value="液剤" >液剤</option>
                                    <option value="顆粒剤" >顆粒剤</option>
                                    <option value="坐剤・膣剤" >坐剤・膣剤</option>
                                    <option value="貼り薬" >貼り薬</option>
                                    <option value="塗り薬" >塗り薬</option>
                                    <option value="点眼薬" >点眼薬</option>
                                    <option value="吸入剤" >吸入剤</option>
                                    <option value="噴霧剤 エアゾール剤" >噴霧剤 エアゾール剤</option>
                                    <option value="注射剤" >注射剤</option>
                                    <option value="その他" >その他</option>
                                </select>
                            </label>
                        </div>
                        <div class="items">
                            <label>
                                <span class="items_title">効果</span>
                                <textarea name="drug_effect" placeholder="血液の流れを良くする薬です。" maxlength="500"></textarea>
                            </label>
                        </div>
                        <div class="items">
                            <label>
                                内服薬
                                <input type="radio" name="drug_guide" value="内服薬" checked>
                            </label>
                            <label style="margin:0 20px;">
                                外用薬
                                <input type="radio" name="drug_guide" value="外用薬">
                            </label>
                            <label>
                                注射薬
                                <input type="radio" name="drug_guide" value="注射薬">
                            </label>
                        </div>
                        <div class="items">
                            <label>
                                <span class="items_title">備考</span>
                                <textarea name="drug_note" placeholder="この薬品にジェネリック医薬品は存在しません。" maxlength="500"></textarea>
                            </label>
                        </div>
                        <div class="items">
                            <label>
                                <span class="items_title">値段</span>
                                <input type="number" name="drug_price" placeholder="200" maxlength="10" oninput="maxLengthCheck(this)" required>
                            </label>
                        </div>
                        <div class="items_btn">
                            <button type="button" onclick="history.back()">戻る</button>
                            <button type="button" class="submit_btn" onclick="checkForm(form)">確認</button>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/pharmacy/u14.js"></script>
</html>