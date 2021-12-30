<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/common.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/member/u01.css">
    <title>新規会員登録</title>
</head>
<body>
	<jsp:include page="../common/header.jsp"/>
    <main>
        <div class="contents">
            <p>
                登録情報と問診表を入力してください。<br>
                <strong>(*)</strong>はすべて必須項目です。<br>
                必須項目には<strong>空白スペースを使用しないでください。</strong><br>
                入力後「確認」ボタンを押してください。
            </p>
            <form class="form" action="NonMemberController" method="POST">
	            <input type="hidden" name="action" value="u01_01">
	            <h3 class="registTitle">新規会員登録</h3>
	            	<table class="registTable" border="1">
	                <tr>
	                    <th><label>メールアドレス<span>(*)</span></label></th>
	                    <td><input name="email" type="email" placeholder="denshiichiro@jec.ac.jp" autocomplete="off" maxlength="50" oninput="maxLengthCheck(this)"></td>
	                </tr>
	                <tr>
	                    <th><label>名前<span>(*)</span></label></th>
	                    <td>
	                        姓<input name="frist_name" id="frist_name" type="text" placeholder="電子" autocomplete="off">
	                        名<input name="last_name" id="last_name" type="text" placeholder="一郎" autocomplete="off">
	                    </td>
	                </tr>
	                <tr>
	                    <th><label>名前(ふりがな)<span>(*)</span></label></th>
	                    <td>
	                        せい<input name="frist_kana" id="frist_kana" type="text" placeholder="でんし" autocomplete="off">
	                        めい<input name="last_kana" id="last_kana" type="text" placeholder="いちろう" autocomplete="off">
	                    </td>
	                </tr>
	                <tr>
	                    <th><label>性別<span>(*)</span></label></th>
	                    <td>
	                        <label><input type="radio" id="man" name="gender" value="男性" checked>男性</label>
	                        <label><input type="radio" id="woman" name="gender" value="女性">女性</label>
	                    </td>
	                </tr>
	                <tr class="birthday">
	                    <th><label>生年月日<span>(*)</span></label></th>
	                    <td>
	                        <input type="number" name="brith" id=brith_year placeholder="1986" min="1900" autocomplete="off" maxlength="4" oninput="maxLengthCheck(this)">-
	                        <input type="number" name="brith" placeholder="01" autocomplete="off" min="01" max="12" maxlength="2" oninput="maxLengthCheck(this)">-
	                        <input type="number" name="brith" placeholder="24" autocomplete="off" min="01" max="31" maxlength="2" oninput="maxLengthCheck(this)">
	                    </td>
	                </tr>
	                <tr>
	                    <th><label>電話番号<span>(*)</span></label></th>
	                    <td>
	                        <input class="phone" name="tel" type="number" placeholder="090" autocomplete="off" maxlength="4" oninput="maxLengthCheck(this)">-
	                        <input class="phone" name="tel" type="number" placeholder="090" autocomplete="off" maxlength="4" oninput="maxLengthCheck(this)">-
	                        <input class="phone" name="tel" type="number" placeholder="090" autocomplete="off" maxlength="4" oninput="maxLengthCheck(this)">
	                    </td>
	                </tr>
	                <tr class="postnum">
	                    <th><label>郵便番号<span>(*)</span></label></th>
	                    <td>
	                        <input class="zip_code" name="zip_code" type="number" placeholder="169" autocomplete="off" maxlength="4" oninput="maxLengthCheck(this)">-
	                        <input class="zip_code" name="zip_code" type="number" placeholder="8522" autocomplete="off" maxlength="4" oninput="maxLengthCheck(this)">
	                    </td>
	                </tr>
	                <tr>
	                    <th><label>住所<span>(*)</span></label></th>
	                    <td>
	                        <input type="text" name="address" id="address" maxlength="150" placeholder="東京都新宿区百人町1-25-4" autocomplete="off">
	                    </td>
	                </tr>
	                <tr>
	                    <th>
	                        <label>保険証番号<span>(*)</span></label>
	                    </th>
	                    <td>
	                        <input id="hoken2" name="insurance_num" type="number" placeholder="番号(012345)" autocomplete="off" maxlength="8" oninput="maxLengthCheck(this)">-
	                        <input id="hoken1" name="insurance_num" type="number" placeholder="枝番(00)" autocomplete="off" maxlength="2" oninput="maxLengthCheck(this)"><br>
	                        <small>保険証には６桁か８桁の「保険者番号」を入力してください。</small><br>
	                        <small>被保険者本人は「00」です。</small>
	                    </td>
	                </tr>
	                <tr>
	                    <th>
	                        <label>保険証記号<span>(*)</span></label>
	                    </th>
	                    <td>
	                        <input type="text" name="insurance_mark" placeholder="記号(戸田)" autocomplete="off" maxlength="10" oninput="maxLengthCheck(this)">
	                    </td>
	                </tr>
	                <tr>
	                    <th>
	                        <label>保険証有効期限<span>(*)</span></label>
	                    </th>
	                    <td class="hoken_td">
	                        <input name="insurance_expiry_date" type="number" placeholder="2023" autocomplete="off" maxlength="4" oninput="maxLengthCheck(this)">-
	                        <input name="insurance_expiry_date" type="number" placeholder="12" autocomplete="off" maxlength="2" oninput="maxLengthCheck(this)">-
	                        <input name="insurance_expiry_date" type="number" placeholder="09" autocomplete="off" maxlength="2" oninput="maxLengthCheck(this)"><br>
	                    </td>
	                </tr>
	                <tr>
	                    <th rowspan="2">
	                        <label>パスワード<span>(*)</span></label>
	                    </th>
	                    <td>
	                        <input name="pw" id="pw1" type="password" placeholder="英数字８文字以上" maxlength="50" oninput="maxLengthCheck(this)" autocomplete="off"><br>
	                        <small>最低8文字の長さが必要です</small>
	                    </td>
	                </tr>
	                <tr>
	                    <td>
	                        <input name="pw" id="pw2" type="password" placeholder="英数字８文字以上" maxlength="50" oninput="maxLengthCheck(this)" autocomplete="off"><br>
	                        <small>最低8文字の長さが必要です</small>
	                    </td>
	                </tr>
	            </table>
	            <div class="question-wrap">
               		<h3 class="questionTitle">問診表</h3>
	                <table class="questionTable" border="1">
	                    <tr>
	                        <th>
	                            <label class="itemTitle">血液型</label>
	                        </th>
	                        <td>
	                            <select name="blood_type">
	                                <option value="A" selected>A</option>
	                                <option value="AB">AB</option>
	                                <option value="B">B</option>
	                                <option value="O">O</option>
	                            </select>
	                        </td>
	                    </tr>
	                    <tr>
	                        <th>
	                            <label class="itemTitle">今までにかかった病気</label>
	                        </th>
	                        <td>
	                            <textarea name="medical_history" rows="3" cols="40" maxlength="200" oninput="maxLengthCheck(this)"></textarea><br>
	                            <span>今までにかかったことがある病気をかいてください。</span>
	                        </td>
	                    </tr>
	                    <tr>
	                        <th>
	                            <label class="itemTitle">服用中のお薬</label>
	                        </th>
	                        <td>
	                            <input type="text" name="medication" size="30" maxlength="150" oninput="maxLengthCheck(this)"
	                            style="width: 100%; max-width: 15em" autocomplete="off"/>
	                        </td>
	                    </tr>
	                    <tr>
	                        <th>
	                            <label class="itemTitle">飲酒</label>
	                        </th>
	                        <td>
	                            <label><input type="radio" value="1" name="drink" >あり</label>
	                            <label><input type="radio" value="0" name="drink" checked>なし</label>
	                        </td>
	                    </tr>
	                    <tr>
	                        <th>
	                            <label class="itemTitle">喫煙</label>
	                        </th>
	                        <td>
	                            <label><input type="radio" value="1" name="smoke">あり</label>
	                            <label><input type="radio" value="0" name="smoke" checked>なし</label>
	                        </td>
	                    </tr>
	                    <tr>
	                        <th>
	                            <label class="itemTitle">妊娠</label>
	                        </th>
	                        <td>
	                            <label><input type="radio" value="1" name="pregnancy">あり</label>
	                            <label><input type="radio" value="0" name="pregnancy" checked>なし</label><br>
	                            <span>男性の方は「なし」を選択してください</span>
	                        </td>
	                    </tr>
	                    <tr>
	                        <th>
	                            <label class="itemTitle">アレルギー情報</label>
	                        </th>
	                        <td>
	                            <textarea name="allergy" rows="3" cols="40" maxlength="150" oninput="maxLengthCheck(this)"></textarea>
	                        </td>
	                    </tr>
	                </table>
                </div>
                <section class="twobtns">
                    <input type="button" onclick="location.href='${pageContext.request.contextPath}/index.jsp'" value="戻る">
                    <input type="button" onclick="isSubmit(form);" value="確定">
                </section>
            </form>
        </div>
    </main>
    <jsp:include page="../common/footer.jsp"/>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/member/u01.js"></script>
</html>