<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="site-header">
    <div class="logo_box">
        <a class="logo_text" href="DoctorController?view=index">COCO薬局</a>
    </div>
    <nav>
        <ul class="menu_bar">
        	<c:choose>
	        	<c:when test="${requestScope.nav eq 'u10' || requestScope.nav eq 'u11' || requestScope.nav eq 'u13'}">
		            <li><a href="DoctorController?view=index" >ホーム</a></li>
		            <li class="menu_li_active"><a class="menu_a_active" href="DoctorController?view=u10_01_doctor">会員検索</a></li>
		            <li><a href="DoctorController?action=u06_01">マイページ</a></li>
		            <li><a href="#">お知らせ</a></li>
		            <li><a href="#">問い合わせ</a></li>
	        	</c:when>
	        	<c:when test="${requestScope.nav eq 'u06' || requestScope.nav eq 'u05' }">
		            <li><a href="DoctorController?view=index" >ホーム</a></li>
		            <li><a href="DoctorController?view=u10_01_doctor">会員検索</a></li>
		            <li class="menu_li_active"><a class="menu_a_active" href="DoctorController?action=u06_01">マイページ</a></li>
		            <li><a href="#">お知らせ</a></li>
		            <li><a href="#">問い合わせ</a></li>
	        	</c:when>
	        	<c:otherwise>
	        		<li class="menu_li_active"><a class="menu_a_active" href="DoctorController?view=index" >ホーム</a></li>
		            <li><a href="DoctorController?view=u10_01_doctor">会員検索</a></li>
		            <li><a href="DoctorController?action=u06_01">マイページ</a></li>
		            <li><a href="#">お知らせ</a></li>
		            <li><a href="#">問い合わせ</a></li>
	        	</c:otherwise>
        	</c:choose>
        </ul>
    </nav>
    <div class="account_box">
        <input type="checkbox" id="account-btn-check">
        <label for="account-btn-check" id="account-btn-menu" class="account-btn"><span></span></label>
        <div class="account-content">
            <h2>${doctor.d_name }&nbsp;様</h2>
            <ul>
                <li><a href="DoctorController?view=index" >ホーム</a></li>
	            <li><a href="DoctorController?view=u10_01_doctor">会員検索</a></li>
	            <li><a href="DoctorController?action=u06_01">マイページ</a></li>
                <li><a href="#">お知らせ</a></li>
	            <li><a href="#">問い合わせ</a></li>
                <li><a href="DoctorController?action=u04">ログアウト</a></li>
            </ul>
        </div>
        <div class="member_box">
        	<span class="member_name">${doctor.d_name }様</span>
	        <a href="DoctorController?action=u04" class="logout_btn">ログアウト</a><br>
        </div>
    </div>
</header>