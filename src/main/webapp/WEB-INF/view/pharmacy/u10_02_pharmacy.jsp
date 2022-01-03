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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/pharmacy/u10_02_pharmacy.css">
    <title>会員検索結果画面</title>
</head>
<body id="body">
    <jsp:include page="../common/pharmacy/header.jsp"/>
    <main>
        <div class="main_box">
            <section class="item_con">
                <div class="item_box">
                    <div class="subtitle">
                        <h2>個人情報</h2>
                    </div>
                    <p class="select_msg">以下の選択ボックスで子どもを選択することができます。<br>選択した上で「表示」ボタンを押してください</p>
                    <form action="PharmacyController?action=u10_s1" method="post">
                        <div class="member_name">
                            <span>${member.m_name }</span>
                            <span>${member.m_kana }</span>
                            <select name="selected">
                                <option value="0" selected>本人</option>
                                <c:set var="children" value="${sessionScope.child }"/>
                                <c:if test="${children ne null }">
                                	<c:forEach var="child" items="${children }" varStatus="index">
		                                <option value="${index.count }">${child.c_name }</option>
                                	</c:forEach>
                                </c:if>
                            </select>
                            <button type="submit">表示</button>
                        </div>
                    </form>
                    <div class="items">
                        <div class="items_row">
                            <span class="items_title">
                                保険証番号
                            </span>
                            <span class="items_text">
                            	<c:set var="m_i_num" value="${fn:split(member.m_i_num,('-')) }"/>
                                保険証番号:<c:out value="${m_i_num[0] }"/> 枝番:<c:out value="${m_i_num[1] }"/>
                            </span>
                        </div>
                        <div class="items_row">
                            <span class="items_title">
                                保険証記号
                            </span>
                            <span class="items_text">
                                <c:out value="${member.m_i_mark }"/>
                            </span>
                        </div>
                    </div>
                    <div class="items">
                    	<div class="items_row">
                            <span class="items_title">
                                保険証 有効期限
                            </span>
                            <span class="items_text">
                                <c:out value="${member.m_i_expiry_date }"/>
                            </span>
                        </div>
                        <div class="items_row">
                            <span class="items_title">
                                性別
                            </span>
                            <span class="items_text">
                                <c:out value="${member.m_gender}"/>
                            </span>
                        </div>
                    </div>
                    <div class="items">
                        <div class="items_row">
                            <span class="items_title">
                                生年月日
                            </span>
                            <span class="items_text">
                                <c:out value="${member.m_birth}"/>
                            </span>
                        </div>
                        <div class="items_row">
                            <span class="items_title">
                                電話番号
                            </span>
                            <span class="items_text">
                                <c:out value="${member.m_tel}"/>
                            </span>
                        </div>
                    </div>
                    <div class="items">
                        <div class="items_row">
                            <span class="items_title">
                                郵便番号
                            </span>
                            <span class="items_text">
                                <c:out value="${member.m_zip_code}"/>
                            </span>
                        </div>
                        <div class="items_row">
                            <span class="items_title">
                                住所
                            </span>
                            <span class="items_text">
                                <c:out value="${member.m_address}"/>
                            </span>
                        </div>
                    </div>
                    <div class="subtitle">
                        <h2>問診票</h2>
                    </div>
                    <div class="items">
                        <div class="items_row">
                            <span class="items_title">
                                血液型
                            </span>
                            <span class="items_text">
                                <c:out value="${questionnaire.q_blood_type}"/>
                            </span>
                        </div>
                        <div class="items_row">
                            <span class="items_title">
                                今までにかかった病気
                            </span>
                            <span class="items_text">
                                <c:set var="medical_history" value="${fn:replace(questionnaire.q_medical_history,'&lt;br&gt;','<br>')}" />
                                <c:out escapeXml="false" value="${medical_history }"/>
                            </span>
                        </div>
                    </div>
                    <div class="items">
                        <div class="items_row">
                            <span class="items_title">
                                服用中のお薬
                            </span>
                            <span class="items_text">
                                <c:out escapeXml="false" value="${questionnaire.q_medication }"/>
                            </span>
                        </div>
                        <div class="items_row">
                            <span class="items_title">
                                アレルギー情報
                            </span>
                            <span class="items_text">
                                <c:set var="allergy" value="${fn:replace(questionnaire.q_allergy,'&lt;br&gt;','<br>')}" />
                                <c:out escapeXml="false" value="${allergy }"/>
                            </span>
                        </div>
                    </div>
                    <div class="items">
                    	<div class="items_row">
                            <span class="items_title">
                                飲酒
                            </span>
                            <span class="items_text">
                                <c:if test="${questionnaire.q_drink eq true }">
                                	<c:out value="あり" />
                                </c:if>
                                <c:if test="${questionnaire.q_drink eq false }">
                                	<c:out value="なし" />
                                </c:if>
                            </span>
                        </div>
                        <div class="items_row">
                            <span class="items_title">
                                喫煙
                            </span>
                            <span class="items_text">
	                            <c:if test="${questionnaire.q_smoke eq true }">
	                            	<c:out value="あり" />
	                            </c:if>
	                            <c:if test="${questionnaire.q_smoke eq false }">
	                            	<c:out value="なし" />
	                            </c:if>
                            </span>
                        </div>
                        <c:if test="${member.m_gender eq '女性' }">
	                        <div class="items_row">
	                            <span class="items_title">
	                                妊娠
	                            </span>
	                            <span class="items_text">
	                                <c:if test="${questionnaire.q_pregnancy eq true }">
		                            	<c:out value="あり" />
		                            </c:if>
		                            <c:if test="${questionnaire.q_pregnancy eq false }">
		                            	<c:out value="なし" />
		                            </c:if>
	                            </span>
	                        </div>
                        </c:if>
                    </div>
                    <div class="prescription_btn">
                    	<form action="PharmacyController?view=u11_01_pharmacy" method="post">
                    	<input type="hidden" name="selected" value="member">
	                        <button type="submit">電子処方箋登録</button>
                    	</form>
                    </div>
                </div>
                <div class="item_box">
                    <div class="subtitle">
                        <h2>電子処方箋履歴</h2>
                    </div>
                    <div class="table_items">
                        <table>
                            <thead>
                                <th id="0">日付</th>
                                <th id="1">病院名</th>
                                <th id="2">薬局名</th>
                                <th id="3">病名</th>
                                <th id="4">薬剤情報提供書</th>
                                <th id="5">電子処方箋</th>
                                <th id="6"></th>
                            </thead>
                            <tbody>
                                <td>2021/12/03</td>
                                <td>xx病院</td>
                                <td>COCO薬局</td>
                                <td>風邪</td>
                                <td>
                                    <!-- 登録されている場合詳細ボタン -->
                                    <form action="./u18_01.html" method="post">
                                        <button>薬剤情報詳細</button>
                                    </form>
                                    <form action="./u17_01.html" method="post">
                                        <button>薬剤情報提供書登録</button>
                                    </form>
                                </td>
                                <td>
                                    <form action="./u13_01_pharmacy.html" method="POST">
                                        <button>処方箋詳細</button>
                                    </form>
                                </td>
                            </tbody>
                        </table>
                    </div>
                </div>
            </section>
        </div>
    </main>
    <jsp:include page="../common/footer.jsp"/>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common.js"></script>
</html>