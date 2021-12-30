<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/common.css">
    <link rel="stylesheet" href="../css/pharmacy/u10_01_pharmacy.css">
    <title>会員検索画面</title>
</head>
<body id="body">
    <header class="site-header">
        <div class="logo_box">
            <a class="logo_text" href="../index.html">COCO薬局</a>
        </div>
        <nav>
            <ul class="menu_bar">
                <li><a href="../index.html" >ホーム</a></li>
                <li><a href="./u15_01.html">薬情報一覧</a></li>
                <li ><a href="./u10_01_pharmacy.html" >会員検索</a></li>
            </ul>
        </nav>
        <div class="account_box">
            <input type="checkbox" id="account-btn-check">
            <label for="account-btn-check" id="account-btn-menu" class="account-btn"><span></span></label>
            <div class="account-content">
                <h2>ログインしてから利用できます。</h2>
                <ul>
                    <li>
                        <a href="../member/u02_01.html">ログイン</a>
                    </li>
                    <li>
                        <a href="../member/u01_01.html">会員登録</a>
                    </li>
                </ul>
            </div>
            <a href="../member/u02_01.html" class="login_btn">ログイン</a><br>
            <a href="../member/u01_01.html" class="singup_btn">会員登録</a>
        </div>
    </header>
    <main>
        <div class="main_box">
            <section class="item_con">
                <div class="item_box">
                    <div class="subtitle">
                        <h2>会員検索</h2>
                    </div>
                    <form action="./u10_02_pharmacy.html" method="post">
                        <div class="items">
                            <span class="items_text">
                                検索する会員のQRコード画像をアップロードし、検索ボタンを押してください。
                            </span><br>
                            <input type="file">
                        </div>
                        <div class="items_btn">
                            <button type="button" onclick="history.back();">戻る</button>
                            <button type="submit" class="submit_btn">検索</button>
                        </div>
                    </form>
                </div>
            </section>
        </div>
    </main>
    <footer>
        <div class="footer_link">
            <span><a href="../index.html">ホーム</a></span>
            <span><a href="#">個人情報の取り扱いについて</a></span>
            <span><a href="#">問い合わせ</a></span>
            <span><a href="#">お知らせ</a></span>
            <span><a href="#">会社情報</a></span>
        </div>
        <div class="footer_bottom">
            <span> Copyright &copy; 株式会社COCO薬局 All Rights Reserved.</span>
        </div>
    </footer>
    <div class="top_btn_con" id="top_btn_con">
        <button class="top_btn_text">⇧</button>
    </div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
</html>