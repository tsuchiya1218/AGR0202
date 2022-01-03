<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/common.css">
    <link rel="stylesheet" href="../css/pharmacy/u17_01.css">
    <title>薬剤情報提供書登録画面</title>
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
                <li class="menu_li_active"><a href="./u10_01_pharmacy.html" class="menu_a_active">会員検索</a></li>
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
        <div class="main_box" id="main_box">
            <form action="./u10_02_pharmacy.html" method="POST">
            <section class="item_con">
                <div class="item_box">
                    <div class="subtitle">
                        <h2>薬剤情報提供書</h2>
                    </div>
                    <div class="member_name">
                        <span>金成憲</span>
                        <span>キムソンホン</span>
                        <span>様</span>
                    </div>
                    <div class="items">
                        <span class="items_title">調剤日</span>
                        <span class="items_text">2021-12-05</span>
                    </div>
                    <div class="items">
                        <span class="items_title">処方日</span>
                        <span class="items_text">2021-12-06</span>
                    </div>
                    <div class="items">
                        <span class="items_title">医者</span>
                        <span class="items_text">吉村タバスコ</span>
                    </div>
                    <div class="items">
                        <span class="items_title">公費負担番号</span>
                        <span class="items_text">公費負担番号</span>
                    </div>
                    <div class="items">
                        <span class="items_title">公費負担医療の受験者</span>
                        <span class="items_text">公費負担医療の受験者</span>
                    </div>
                    <div class="items">
                        <span class="items_title">区分</span>
                        <span class="items_text">被保険者</span>
                    </div>
                    <div class="items">
                        <span class="items_title">交付年月日</span>
                        <span class="items_text">2021-12-03</span>
                    </div>
                </div>
                <div class="item_box">
                    <div class="subtitle">
                        <h2>処方</h2>
                    </div>
                    <div class="items">
                        <span class="items_title">病名</span>
                        <span class="items_text">風邪</span>
                    </div>
                    <div class="items_medicine">
                        <div class="items_img">
                            <span class="items_title">写真</span>
                            <img src="../img/medicine1.jpg" alt="">
                        </div>
                        <div class="items">
                            <span class="items_title">薬名</span>
                            <span class="items_text">リリカOD 25mg</span>
                        </div>
                        <div class="items">
                            <span class="items_title">効果</span>
                            <span class="items_text">この薬は・・・</span>
                        </div>
                        <div class="items">
                            <span class="items_title">分量</span>
                            <span class="items_text">1回2錠</span>
                        </div>
                        <div class="items">
                            <span class="items_title">用量</span>
                            <span class="items_text">1日2回</span>
                        </div>
                        <div class="items">
                            <span class="items_title">用法</span>
                            <span class="items_text">夕食後</span>
                        </div>
                        <div class="items">
                            <span class="items_title">日数</span>
                            <span class="items_text">28</span>
                        </div>
                        <div class="items">
                            <span class="items_title">総投与日数</span>
                            <span class="items_text">56</span>
                        </div>
                        <div class="items">
                            <span class="items_title">備考</span>
                            <span class="items_text">備考</span>
                        </div>
                    </div>
                    <div class="items">
                        <span class="items_title">保険医署名</span>
                        <span class="items_text">中村 吉田</span>
                    </div>
                </div>
            </section>
            <div class="hospital_info">
                <div class="item_box">
                    <div class="subtitle">
                        <h2>病院情報</h2>
                    </div>
                    <div class="items">
                        <span class="items_title">病院名</span>
                        <span class="items_text">xx病院</span>
                    </div>
                    <div class="items">
                        <span class="items_title">医者名</span>
                        <span class="items_text">中村 吉田</span>
                    </div>
                </div>
            </div>
            <div class="itembox">
                <div class="subtitle">
                    <h2>薬局情報</h2>
                </div>
                <div class="items">
                    <span class="items_title">薬局名</span>
                    <span class="items_text">COCO薬局</span>
                </div>
                <div class="items">
                    <span class="items_title">薬局 電話番号</span>
                    <span class="items_text">00-0000-0000</span>
                </div>
                <div class="items">
                    <span class="items_title">薬局 住所</span>
                    <span class="items_text">東京都新宿区百人町</span>
                </div>
            </div>
            <div class="btn_box">
                <button type="button" onclick="history.back();">戻る</button>
                <button type="button" class="submit_btn" onclick="isSubmit(form);">薬剤情報提供書登録</button>
            </div>
        </form>
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
<script type="text/javascript" src="../js/pharmacy/u17.js"></script>
</html>