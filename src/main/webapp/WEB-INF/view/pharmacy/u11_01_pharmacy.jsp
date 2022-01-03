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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/pharmacy/u11_01_pharmacy.css">
    <title>電子処方箋登録画面</title>
</head>
<body id="body">
	<jsp:include page="../common/pharmacy/header.jsp"/>
    <main>
        <div class="main_box" id="main_box">
            <form action="PharmacyController?action=u10_02" method="POST">
                <section class="item_con">
                    <div class="item_box">
                        <div class="subtitle">
                            <h2>電子処方箋登録</h2>
                        </div>
                        <div class="items">
                            <label>
                                <span class="items_title">患者名</span>
                                <span class="items_text">${member.m_name }</span>
                            </label>
                        </div>
                        <div class="items">
                            <label>
                                <span class="items_title">ふりがな</span>
                                <span class="items_text">${member.m_kana }</span>
                            </label>
                        </div>
                        <div class="items">
                            <label>
                                <span class="items_title">生年月日</span>
                                <span class="items_text">${member.m_birth }</span>
                            </label>
                        </div>
                        <div class="items_req">
                            <label>
                                <span class="items_title">処方箋使用期間</span>
                                <input type="number" name="ep_expiry_date" placeholder="2010" maxlength="4" min="0" oninput="maxLengthCheck(this)">-
                                <input type="number" name="ep_expiry_date" placeholder="01" maxlength="2" min="0" oninput="maxLengthCheck(this)">-
                                <input type="number" name="ep_expiry_date" placeholder="12" maxlength="2" min="0" oninput="maxLengthCheck(this)">
                            </label>
                        </div>
                        <div class="items">
                            <label>
                                <span class="items_title">公費負担番号</span>
                                <input type="text" name="ep_burden_num" placeholder="公費負担番号" maxlength="20">
                            </label>
                        </div>
                        <div class="items">
                            <label>
                                <span class="items_title">公費負担医療の受験者</span>
                                <input type="text" name="ep_burden_person" placeholder="公費負担医療の受験者" maxlength="50">
                            </label>
                        </div>
                    </div>
                    <div class="item_box">
                        <div class="items_radio">
                            <span class="items_title">区分</span>
                            <label>
                                <input type="radio" name="ep_patient_type" checked>被保険者
                            </label>
                            <label>
                                <input type="radio" name="ep_patient_type">被扶養者
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
                        <div class="items_req">
                            <label>
                                <span class="items_title">病名</span>
                                <input type="text" name="ep_disease" placeholder="風邪" required>
                            </label>
                        </div>
                        <p>
                            薬一覧から薬を選択するとテキストボックスが追加されます。<br>
                            項目を削除したい場合はXマークを押してください。<br>
                            薬名・症状で検索する場合は検索ボタンの左にある<br>
                            テキストボックスに入力し検索してください。<br>
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
                                <button type="button" id="delete_btn" value="0"
                                    onclick="deleteItem(this);">X</button>
                                <label>
                                    <span class="medicine_item">薬名</span>
                                    <input type="text" value="リリカOD 25mg" class="medicine_name_index"
                                        name="medicine_name[]" required disabled>
                                </label>
                                <label class="two_text">
                                    <span class="medicine_item">分量</span>
                                    <input type="number" name="pm_dosage" placeholder="1" required>回
                                    <input type="number" name="pm_dosage" placeholder="2" required>錠
                                </label>
                                <label class="two_text">
                                    <span class="medicine_item">用量</span>
                                    <input type="number" name="pm_dose" placeholder="1" required>日
                                    <input type="number" name="pm_dose" placeholder="1" required>回
                                </label>
                                <label>
                                    <span class="medicine_item">用法</span>
                                    <select name="pm_usage">
                                        <option value="朝食前" selected>朝食前</option>
                                        <option value="朝食後">朝食後</option>
                                        <option value="昼食前">昼食前</option>
                                        <option value="昼食後">昼食後</option>
                                        <option value="夕食前">夕食前</option>
                                        <option value="夕食後">夕食後</option>
                                    </select>
                                </label>
                                <label>
                                    <span class="medicine_item">日数</span>
                                    <input type="number" name="pm_dose_day" placeholder="28" required>
                                </label>
                                <label>
                                    <span class="medicine_item">総投与日数</span>
                                    <input type="number" name="pm_all_dose_day" placeholder="56" required>
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="items_another">
                        <span class="items_title_another">備考</span>
                        <label>
                            <textarea name="ep_note" maxlength="1000" placeholder="備考"></textarea>
                        </label>
                    </div>
                    <div class="items">
                        <label>
                            <span class="items_title">保険医署名</span>
                            <span class="items_text">中村 吉田</span>
                        </label>
                    </div>
                </div>
                <div class="btn_box">
                    <button type="button" onclick="location.href='./u10_02_doctor.html'">戻る</button>
                    <button type="button" class="submit_btn" onclick="isSubmit(this.form);">電子処方箋登録</button>
                </div>
            </section>
            <div class="hospital_info">
                <div class="item_box">
                    <div class="subtitle">
                        <h2>病院情報</h2>
                    </div>
                    <div class="items">
                        <label>
                            <span class="items_title">病院名</span>
                            <input type="text" name="h_name" placeholder="xx病院">
                        </label>
                    </div>
                    <div class="items">
                        <label>
                            <span class="items_title">医者名</span>
                            <input type="text" name="d_name" placeholder="中村 吉田">
                        </label>
                    </div>
                    <div class="items">
                        <label>
                            <span class="items_title">病院電話番号</span>
                            <input type="text" name="h_tel" placeholder="00-0000-0000">
                        </label>
                    </div>
                    <div class="items">
                        <label>
                            <span class="items_title">診察科</span>
                            <input type="text" name="d_department" placeholder="内科">
                        </label>
                    </div>
                </div>
                <div class="btn_box">
                    <button type="button" onclick="hisotry.back();">戻る</button>
                    <button type="button" class="submit_btn" onclick="isSubmit(this.form);">電子処方箋登録</button>
                </div>
            </div>
        </form>
        </div>
    </main>
    <jsp:include page="../common/footer.jsp"/>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/pharmacy/u11_pharmacy.js"></script>
</html>