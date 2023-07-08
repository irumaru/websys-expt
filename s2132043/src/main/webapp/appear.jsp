<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="db.Appear, java.util.List"%>
<%
List<Appear> appearList = (List<Appear>) request.getAttribute("list");
%>

<!doctype html>
<html lang="ja">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <title>Hello, world!</title>
  </head>
  <body>
  	<div class="container-lg">
    <h1>
		<a href="AppearServlet">ポケモン出現DB</a>
	</h1>
	<hr>
	<form action="AppearServlet" method="POST">
		<!--
		番号<input type="text" name="newnumber">
		市コード<input type="text" name="newshicode">
		-->
		名前を入力<input type="text" name="newname">
		県を入力<input type="text" name="newken">
		市を入力<input type="text" name="newshi">
		
		<input type="submit" name="submit" value="登録">
		<hr>
		<input type="radio" name="item" value="ID" checked="checked">ID
		<input type="radio" name="item" value="番号">番号
		<input type="radio" name="item" value="名前">名前
		<br>
		<input type="radio" name="order" value="asc" checked="checked">昇順
		<input type="radio" name="order" value="desc">降順
		<br>
		<input type="submit" name="submit" value="並び替え">
		<hr>
		<!--
		ID<input type="text" name="deleteid">
		<input type="submit" name="submit" value="削除">
		-->
	</form>
	<!--
	<hr>
	<a href="AppearServlet?shimei=習志野市">習志野市</a>
	<a href="AppearServlet?shimei=船橋市">船橋市</a>
	<hr>
	-->
	<%
	if (appearList != null) {
	%>
	出現情報
	<table border="1">
		<tr>
			<th>ID</th>
			<th>番号</th>
			<th>名前</th>
			<th>県名</th>
			<th>市名</th>
			<th>日付</th>
			<th>時刻</th>
			<th>処理</th>
		</tr>
		<%
		for (Appear appear : appearList) {
		%>
		<tr>
			<td><%=appear.getId()%></td>
			<td><%=appear.getNumber()%></td>
			<td><%=appear.getName()%></td>
			<td><%=appear.getKen()%></td>
			<td><%=appear.getShi()%></td>
			<td><%=appear.getDate()%></td>
			<td><%=appear.getTime() %></td>
			<td>
				<form action="AppearServlet" method="POST">
					<input type="hidden" name="deleteid" value="<%=appear.getId()%>">
					<input type="submit" name="submit" value="削除">
				</form>
			</td>
		</tr>
		<% } %>
	</table>
	<% } %>
	
	</div>

    <!-- Optional JavaScript; choose one of the two! -->

    <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

    <!-- Option 2: Separate Popper and Bootstrap JS -->
    <!--
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
    -->
  </body>
</html>