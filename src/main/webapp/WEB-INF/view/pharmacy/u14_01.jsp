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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/pharmacy/u14_01.css">
    <title>薬情報登録確認画面</title>
</head>
<body id="body">
    <jsp:include page="../common/pharmacy/header.jsp"/>
    <main>
        <div class="main_box">
            <section class="item_con">
                <div class="item_box">
                    <div class="subtitle">
                        <h2>薬情報確認</h2>
                    </div>
                    <form action="PharmacyController?action=u14_01" method="POST">
                        <div class="items">
                            <span class="items_title">写真</span><br>
							<img id="preview" src="C:\Users\20jy0211\git\AGR0202\src\main\webapp\static\img${drugBean.drug_img_name }" 
							alt="${drugBean.drug_img_name }" width="200"><br>
                        </div>
                        <div class="items">
	                        <span class="items_title">薬名</span>
	                        <p class="items_text">${drugBean.drug_name }</p>
                        </div>
                        <div class="items">
                                <span class="items_title">種類</span>
                                <p class="items_text">${drugBean.drug_type }</p>
                        </div>
                        <div class="items">
                            	<c:set var="drug_effect" value="${fn:replace(drugBean.drug_effect,'&lt;br&gt;','<br>')}"/>
                                <span class="items_title">効果</span>
                                <p class="items_text">${drug_effect }</p>
                        </div>
                        <div class="items">
                        	<span class="items_title">服用方法</span>
                        	<p class="items_text">${drugBean.drug_guide }</p>
                        </div>
                        <div class="items">
                            	<c:set var="drug_note" value="${fn:replace(drugBean.drug_note,'&lt;br&gt;','<br>')}"/>
                                <span class="items_title">注意事項</span>
                                <p class="items_text">${drug_note }</p>
                        </div>
                        <div class="items">
                                <span class="items_title">値段</span>
                                <p class="items_text">${drugBean.drug_price }</p>
                        </div>
                        <div class="items_btn">
                            <button type="button" onclick="history.back()">戻る</button>
                            <button type="button" class="submit_btn" onclick="checkForm(form)">確定</button>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/pharmacy/u14.js"></script>
</html>