<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<main>
    <div class="notice_con">
        <span>お知らせ</span>
        <a href="">暗証番号紛失に注意</a>
        <a href="">パスワードを忘れた場合</a>
        <a href="">コロナウィルス感染対策</a>
    </div>
    <div class="main_box">
        <article class="wrap_info_con">
            <div class="info_con">
                <div class="item_box">
                    <div class="subtitle">
                        <h2>薬局の利用方法</h2>
                    </div>
                    <div class="manual_box">
                    	<div class="manual_title">
                            <span>会員を検索する</span>
                        </div>
                        <div class="manual_text">
                            <span>1.会員検索画面へ</span><br>
                            <span>2.QRコードを読み取り、検索ボタンを押す。</span><br>
                        </div>
                    </div>
                    <div class="manual_box">
                        <div class="manual_title">
                            <span>薬剤情報提供書・電子処方箋</span>
                        </div>
                        <div class="manual_text">
                            <span>会員検索した後から登録・詳細・変更可能です。</span><br>
                        </div>
                    </div>
                </div>
            </div>
        </article>
        <section class="menu_con">
            <div class="subtitle">
                <h2>MENU一覧</h2>
            </div>
            <div class="menu_items">
                <div class="menu_item_box">
                    <a class="item_title" href="PhamacyController?view=u10_01_pharmacy.jsp">会員検索</a><br>
                    <span>診察のために会員のQRコードで検索する必要があります。</span>
                </div>
                <div class="menu_item_box">
                    <a class="item_title" href="PhamacyController?view=u15_01.jsp">薬情報一覧</a><br>
                    <a href="PhamacyController?view=u14_01.jsp">薬情報登録</a><br>
                    <span>薬情報を登録・変更・削除・確認ができます。</span>
                </div>
            </div>
            <div class="menu_items">
                <div class="menu_item_box">
                    <span class="item_title">お知らせ</span><br>
                    <a href="">お知らせ</a>
                </div>
                <div class="menu_item_box">
                    <span class="item_title">問い合わせ</span><br>
                    <a href="">問い合わせ</a><br>
                    <a href="">よくあるQ&A</a>
                </div>
            </div>
        </section>
    </div>
</main>