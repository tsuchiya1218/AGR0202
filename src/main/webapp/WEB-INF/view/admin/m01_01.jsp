<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/admin/m1_01.css">
    <c:if test="${requestScope.for_footer_css > 7 }"><style type="text/css">footer{ position: unset; }</style></c:if>
	<c:if test="${requestScope.for_footer_css <= 7 }"><style type="text/css">footer{ position: absolute; }</style></c:if>
    <title>医者一覧画面</title>
</head>
<body id="body">
	<jsp:include page="../../view/common/admin/header.jsp"/>
    <main>
        <div class="main_box">
            <section class="item_con">
                <div class="item_box">
                    <div class="subtitle">
                        <h2>医者検索</h2>
                    </div>
                    <form action="AdminController?action=m01_s2" method="POST">
                        <div class="search_items">
                            <select name="keyword_type">
                            	<option value="name" ${(keyword_type eq 'name') ? 'selected' : '' }>名前</option>
                                <option value="kana" ${(keyword_type eq 'kana') ? 'selected' : '' }>ひらがな</option>
                                <option value="email" ${(keyword_type eq 'email') ? 'selected' : '' }>メールアドレス</option>
                            </select>
                            <input type="text" name="keyword" value="${(keyword ne '' && keyword ne null) ? keyword : ''}" autocomplete="off">
                            <button type="submit">検索</button>
                        </div>
	                    <p class="info_text">ひらがな・名前で検索する場合、苗字と名前の間にスペースを入れてください。例） ○○○ ○○○</p><br>
                        <div class="btn_box">
                            <button type="button" onclick="location.href='AdminController?view=index'">トップ画面へ</button>
                            <button type="button" class="add_btn" onclick="location.href='AdminController?view=m01_02'" >医者情報登録</button>
                        </div>
                    </form>
                    <hr>
                </div>
                <div class="item_box">
                	<c:if test="${empty doctorList && keyword ne '' && keyword ne null}">
	               		<div class="subtitle">
	                        <h2>検索結果</h2>
	                    </div>
	               		<span>検索結果がありません。</span>
                	</c:if>
                	<c:if test="${!empty doctorList }">
                    <div class="subtitle">
                        <h2>検索結果</h2>
                    </div>
                    <table>
                        <thead>
                            <tr>
                                <th id="1">名前</th>
                                <th id="2">ひらがな</th>
                                <th id="3">生年月日</th>
                                <th id="4">電話番号</th>
                                <th id="5">性別</th>
                                <th id="6">診療科</th>
                                <th id="7">所属病院</th>
                                <th id="8"></th>
                            </tr>
                        </thead>
                        <tbody>
                        	<c:set var="h_name"  value="${h_name_list }"/>
                        	<c:forEach var="doctor" items="${doctorList }" varStatus="st">
                            <tr>
	                            <td><c:out value="${doctor.d_name }"/></td>
	                            <td><c:out value="${doctor.d_kana }"/></td>
	                            <td><c:out value="${doctor.d_birth }"/></td>
	                            <td><c:out value="${doctor.d_tel }"/></td>
	                            <td><c:out value="${doctor.d_gender }"/></td>
	                            <td><c:out value="${doctor.d_department }"/></td>
	                            <td><c:out value="${h_name[st.index] }"/></td>
	                            <td>
		                            <form action="AdminController?action=m01_s1_01" method="post">
		                            	<input type="hidden" name="d_num" value="${doctor.d_num }">
		                            	<button type="submit">変更</button>
	                        		</form>
                            	</td>
                            </tr>
                        	</c:forEach>
                        </tbody>
                    </table>
	                </c:if>
                </div>
            </section>
        </div>
    </main>
    <jsp:include page="../../view/common/footer.jsp"/>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common.js"></script>
</html>