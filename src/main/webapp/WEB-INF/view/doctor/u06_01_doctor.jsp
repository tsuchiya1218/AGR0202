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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/common.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/doctor/u06_01.css">
    <title>マイページ</title>
</head>
<body>
	<jsp:include page="../common/doctor/header.jsp"/>
    <main>
        <div class="contents">
            <h3 class="account_manage_title">個人情報</h3>
            <table class="account_manage_table" border="1">
                <tr>
                    <th><label>名前</label></th>
                    <td>${doctor.d_name }</td>
                </tr>
                <tr>
                    <th><label>名前(ふりがな)</label></th>
                    <td>${doctor.d_kana }</td>
                </tr>
                <tr>
                    <th><label>性別</label></th>
                    <td>${doctor.d_gender }</td>
                </tr>
                <tr>
                    <th><label>生年月日</label></th>
                    <td>${doctor.d_birth }</td>
                </tr>
                <tr>
                    <th><label>電話番号</label></th>
                    <td>${doctor.d_tel }</td>
                </tr>
                <tr>
                    <th><label>診療科</label></th>
                    <td>${doctor.d_department }</td>
                </tr>
                <tr>
                    <th><label>所属病院</label></th>
                    <td>${requestScope.h_name }</td>
                </tr>
            </table>
            <div class="account_manage_btn_box">
                <form action="DoctorController?action=u06_02" method="post">
                    <button class="member_update">個人情報変更</button>
                </form>
            </div>
            <div class="member_leave_btn_box">
                <p>
                    退会を希望する方は<a href="DoctorController?view=u05_01">退会</a>に
                    アクセスしてください。
                </p>
            </div>
        </div>
    </main>
    <jsp:include page="../common/footer.jsp"/>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common.js"></script>
</html>