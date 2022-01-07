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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/doctor/u06_02.css">
    <title>個人情報変更画面</title>
</head>
<body>
	<jsp:include page="../common/doctor/header.jsp"/>
    <main>
        <div class="contents">
            <form class="form" action="DoctorController?action=u06_03" method="POST">
                <h2 class="account_manage_title">個人情報の変更</h2>
                <p>変更する項目の内容を再入力してください。入力後「保存して戻る」ボタンを押してください。</p>
                <table class="account_manage_table" border="1">
                	<tr>
                        <th><label>メールアドレス</label></th>
                        <td>
                            <button type="button" onclick="location.href='DoctorController?view=u06_s2_01'">メール変更</button>
                        </td>
                    </tr>
                    <tr>
                    	<c:set var="d_name" value="${fn:split(doctor.d_name,' ')}" />
                        <th><label>名前</label></th>
                        <td>
                        	姓　<input name="frist_name" id="frist_name" type="text" value="${d_name[0] }" required><br>
                            名　<input name="last_name" id="last_name" type="text" value="${d_name[1] }" required>
                        </td>
                    </tr>
                    <tr>
                   		<c:set var="d_kana" value="${fn:split(doctor.d_kana,' ')}" />
                        <th><label>名前(ふりがな)</label></th>
                        <td>
                        	せい<input name="frist_kana" id="frist_kana" type="text" value="${d_kana[0] }" required><br>
                            めい<input name="last_kana" id="last_kana" type="text" value="${d_kana[1] }" required>
                        </td>
                    </tr>
                    <tr>
	                    <th><label>性別</label></th>
	                    <td>
	                    	<c:if test="${doctor.d_gender eq '男性' }">
		                        <label>男性<input type="radio" name="gender" value="男性" checked></label>
		                        <label>女性<input type="radio" name="gender" value="女性"></label>
	                    	</c:if>
	                    	<c:if test="${doctor.d_gender eq '女性' }">
		                        <label>男性<input type="radio" name="gender" value="男性"></label><br>
		                        <label>女性<input type="radio" name="gender" value="女性" checked></label>
	                    	</c:if>
	                    </td>
	                </tr>
                    <tr class="birthday">
                    	<c:set var="d_birth" value="${fn:split(doctor.d_birth,'-')}" />
                        <th>生年月日</th>
                        <td><input type="number" name="birth" value="${d_birth[0] }" maxlength="4" oninput="maxLengthCheck(this)" required>
                            <input type="number" name="birth" value="${d_birth[1] }" maxlength="2" oninput="maxLengthCheck(this)" required>
                            <input type="number" name="birth" value="${d_birth[2] }" maxlength="2" oninput="maxLengthCheck(this)" required>
                        </td>
                    </tr>
                    <tr class="tel">
                    	<c:set var="d_tel" value="${fn:split(doctor.d_tel,'-')}" />
                        <th><label>電話番号</label></th>
                        <td><input name="tel" type="number" value="${d_tel[0] }" maxlength="4" oninput="maxLengthCheck(this)" required>-
                            <input name="tel" type="number" value="${d_tel[1] }" maxlength="4" oninput="maxLengthCheck(this)" required>-
                            <input name="tel" type="number" value="${d_tel[2] }" maxlength="4" oninput="maxLengthCheck(this)" required>
                        </td>
                    </tr>
                    <tr>
                        <th><label>診察科</label></th>
                        <td>
                        	<input name="d_department" type="text" value="${doctor.d_department }" required>
                        </td>
                    </tr>
                    <tr>
                        <th><label>現在のパスワード</label></th>
                        <td>
                            <input name="pw_original" type="password"><br>
                            <small>
	                            パスワードを変更したい場合、現在のパスワードを入力してください。<br>
	                            現在のパスワード・パスワード・確認用を全部入力しない限り<br>
	                            パスワードは変更されません。
                            </small>
                        </td>
                    </tr>
                    <tr>
                        <th><label>パスワード</label></th>
                        <td>
                            <input name="pw" type="password"><br>
                            <small>最低8文字の長さが必要です</small>
                        </td>
                    </tr>
                    <tr>
                        <th><label>パスワード<span>(確認用)</span></label></th>
                        <td>
                            <input name="pw" type="password">
                        </td>
                    </tr>
                </table>
                <section class="twobtns">
                    <input type="button" onclick="history.back();" value="戻る">
                    <input type="submit" value="確定">
                </section>
            </form>
    	</div>
    </main>
    <jsp:include page="../common/footer.jsp"/>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common.js"></script>
</html>