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
        <div class="wrap_info_con">
            <div class="info_con">
                <div class="subtitle">
                    <h2>初めての方へ</h2>
                </div>
                <div class="info_text">
                    <p>
                        電子お薬手帳は<a href="NonMemberController?view=u01_01">会員登録</a>(無料)してから利用できます。<br>
                    </p>
                </div>
                <div class="item_box">
                    <div class="subtitle">
                        <h2>よくあるQ&A</h2>
                    </div>
                    <div class="manual_box">
                        <div class="manual_title">
                            <span>QRコード画像を保存したい</span>
                        </div>
                        <div class="manual_text">
                            <span>1.<a href="NonMemberController?view=u02">My QRコード</a>
                                にアクセス</span><br>
                            <span>2.QRコードを送信ボタンを押す。</span><br>
                            <span>3.登録したメールにQRコード画像が送られます。</span><br>
                        </div>
                    </div>
                    <div class="manual_box">
                        <div class="manual_title">
                            <span>どう使う？</span>
                        </div>
                        <div class="manual_text">
                            <span>1.QRコード画像を見せればOKです。</span>
                        </div>
                    </div>
                    <div class="manual_box">
                        <div class="manual_title">
                            <span>子供も登録し利用したい</span>
                        </div>
                        <div class="manual_text">
                            <span>1.まず子供登録する必要があります。<br></span>
                            <span>
                                <a href="NonMemberController?view=u02">子供情報</a>
                                にアクセスしてください。
                            </span><br>
                            <span>2.子供を登録する。</span><br>
                            <span>3.My QRコードを見せる</span><br>
                            <span>4.関係者の方は子ども情報を選び処方箋することができます。</span>
                        </div>    
                    </div>
                </div>
            </div>
        </div>
        <section class="menu_con">
            <div class="subtitle">
                <h2>MENU一覧</h2>
            </div>
            <div class="menu_items">
                <div class="menu_item_box">
                    <a class="item_title" href="NonMemberController?view=u02">My QRコード</a><br>
                    <p>
                        自分のQRコードが確認できます。<br>
                        QRコード画像をメールアドレスに送られます。
                    </p>
                </div>
                <div class="menu_item_box">
                    <a class="item_title" href='NonMemberController?view=u02'>マイページ</a><br>
                    <p>
                        個人情報の確認・変更・退会<br>
                        問診票の変更ができます。
                    </p>
                </div>
            </div>
            <div class="menu_items">
                <div class="menu_item_box">
                    <a class="item_title" href="NonMemberController?view=u02">子供情報</a><br>
                    <p>
                        子供情報の登録・変更・削除ができます。
                    </p>
                </div>
                <div class="menu_item_box">
                    <a class="item_title" href="NonMemberController?view=u02">薬歴</a><br>
                       <p>過去の薬歴が表示されます。</p>
                </div>
            </div>
            <div class="menu_items">
                <div class="menu_item_box">
                    <a class="item_title" href="#">お知らせ</a><br>
                    <a href="#">お知らせ</a>
                </div>
                <div class="menu_item_box">
                    <a class="item_title" href="#">問い合わせ</a><br>
                    <a href="#">問い合わせ</a><br>
                    <a href="#">よくあるQ&A</a>
                </div>
            </div>
        </section>
    </div>
</main>