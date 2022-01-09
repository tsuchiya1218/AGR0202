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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/member/u07_01.css">
    <title>子供情報一覧画面</title>
</head>
<body id="body">
	<jsp:include page="../common/member/header.jsp"/>
    <main>
        <div class="main_box">
            <section class="item_con">
                <div class="subtitle">
                    <h2>子供情報一覧</h2>
                </div>
                <div class="newchild_btn">
                    <a href="MemberController?view=u07_02">
                        <button>子供情報登録</button>
                    </a>
                </div>
                <c:if test="${empty children eq null }">
                	<p>登録されている子供情報はありません。</p>
                	<style type="text/css">
						footer {
							position: absolute;
							bottom: 0;
						}
					</style>
                </c:if>
                <c:if test="${!empty children }">
                	<p>
                		登録されている子供:  
                		<strong><c:out value="${sessionScope.member.children_count }"/>名</strong>
                		
                	</p>
                	<c:forEach var="child" items="${requestScope.children }" varStatus="status">
	                	<div class="items_box">
                            <div class="items">
                                <div class="items_title">
                                    <span>名前</span>
                                </div>
                                <div class="items_text">
                                    <c:out value="${child.c_name }"/>
                                </div>
                            </div>
                            <div class="items">
                                <div class="items_title">
                                    <span>ふりがな</span>
                                </div>
                                <div class="items_text">
                                    <c:out value="${child.c_kana }"/>
                                </div>
                            </div>
                            <div class="items">
                                <div class="items_title">
                                    <span>生年月日</span>
                                </div>
                                <div class="items_text">
                                    <c:out value="${child.c_birth }"/>
                                    <c:out value="(${child.age }歳)"/>
                                </div>
                            </div>
                            <div class="items">
                                <div class="items_title">
                                    <span>性別</span>
                                </div>
                                <div class="items_text">
                                    <c:out value="${child.c_gender }"/>
                                </div>
                            </div>
                            <div class="items">
                                <div class="items_title">
                                    <span>こども医療証</span>
                                </div>
                                <div class="items_text">
                                    <c:out value="${child.c_medical_num }"/>
                                </div>
                            </div>
                            <div class="items">
                                <div class="items_title">
                                    <span>保険証番号</span>
                                </div>
                                <div class="items_text">
                                	<c:set var="c_i_num" value="${fn:split(child.c_i_num,'-')}" />
                                	番号:<c:out value="${c_i_num[0] }"/>
                                	枝番:<c:out value="${c_i_num[1] }"/>
                                </div>
                            </div>
                            <div class="items">
                                <div class="items_title">
                                    <span>保険証記号</span>
                                </div>
                                <div class="items_text">
                                	<c:out value="${child.c_i_mark }"/>
                                </div>
                            </div>
                            <div class="items">
                                <div class="items_title">
                                    <span>保険証 有効期限</span>
                                </div>
                                <div class="items_text">
                                    <c:out value="${child.c_i_expiry_date }"/>
                                </div>
                            </div>
                            <div class="items">
                                <div class="items_title">
                                    <span>血液型</span>
                                </div>
                                <div class="items_text">
                                    <c:out value="${child.c_blood_type }"/>
                                </div>
                            </div>
                            <div class="items">
                                <div class="items_title">
                                    <span>病歴</span>
                                </div>
                                <div class="items_text">
                                	<c:set var="c_medical_history" value="${fn:replace(child.c_medical_history,'&lt;br&gt;','<br>')}" />
                                    <c:out escapeXml="false" value="${c_medical_history }"/>
                                </div>
                            </div>
                            <div class="items">
                                <div class="items_title">
                                    <span>服用中の薬</span>
                                </div>
                                <div class="items_text">
                                    <c:out value="${child.c_medication }"/>
                                </div>
                            </div>
                            <div class="items">
                                <div class="items_title">
                                    <span>アレルギー情報</span>
                                </div>
                                <div class="items_text">
                                	<c:set var="c_allergy" value="${fn:replace(child.c_allergy,'&lt;br&gt;','<br>')}" />
                                   	<c:out escapeXml="false" value="${c_allergy }"/>
                                </div>
                            </div>
                            <div class="btn_box">
                            <form action="MemberController?action=u07_s2" method="POST">
				                <input type="hidden" name="c_num" value="${child.c_num }">
                                <button type="button" class="delete_btn" onclick="isDelete(form);">削除</button>
                			</form>
                			<form action="MemberController?action=u07_s1_01" method="POST">
                				<input type="hidden" name="c_num" value="${child.c_num }">
                                <button type="submit" class="update_btn">変更</button>
                            </form>
                            </div>
                       </div>
                	</c:forEach>
                </c:if>
            </section>
        </div>
    </main>
    <jsp:include page="../common/footer.jsp"/>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/member/u07.js"></script>
</html>