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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/member/u08.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/common.css">
    <title>薬歴一覧画面</title>
</head>
<body>
	<jsp:include page="../common/member/header.jsp"/>
    <main>
        <div class="main_box">
            <h2>薬歴一覧</h2>
            <div class="searchTag">
            	<form action="MemberController?action=u08_01" method="post">
	                <input type="date" id="startDate" value="${(startDate != null) ? startDate : '' }" name="startDate" onchange="limitEndDate();" >から
	                <input type="date" id="endDate" value="${(endDate != null) ? endDate : '' }" name="endDate" onchange="limitEndDate();">まで
            		
            		<select name="selected" style="text-align: center;">
	                    <option value="0">本人</option>
	                    <c:set var="children" value="${requestScope.children }"/>
	                    <c:if test="${!empty children}">
	                    	<c:forEach var="child" items="${children }" varStatus="index">
	                    		<option value="${child.c_num}" ${(child.c_num eq selected) ? 'selected' : ''}>${child.c_name }</option>
	                    	</c:forEach>
	                    </c:if>
                   	</select>
	                <button type="submit">検索</button>
            	</form>
            </div>
            <c:if test="${startDate ne null  && endDate ne null && startDate ne '' && endDate ne ''}">
	            <p><strong>${startDate }</strong>から<strong>${endDate }</strong>までの検索結果</p>
            </c:if>
            <c:if test="${startDate ne null && startDate ne ''  && (endDate eq null || endDate eq '')}">
	            <p><strong>${startDate }</strong>からの検索結果</p>
            </c:if>
            <c:if test="${(startDate eq null || startDate eq '') && endDate ne null && endDate ne ''}">
	            <p><strong>${endDate }</strong>までの検索結果</p>
            </c:if>
            <div class="medcineTable">
            <c:if test="${requestScope.noHaveDiNumSize ne 0}">
            	<br><strong style="color:red;">まだ受け取っていない薬剤情報提供書が ${requestScope.noHaveDiNumSize } 件あります。</strong>
           	</c:if>
                <table>
                    <tr>
                        <th></th>
                        <th>日付</th>
                        <th>病院名</th>
                        <th>薬局名</th>
                        <th>合計薬代(税込)</th>
                        <th></th>
                    </tr>
                   	<c:set var="h_name" value="${requestScope.hospital_nameList }" />
                   	<c:set var="p_name" value="${requestScope.pharmacy_nameList }" />
                   	<c:set var="total_price" value="${requestScope.totalPriceList }" />
                   	<c:forEach var="di" items="${requestScope.diList }" varStatus="st">
                    <tr>
                        <td>${st.count }</td>
                        <td>${di.di_reg_date }</td>
                        <td>${h_name[st.index] }</td>
                        <td>${p_name[st.index] }</td>
                        <td><fmt:formatNumber value="${total_price[st.index]}" pattern="###,###,###"/>円</td>
                        <td>
                        	<form action="MemberController?action=u08_s2" method="POST">
                        		<input type="hidden" name="di_num" value="${di.di_num }">
	                        	<input type="submit" value="詳細">
                        	</form>
                        </td>
                    </tr>
                   	</c:forEach>
                </table>
            </div> 
        </div>
    </main>
    <jsp:include page="../common/footer.jsp"/>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/member/u08.js"></script>
</html>