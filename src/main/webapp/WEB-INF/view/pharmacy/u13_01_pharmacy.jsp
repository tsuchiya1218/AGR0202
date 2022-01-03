<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/common.css">
    <link rel="stylesheet" href="../css/pharmacy/u13.css">
    <title>電子処方箋詳細画面</title>
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
        <div class="main_box">
            <section class="item_con">
                <div class="item_box">
                    <div class="subtitle">
                        <h2>電子処方箋詳細</h2>
                    </div>
                    <div class="items">
                        <label>
                            <span class="items_title">患者名</span>
                            <span class="items_text">金成憲</span>
                        </label>
                    </div>
                    <div class="items">
                        <label>
                            <span class="items_title">ふりがな</span>
                            <span class="items_text">キムソンホン</span>
                        </label>
                    </div>
                    <div class="items">
                        <label>
                            <span class="items_title">生年月日</span>
                            <span class="items_text">1995-06-24</span>
                        </label>
                    </div>
                    <div class="items_req">
                        <label>
                            <span class="items_title">処方箋使用期間</span>
                            <span class="items_text">2021-01-13</span>
                        </label>
                    </div>
                    <div class="items">
                        <label>
                            <span class="items_title">公費負担番号</span>
                            <span class="items_text"></span>
                        </label>
                    </div>
                    <div class="items">
                        <label>
                            <span class="items_title">公費負担医療の受験者</span>
                            <span class="items_text"></span>
                        </label>
                    </div>
                </div>
                <div class="item_box">
                    <div class="items">
                        <label>
                            <span class="items_title">区分</span>
                            <span class="items_text">被保険者</span>
                        </label>
                    </div>
                    <div class="items">
                        <label>
                            <span class="items_title">交付年月日</span>
                            <span class="items_text">2021-12-03</span>
                        </label>
                    </div>
                    <div class="subtitle">
                        <h2>処方</h2>
                    </div>
                    <div class="items">
                        <label>
                            <span class="items_title">病名</span>
                            <span class="items_text">風邪</span>
                        </label>
                    </div>
                    <div class="medicine_box">
                        <div class="items">
                            <label>
                                <span class="items_title">薬名</span>
                                <span class="items_text">リリカOD 25mg</span>
                            </label>
                        </div>
                        <div class="items">
                            <label>
                                <span class="items_title">分量</span>
                                <span class="items_text">1回2錠</span>
                            </label>
                        </div>
                        <div class="items">
                            <label>
                                <span class="items_title">用量</span>
                                <span class="items_text">1日2回</span>
                            </label>
                        </div>
                        <div class="items">
                            <label>
                                <span class="items_title">用法</span>
                                <span class="items_text">夕食後</span>
                            </label>
                        </div>
                        <div class="items">
                            <label>
                                <span class="items_title">日数</span>
                                <span class="items_text">7</span>
                            </label>
                        </div>
                        <div class="items">
                            <label>
                                <span class="items_title">総投与日数</span>
                                <span class="items_text">7</span>
                            </label>
                        </div>
                    </div>
                    <div class="medicine_box">
                        <div class="items">
                            <label>
                                <span class="items_title">薬名</span>
                                <span class="items_text">リリカOD 25mg</span>
                            </label>
                        </div>
                        <div class="items">
                            <label>
                                <span class="items_title">分量</span>
                                <span class="items_text">1回2錠</span>
                            </label>
                        </div>
                        <div class="items">
                            <label>
                                <span class="items_title">用量</span>
                                <span class="items_text">1日2回</span>
                            </label>
                        </div>
                        <div class="items">
                            <label>
                                <span class="items_title">用法</span>
                                <span class="items_text">夕食後</span>
                            </label>
                        </div>
                        <div class="items">
                            <label>
                                <span class="items_title">日数</span>
                                <span class="items_text">7</span>
                            </label>
                        </div>
                        <div class="items">
                            <label>
                                <span class="items_title">総投与日数</span>
                                <span class="items_text">7</span>
                            </label>
                        </div>
                    </div>
                    <div class="items">
                        <span class="items_title">備考</span>
                        <span class="items_text">少し熱</span>
                    </div>
                    <div class="items">
                        <label>
                            <span class="items_title">保険医署名</span>
                            <span class="items_text">中村 吉田</span>
                        </label>
                    </div>
                </div>
                <div class="item_box">
                    <div class="items">
                        <label>
                            <span class="items_title">病院名</span>
                            <span class="items_text">xx病院</span>
                        </label>
                    </div>
                    <div class="items">
                        <label>
                            <span class="items_title">医者名</span>
                            <span class="items_text">中村 吉田</span>
                        </label>
                    </div>
                    <div class="items">
                        <label>
                            <span class="items_title">病院 電話番号</span>
                            <span class="items_text">00-0000-0000</span>
                        </label>
                    </div>
                    <div class="items">
                        <label>
                            <span class="items_title">診察科</span>
                            <span class="items_text">内科</span>
                        </label>
                    </div>
                </div>
                <div class="btn_box">
                    <button type="button" onclick="location.href='./u10_02_pharmacy.html'">戻る</button>
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