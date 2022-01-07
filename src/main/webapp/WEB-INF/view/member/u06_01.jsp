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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/member/u06_01.css">
    <title>マイページ</title>
</head>
<body>
	<jsp:include page="../common/member/header.jsp"/>
    <main>
        <div class="contents">
            <h3 class="account_manage_title">個人情報</h3>
            <table class="account_manage_table" border="1">
                <tr>
                    <th><label>名前</label></th>
                    <td><c:out value="${sessionScope.member.m_name }"/></td>
                </tr>
                <tr>
                    <th><label>名前(ふりがな)</label></th>
                    <td><c:out value="${sessionScope.member.m_kana }"/></td>
                </tr>
                <tr>
                    <th><label>性別</label></th>
                    <td><c:out value="${sessionScope.member.m_gender }"/></td>
                </tr>
                <tr>
                    <th><label>生年月日</label></th>
                    <td>
                    	<c:out value="${sessionScope.member.m_birth }"/>
                    	<c:out value="(${sessionScope.member.age }歳)"/>
                    </td>
                </tr>
                <tr>
                    <th><label>電話番号</label></th>
                    <td><c:out value="${sessionScope.member.m_tel }"/></td>
                </tr>
                <tr>
                    <th><label>郵便番号</label></th>
                    <td><c:out value="${sessionScope.member.m_zip_code }"/></td>
                </tr>
                <tr>
                    <th><label>住所</label></th>
                    <td><c:out value="${sessionScope.member.m_address }"/></td>
                </tr>
                <tr>
                    <th><label>保険証番号</label></th>
                    <c:set var="m_i_num" value="${fn:split(sessionScope.member.m_i_num,'-')}" />
                    <td>番号:<c:out value="${m_i_num[0] }"/> 枝番:<c:out value="${m_i_num[1] }"/></td>
                </tr>
                <tr>
                    <th><label>保険証記号</label></th>
                    <td><c:out value="${sessionScope.member.m_i_mark }"/></td>
                </tr>
                <tr>
                    <th><label>保険証有効期限</label></th>
                    <td><c:out value="${sessionScope.member.m_i_expiry_date }"/></td>
                </tr>
            </table>
            <div class="account_manage_btn_box">
                <form action="MemberController?view=u06_02" method="post">
                    <button class="member_update">個人情報変更</button>
                </form>
            </div>
            <h3 class="account_manage_title">問診表</h3>
            <table class="account_manage_table" border="1">
                <tr>
                    <th><label>血液型</label></th>
                    <td>
                    	<c:out value="${sessionScope.questionnaire.q_blood_type }"/>
                    </td>
                </tr>
                <tr>
                    <th><label>今までにかかった病気</label></th>
                    <td>
                    	<c:set var="q_medical_history" value="${fn:replace(questionnaire.q_medical_history,'&lt;br&gt;','<br>')}" />
                    	<c:out escapeXml="false" value="${q_medical_history }"/>
                    </td>
                </tr>
                <tr>
                    <th><label>服用中のお薬</label></th>
                    <td><c:out value="${sessionScope.questionnaire.q_medication }"/></td>
                </tr>
                <tr>
                    <th><label>飲酒</label></th>
                    <td>
                    	<c:if test="${sessionScope.questionnaire.q_drink eq true }">
	                    	<c:out value="あり"/>
                    	</c:if>
                    	<c:if test="${sessionScope.questionnaire.q_drink eq false }">
	                    	<c:out value="なし"/>
                    	</c:if>
                    </td>
                </tr>
                <tr>
                    <th><label>喫煙</label></th>
                    <td>
                    	<c:if test="${sessionScope.questionnaire.q_smoke eq true }">
	                    	<c:out value="あり"/>
                    	</c:if>
                    	<c:if test="${sessionScope.questionnaire.q_smoke eq false }">
	                    	<c:out value="なし"/>
                    	</c:if>
					</td>
                </tr>
                <c:if test="${member.m_gender eq '女性' }">
	                <tr>
	                    <th><label>妊娠</label></th>
	                    <td>
	                    	<c:if test="${sessionScope.questionnaire.q_pregnancy eq true }">
		                    	<c:out value="あり"/>
	                    	</c:if>
	                    	<c:if test="${sessionScope.questionnaire.q_pregnancy eq false }">
		                    	<c:out value="なし"/>
	                    	</c:if>
	                    </td>
	                </tr>
                </c:if>
                <tr>
                    <th><label>アレルギー情報</label></th>
                    <td>
                    	<c:set var="q_allergy" value="${fn:replace(questionnaire.q_allergy,'&lt;br&gt;','<br>')}" />
                    	<c:out escapeXml="false" value="${q_allergy}"/>
                    </td>
                </tr>
            </table>
            <div class="questionnaire_btn_box">
                <a href="MemberController?view=u06_s1"><button>問診票変更</button></a>
                    
            </div>
            <div class="member_leave_btn_box">
                <p>
                    退会を希望する方は以下にアクセスしてください。
                    <a href="MemberController?view=u05_01">退会する</a>
                </p>
            </div>
        </div>
    </main>
    <jsp:include page="../common/footer.jsp"/>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common.js"></script>
</html>