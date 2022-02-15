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
	                    <input type="hidden" name="m_num" value="${requestScope.member.m_num }">
	                    <c:set var="select_index" value="${requestScope.selected }"/>
	                    <c:set var="children" value="${requestScope.child }"/>
	                    <c:set var="index" value="${requestScope.selected-1 }"/>
                        <div class="member_name">
                            <span>${child[index].c_name }</span>
                            <span>${child[index].c_kana }</span>
                            <select name="selected">
                                <option value="0">本人</option>
                                <c:if test="${children ne null }">
                                	<c:forEach var="child" items="${children }" varStatus="st">
                                		<c:if test="${select_index eq st.count}">
                                			<option value="${st.count }" selected>${child.c_name }</option>
                                		</c:if>
                                		<c:if test="${select_index ne st.count}">
                                			<option value="${st.count }">${child.c_name }</option>
                                		</c:if>
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
                            	<c:set var="c_i_num" value="${fn:split(children[index].c_i_num,('-')) }"/>
                                保険証番号:<c:out value="${c_i_num[0] }"/> 枝番:<c:out value="${c_i_num[1] }"/>
                            </span>
                        </div>
                        <div class="items_row">
                            <span class="items_title">
                                保険証記号
                            </span>
                            <span class="items_text">
                                <c:out value="${children[index].c_i_mark }"/>
                            </span>
                        </div>
                    </div>
                    <div class="items">
                    	<div class="items_row">
                            <span class="items_title">
                                保険証 有効期限
                            </span>
                            <span class="items_text">
                                <c:out value="${children[index].c_i_expiry_date }"/>
                            </span>
                        </div>
                        <div class="items_row">
                            <span class="items_title">
                                子ども医療証番号
                            </span>
                            <span class="items_text">
                                <c:out value="${children[index].c_medical_num }"/>
                            </span>
                        </div>
                    </div>
                    <div class="items">
                    	<div class="items_row">
                            <span class="items_title">
                                性別
                            </span>
                            <span class="items_text">
                                <c:out value="${children[index].c_gender}"/>
                            </span>
                        </div>
                        <div class="items_row">
                            <span class="items_title">
                                生年月日
                            </span>
                            <span class="items_text">
                                <c:out value="${children[index].c_birth}"/> (${children[index].age }歳)
                            </span>
                        </div>
                        <div class="items_row" style="visibility: hidden;"></div>
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
                                <c:out value="${children[index].c_blood_type}"/>
                            </span>
                        </div>
                        <div class="items_row">
                            <span class="items_title">
                                今までにかかった病気
                            </span>
                            <span class="items_text">
                                <c:set var="medical_history" value="${fn:replace(children[index].c_medical_history,'&lt;br&gt;','<br>')}" />
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
                                <c:out escapeXml="false" value="${children[index].c_medication }"/>
                            </span>
                        </div>
                        <div class="items_row">
                            <span class="items_title">
                                アレルギー情報
                            </span>
                            <span class="items_text">
                                <c:set var="allergy" value="${fn:replace(children[index].c_allergy,'&lt;br&gt;','<br>')}" />
                                <c:out escapeXml="false" value="${allergy }"/>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="item_box">
                    <div class="subtitle">
                        <h2>電子処方箋履歴</h2>
                    </div>
                    <div class="table_items">
	                    <table>
                            <thead>
                            	<tr>
	                                <th>日付</th>
	                                <th>病名</th>
	                                <th>有効時間</th>
	                                <th>薬剤情報提供書</th>
	                                <th>電子処方箋</th>
	                                <th></th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach var="ep" items="${requestScope.epList }">
                            	<tr>
	                                <td>${ep.ep_reg_date }</td>
	                                <td>${ep.ep_disease }</td>
	                                <td>${ep.ep_expiry_date}</td>
	                                <td>
	                                	<c:if test="${ep.ep_di_num ne 0}">
		                                    <form action="PharmacyController?action=u18" method="post">
		                                    	<input type="hidden" name="ep_di_num" value="${ep.ep_di_num }">
		                                        <button>薬剤情報詳細</button>
		                                    </form>
	                                	</c:if>
	                                	<c:if test="${ep.ep_auth eq true && ep.ep_di_num eq 0 }">
		                                    <form action="PharmacyController?action=u17_01" method="post">
		                                    	<input type="hidden" name="ep_num" value="${ep.ep_num }">
		                                        <button>薬剤情報提供書登録</button>
		                                    </form>
	                                	</c:if>
	                                </td>
	                                <td>
	                                    <form action="PharmacyController?action=u13_01" method="POST">
	                                    	<input type="hidden" name="ep_num" value="${ep.ep_num }">
	                                        <button type="submit">処方箋詳細</button>
	                                    </form>
	                                </td>
                            	</tr>
                            	</c:forEach>
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