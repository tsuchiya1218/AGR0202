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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/member/u06_02.css">
    <title>個人情報変更画面</title>
</head>
<body>
	<jsp:include page="../common/member/header.jsp"/>
    <main>
        <div class="contents">
            <form class="form" action="MemberController?action=u06" method="POST">
                <h2 class="account_manage_title">個人情報の変更</h2>
                <p>変更する項目の内容を再入力してください。入力後「保存して戻る」ボタンを押してください。</p>
                <table class="account_manage_table" border="1">
                	<tr>
                        <th><label>メールアドレス</label></th>
                        <td>
                            <button type="button" onclick="location.href='MemberController?view=u06_s2_01'">メール変更</button>
                        </td>
                    </tr>
                    <tr>
                    <c:set var="m_name" value="${fn:split(member.m_name,' ')}" />
                        <th><label>名前</label></th>
                        <td>姓　<input name="frist_name" id="frist_name" type="text" value="${m_name[0] }" required><br>
                            名　<input name="last_name" id="last_name" type="text" value="${m_name[1] }" required>
                        </td>
                    </tr>
                    <tr>
                    <c:set var="m_kana" value="${fn:split(member.m_kana,' ')}" />
                        <th><label>名前(ふりがな)</label></th>
                        <td>せい<input name="frist_kana" id="frist_kana" type="text" value="${m_kana[0] }" required>
                            めい<input name="last_kana" id="last_kana" type="text" value="${m_kana[1] }" required>
                        </td>
                    </tr>
                    <tr>
	                    <th><label>性別</label></th>
	                    <td>
	                    	<c:if test="${member.m_gender eq '男性' }">
		                        <label><input type="radio" id="man" name="gender" value="男性" checked>男性</label>
		                        <label><input type="radio" id="woman" name="gender" value="女性">女性</label>
	                    	</c:if>
	                    	<c:if test="${member.m_gender eq '女性' }">
		                        <label><input type="radio" id="man" name="gender" value="男性">男性</label>
		                        <label><input type="radio" id="woman" name="gender" value="女性" checked>女性</label>
	                    	</c:if>
	                    </td>
	                </tr>
                    <tr class="birthday">
                    	<c:set var="m_birth" value="${fn:split(member.m_birth,'-')}" />
                        <th>生年月日</th>
                        <td><input type="number" name="birth" value="${m_birth[0] }" maxlength="4" oninput="maxLengthCheck(this)" required>
                            <input type="number" name="birth" value="${m_birth[1] }" maxlength="2" oninput="maxLengthCheck(this)" required>
                            <input type="number" name="birth" value="${m_birth[2] }" maxlength="2" oninput="maxLengthCheck(this)" required>
                        </td>
                    </tr>
                    <tr>
                    	<c:set var="m_tel" value="${fn:split(member.m_tel,'-')}" />
                        <th><label>電話番号</label></th>
                        <td><input name="tel" type="number" value="${m_tel[0] }" maxlength="4" oninput="maxLengthCheck(this)" required>-
                            <input name="tel" type="number" value="${m_tel[1] }" maxlength="4" oninput="maxLengthCheck(this)" required>-
                            <input name="tel" type="number" value="${m_tel[2] }" maxlength="4" oninput="maxLengthCheck(this)" required>
                        </td>
                    </tr>
                    <tr>
                    	<c:set var="m_zip_code" value="${fn:split(member.m_zip_code,'-')}" />
                        <th><label>郵便番号</label></th>
                        <td><input name="zip_code" type="number" value="${m_zip_code[0] }" required maxlength="4" oninput="maxLengthCheck(this)">-
                            <input name="zip_code" type="number" value="${m_zip_code[1] }" required maxlength="4" oninput="maxLengthCheck(this)">
                        </td>
                    </tr>
                    <tr>
                        <th>住所</th>
                        <td><input name="address" id="address" type="text" value="${member.m_address }" required></td>
                    </tr>
                    <tr>
                        <th>保険証記号</th>
                        <td>記号<input name="insurance_mark" type="text" value="${member.m_i_mark }" required>
                        </td>
                    </tr>
                    <tr>
                    	<c:set var="m_i_num" value="${fn:split(member.m_i_num,'-')}" />
                        <th>保険証番号</th>
                        <td>番号<input name="insurance_num" type="number" value="${m_i_num[0] }" maxlength="8" oninput="maxLengthCheck(this)" required>
                            枝番<input name="insurance_num" type="number" value="${m_i_num[1] }" maxlength="2" oninput="maxLengthCheck(this)">
                        </td>
                    </tr>
                    <tr class="hoken">
                    	<c:set var="m_i_expiry_date" value="${fn:split(member.m_i_expiry_date,'-')}" />
                        <th>保険証有効期限</th>
                        <td><input type="number" name="insurance_expiry_date" value="${m_i_expiry_date[0] }" maxlength="4" oninput="maxLengthCheck(this)" required>
                            <input type="number" name="insurance_expiry_date" value="${m_i_expiry_date[1] }" maxlength="2" oninput="maxLengthCheck(this)" required>
                            <input type="number" name="insurance_expiry_date" value="${m_i_expiry_date[2] }" maxlength="2" oninput="maxLengthCheck(this)" required>
                        </td>
                    </tr>
                    <tr>
                        <th><label>現在のパスワード</label></th>
                        <td>
                            <input name="pw_original" type="password" required><br>
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
                            <input name="pw" type="password" required>
                            <small>最低8文字の長さが必要です</small>
                        </td>
                    </tr>
                    <tr>
                        <th><label>パスワード<span>(確認用)</span></label></th>
                        <td>
                            <input name="pw" type="password" required>
                        </td>
                    </tr>
                </table>
                <section class="twobtns">
                    <input type="button" onclick="location.href='MemberController?view=u06_01'" value="戻る">
                    <input type="button" onclick="isUpdate(form)" value="確定">
                </section>
            </form>
    	</div>
    </main>
    <jsp:include page="../common/footer.jsp"/>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/member/u06.js"></script>
</html>