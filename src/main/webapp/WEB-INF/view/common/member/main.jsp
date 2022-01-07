<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<main>
    <div class="notice_con">
        <span>お知らせ</span>
        <a href="">暗証番号紛失に注意</a>
        <a href="">パスワードを忘れた場合</a>
        <a href="">コロナウィルス感染対策</a>
    </div>
    <div class="main_box">
        <section class="qr_item">
            <div class="item_box">
                <div class="subtitle"><h2>My QRコード</h2></div>
                <img src="${pageContext.request.contextPath}/static/img/qr_code/${member.m_qr_num}.png" alt="My QRコード" >
            </div>
            <div class="qr_text">
                <span>${member.m_qr_num}</span>
            </div>
        </section>
        <div class="wrap_info_con">
            <div class="info_con">
                <div class="item_box">
                    <div class="subtitle">
                        <h2>よくあるQ&A</h2>
                    </div>
                    <div class="manual_box">
                        <div class="manual_title">
                            <span>QRコード画像を保存したい</span>
                        </div>
                        <div class="manual_text">
                            <span>1.<a href="MemberController?view=u09">My QRコード</a>
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
                            <span>1.QRコード画像を見せればOKです。</span><br>
                        </div>
                    </div>
                    <div class="manual_box">
                        <div class="manual_title">
                            <span>子供も登録し利用したい</span>
                        </div>
                        <div class="manual_text">
                            <span>1.まず子供登録する必要があります。<br></span>
                            <span>
                                <a href="MemberController?view=u07_01">子供情報</a>
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
                    <a class="item_title" href="MemberController?view=u09">My QRコード</a><br>
                    <span>
                        自分のQRコードが確認できます。<br>
                        QRコード画像をメールアドレスに送られます。
                    </span>
                </div>
                <div class="menu_item_box">
                    <a class="item_title" href='MemberController?view=u06_01'>マイページ</a><br>
                    <span>
                        個人情報の確認・変更・退会<br>
                        問診票の変更ができます。
                    </span>
                </div>
            </div>
            <div class="menu_items">
                <div class="menu_item_box">
                    <a class="item_title" href="MemberController?view=u07_01">子供情報</a><br>
                    <span>子供情報の登録・変更・削除ができます。</span>
                </div>
                <div class="menu_item_box">
                    <a class="item_title" href="MemberController?action=u08_01">薬歴</a><br>
                       <span>過去の薬歴が表示されます。</span>
                </div>
            </div>
            <div class="menu_items">
                <div class="menu_item_box">
                    <a class="item_title" href="#">お知らせ</a><br>
                </div>
                <div class="menu_item_box">
                    <a class="item_title" href="#">問い合わせ</a><br>
                    <a href="">よくあるQ&A</a>
                </div>
            </div>
        </section>
    </div>
</main>