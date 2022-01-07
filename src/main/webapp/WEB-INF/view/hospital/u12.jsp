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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/hospital/u12.css">
    <title>承認リスト画面</title>
</head>
<body id="body">
	<jsp:include page="../common/hospital/header.jsp"/>
    <main>
        <div class="main_box">
            <section class="item_con">
                <div class="item_box">
                    <div class="subtitle">
                        <h2>承認リスト</h2>
                    </div>
                    <div class="table_items">
                        <table>
                            <thead>
                            	<tr>
                            		<th>番号</th>
	                                <th>日付</th>
	                                <th>患者名</th>
	                                <th>ふりがな</th>
	                                <th>生年月日</th>
	                                <th>性別</th>
	                                <th></th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:set var="member" value="${requestScope.memberList }" />
                            	<c:forEach var="ep" items="${requestScope.epList }" varStatus="st">
	                            <tr>
	                            	<td>${st.count }</td>
	                                <td>${ep.ep_reg_date }</td>
	                                <td>${member[st.index].m_name }</td>
	                                <td>${member[st.index].m_kana }</td>
	                                <td>${member[st.index].m_birth }</td>
	                                <td>${member[st.index].m_gender }</td>
	                                <td>
	                                	<c:if test="${ep.ep_auth ne true }">
		                                    <form action="HospitalController?action=u12_02" method="POST">
		                                    	<input type="hidden" name="ep_num" value="${ep.ep_num }">
		                                        <button type="button" onclick="isAvailable(this.form);">有効</button>
		                                    </form>
	                                	</c:if>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/hospital/u12.js"></script>

</html>