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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/admin/m4_01.css">
    <c:if test="${requestScope.for_footer_css > 7 }"><style type="text/css">footer{ position: unset; }</style></c:if>
	<c:if test="${requestScope.for_footer_css <= 7 }"><style type="text/css">footer{ position: absolute; }</style></c:if>
    <title>会員検索画面</title>
</head>
<body id="body">
    <jsp:include page="../../view/common/admin/header.jsp"/>
    <main>
        <div class="main_box">
            <section class="item_con">
                <div class="item_box">
                    <div class="subtitle">
                        <h2>会員検索</h2>
                    </div>
                    <form action="AdminController?action=m04_01" method="POST">
                        <div class="search_items">
                            <select name="keyword_type">
                            	<option value="name" ${(keyword_type eq 'name') ? 'selected' : '' }>名前</option>
                                <option value="kana" ${(keyword_type eq 'tel') ? 'selected' : '' }>ふりがな</option>
                                <option value="email" ${(keyword_type eq 'email') ? 'selected' : '' }>メールアドレス</option>
                            </select>
                            <input type="text" name="keyword" value="${(keyword ne '' && keyword ne null) ? keyword : ''}" autocomplete="off">
                            <button type="submit">検索</button>
                        </div>
                        <p class="info_text">ひらがな・名前で検索する場合、苗字と名前の間にスペースを入れてください。例） ○○○ ○○○</p><br>
                        <div class="btn_box">
                            <button type="button" onclick="location.href='AdminController?view=index'">トップ画面へ</button>
                        </div>
                    </form>
                </div>
                <div class="item_box" style="text-align: center;">
                    <c:if test="${empty memberList && keyword ne '' && keyword ne null}">
	               		<div class="subtitle">
	                        <h2>検索結果</h2>
	                    </div>
	               		<span>検索結果がありません。</span>
                	</c:if>
                	<c:if test="${!empty memberList }">
	                    <div class="subtitle">
	                        <h2>検索結果</h2>
	                    </div>
	                    <c:forEach var="member" items="${memberList }" varStatus="st">
		                    <div class="items">
		                        <span class="items_title">名前</span>
		                        <span class="items_text">${member.m_name }</span>
		                    </div>
		                    <div class="items">
		                        <span class="items_title">ひらがな</span>
		                        <span class="items_text">${member.m_kana }</span>
		                    </div>
		                    <div class="items">
		                        <span class="items_title">生年月日</span>
		                        <span class="items_text">${member.m_birth }</span>
		                    </div>
		                    <div class="items">
		                        <span class="items_title">性別</span>
		                        <span class="items_text">${member.m_gender }</span>
		                    </div>
		                    <div class="items">
		                        <span class="items_title">電話番号</span>
		                        <span class="items_text">${member.m_tel }</span>
		                    </div>
		                    <div class="items">
		                        <span class="items_title">住所</span>
		                        <span class="items_text">${member.m_address }</span>
		                    </div>
	                	</c:forEach>
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