<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header class="site-header">
    <div class="logo_box">
        <a class="logo_text" href="HospitalController?view=index">COCO薬局</a>
    </div>
    <nav>
        <ul class="menu_bar">
            <li class="menu_li_active">
            	<a href="HospitalController?view=m4_01.jsp" class="menu_a_active">承認リスト</a>
           	</li>
        </ul>
    </nav>
    <div class="account_box">
        <input type="checkbox" id="account-btn-check">
        <label for="account-btn-check" id="account-btn-menu" class="account-btn"><span></span></label>
        <div class="account-content">
            <h2>${sessionScope.hospital.name }様</h2>
            <ul>
	            <li><a href="HospitalController?view=m4_01.jsp">承認リスト</a></li>
                <li><a href="HospitalController?view=u02_01.jsp">ログアウト</a></li>
            </ul>
        </div>
        <a href="HospitalController?view=u04.jsp" class="logout_btn">ログアウト</a><br>
    </div>
</header>