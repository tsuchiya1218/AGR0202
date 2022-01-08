<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/admin/m1_s1.css">
    <title>医者情報変更画面</title>
</head>
<body id="body">
    <jsp:include page="../../view/common/admin/header.jsp"/>
    <main>
        <div class="main_box">
            <section class="item_con">
                <div class="item_box">
                    <div class="subtitle">
                        <h2>医者情報変更</h2>
                    </div>
                    <form action="AdminController?action=m01_s1_02" method="post">
                    <input type="hidden" name="d_num" value="${doctor.d_num }"/>
                        <div class="items">
                            <label>
                                <span class="items_title">メールアドレス</span>
                                <input type="email" name="email" placeholder="メールアドレス">
                            </label>
                        </div>
                        <div class="items">
                            <label>
                                <span class="items_title">パスワード</span>
                                <input type="password" name="password">
                            </label>
                        </div>
                        <c:set var="name" value="${fn:split(doctor.d_name,' ') }"/>
                        <div class="items">
                            <label>
                                <span class="items_title">名前(姓)</span>
                                <input type="text" name="frist_name" id="frist_name" value="${name[0] }" placeholder="福井">
                            </label>
                        </div>
                        <div class="items">
                            <label>
                                <span class="items_title">名前(名)</span>
                                <input type="text" name="last_name" id="last_name" value="${name[1] }" placeholder="岬">
                            </label>
                        </div>
                        <c:set var="kana" value="${fn:split(doctor.d_kana,' ') }"/>
                        <div class="items">
                            <label>
                                <span class="items_title">ふりがな(せい)</span>
                                <input type="text" name="frist_kana" id="frist_kana" value="${kana[0] }" placeholder="ふくい">
                            </label>
                        </div>
                        <div class="items">
                            <label>
                                <span class="items_title">ふりがな(めい)</span>
                                <input type="text" name="last_kana" id="last_kana" value="${kana[1] }" placeholder="みさき">
                            </label>
                        </div>
                        <div class="items">
                            <label class="brith">
                                <span class="items_title">生年月日</span>
                                <c:set var="birth" value="${fn:split(doctor.d_birth,'-') }"/>
                                <input type="number" name="birth" value="${birth[0] }" placeholder="1987" maxlength="4" oninput="maxLengthCheck(this)">-
                                <input type="number" name="birth" value="${birth[1] }" placeholder="06" maxlength="2" oninput="maxLengthCheck(this)">-
                                <input type="number" name="birth" value="${birth[2] }" placeholder="23" maxlength="2" oninput="maxLengthCheck(this)">
                            </label>
                        </div>
                        <div class="items">
                            <label class="tel">
                                <span class="items_title">電話番号</span>
                                <c:set var="tel" value="${fn:split(doctor.d_tel,'-') }"/>
                                <input type="number" name="tel" value="${tel[0] }" placeholder="070" min="0" maxlength="4" oninput="maxLengthCheck(this)">-
                                <input type="number" name="tel" value="${tel[1] }" placeholder="0123" min="0" maxlength="4" oninput="maxLengthCheck(this)">-
                                <input type="number" name="tel" value="${tel[2] }" placeholder="5678" min="0" maxlength="4" oninput="maxLengthCheck(this)">
                            </label>
                        </div>
                        <div class="items">
                            <label>
                                <span class="items_title">性別</span>
                            </label>
                            <c:if test="${doctor.d_gender eq '男性' }">
	                            <label><input type="radio" name="gender" value="男性" checked>男性</label>
	                            <label><input type="radio" name="gender" value="女性">女性</label>
                            </c:if>
                            <c:if test="${doctor.d_gender eq '女性' }">
	                            <label><input type="radio" name="gender" value="男性">男性</label>
	                            <label><input type="radio" name="gender" value="女性" checked>女性</label>
                            </c:if>
                        </div>
                        <div class="items">
                            <label>
                                <span class="items_title">診療科</span>
                                <input type="text" name="department" value="${doctor.d_department }" placeholder="内科">
                            </label>
                        </div>
                        <div class="items">
                            <label>
                                <span class="items_title">所属病院名</span>
                                <input type="text" name="hospital_name" value="${h_name }" placeholder="XX病院">
                            </label>
                        </div>
                        <div class="btn_box">
                            <button type="button" onclick="history.back()">戻る</button>
                            <button type="button" class="add_btn" onclick="isUpdate(this.form);" >確定</button>
                        </div>
                    </form>
                </div>
            </section>
        </div>
    </main>
    <jsp:include page="../../view/common/footer.jsp"/>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/admin/admin_common.js"></script>
</html>