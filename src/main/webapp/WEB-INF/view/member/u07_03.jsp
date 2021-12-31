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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/member/u07_03.css">
    <title>内容確認画面</title>
</head>
<body id="body">
	<jsp:include page="../common/member/header.jsp"/>
    <main>
        <div class="main_box">
            <section class="item_con">
                <div class="item_box">
                    <div class="subtitle">
                        <h2>子供登録確認</h2>
                    </div>
                    <form action="MemberController" method="post">
                    <input type="hidden" name="action" value="u07_03">
                        <div class="items">
                            <span class="items_title">名前</span>
                            <span class="items_text">
                            	<c:out value="${sessionScope.childBean.c_name }"/>
                            </span>
                        </div>
                        <div class="items">
                            <span class="items_title">ふりがな</span>
                            <span class="items_text">
                            	<c:out value="${sessionScope.childBean.c_kana }"/>
                            </span>
                        </div>
                        <div class="items">
                            <span class="items_title">生年月日</span>
                            <span class="items_text">
                            	<c:out value="${sessionScope.childBean.c_birth }"/>
                            </span>
                        </div>
                        <div class="items">
                            <span class="items_title">性別</span>
                            <span class="items_text">
                            	<c:out value="${sessionScope.childBean.c_gender }"/>
                            </span>
                        </div>
                        <div class="items">
                            <span class="items_title">こども医療証<br>受給者番号</span>
                            <span class="items_text">
                            	<c:out value="${sessionScope.childBean.c_medical_num }"/>
                            </span>
                        </div>
                        <div class="items">
                            <span class="items_title">保険証番号</span>
                            <span class="items_text">
                            	<c:out value="${sessionScope.childBean.c_i_num }"/>
                            </span>
                        </div>
                        <div class="items">
                            <span class="items_title">保険証記号</span>
                            <span class="items_text">
                            	<c:out value="${sessionScope.childBean.c_i_mark }"/>
                            </span>
                        </div>
                        <div class="items">
                            <span class="items_title">保険証有効期限</span>
                            <span class="items_text">
                            	<c:out value="${sessionScope.childBean.c_i_expiry_date }"/>
                            </span>
                        </div>
                        <div class="items">
                                <span class="items_title">血液型</span>
                                <span class="items_text">
	                            	<c:out value="${sessionScope.childBean.c_blood_type }"/>
	                            </span>
                        </div>
                        <div class="items">
                                <span class="items_title">病歴</span>
                                <span class="items_text">
                            		<c:set var="c_medical_history" value="${fn:replace(sessionScope.childBean.c_medical_history,'&lt;/br&gt;','<br>')}" />
                       	    		<c:out value="${c_medical_history}" escapeXml="false"/>
                            	</span>
                        </div>
                        <div class="items">
                                <span class="items_title">服用中の薬</span>
                                <span class="items_text">
	                            	<c:out value="${sessionScope.childBean.c_medication }"/>
	                            </span>
                        </div>
                        <div class="items">
                            <span class="items_title">アレルギー情報</span>
	                        <span class="items_text">
                           		<c:set var="c_allergy" value="${fn:replace(sessionScope.childBean.c_allergy,'&lt;/br&gt;','<br>')}" />
                       	    	<c:out value="${c_allergy}" escapeXml="false"/>
                            </span>
                    </div>
                        <div class="btn_box">
                            <button type="button" onclick="history.back()">戻る</button>
                            <button type="button" class="add_btn" onclick="isSubmit(this.form);" >確定</button>
                        </div>
                    </form>
                </div>
            </section>
        </div>
    </main>
    <jsp:include page="../common/footer.jsp"/>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/member/u07.js"></script>
</html>