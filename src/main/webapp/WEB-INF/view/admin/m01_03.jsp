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
    <title>医者登録確認画面</title>
</head>
<body id="body">
    <jsp:include page="../../view/common/admin/header.jsp"/>
    <main>
        <div class="main_box">
            <section class="item_con">
                <div class="item_box">
                    <div class="subtitle">
                        <h2>医者内容確認</h2>
                    </div>
                    <form action="AdminController?action=m01_02" method="post">
                        <div class="items">
                            <span class="items_title">メールアドレス</span>
                            <span class="items_text">${doctorBean.d_email }</span>
                        </div>
                        <div class="items">
                            <span class="items_title">パスワード</span>
                            <span class="items_text">${doctorBean.d_pw }</span>
                        </div>
                        <div class="items">
                            <span class="items_title">名前</span>
                            <span class="items_text">${doctorBean.d_name }</span>
                        </div>
                        <div class="items">
                            <span class="items_title">ふりがな</span>
                            <span class="items_text">${doctorBean.d_kana }</span>
                            </div>
                        <div class="items">
                            <span class="items_title">生年月日</span>
                            <span class="items_text">${doctorBean.d_birth }</span>
                        </div>
                        <div class="items">
                            <span class="items_title">電話番号</span>
                            <span class="items_text">${doctorBean.d_tel }</span>
                        </div>
                        <div class="items">
                            <span class="items_title">性別</span>
                            <span class="items_text">${doctorBean.d_gender }</span>
                        </div>
                        <div class="items">
                            <span class="items_title">診療科</span>
                            <span class="items_text">${doctorBean.d_department }</span>
                        </div>
                        <div class="items">
                            <span class="items_title">所属病院</span>
                            <span class="items_text">${hospital_name }</span>
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