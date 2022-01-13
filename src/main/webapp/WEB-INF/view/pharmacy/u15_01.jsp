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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/pharmacy/u15.css">
    <title>薬情報一覧画面</title>
</head>
<body id="body">
    <jsp:include page="../common/pharmacy/header.jsp"/>
    <main>
        <div class="main_box">
            <section class="item_con">
                <div class="item_box">
                    <div class="subtitle">
                        <h2>お薬検索</h2>
                    </div>
                    <form action="PharmacyController?action=u15_01" method="POST" id="form">
                        <div class="search_con">
                            <select name="keyword_type" class="search_keyword">
                                <option value="名前" ${(param.keyword_type eq "名前") ? 'selected' : ''}>名前</option>
                                <option value="効果" ${(param.keyword_type eq "効果") ? 'selected' : ''}>効果</option>
                            </select>
                            <select name="medicine_type" class="search_keyword_option">
                            	<option value="全部" ${(param.medicine_type eq "全部") ? 'selected' : ''}>全部</option>
                                <option value="錠剤" ${(param.medicine_type eq "錠剤") ? 'selected' : ''} >錠剤</option>
                                <option value="カプセル剤" ${(param.medicine_type eq "カプセル剤") ? 'selected' : ''}>カプセル剤</option>
                                <option value="散剤(粉薬)" ${(param.medicine_type eq "散剤(粉薬)") ? 'selected' : ''}>散剤(粉薬)</option>
                                <option value="液剤" ${(param.medicine_type eq "液剤") ? 'selected' : ''}>液剤</option>
                                <option value="顆粒剤" ${(param.medicine_type eq "顆粒剤") ? 'selected' : ''}>顆粒剤</option>
                                <option value="坐剤・膣剤" ${(param.medicine_type eq "坐剤・膣剤") ? 'selected' : ''}>坐剤・膣剤</option>
                                <option value="貼り薬" ${(param.medicine_type eq "貼り薬") ? 'selected' : ''}>貼り薬</option>
                                <option value="塗り薬" ${(param.medicine_type eq "塗り薬") ? 'selected' : ''}>塗り薬</option>
                                <option value="点眼薬" ${(param.medicine_type eq "点眼薬") ? 'selected' : ''} >点眼薬</option>
                                <option value="吸入剤" ${(param.medicine_type eq "吸入剤") ? 'selected' : ''} >吸入剤</option>
                                <option value="噴霧剤 エアゾール剤" ${(param.medicine_type eq "噴霧剤 エアゾール剤") ? 'selected' : ''} >噴霧剤 エアゾール剤</option>
                                <option value="注射剤" ${(param.medicine_type eq "注射剤") ? 'selected' : ''} >注射剤</option>
                                <option value="その他" ${(param.medicine_type eq "その他") ? 'selected' : ''} >その他</option>
                            </select>
                            <input type="text" name="keyword" value="${param.keyword }" autocomplete="off">
                            <button class="search_btn">検索</button><br>
                            <p>
                                名前・効果・種類を選択し検索<br>
                                又は種類のままに検索すると関係なく全て検索します。
                            </p>
                        </div>
                    </form>
                </div>
                <div class="item_box">
                    <div class="subtitle">
                        <h2>薬情報登録</h2>
                    </div>
                    <button class="medicine_btn" onclick="location.href='PharmacyController?view=u14'">薬情報登録</button>
                </div>
            </section>
            <div class="item_box">
                <div class="subtitle">
                    <h2>薬情報一覧</h2>
                </div>
               	<div class="item_text">
	                <c:if test="${fn:length(drugList) eq 0}">
	           			検索結果がありません。
	           		</c:if>
	           		<c:if test="${fn:length(drugList) ne 0}">
	           			<c:if test="${!empty search_result}">
		           			<c:out value="検索結果数 : ${search_result }" />
	           			</c:if>
	           		</c:if>
                </div>
                <table>
                    <thead>
                        <tr>
                            <th id="1">薬名</th>
                            <th id="2">種類</th>
                            <th id="3">服用方法</th>
                            <th id="4" style="width:100px;">値段(円)</th>
                            <th id="5"></th>
                        </tr>
                    </thead>
                    <tbody>
                    	<c:if test="${fn:length(drugList) > 0}">
	                    	<c:forEach var="drug" items="${drugList }" varStatus="st">
		                        <tr>
		                            <td>${drug.drug_name }</td>
		                            <td>${drug.drug_type }</td>
		                            <td>${drug.drug_guide }</td>
		                            <td><fmt:formatNumber value="${drug.drug_price}" pattern="###,###,###"/></td>
		                            <td class="btn_in_table">
		                                <form action="PharmacyController?action=u15_s1" method="POST">
		                                	<input type="hidden" name="drug_num" value="${drug.drug_num }">
		                                    <button class="delete_btn" type="button" onclick="isDelete(form);">削除</button>
		                                </form>
		                                <form action="PharmacyController?action=u16_01" method="POST">
		                                	<input type="hidden" name="drug_num" value="${drug.drug_num }">
			                                <button class="detail_btn" type="submit">詳細</button>
		                                </form>
		                            </td>
		                        </tr>
	                    	</c:forEach>
                    	</c:if>
                    </tbody>
                </table>
                <c:if test="${fn:length(drugList) ne 0}">
	                <div class="page_con">
		                <c:set var="page" value="${(empty param.p) ? 1:param.p}"/>
		                <c:set var="startNum" value="${page-(page-1)%5 }"/>
		                <ul>
		                	<c:if test="${page >= 6}">
		                        <li><a href="PharmacyController?action=u15_01&p=${startNum-5}">&lt;</a></li>
	                        </c:if>
									<c:forEach var="i" begin="0" end="4">
	                        		<c:if test="${(startNum+i) <= endPage}">
										<li>
											<c:if test="${search_result ne 0}">
												<a class="${(page==(startNum+i))?'page_active':'' }" 
													href="PharmacyController?action=u15_01&p=${startNum+i}&
													keyword_type=${param.keyword_type}&
													medicine_type=${param.medicine_type}&
													keyword=${param.keyword}">${startNum+i}</a>
											</c:if>
											<c:if test="${search_result eq 0}">
												<a class="${(page==(startNum+i))?'page_active':'' }" 
													href="PharmacyController?action=u15_01&p=${startNum+i}">${startNum+i}</a>
											</c:if>
										</li>
									</c:if>
									</c:forEach>
		                    <c:if test="${startNum+5 < endPage }">
		                        <li><a href="PharmacyController?action=u15_01&p=${startNum+5}">&gt;</a></li>
		                    </c:if>
	                    </ul>
	                </div>
                </c:if>
            </div>
        </div>
    </main>
    <jsp:include page="../common/footer.jsp"/>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/pharmacy/u15.js"></script>
</html>