<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="site-header">
    <div class="logo_box">
        <a class="logo_text" href="MemberController?view=index">COCO薬局</a>
    </div>
    <nav>
        <ul class="menu_bar">
        <c:choose>
	        	<c:when test="${requestScope.nav eq 'u08' }">
	        		<li><a href="MemberController?view=index">ホーム</a></li>
		            <li class="menu_li_active">
		            	<a href="MemberController?action=u08_01" class="menu_a_active">薬歴</a>
		            </li>
		            <li><a href="MemberController?view=u09">My QRコード</a></li>
		            <li><a href="MemberController?action=u07_01">子供情報</a></li>
		            <li><a href="MemberController?view=u06_01">マイページ</a></li>
		            <li><a href="#">お知らせ</a></li>
		            <li><a href="#">問い合わせ</a></li>
	        	</c:when>
	        	<c:when test="${requestScope.nav eq 'u09' }">
	        		<li><a href="MemberController?view=index">ホーム</a></li>
		            <li><a href="MemberController?action=u08_01">薬歴</a></li>
		            <li class="menu_li_active">
		            	<a href="MemberController?view=u09" class="menu_a_active">My QRコード</a>
		            </li>
		            <li><a href="MemberController?action=u07_01">子供情報</a></li>
		            <li><a href="MemberController?view=u06_01">マイページ</a></li>
		            <li><a href="#">お知らせ</a></li>
		            <li><a href="#">問い合わせ</a></li>
	        	</c:when>
	        	<c:when test="${requestScope.nav eq 'u07' }">
	        		<li><a href="MemberController?view=index">ホーム</a></li>
		            <li><a href="MemberController?action=u08_01">薬歴</a></li>
		            <li><a href="MemberController?view=u09">My QRコード</a></li>
		            <li class="menu_li_active">
		            	<a href="MemberController?action=u07_01" class="menu_a_active">子供情報</a>
		            </li>
		            <li><a href="MemberController?view=u06_01">マイページ</a></li>
		            <li><a href="#">お知らせ</a></li>
		            <li><a href="#">問い合わせ</a></li>
	        	</c:when>
	        	<c:when test="${requestScope.nav eq 'u06' }">
		            <li><a href="MemberController?view=index">ホーム</a></li>
		            <li><a href="MemberController?action=u08_01">薬歴</a></li>
		            <li><a href="MemberController?view=u09">My QRコード</a></li>
		            <li><a href="MemberController?action=u07_01">子供情報</a></li>
		            <li class="menu_li_active">
		            	<a href="MemberController?view=u06_01" class="menu_a_active">マイページ</a>
	            	</li>
	            	<li><a href="#">お知らせ</a></li>
		            <li><a href="#">問い合わせ</a></li>
	        	</c:when>
	        	<c:otherwise>
	        		<li class="menu_li_active">
            			<a href="MemberController?view=index" class="menu_a_active">ホーム</a>
		            </li>
		            <li><a href="MemberController?action=u08_01">薬歴</a></li>
		            <li><a href="MemberController?view=u09">My QRコード</a></li>
		            <li><a href="MemberController?action=u07_01">子供情報</a></li>
		            <li><a href="MemberController?view=u06_01">マイページ</a></li>
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
            <h2>${member.m_name }&nbsp;様</h2>
            <ul>
                <li><a href="MemberController?view=index">ホーム</a></li>
	            <li><a href="MemberController?action=u08_01">薬歴</a></li>
	            <li><a href="MemberController?view=u09">My QRコード</a></li>
	            <li><a href="MemberController?action=u07_01">子供情報</a></li>
	            <li><a href="MemberController?view=u06_01">マイページ</a></li>
                <li><a href="#">お知らせ</a></li>
	            <li><a href="#">問い合わせ</a></li>
                <li><a href="MemberController?action=u04">ログアウト</a></li>
            </ul>
        </div>
        <div class="member_box">
        	<span class="member_name">${member.m_name }様</span>
	        <a href="MemberController?action=u04" class="logout_btn">ログアウト</a>
        </div>
    </div>
    <div>
    </div>
</header>