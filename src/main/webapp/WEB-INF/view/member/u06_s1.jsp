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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/member/u06_s1.css">
    <title>問診票情報変更画面</title>
</head>
<body>
	<jsp:include page="../common/member/header.jsp"/>
    <main>
        <div class="contents">
            <h2 class="account_manage_title">問診票の入力</h2>
           <p>変更する項目の内容を再入力してください。入力後「保存して戻る」ボタンを押してください。</p> 
            <form action="MemberController?action=u06_s1" method="POST">
                <table border="1">
                    <tr>
                        <th>
                            <label class="itemTitle">血液型</label>
                        </th>
                        <td>
                        	<select name="blood_type">
		                        <c:choose>
		                        	<c:when test="${sessionScope.questionnaire.q_blood_type eq 'A'}">
		                                <option value="A" selected>A</option>
		                                <option value="AB">AB</option>
		                                <option value="B">B</option>
		                                <option value="O">O</option>
		                        	</c:when>
		                        	<c:when test="${questionnaire.q_blood_type eq 'AB'}">
		                                <option value="A">A</option>
		                                <option value="AB" selected>AB</option>
		                                <option value="B">B</option>
		                                <option value="O">O</option>
		                        	</c:when>
		                        	<c:when test="${questionnaire.q_blood_type eq 'B'}">
		                                <option value="A">A</option>
		                                <option value="AB">AB</option>
		                                <option value="B" selected>B</option>
		                                <option value="O">O</option>
		                        	</c:when>
		                        	<c:otherwise>
		                                <option value="A">A</option>
		                                <option value="AB">AB</option>
		                                <option value="B">B</option>
		                                <option value="O" selected>O</option>
		                        	</c:otherwise>
		                        </c:choose>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label class="itemTitle">今までにかかった病気</label>
                        </th>
                        <td>
                            <c:set var="q_medical_history" value="${fn:replace(questionnaire.q_medical_history,'&lt;br&gt;','&#10;')}" />
                            <textarea name="medical_history" rows="3" cols="40" maxlength="200"  oninput="maxLengthCheck(this)">${q_medical_history }</textarea><br>
                            <span>今までにかかったことがある病気をかいてください。</span>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label class="itemTitle">服用中のお薬</label>
                        </th>
                        <td>
                            <input type="text" name="medication" size="30" maxlength="150" oninput="maxLengthCheck(this)"
                           	style="width: 100%; max-width: 15em" value="${questionnaire.q_medication }" autocomplete="off"/>		
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label class="itemTitle">飲酒</label>
                        </th>
                        <td>
                            <c:if test="${questionnaire.q_drink eq true }">
	                            <label><input type="radio" value="1" name="drink" checked>あり</label>
	                            <label><input type="radio" value="0" name="drink">なし</label>
                        	</c:if>
                        	<c:if test="${questionnaire.q_drink eq false }">
	                            <label><input type="radio" value="1" name="drink" >あり</label>
	                            <label><input type="radio" value="0" name="drink" checked>なし</label>
                        	</c:if>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label class="itemTitle">喫煙</label>
                        </th>
                        <td>
                            <c:if test="${questionnaire.q_smoke eq true }">
	                            <label><input type="radio" value="1" name="smoke" checked>あり</label>
	                            <label><input type="radio" value="0" name="smoke">なし</label>
                            </c:if>
                            <c:if test="${questionnaire.q_smoke eq false }">
	                            <label><input type="radio" value="1" name="smoke">あり</label>
	                            <label><input type="radio" value="0" name="smoke" checked>なし</label>
                            </c:if>
                        </td>
                    </tr>
                    <c:if test="${member.m_gender eq '女性' }">
	                    <tr>
	                        <th>
	                            <label class="itemTitle">妊娠</label>
	                        </th>
	                        <td>
	                            <c:if test="${questionnaire.q_pregnancy eq true }">
		                            <label><input type="radio" value="1" name="pregnancy" checked>あり</label>
		                            <label><input type="radio" value="0" name="pregnancy">なし</label><br>
	                            </c:if>
	                            <c:if test="${questionnaire.q_pregnancy eq false }">
		                            <label><input type="radio" value="1" name="pregnancy">あり</label>
		                            <label><input type="radio" value="0" name="pregnancy" checked>なし</label><br>
	                            </c:if>
	                            <span>男性の方は「なし」を選択してください</span>
	                        </td>
	                    </tr>
                    </c:if>
                    <tr>
                        <th>
                            <label class="itemTitle">アレルギー情報</label>
                        </th>
                        <td>
                            <c:set var="q_allergy" value="${fn:replace(questionnaire.q_allergy,'&lt;br&gt;','&#10;')}" />
                            <textarea name="allergy" rows="3" cols="40" maxlength="150" oninput="maxLengthCheck(this)">${q_allergy }</textarea>
                        </td>
                    </tr>
                </table>
                <section class="twobtns">
                    <input type="button" onclick="location.href='MemberController?view=u06_01'" value="戻る">
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