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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/doctor/u13_01_doctor.css">
    <title>電子処方箋詳細画面</title>
</head>
<body id="body">
    <jsp:include page="../common/doctor/header.jsp"/>
    <main>
        <div class="main_box">
            <form action="DoctorController?action=u13_s1_01" method="POST">
            <input type="hidden" name="ep_num" value="${requestScope.ep.ep_num }">
                <section class="item_con">
                    <div class="item_box">
                        <div class="subtitle">
                            <h2>電子処方箋詳細</h2>
                        </div>
                        <div class="items">
                            <input type="hidden" name="m_num" value="${member.m_num}">
                            <span class="items_title">患者名</span>
                            <c:if test="${empty requestScope.child}">
                            	<span class="items_text">${member.m_name}</span>
                            </c:if>
                            <c:if test="${!empty requestScope.child}">
                            	<input type="hidden" name="c_num" value="${child.c_num}">
                            	<span class="items_text">${child.c_name}</span>
                            </c:if>
                        </div>
                        <div class="items">
	                        <span class="items_title">ふりがな</span>
	                        <c:if test="${empty requestScope.child }">
	                        	<span class="items_text">${member.m_kana }</span>
	                        </c:if>
	                        <c:if test="${!empty requestScope.child }">
	                        	<span class="items_text">${child.c_kana }</span>
	                        </c:if>
                        </div>
                        <div class="items">
                        	<span class="items_title">生年月日</span>
	                        <c:if test="${empty requestScope.child }">
	                        	<span class="items_text">${member.m_birth } (${member.age }歳)</span>
	                        </c:if>
	                        <c:if test="${!empty requestScope.child }">
	                        	<span class="items_text">${child.c_birth } (${child.age }歳)</span>
	                        </c:if>
                        </div>
                        <div class="items_req">
	                        <span class="items_title">処方箋使用期間</span>
	                        <span class="items_text">${ep.ep_expiry_date }</span>
                        </div>
                        <div class="items">
                            <span class="items_title">公費負担番号</span>
                            <span class="items_text">${ep.ep_burden_num }</span>
                        </div>
                        <div class="items">
                            <span class="items_title">公費負担医療の受験者</span>
                            <span class="items_text">${ep.ep_burden_person }</span>
                        </div>
                    </div>
                    <div class="item_box">
                        <div class="items">
                            <span class="items_title">区分</span>
                            <span class="items_text">${ep.ep_patient_type }</span>
                        </div>
                        <div class="items">
                            <span class="items_title">交付年月日</span>
                            <span class="items_text">${ep.ep_reg_date }</span>
                        </div>
                        <div class="subtitle">
                            <h2>処方</h2>
                        </div>
                        <div class="items">
                            <span class="items_title">病名</span>
                            <span class="items_text">風邪</span>
                        </div>
                        <c:set var="pm" value="${requestScope.pmList }"/>
                        <c:forEach var="drug" items="${requestScope.drugList }" varStatus="st">
	                        <div class="medicine_box">
	                            <div class="items">
                                    <span class="items_title">薬名</span>
                                    <span class="items_text">${drug.drug_name }
	                                 	<a class="medicine_info_link" href="DoctorController?action=u11_s3&medicine_name=${drug.drug_name }" target="_blank">
	                                   		<button type="button">詳細</button>
	                                   	</a>
                                    </span>
	                            </div>
	                            <div class="items">
                                    <span class="items_title">分量</span>
                                    <span class="items_text">${pm[st.index].pm_dosage }</span>
	                            </div>
	                            <div class="items">
                                    <span class="items_title">用量</span>
                                    <span class="items_text">${pm[st.index].pm_dose }</span>
	                            </div>
	                            <div class="items">
                                    <span class="items_title">用法</span>
                                    <span class="items_text">${pm[st.index].pm_usage }</span>
	                            </div>
	                            <div class="items">
                                    <span class="items_title">日数</span>
                                    <span class="items_text">${pm[st.index].pm_dose_day }</span>
	                            </div>
	                            <div class="items">
                                    <span class="items_title">総投与日数</span>
                                    <span class="items_text">${pm[st.index].pm_all_dose_day }</span>
	                            </div>
	                        </div>
                        </c:forEach>
                        <div class="items">
                            <span class="items_title">備考</span>
                            <c:set var="ep_note" value="${fn:replace(ep.ep_note,'&lt;br&gt;','<br>')}" />
                            <span class="items_text">${ep_note }</span>
                        </div>
                        <div class="items">
	                        <span class="items_title">保険医署名</span>
	                        <span class="items_text">${requestScope.doctorBean.d_name }</span>
                        </div>
                    </div>
                    <div class="item_box">
                        <div class="items">
	                        <span class="items_title">病院名</span>
	                        <span class="items_text">${requestScope.hospital.h_name }</span>
                        </div>
                        <div class="items">
	                        <span class="items_title">医者名</span>
	                        <span class="items_text">${requestScope.doctorBean.d_name }</span>
                        </div>
                        <div class="items">
	                        <span class="items_title">病院 電話番号</span>
	                        <span class="items_text">${requestScope.hospital.h_tel }</span>
                        </div>
                        <div class="items">
	                        <span class="items_title">診察科</span>
	                        <span class="items_text">${requestScope.doctorBean.d_department }</span>
                        </div>
                    </div>
                    <div class="btn_box">
                        <button type="button" onclick="history.back();">戻る</button>
                        <c:if test="${doctor.d_num eq ep.ep_d_num }">
	                        <button type="submit" class="submit_btn">電子処方箋変更</button>
                        </c:if>
                    </div>
                </section>
            </form>
        </div>
    </main>
    <jsp:include page="../common/footer.jsp"/>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common.js"></script>
</html>