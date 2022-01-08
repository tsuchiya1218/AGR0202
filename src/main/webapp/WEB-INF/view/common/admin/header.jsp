<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="site-header">
    <div class="logo_box">
        <a class="logo_text" href="AdminController?view=index">COCO薬局</a>
    </div>
    <nav>
        <ul class="menu_bar">
            <c:choose>
            	<c:when test="${requestScope.nav eq 'm04' }">
	        		<li><a href="AdminController?view=index">ホーム</a></li>
		            <li class="menu_li_active"><a href="AdminController?view=m04_01" class="menu_a_active">会員検索</a></li>
		            <li><a href="AdminController?view=m01_01">医者一覧</a></li>
		            <li><a href="AdminController?view=m02_01">薬局一覧</a></li>
		            <li><a href="AdminController?view=m03_01">病院一覧</a></li>
		            <li><a href="#">お知らせ</a></li>
		            <li><a href="#">問い合わせ</a></li>
	        	</c:when>
	        	<c:when test="${requestScope.nav eq 'm01'}">
	        		<li><a href="AdminController?view=index">ホーム</a></li>
		            <li><a href="AdminController?view=m04_01">会員検索</a></li>
		            <li class="menu_li_active"><a href="AdminController?view=m01_01" class="menu_a_active">医者一覧</a></li>
		            <li><a href="AdminController?view=m02_01">薬局一覧</a></li>
		            <li><a href="AdminController?view=m03_01">病院一覧</a></li>
		            <li><a href="#">お知らせ</a></li>
		            <li><a href="#">問い合わせ</a></li>
	        	</c:when>
	        	<c:when test="${requestScope.nav eq 'm02'}">
	        		<li><a href="AdminController?view=index">ホーム</a></li>
		            <li><a href="AdminController?view=m04_01">会員検索</a></li>
		            <li><a href="AdminController?view=m01_01">医者一覧</a></li>
		            <li class="menu_li_active"><a href="AdminController?view=m02_01" class="menu_a_active">薬局一覧</a></li>
		            <li><a href="AdminController?view=m03_01">病院一覧</a></li>
		            <li><a href="#">お知らせ</a></li>
		            <li><a href="#">問い合わせ</a></li>
	        	</c:when>
	        	<c:when test="${requestScope.nav eq 'm03'}">
	        		<li><a href="AdminController?view=index">ホーム</a></li>
		            <li><a href="AdminController?view=m04_01">会員検索</a></li>
		            <li><a href="AdminController?view=m01_01">医者一覧</a></li>
		            <li><a href="AdminController?view=m02_01">薬局一覧</a></li>
		            <li class="menu_li_active"><a href="AdminController?view=m03_01" class="menu_a_active">病院一覧</a></li>
		            <li><a href="#">お知らせ</a></li>
		            <li><a href="#">問い合わせ</a></li>
	        	</c:when>
	        	<c:otherwise>
	        		<li class="menu_li_active"><a href="AdminController?view=index" class="menu_a_active">ホーム</a></li>
		            <li><a href="AdminController?view=m04_01">会員検索</a></li>
		            <li><a href="AdminController?view=m01_01">医者一覧</a></li>
		            <li><a href="AdminController?view=m02_01">薬局一覧</a></li>
		            <li><a href="AdminController?view=m03_01">病院一覧</a></li>
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
            <h2>ログインしてから利用できます。</h2>
            <ul>
                <li><a href="AdminController?view=index.html" >ホーム</a></li>
	            <li><a href="AdminController?view=m4_01.jsp">会員検索</a></li>
	            <li><a href="AdminController?view=m1_01">医者一覧</a></li>
	            <li><a href="AdminController?view=m2_01">薬局一覧</a></li>
	            <li><a href="AdminController?view=m3_01">病院一覧</a></li>
	            <li><a href="#">お知らせ</a></li>
	            <li><a href="#">問い合わせ</a></li>
                <li><a href="AdminController?action=u04">ログアウト</a></li>
            </ul>
        </div>
        <div class="member_box">
        	<span class="member_name">管理者様</span>
	        <a href="AdminController?action=u04" class="logout_btn">ログアウト</a><br>
        </div>
    </div>
</header>