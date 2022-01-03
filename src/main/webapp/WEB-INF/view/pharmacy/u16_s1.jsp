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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/pharmacy/u16_s1.css">
    <title>薬情報変更画面</title>
</head>
<body id="body">
	<jsp:include page="../common/pharmacy/header.jsp"/>
    <main>
        <div class="main_box">
            <section class="item_con">
                <div class="item_box">
                    <div class="subtitle">
                        <h2>薬情報変更</h2>
                    </div>
                        <form action="PharmacyController?action=u16_s1_02" method="POST" enctype="multipart/form-data">
                        <input type="hidden" name="drug_num" value="${requestScope.drug.drug_num }">
                        <div class="img_box">
                            <span class="before_img_title">写真</span><br>
                            <span><img id="preview" src="${pageContext.request.contextPath}/static/img/medicine/${requestScope.drug.drug_img_name}" width="200" alt=""></span>
                            <hr>
                            <input type="file" id="img_file" name="drug_img">
                        </div>
                        <div class="items">
                            <label>
                                <span class="items_title">薬名</span>
                                <input type="text" name="drug_name" value="${requestScope.drug.drug_name }" placeholder="リリカOD" maxlength="50" autocomplete="off" oninput="maxLengthCheck(this)" required>
                            </label>
                        </div>
                        <div class="items">
                            <label>
                                <span class="items_title">種類</span>
                                <select name="drug_type">
                                    <c:choose>
                                    	<c:when test="${requestScope.drug.drug_type eq '錠剤' }">
                                    		<option value="錠剤" selected>錠剤</option>
		                                    <option value="カプセル剤" >カプセル剤</option>
		                                    <option value="散剤(粉薬)" >散剤(粉薬)</option>
		                                    <option value="液剤" >液剤</option>
		                                    <option value="顆粒剤" >顆粒剤</option>
		                                    <option value="坐剤・膣剤" >坐剤・膣剤</option>
		                                    <option value="貼り薬" >貼り薬</option>
		                                    <option value="塗り薬" >塗り薬</option>
		                                    <option value="点眼薬" >点眼薬</option>
		                                    <option value="吸入剤" >吸入剤</option>
		                                    <option value="噴霧剤 エアゾール剤" >噴霧剤 エアゾール剤</option>
		                                    <option value="注射剤" >注射剤</option>
		                                    <option value="その他" >その他</option>
                                    	</c:when>
                                    	<c:when test="${requestScope.drug.drug_type eq 'カプセル剤' }">
                                    		<option value="錠剤">錠剤</option>
		                                    <option value="カプセル剤" selected>カプセル剤</option>
		                                    <option value="散剤(粉薬)" >散剤(粉薬)</option>
		                                    <option value="液剤" >液剤</option>
		                                    <option value="顆粒剤" >顆粒剤</option>
		                                    <option value="坐剤・膣剤" >坐剤・膣剤</option>
		                                    <option value="貼り薬" >貼り薬</option>
		                                    <option value="塗り薬" >塗り薬</option>
		                                    <option value="点眼薬" >点眼薬</option>
		                                    <option value="吸入剤" >吸入剤</option>
		                                    <option value="噴霧剤 エアゾール剤" >噴霧剤 エアゾール剤</option>
		                                    <option value="注射剤" >注射剤</option>
		                                    <option value="その他" >その他</option>
                                    	</c:when>
                                    	<c:when test="${requestScope.drug.drug_type eq '散剤(粉薬)' }">
                                    		<option value="錠剤">錠剤</option>
		                                    <option value="カプセル剤" >カプセル剤</option>
		                                    <option value="散剤(粉薬)" selected>散剤(粉薬)</option>
		                                    <option value="液剤" >液剤</option>
		                                    <option value="顆粒剤" >顆粒剤</option>
		                                    <option value="坐剤・膣剤" >坐剤・膣剤</option>
		                                    <option value="貼り薬" >貼り薬</option>
		                                    <option value="塗り薬" >塗り薬</option>
		                                    <option value="点眼薬" >点眼薬</option>
		                                    <option value="吸入剤" >吸入剤</option>
		                                    <option value="噴霧剤 エアゾール剤" >噴霧剤 エアゾール剤</option>
		                                    <option value="注射剤" >注射剤</option>
		                                    <option value="その他" >その他</option>
                                    	</c:when>
                                    	<c:when test="${requestScope.drug.drug_type eq '液剤' }">
                                    		<option value="錠剤">錠剤</option>
		                                    <option value="カプセル剤" >カプセル剤</option>
		                                    <option value="散剤(粉薬)" >散剤(粉薬)</option>
		                                    <option value="液剤" selected>液剤</option>
		                                    <option value="顆粒剤" >顆粒剤</option>
		                                    <option value="坐剤・膣剤" >坐剤・膣剤</option>
		                                    <option value="貼り薬" >貼り薬</option>
		                                    <option value="塗り薬" >塗り薬</option>
		                                    <option value="点眼薬" >点眼薬</option>
		                                    <option value="吸入剤" >吸入剤</option>
		                                    <option value="噴霧剤 エアゾール剤" >噴霧剤 エアゾール剤</option>
		                                    <option value="注射剤" >注射剤</option>
		                                    <option value="その他" >その他</option>
                                    	</c:when>
                                    	<c:when test="${requestScope.drug.drug_type eq '顆粒剤' }">
                                    		<option value="錠剤">錠剤</option>
		                                    <option value="カプセル剤" >カプセル剤</option>
		                                    <option value="散剤(粉薬)" >散剤(粉薬)</option>
		                                    <option value="液剤" >液剤</option>
		                                    <option value="顆粒剤" selected>顆粒剤</option>
		                                    <option value="坐剤・膣剤" >坐剤・膣剤</option>
		                                    <option value="貼り薬" >貼り薬</option>
		                                    <option value="塗り薬" >塗り薬</option>
		                                    <option value="点眼薬" >点眼薬</option>
		                                    <option value="吸入剤" >吸入剤</option>
		                                    <option value="噴霧剤 エアゾール剤" >噴霧剤 エアゾール剤</option>
		                                    <option value="注射剤" >注射剤</option>
		                                    <option value="その他" >その他</option>
                                    	</c:when>
                                    	<c:when test="${requestScope.drug.drug_type eq '坐剤・膣剤' }">
                                    		<option value="錠剤">錠剤</option>
		                                    <option value="カプセル剤" >カプセル剤</option>
		                                    <option value="散剤(粉薬)" >散剤(粉薬)</option>
		                                    <option value="液剤" >液剤</option>
		                                    <option value="顆粒剤" >顆粒剤</option>
		                                    <option value="坐剤・膣剤" selected>坐剤・膣剤</option>
		                                    <option value="貼り薬" >貼り薬</option>
		                                    <option value="塗り薬" >塗り薬</option>
		                                    <option value="点眼薬" >点眼薬</option>
		                                    <option value="吸入剤" >吸入剤</option>
		                                    <option value="噴霧剤 エアゾール剤" >噴霧剤 エアゾール剤</option>
		                                    <option value="注射剤" >注射剤</option>
		                                    <option value="その他" >その他</option>
                                    	</c:when>
                                    	<c:when test="${requestScope.drug.drug_type eq '貼り薬' }">
                                    		<option value="錠剤">錠剤</option>
		                                    <option value="カプセル剤" >カプセル剤</option>
		                                    <option value="散剤(粉薬)" >散剤(粉薬)</option>
		                                    <option value="液剤" >液剤</option>
		                                    <option value="顆粒剤" >顆粒剤</option>
		                                    <option value="坐剤・膣剤" >坐剤・膣剤</option>
		                                    <option value="貼り薬" selected>貼り薬</option>
		                                    <option value="塗り薬" >塗り薬</option>
		                                    <option value="点眼薬" >点眼薬</option>
		                                    <option value="吸入剤" >吸入剤</option>
		                                    <option value="噴霧剤 エアゾール剤" >噴霧剤 エアゾール剤</option>
		                                    <option value="注射剤" >注射剤</option>
		                                    <option value="その他" >その他</option>
                                    	</c:when>
                                    	<c:when test="${requestScope.drug.drug_type eq '塗り薬' }">
                                    		<option value="錠剤">錠剤</option>
		                                    <option value="カプセル剤" >カプセル剤</option>
		                                    <option value="散剤(粉薬)" >散剤(粉薬)</option>
		                                    <option value="液剤" >液剤</option>
		                                    <option value="顆粒剤" >顆粒剤</option>
		                                    <option value="坐剤・膣剤" >坐剤・膣剤</option>
		                                    <option value="貼り薬" >貼り薬</option>
		                                    <option value="塗り薬" selected>塗り薬</option>
		                                    <option value="点眼薬" >点眼薬</option>
		                                    <option value="吸入剤" >吸入剤</option>
		                                    <option value="噴霧剤 エアゾール剤" >噴霧剤 エアゾール剤</option>
		                                    <option value="注射剤" >注射剤</option>
		                                    <option value="その他" >その他</option>
                                    	</c:when>
                                    	<c:when test="${requestScope.drug.drug_type eq '点眼薬' }">
                                    		<option value="錠剤">錠剤</option>
		                                    <option value="カプセル剤" >カプセル剤</option>
		                                    <option value="散剤(粉薬)" >散剤(粉薬)</option>
		                                    <option value="液剤" >液剤</option>
		                                    <option value="顆粒剤" >顆粒剤</option>
		                                    <option value="坐剤・膣剤" >坐剤・膣剤</option>
		                                    <option value="貼り薬" >貼り薬</option>
		                                    <option value="塗り薬" >塗り薬</option>
		                                    <option value="点眼薬" selected>点眼薬</option>
		                                    <option value="吸入剤" >吸入剤</option>
		                                    <option value="噴霧剤 エアゾール剤" >噴霧剤 エアゾール剤</option>
		                                    <option value="注射剤" >注射剤</option>
		                                    <option value="その他" >その他</option>
                                    	</c:when>
                                    	<c:when test="${requestScope.drug.drug_type eq '吸入剤' }">
	                                    	<option value="錠剤">錠剤</option>
		                                    <option value="カプセル剤" >カプセル剤</option>
		                                    <option value="散剤(粉薬)" >散剤(粉薬)</option>
		                                    <option value="液剤" >液剤</option>
		                                    <option value="顆粒剤" >顆粒剤</option>
		                                    <option value="坐剤・膣剤" >坐剤・膣剤</option>
		                                    <option value="貼り薬" >貼り薬</option>
		                                    <option value="塗り薬" >塗り薬</option>
		                                    <option value="点眼薬" >点眼薬</option>
		                                    <option value="吸入剤" selected>吸入剤</option>
		                                    <option value="噴霧剤 エアゾール剤" >噴霧剤 エアゾール剤</option>
		                                    <option value="注射剤" >注射剤</option>
		                                    <option value="その他" >その他</option>
                                    	</c:when>
                                    	<c:when test="${requestScope.drug.drug_type eq '噴霧剤 エアゾール剤' }">
	                                    	<option value="錠剤">錠剤</option>
		                                    <option value="カプセル剤" >カプセル剤</option>
		                                    <option value="散剤(粉薬)" >散剤(粉薬)</option>
		                                    <option value="液剤" >液剤</option>
		                                    <option value="顆粒剤" >顆粒剤</option>
		                                    <option value="坐剤・膣剤" >坐剤・膣剤</option>
		                                    <option value="貼り薬" >貼り薬</option>
		                                    <option value="塗り薬" >塗り薬</option>
		                                    <option value="点眼薬" >点眼薬</option>
		                                    <option value="吸入剤" >吸入剤</option>
		                                    <option value="噴霧剤 エアゾール剤" selected>噴霧剤 エアゾール剤</option>
		                                    <option value="注射剤" >注射剤</option>
		                                    <option value="その他" >その他</option>
                                    	</c:when>
                                    	<c:when test="${requestScope.drug.drug_type eq '注射剤' }">
                                    		<option value="錠剤">錠剤</option>
		                                    <option value="カプセル剤" >カプセル剤</option>
		                                    <option value="散剤(粉薬)" >散剤(粉薬)</option>
		                                    <option value="液剤" >液剤</option>
		                                    <option value="顆粒剤" >顆粒剤</option>
		                                    <option value="坐剤・膣剤" >坐剤・膣剤</option>
		                                    <option value="貼り薬" >貼り薬</option>
		                                    <option value="塗り薬" >塗り薬</option>
		                                    <option value="点眼薬" >点眼薬</option>
		                                    <option value="吸入剤" >吸入剤</option>
		                                    <option value="噴霧剤 エアゾール剤" >噴霧剤 エアゾール剤</option>
		                                    <option value="注射剤" selected>注射剤</option>
		                                    <option value="その他" >その他</option>
                                    	</c:when>
                                    	<c:otherwise>
                                    		<option value="錠剤">錠剤</option>
		                                    <option value="カプセル剤" >カプセル剤</option>
		                                    <option value="散剤(粉薬)" >散剤(粉薬)</option>
		                                    <option value="液剤" >液剤</option>
		                                    <option value="顆粒剤" >顆粒剤</option>
		                                    <option value="坐剤・膣剤" >坐剤・膣剤</option>
		                                    <option value="貼り薬" >貼り薬</option>
		                                    <option value="塗り薬" >塗り薬</option>
		                                    <option value="点眼薬" >点眼薬</option>
		                                    <option value="吸入剤" >吸入剤</option>
		                                    <option value="噴霧剤 エアゾール剤" >噴霧剤 エアゾール剤</option>
		                                    <option value="注射剤" >注射剤</option>
		                                    <option value="その他" selected>その他</option>
                                    	</c:otherwise>
                                    </c:choose>
                                </select>
                            </label>
                        </div>
                        <div class="items">
                            <label>
                                <span class="items_title">効果</span>
                                <textarea name="drug_effect" placeholder="血液の流れを良くする薬です。" maxlength="500">${requestScope.drug.drug_effect }</textarea>
                            </label>
                        </div>
                        <div class="items">
                        	<c:if test="${requestScope.drug.drug_guide eq '内服薬' }">
	                        	<label>
	                                内服薬
	                                <input type="radio" name="drug_guide" value="内服薬" checked>
	                            </label>
	                            <label style="margin:0 20px;">
	                                外用薬
	                                <input type="radio" name="drug_guide" value="外用薬">
	                            </label>
	                            <label>
	                                注射薬
	                                <input type="radio" name="drug_guide" value="注射薬">
	                            </label>
                        	</c:if>
                        	<c:if test="${requestScope.drug.drug_guide eq '外用薬' }">
                        		<label>
	                                内服薬
	                                <input type="radio" name="drug_guide" value="内服薬" >
	                            </label>
	                            <label style="margin:0 20px;">
	                                外用薬
	                                <input type="radio" name="drug_guide" value="外用薬" checked>
	                            </label>
	                            <label>
	                                注射薬
	                                <input type="radio" name="drug_guide" value="注射薬">
	                            </label>
                        	</c:if>
                        	<c:if test="${requestScope.drug.drug_guide eq '注射薬' }">
	                            <label>
	                                内服薬
	                                <input type="radio" name="drug_guide" value="内服薬">
	                            </label>
	                            <label style="margin:0 20px;">
	                                外用薬
	                                <input type="radio" name="drug_guide" value="外用薬">
	                            </label>
	                            <label>
	                                注射薬
	                                <input type="radio" name="drug_guide" value="注射薬" checked>
	                            </label>
                        	</c:if>
                        </div>
                        <div class="items">
                            <label>
                                <span class="items_title">備考</span>
                                <textarea name="drug_note" placeholder="この薬品にジェネリック医薬品は存在しません。" maxlength="500">${requestScope.drug.drug_note }</textarea>
                            </label>
                        </div>
                        <div class="items">
                            <label>
                                <span class="items_title">値段</span>
                                <input type="number" name="drug_price" placeholder="200" maxlength="10" oninput="maxLengthCheck(this)" required
                                value="${requestScope.drug.drug_price }">
                            </label>
                        </div>
                        <div class="items_btn">
                            <button type="button" onclick="history.back()">戻る</button>
                            <button type="button" class="submit_btn" onclick="checkForm(form)">確認</button>
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