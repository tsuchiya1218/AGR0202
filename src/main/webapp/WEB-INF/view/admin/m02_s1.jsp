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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/admin/m1_s1.css">
    <title>薬局情報変更画面</title>
</head>
<body id="body">
	<jsp:include page="../../view/common/admin/header.jsp"/>
    <main>
        <div class="main_box">
            <section class="item_con">
                <div class="item_box">
                    <div class="subtitle">
                        <h2>薬局情報変更</h2>
                    </div>
                    <form action="AdminController?action=m02_s1_02" method="post">
                    <input type="hidden" name="p_num" value="${pharmacy.p_num }">
                        <div class="items">
                            <label>
                                <span class="items_title">メールアドレス</span>
                                <input type="email" name="email" autocomplete="off">
                            </label>
                        </div>
                        <div class="items">
                            <label>
                                <span class="items_title">パスワード</span>
                                <input type="text" name="password">
                            </label>
                        </div>
                        <div class="items">
                            <label>
                                <span class="items_title">薬局名</span>
                                <input type="text" name="name" value="${pharmacy.p_name }" autocomplete="off">
                            </label>
                        </div>
                        <div class="items">
                            <label class="tel">
                            	<c:set var="tel" value="${fn:split(pharmacy.p_tel,'-') }" />
                                <span class="items_title">電話番号</span>
                                <input type="number" name="tel" value="${tel[0] }" min="0" maxlength="4" oninput="maxLengthCheck(this)">-
                                <input type="number" name="tel" value="${tel[1] }" min="0" maxlength="4" oninput="maxLengthCheck(this)">-
                                <input type="number" name="tel" value="${tel[2] }" min="0" maxlength="4" oninput="maxLengthCheck(this)">
                            </label>
                        </div>
                        <div class="items">
                            <label>
                                <span class="items_title">住所</span>
                                <input type="text" name="address" value="${pharmacy.p_address }">
                            </label>
                        </div>
                        <div class="btn_box">
                            <button type="button" onclick="history.back()">戻る</button>
                            <button type="button" class="add_btn" onclick="isUpdate(this.form);" >確定</button>
                        </div>
                    </form>
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