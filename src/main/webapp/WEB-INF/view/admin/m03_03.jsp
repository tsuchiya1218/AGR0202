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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/admin/m1_03.css">
    <title>病院登録確認画面</title>
</head>
<body id="body">
    <jsp:include page="../../view/common/admin/header.jsp"/>
    <main>
        <div class="main_box">
            <section class="item_con">
                <div class="item_box">
                    <div class="subtitle">
                        <h2>病院登録</h2>
                    </div>
                    <form action="AdminController?action=m03_02" method="post">
                        <div class="items">
                            <span class="items_title">メールアドレス</span>
                            <span class="items_text">${hospitalBean.h_email }</span>
                        </div>
                        <div class="items">
                            <span class="items_title">パスワード</span>
                            <span class="items_text">${hospitalBean.h_pw }</span>
                        </div>
                        <div class="items">
                            <span class="items_title">病院名</span>
                            <span class="items_text">${hospitalBean.h_name }</span>
                        </div>
                        <div class="items">
                            <span class="items_title">電話番号</span>
                            <span class="items_text">${hospitalBean.h_tel }</span>
                        </div>
                        <div class="items">
                            <span class="items_title">住所</span>
                            <span class="items_text">${hospitalBean.h_address }</span>
                        </div>
                        <div class="btn_box">
                            <button type="button" onclick="history.back();">戻る</button>
                            <button type="button" class="add_btn" onclick="isSubmit(this.form);" >確定</button>
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