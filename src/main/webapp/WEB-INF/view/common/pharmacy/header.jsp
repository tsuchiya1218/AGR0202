<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header class="site-header">
        <div class="logo_box">
            <a class="logo_text" href="PhamacyController?view=index">COCO薬局</a>
        </div>
        <nav>
            <ul class="menu_bar">
                <li><a href="PhamacyController?view=index" >ホーム</a></li>
                <li><a href="PhamacyController?view=u15_01">薬情報一覧</a></li>
                <li><a href="PhamacyController?view=u10_01_pharmacy">会員検索</a></li>
            </ul>
        </nav>
        <div class="account_box">
            <input type="checkbox" id="account-btn-check">
            <label for="account-btn-check" id="account-btn-menu" class="account-btn"><span></span></label>
            <div class="account-content">
                <h2>ログインしてから利用できます。</h2>
                <ul>
                    <li>
                        <a href="PhamacyController?view=u02_01">ログアウト</a>
                    </li>
                    <li><a href="PhamacyController?view=index" >ホーム</a></li>
	                <li><a href="PhamacyController?view=u15_01">薬情報一覧</a></li>
	                <li><a href="PhamacyController?view=u10_01_pharmacy">会員検索</a></li>
                </ul>
            </div>
            <a href="PhamacyController?view=u04" class="logout_btn">ログアウト</a><br>
        </div>
    </header>