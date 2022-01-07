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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/doctor/u11_s3.css">
    <title>${drug.drug_name }の詳細</title>
</head>
<body>
	<div class="main_box" id="main_box">
	    <section class="item_con">
	        <div class="item_box">
	            <div class="subtitle">
	                <h2>${drug.drug_name}の情報</h2>
	            </div>
	            <div class="items">
               		<span class="items_title">写真</span>
               		<span class="items_text">
              			<c:if test="${drug.drug_img_name eq '' || drug.drug_img_name eq null }">
                        	<span>写真が登録されていません。</span>
                        </c:if>
                        <c:if test="${drug.drug_img_name ne '' && drug.drug_img_name ne null }">
                        	<img src="${pageContext.request.contextPath}/static/img/medicine/${requestScope.drug.drug_img_name}" alt="${drug.drug_name }">
                        </c:if>
               		</span>
                </div>
	            <div class="items">
	                <label>
	                    <span class="items_title">薬名</span>
	                    <span class="items_text">${drug.drug_name}</span>
	                </label>
	            </div>
	            <div class="items">
	                <label>
	                    <span class="items_title">種類</span>
	                    <span class="items_text">${drug.drug_type}</span>
	                </label>
	            </div>
	            <div class="items">
	                <label>
	                    <span class="items_title">効果</span>
	                    <c:set var="drug_effect" value="${fn:replace(drug.drug_effect,'&lt;br&gt;','<br>')}" />
	                    <span class="items_text">${drug_effect}</span>
	                </label>
	            </div>
	            <div class="items">
	                <label>
	                    <span class="items_title">服用方法</span>
	                    <span class="items_text">${drug.drug_guide}</span>
	                </label>
	            </div>
	            <div class="items">
	                <label>
	                    <span class="items_title">備考</span>
	                    <c:set var="drug_note" value="${fn:replace(drug.drug_note,'&lt;br&gt;','<br>')}" />
	                    <span class="items_text">${drug_note}</span>
	                </label>
	            </div>
	      	</div>
	    </section>
	</div>
</body>
</html>