<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="site-header">
    <div class="logo_box">
        <a class="logo_text" href="NonMemberController?view=index">COCO薬局</a>
    </div>
    <nav>
        <ul class="menu_bar">
        	<c:if test="${requestScope.nav eq null}">
	        		<li class="menu_li_active">
            			<a href="NonMemberController?view=index" class="menu_a_active">ホーム</a>
		            </li>
		            <li><a href="NonMemberController?view=u02">薬歴</a></li>
		            <li><a href="NonMemberController?view=u02">My QRコード</a></li>
		            <li><a href="NonMemberController?view=u02">子供情報</a></li>
		            <li><a href="NonMemberController?view=u02">マイページ</a></li>
		            <li><a href="#">お知らせ</a></li>
		            <li><a href="#">問い合わせ</a></li>
            </c:if>
            <c:if test="${requestScope.nav ne null}">
	        		<li><a href="NonMemberController?view=index">ホーム</a></li>
		            <li><a href="NonMemberController?view=u02">薬歴</a></li>
		            <li><a href="NonMemberController?view=u02">My QRコード</a></li>
		            <li><a href="NonMemberController?view=u02">子供情報</a></li>
		            <li><a href="NonMemberController?view=u02">マイページ</a></li>
		            <li><a href="#">お知らせ</a></li>
		            <li><a href="#">問い合わせ</a></li>
            </c:if>
        </ul>
    </nav>
    <div class="account_box">
        <input type="checkbox" id="account-btn-check">
        <label for="account-btn-check" id="account-btn-menu" class="account-btn"><span></span></label>
        <div class="account-content">
            <h2>ログインしてから利用できます。</h2>
            <ul>
                <li>
                    <a href="NonMemberController?view=u02">ログイン</a>
                </li>
                <li>
                    <a href="NonMemberController?view=u01_01">会員登録</a>
                </li>
           		<li><a href="#">お知らせ</a></li>
	            <li><a href="#">問い合わせ</a></li>
            </ul>
        </div>
        <c:choose>
        	<c:when test="${requestScope.nav eq 'u02' }">
	        	<a href="NonMemberController?view=u02" class="login_btn active">ログイン</a><br>
		        <a href="NonMemberController?view=u01_01" class="singup_btn">会員登録</a>
        	</c:when>
        	<c:when test="${requestScope.nav eq 'u01' }">
	        	<a href="NonMemberController?view=u02" class="login_btn">ログイン</a><br>
		        <a href="NonMemberController?view=u01_01" class="singup_btn active">会員登録</a>
        	</c:when>
        	<c:otherwise>
        		<a href="NonMemberController?view=u02" class="login_btn">ログイン</a><br>
		        <a href="NonMemberController?view=u01_01" class="singup_btn">会員登録</a>
        	</c:otherwise>
        </c:choose>
    </div>
</header>