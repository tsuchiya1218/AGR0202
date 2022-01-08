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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/admin/m2_01.css">
    <c:if test="${requestScope.for_footer_css > 7 }"><style type="text/css">footer{ position: unset; }</style></c:if>
	<c:if test="${requestScope.for_footer_css <= 7 }"><style type="text/css">footer{ position: absolute; }</style></c:if>
    <title>薬局一覧画面</title>
</head>
<body id="body">
    <jsp:include page="../../view/common/admin/header.jsp"/>
    <main>
        <div class="main_box">
            <section class="item_con">
                <div class="item_box" style="padding: 20px;">
                    <div class="subtitle">
                        <h2>薬局検索</h2>
                    </div>
                    <form action="AdminController?action=m02_s3" method="POST">
                        <div class="search_items">
                            <select name="keyword_type">
                            	<option value="name" ${(keyword_type eq 'name') ? 'selected' : '' }>薬局名</option>
                                <option value="tel" ${(keyword_type eq 'tel') ? 'selected' : '' }>電話番号</option>
                                <option value="email" ${(keyword_type eq 'email') ? 'selected' : '' }>メールアドレス</option>
                            </select>
                            <input type="text" name="keyword" value="${(keyword ne '' && keyword ne null) ? keyword : ''}" autocomplete="off">
                            <button type="submit">検索</button>
                        </div>
                        <div class="btn_box">
                            <button type="button" onclick="location.href='AdminController?view=index'">トップ画面へ</button>
                            <button type="button" class="add_btn" onclick="location.href='AdminController?view=m02_02'" >薬局情報登録</button>
                        </div>
                    </form>
                    <hr>
                </div>
                <div class="item_box">
                	<c:if test="${empty pharmacyList && keyword ne '' && keyword ne null}">
	               		<div class="subtitle">
	                        <h2>検索結果</h2>
	                    </div>
	               		<span>検索結果がありません。</span>
                	</c:if>
                	<c:if test="${!empty pharmacyList }">
	                    <div class="subtitle">
	                        <h2>検索結果</h2>
	                    </div>
                    <table>
                        <thead>
                            <tr>
                                <th id="1">薬局名</th>
                                <th id="3">住所</th>
                                <th id="4">電話番号</th>
                                <th id="5"></th>
                                <th id="6"></th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="pharmacy" items="${pharmacyList }" varStatus="st">
                            <tr>
                                <td>${pharmacy.p_name }</td>
                                <td>${pharmacy.p_address}</td>
                                <td>${pharmacy.p_tel }</td>
                                <td>
                                    <form action="AdminController?action=m02_s2" method="POST">
                                    	<input type="hidden" name="p_num" value="${pharmacy.p_num }">
                                        <button class="delete_btn" type="button" onclick="isDelete(this.form)" >削除</button>
                                    </form>
                                </td>
                                <td class="btn_td">
                                    <form action="AdminController?action=m02_s1_01" method="post">
                                    	<input type="hidden" name="p_num" value="${pharmacy.p_num }">
                                        <button class="update_btn" type="submit">変更</button>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/admin/admin_common.js"></script>
</html>