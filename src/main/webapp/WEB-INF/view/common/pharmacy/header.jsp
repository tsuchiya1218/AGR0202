<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="site-header">
    <div class="logo_box">
        <a class="logo_text" href="PharmacyController?view=index">COCO薬局</a>
    </div>
    <nav>
        <ul class="menu_bar">
        	<c:choose>
	        	<c:when test="${requestScope.nav eq 'u15' || requestScope.nav eq 'u16' || requestScope.nav eq 'u14'}">
	        		<li><a href="PharmacyController?view=index" >ホーム</a></li>
		            <li class="menu_li_active">
		            	<a href="PharmacyController?action=u15_01" class="menu_a_active">薬情報一覧</a>
		            </li>
		            <li><a href="PharmacyController?view=u10_01_pharmacy">会員検索</a></li>
		            <li><a href="#">お知らせ</a></li>
		            <li><a href="#">問い合わせ</a></li>
	        	</c:when>
	        	<c:when test="${requestScope.nav eq 'u10' || requestScope.nav eq 'u11' || requestScope.nav eq 'u13' ||
	        		requestScope.nav eq 'u17' || requestScope.nav eq 'u18' }">
	        		<li><a href="PharmacyController?view=index" >ホーム</a></li>
		            <li><a href="PharmacyController?action=u15_01">薬情報一覧</a></li>
		            <li class="menu_li_active">
		            	<a href="PharmacyController?view=u10_01_pharmacy" class="menu_a_active">会員検索</a>
		            </li>
		            <li><a href="#">お知らせ</a></li>
		            <li><a href="#">問い合わせ</a></li>
	        	</c:when>
	        	<c:otherwise>
	        		<li class="menu_li_active">
	        			<a href="PharmacyController?view=index" class="menu_a_active">ホーム</a>
	        		</li>
		            <li>
		            	<a href="PharmacyController?action=u15_01">薬情報一覧</a>
		            </li>
		            <li>
		            	<a href="PharmacyController?view=u10_01_pharmacy">会員検索</a>
		            </li>
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
            <h2>${pharmacy.p_name }&nbsp;様</h2>
            <ul>
                <li><a href="PharmacyController?view=index" >ホーム</a></li>
	            <li><a href="PharmacyController?action=u15_01">薬情報一覧</a></li>
	            <li><a href="PharmacyController?view=u10_01_pharmacy">会員検索</a></li>
	            <li><a href="#">お知らせ</a></li>
	            <li><a href="#">問い合わせ</a></li>
	            <li><a href="PharmacyController?action=u04">ログアウト</a></li>
            </ul>
        </div>
        <div class="member_box">
        	<span class="member_name">${pharmacy.p_name }様</span>
	        <a href="PharmacyController?action=u04" class="logout_btn">ログアウト</a><br>
        </div>
    </div>
</header>