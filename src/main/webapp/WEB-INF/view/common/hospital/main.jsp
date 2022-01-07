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
        <section class="menu_con">
            <div class="subtitle">
                <h2>MENU一覧</h2>
            </div>
            <div class="menu_items">
                <div class="menu_item_box">
                    <a class="item_title" href="HospitalController?action=u12">承認リスト</a><br>
                    <span>
                        診察が終わった患者様の承認を行う必要があります。
                    </span>
                </div>
                <div class="menu_item_box">
                    <a class="item_title" href="#">お知らせ</a><br>
                </div>
            </div>
            <div class="menu_items">
                <div class="menu_item_box">
                    <a class="item_title" href="#">問い合わせ</a><br>
                    <a href="">よくあるQ&A</a>
                </div>
                <div class="menu_item_box" style="background-color: white;"></div>
            </div>
        </section>
    </div>
</main>