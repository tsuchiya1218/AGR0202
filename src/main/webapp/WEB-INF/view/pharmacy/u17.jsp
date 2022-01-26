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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/pharmacy/u17_01.css">
    <title>薬剤情報提供書登録画面</title>
</head>
<body id="body">
	<jsp:include page="../common/pharmacy/header.jsp"/>
    <main>
        <div class="main_box" id="main_box">
            <form action="PharmacyController?action=u17_02" method="POST">
            <input type="hidden" name="ep_num" value="${ep.ep_num }">
            <section class="item_con">
                <div class="item_box">
                    <div class="subtitle">
                        <h2>薬剤情報提供書</h2>
                    </div>
                    <c:if test="${!empty member }">
	                    <div class="member_name">
	                        <span>${member.m_name }</span>
	                        <span>${member.m_kana }</span>
	                        <span>様</span>
	                    </div>
                    </c:if>
                    <c:if test="${!empty child }">
	                    <div class="member_name">
	                        <span>${child.c_name }</span>
	                        <span>${child.c_kana }</span>
	                        <span>様</span>
	                    </div>
                    </c:if>
                    <div class="items">
                        <span class="items_title">公費負担番号</span>
                        <span class="items_text">${ep.ep_burden_num }</span>
                    </div>
                    <div class="items">
                        <span class="items_title">公費負担医療の受験者</span>
                        <span class="items_text">${ep.ep_burden_person }</span>
                    </div>
                    <div class="items">
                        <span class="items_title">区分</span>
                        <span class="items_text">${ep.ep_patient_type }</span>
                    </div>
                    <div class="items">
                        <span class="items_title">交付年月日</span>
                        <span class="items_text">${ep.ep_reg_date }</span>
                    </div>
                </div>
                <div class="item_box">
                    <div class="subtitle">
                        <h2>処方</h2>
                    </div>
                    <div class="items">
                        <span class="items_title">病名</span>
                        <span class="items_text">${ep.ep_disease }</span>
                    </div>
                    <c:set var="drug" value="${drugList }"/>
                    <c:forEach var="pm" items="${pmList }" varStatus="st">
	                    <div class="items_medicine">
	                        <div class="items_img">
	                            <span class="items_title">写真</span>
	                            <img src="${pageContext.request.contextPath}/static/img/medicine/${drug[st.index].drug_img_name}" alt="${drug[st.index].drug_name }">
	                        </div>
	                        <div class="items">
	                            <span class="items_title">薬名</span>
	                            <span class="items_text">${drug[st.index].drug_name }</span>
	                        </div>
	                        <div class="items">
	                            <c:set var="drug_effect" value="${fn:replace(drug[st.index].drug_effect,'&lt;br&gt;','<br>')}"/>
	                            <span class="items_title">効果</span>
	                            <span class="items_text">${drug_effect }</span>
	                        </div>
	                        <div class="items">
	                            <span class="items_title">分量</span>
	                            <span class="items_text">${pm.pm_dosage }</span>
	                        </div>
	                        <div class="items">
	                            <span class="items_title">用量</span>
	                            <span class="items_text">${pm.pm_dose }</span>
	                        </div>
	                        <div class="items">
	                            <span class="items_title">用法</span>
	                            <span class="items_text">${pm.pm_usage }</span>
	                        </div>
	                        <div class="items">
	                            <span class="items_title">日数</span>
	                            <span class="items_text">${pm.pm_dose_day }</span>
	                        </div>
	                        <div class="items">
	                            <span class="items_title">総投与日数</span>
	                            <span class="items_text">${pm.pm_all_dose_day }</span>
	                        </div>
	                        <div class="items">
	                        	<c:set var="drug_note" value="${fn:replace(drug[st.index].drug_note,'&lt;br&gt;','<br>')}"/>
	                            <span class="items_title">注意事項</span>
	                            <span class="items_text">${drug_note }</span>
	                        </div>
	                    </div>
                    </c:forEach>
                    <div class="items">
                        <span class="items_title">保険医署名</span>
                        <span class="items_text">${doctor.d_name }</span>
                    </div>
                </div>
            </section>
            <div class="hospital_info">
                <div class="item_box">
                    <div class="subtitle">
                        <h2>病院情報</h2>
                    </div>
                    <div class="items">
                        <span class="items_title">病院名</span>
                        <span class="items_text">${hospital.h_name }</span>
                    </div>
                    <div class="items">
                        <span class="items_title">電話番号</span>
                        <span class="items_text">${hospital.h_tel }</span>
                    </div>
                    <div class="items">
                        <span class="items_title">住所</span>
                        <span class="items_text">${hospital.h_address }</span>
                    </div>
                    <div class="items">
                        <span class="items_title">医者名</span>
                        <span class="items_text">${doctor.d_name }</span>
                    </div>
                </div>
            </div>
            <div class="itembox">
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
                <button type="button" class="submit_btn" onclick="isSubmit(form);">薬剤情報提供書登録</button>
            </div>
        </form>
        </div>
    </main>
    <jsp:include page="../common/footer.jsp"/>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/pharmacy/u17.js"></script>
</html>