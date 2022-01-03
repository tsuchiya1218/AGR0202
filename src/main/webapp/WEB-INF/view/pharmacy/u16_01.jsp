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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/pharmacy/u16_01.css">
    <title>薬情報詳細画面</title>
</head>
<body id="body">
	<jsp:include page="../common/pharmacy/header.jsp"/>
    <main>
        <div class="main_box">
            <section class="item_con">
                <div class="img_box">
                    <div class="subtitle">
                        <h2>写真</h2>
                    </div>
                    <div class="items">
                        <div class="items_img">
                            <img src="${pageContext.request.contextPath}/static/img/medicine/${requestScope.drug.drug_img_name}" alt="薬写真">
                        </div>
                    </div>
                </div>
                <div class="item_box">
                    <div class="subtitle">
                        <h2>薬情報</h2>
                    </div>
                    <form action="PharmacyController?action=u16_s1_01" method="POST">
                    	<input type="hidden" name="drug_num" value="${requestScope.drug.drug_num }">
                        <div class="items">
                            <div class="items_text">
                                <span>薬名</span><span class="items_subText">${requestScope.drug.drug_guide }</span>
                                <p>${requestScope.drug.drug_name }</p>
                            </div>
                            <div class="items_text">
                                <span>種類</span>
                                <p>${requestScope.drug.drug_type }</p>
                            </div>
                            <div class="items_text">
                                <span>効果</span>
                                <c:set var="drug_effect" value="${fn:replace(requestScope.drug.drug_effect,'&lt;br&gt;','<br>')}" />
                                <p>${drug_effect }</p>
                            </div>
                            <div class="items_text">
                                <span>備考</span>
                                <c:set var="drug_note" value="${fn:replace(requestScope.drug.drug_note,'&lt;br&gt;','<br>')}" />
                                <p>${drug_note }</p>
                            </div>
                            <div class="items_text">
                                <span>値段</span>
                                <p><fmt:formatNumber value="${requestScope.drug.drug_price}" pattern="###,###,###"/>円</p>
                            </div>
                        </div>
                        <div class="items_btn">
                            <button type="button" onclick="history.back()">戻る</button>
                            <button type="submit" class="update_btn">薬情報変更</button>
                        </div>
                    </form>
                </div>
            </section>
        </div>
    </main>
   <jsp:include page="../common/footer.jsp"/>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/pharmacy/u16.js"></script>
</html>