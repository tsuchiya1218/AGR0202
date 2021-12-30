<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<main>
        <div class="main_box">
            <section class="item_con">
                <div class="item_box">
                    <div class="subtitle">
                        <h2>承認リスト</h2>
                    </div>
                    <div class="table_items">
                        <table>
                            <thead>
                                <th id="0">日付</th>
                                <th id="1">患者名</th>
                                <th id="2">生年月日</th>
                                <th id="3">性別</th>
                                <th id="6"></th>
                            </thead>
                            <tbody>
                                <td>2021-12-03 15:30</td>
                                <td>キムソンホン</td>
                                <td>1995-06-24</td>
                                <td>男性</td>
                                <td>
                                <form action="" method="POST">
                                    <button type="button" onclick="isAvailable(this.form);">有効</button>
                                </form>
                                </td>
                            </tbody>
                        </table>
                    </div>
                </div>
            </section>
        </div>
    </main>
</body>
</html>