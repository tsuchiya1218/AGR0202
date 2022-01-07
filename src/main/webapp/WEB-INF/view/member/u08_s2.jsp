<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/member/u08_s2.css">
    <title>薬歴詳細画面</title>
</head>
<body id="body">
	<jsp:include page="../common/member/header.jsp"/>
    <main>
        <div class="main_box" id="main_box">
            <section class="item_con">
                <div class="item_box">
                    <div class="subtitle">
                        <h2>お薬の説明</h2>
                    </div>
                    <c:if test="${!empty requestScope.member}">
	                    <div class="member_name">
	                        <span>${requestScope.member.m_name }</span>
	                        <span>${requestScope.member.m_kana }</span>
	                        <span>様</span>
	                    </div>
                    </c:if>
                    <c:if test="${!empty requestScope.child }">
	                    <div class="member_name">
	                        <span>${requestScope.child.c_name }</span>
	                        <span>${requestScope.child.c_kana }</span>
	                        <span>様</span>
	                    </div>
                    </c:if>
                    <div class="items">
                        <span class="items_title">調剤日</span>
                        <span class="items_text"><c:out value="${drug_information.di_reg_date }"/></span>
                    </div>
                    <div class="items">
                        <span class="items_title">処方日</span>
                        <span class="items_text"><c:out value="${electronic_prescription.ep_reg_date }"/></span>
                    </div>
                    <div class="items">
                        <span class="items_title">医療機関</span>
                        <span class="items_text"><c:out value="${hospital_name }"/></span>
                    </div>
                    <div class="items">
                        <span class="items_title">医師</span>
                        <span class="items_text"><c:out value="${doctor_name }"/></span>
                    </div>
                    <c:set var="pm" value="${Prescribe_medicine }" />
                    <c:forEach var="medicine" items="${drug }" varStatus="st">
	                    <div class="medicine_con">
	                    	<div class="medcineName">
	                           	<h3><c:out value="${medicine.drug_name }"/></h3>
	                       	</div>
		                   	<div class="medicine_box">
                    	 		<div class="medicine_img">
	                                <img src="${pageContext.request.contextPath}/static/img/medicine/${medicine.drug_img_name}" alt="${medicine.drug_name }">
                            	</div>
		                        <div class="goodsDetail">
		                            <div class="items">
		                            	<div class="items_title">
		                            		<span>服用方法</span>
		                            	</div>
		                            	<div class="items_info">
		                            		<span>
		                            			<c:out value="${pm[st.index].pm_dosage } ${pm[st.index].pm_dose } ${pm[st.index].pm_usage } ${pm[st.index].pm_dose_day }"/>
	                                        	日分
	                            			</span>
		                            	</div>
		                            	<div class="items_title">
	                                        <span>効果</span>
		                            	</div>
		                            	<div class="items_info">
		                            		<c:set var="drug_effect" value="${fn:replace(medicine.drug_effect,'&lt;br&gt;','<br>')}" />
		                            		<span><c:out escapeXml="false" value="${drug_effect }"/></span>
		                            	</div>
		                            	<div class="items_title">
	                                        <span>注意事項</span>
		                            	</div>
		                            	<div class="items_info">
		                            		<c:set var="drug_note" value="${fn:replace(medicine.drug_note,'&lt;br&gt;','<br>')}" />
		                            		<span><c:out escapeXml="false" value="${drug_note }"/></span>
		                            	</div>
		                            	<div class="items_title">
	                                        <span>値段(税込)</span>
		                            	</div>
		                            	<div class="items_info">
		                            		<span><fmt:formatNumber value="${medicine.drug_price}" pattern="###,###,###"/>円</span>
		                            	</div>
		                            </div>
		                        </div>
		                    </div>
	                    </div>
                    </c:forEach>
	            	<div style="text-align: right; margin: 20px 0;">
	                    <span>合計薬代:<fmt:formatNumber value="${requestScope.totalPrice}" pattern="###,###,###"/>円(税込)</span>
	            	</div>
            	</div>
            </section>
            <div class="item_box">
                <div class="subtitle">
                    <h2>薬局情報</h2>
                </div>
                <div class="items">
                    <span class="items_title">薬局名</span>
                    <span class="items_text">${pharmacy.p_name }</span>
                </div>
                <div class="items">
                    <span class="items_title">電話番号</span>
                    <span class="items_text">${pharmacy.p_tel }</span>
                </div>
                <div class="items">
                    <span class="items_title">住所</span>
                    <span class="items_text">${pharmacy.p_address }</span>
                </div>
           	</div>
            <div class="btn_box">
                <button type="button" onclick="history.back();">戻る</button>
            </div>
        </div>
    </main>
    <jsp:include page="../common/footer.jsp"/>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common.js"></script>
</html>