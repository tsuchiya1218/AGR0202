<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/common.css">
    <link rel="stylesheet" href="../css/pharmacy/u18.css">
    <title>薬剤情報提供書変更画面</title>
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
            <form action="./u18_01.html" method="POST">
            <section class="item_con">
                <div class="item_box">
                    <div class="subtitle">
                        <h2>薬剤情報提供書変更</h2>
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
                    <div class="items_req">
                        <label>
                            <span class="items_title">病名</span>
                            <input type="text" placeholder="風邪" required >
                        </label>
                    </div>
                    <p class="info_text">
                        薬一覧で薬を選択したらテキストボックスが追加されます。<br>
                        消したい場合はXマークを押せば消されます。<br>
                        薬名・症状で検索する場合は検索ボタンの左にある<br>
                        テキストボックスに一部だけでも入力し検索を押してください。<br>
                    </p>
                    <div class="medicine_btn">
                        <button type="button" onclick="openTab();" id="medicine_tap_btn">薬一覧</button>
                        <input type="text" id="search_medicine_keyword" placeholder="薬名">
                        <button type="button" onclick="searchMedicine();">検索</button>
                    </div>
                    <!-- MEDICINE TAP MENU -->
                    <div class="medicine_tap_menu" id="medicine_tap_menu">
                        <button type="button" id="close_tap" onclick="closeTap();">X</button>
                        <div class="tap_menu_item_con">
                            <div class="tap_item_box">
                                <span id="search_result">検索結果</span>
                                <div class="tap_item">
                                    <label>リリカOD 25mg
                                        <input type="checkbox" name="medicine_cbx[]" value="リリカOD 25mg">
                                    </label>
                                    <label>リリカOD 45mg
                                        <input type="checkbox" name="medicine_cbx[]" value="リリカOD 45mg">
                                    </label>
                                    <label>リリカOD 100mg
                                        <input type="checkbox" name="medicine_cbx[]" value="リリカOD 100mg">
                                    </label>
                                </div>
                                <div class="tap_item">
                                    <label>リリカOD 65mg
                                        <input type="checkbox" name="medicine_cbx[]" value="リリカOD 65mg">
                                    </label>
                                    <label>リリカOD 90mg
                                        <input type="checkbox" name="medicine_cbx[]" value="リリカOD 90mg">
                                    </label>
                                    <label>リリカOD 110mg
                                        <input type="checkbox" name="medicine_cbx[]" value="リリカOD 110mg">
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="medicine" id="medicine">
                        <div class="medicine_con" id="medicine_con" style="display: none;">
                            <div class="items_medicine" id="items_medicine none">
                                <button type="button" id="delete_btn" value="0" onclick="deleteItem(this);">X</button>
                                <label>
                                    <span class="items_title">薬名</span>
                                    <input type="text" value="リリカOD 25mg" class="medicine_name_index" name="medicine_name[]" required disabled>
                                </label>
                                <label class="two_text">
                                    <span class="items_title">分量</span>
                                    <input type="number" placeholder="1" required >回
                                    <input type="number" placeholder="2" required >錠
                                </label>
                                <label class="two_text">
                                    <span class="items_title">用量</span>
                                    <input type="number" placeholder="1" required >日
                                    <input type="number" placeholder="1" required >回
                                </label>
                                <label>
                                    <span class="items_title">用法</span>
                                    <select name="">
                                        <option value="" selected>朝食前</option>
                                        <option value="">朝食後</option>
                                        <option value="">昼食前</option>
                                        <option value="">昼食後</option>
                                        <option value="">夕食前</option>
                                        <option value="">夕食後</option>
                                    </select>
                                </label>
                                <label>
                                    <span class="items_title">日数</span>
                                    <input type="number" placeholder="28" required >
                                </label>
                                <label>
                                    <span class="items_title">総投与日数</span>
                                    <input type="number" placeholder="56" required >
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="items_another">
                    <span class="items_title_another">備考</span>
                    <label>
                        <textarea maxlength="1000" placeholder="備考"></textarea>
                    </label>
                </div>
                <div class="items">
                    <label>
                        <span class="items_title">保険医署名</span>
                        <input type="text" value="中村 吉田" disabled>
                    </label>
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
            <div class="item_box">
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
                <button type="button" class="submit_btn" onclick="isSubmit(form)">確定</button>
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
<script type="text/javascript" src="../js/pharmacy/u18.js"></script>
</html>