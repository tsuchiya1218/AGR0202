<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/member/u07_02.css">
    <title>子供登録画面</title>
</head>
<body id="body">
	<jsp:include page="../common/member/header.jsp"/>
    <main>
        <div class="main_box">
            <section class="item_con">
                <div class="item_box">
                    <div class="subtitle">
                        <h2>子供登録</h2>
                    </div>
                    <form action="MemberController?action=u07_02" method="post">
                        <div class="items_req">
                            <label>
                                <span class="items_title">名前(姓)</span>
                                <input type="text" name="frist_name" placeholder="電子">
                            </label>
                        </div>
                        <div class="items_req">
                            <label>
                                <span class="items_title">名前(名)</span>
                                <input type="text" name="last_name" placeholder="一郎">
                            </label>
                        </div>
                        <div class="items_req">
                            <label>
                                <span class="items_title">ふりがな(せい)</span>
                                <input type="text" name="frist_kana" placeholder="でんし">
                            </label>
                        </div>
                        <div class="items_req">
                            <label>
                                <span class="items_title">ふりがな(めい)</span>
                                <input type="text" name="last_kana" placeholder="いちろう">
                            </label>
                        </div>
                        <div class="items_req">
                            <label class="birth">
                                <span class="items_title">生年月日</span>
                                <input type="number" name="birth" placeholder="2010" maxlength="4" oninput="maxLengthCheck(this)">
                                <input type="number" name="birth" placeholder="01" maxlength="2" oninput="maxLengthCheck(this)">
                                <input type="number" name="birth" placeholder="12" maxlength="2" oninput="maxLengthCheck(this)">
                            </label>
                        </div>
                        <div class="items_req">
                            <label>
                                <span class="items_title">性別</span>
                            </label>
                            <label><input type="radio" name="gender" value="男" checked>男</label>
                            <label><input type="radio" name="gender" value="女">女</label>
                        </div>
                        <div class="items_req">
                            <label class="child_num">
                                <span class="items_title">子ども医療証<br>受給者番号</span>
                                <input type="number" name="medical_num" placeholder="1237894" maxlength="7" oninput="maxLengthCheck(this)">
                            </label>
                        </div>
                        <div class="items">
	                        <div class="insurance">
                                <span class="items_title">保険証情報</span>
	                            <label>
	                                番号:<input type="number" name="insurance_num" placeholder="01234567" maxlength="8" oninput="maxLengthCheck(this)">
	                            </label>
								<label>
	                                枝番:<input type="number" name="insurance_num" placeholder="01" maxlength="2" oninput="maxLengthCheck(this)">
								</label>
	                        </div>
                        </div>
                        <div class="items">
	                        <div class="insurance">
                                <span class="items_title">保険証記号</span>
	                            <label>
	                                記号:<input type="text" name="insurance_mark" maxlength="10">
	                            </label>
	                        </div>
                        </div>
                        <div class="items">
                            <label class="expiry_date">
                                <span class="items_title">保険証 有効期限</span>
                                <input type="number" name="insurance_expiry_date" placeholder="2024" maxlength="4" oninput="maxLengthCheck(this)">
                                <input type="number" name="insurance_expiry_date" placeholder="01" maxlength="2" oninput="maxLengthCheck(this)">
                                <input type="number" name="insurance_expiry_date" placeholder="12" maxlength="2" oninput="maxLengthCheck(this)">
                            </label>
                        </div>
                        <div class="items">
                            <label>
                                <span class="items_title">血液型</span>
                                <select name="blood_type">
	                                <option value="A" selected>A</option>
	                                <option value="AB">AB</option>
	                                <option value="B">B</option>
	                                <option value="O">O</option>
	                            </select>
                            </label>
                        </div>
                        <div class="items">
                            <label>
                                <span class="items_title">病歴</span>
                                <span>
	                                <textarea name="medical_history" rows="3" cols="40" maxlength="200" 
	                                oninput="maxLengthCheck(this)" placeholder="今までにかかったことがある病気をかいてください。"></textarea><br>
                                </span>
                            </label>
                        </div>
                        <div class="items">
                            <label>
                                <span class="items_title">服用中の薬</span>
                                <input type="text" name="medication" size="30" maxlength="150" 
                                oninput="maxLengthCheck(this)"autocomplete="off"/>
                            </label>
                        </div>
                        <div class="items">
                            <label>
                                <span class="items_title">アレルギー情報</span>
                                <textarea name="allergy" rows="3" cols="40" maxlength="150" 
                                oninput="maxLengthCheck(this)"></textarea>
                            </label>
                        </div>
                        <div class="btn_box">
                            <button type="button" onclick="location.href='MemberController?action=u07_01'">戻る</button>
                            <button type="button" class="add_btn" onclick="addSubmit(this.form);" >確認</button>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/member/u07.js"></script>
</html>