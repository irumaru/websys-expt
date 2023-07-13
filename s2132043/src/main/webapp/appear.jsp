<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="db.Appear, db.Pokemon, db.Region, java.util.List, java.time.LocalDateTime, java.time.format.DateTimeFormatter"%>
<%
List<Appear> appearList = (List<Appear>) request.getAttribute("appearList");
List<Pokemon> pokemonList = (List<Pokemon>) request.getAttribute("pokemonList");
List<Region> regionList = (List<Region>) request.getAttribute("regionList");
String item = (String)request.getAttribute("item");
String order = (String)request.getAttribute("order");

LocalDateTime nowDate = LocalDateTime.now();
DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern("HH:mm");
String formatNowDate = dateFmt.format(nowDate);
String formatNowTime = timeFmt.format(nowDate);
%>

<!doctype html>
<html lang="ja">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
		
    <title>ポケモン出現DB</title>
  </head>
  <body>
  	<div class="container-lg">
    <h1>
		<a href="AppearServlet">ポケモン出現DB</a>
	</h1>
	<hr>
	<form action="AppearServlet?item=<%= item %>&order=<%= order %>" method="POST" id="pokemon-add-form">
		<!--
		番号<input type="text" name="newnumber">
		市コード<input type="text" name="newshicode">
		-->
		<!-- ポケモン名を入力 -->
		名前を入力
		<input type="text" name="newname" list="polemonList" class="form-control datalist-validate" placeholder="キーワードを入力して選択" autocomplete="off" id="newname">
		<%
		if (pokemonList != null) {
		%>
		<datalist id="polemonList">
		   	<%
			for (Pokemon pokemon : pokemonList) {
			%>
				<option value="<%= pokemon.getName() %>" class="newname-suggest">
			<% } %>
		</datalist>
		<% } %>
		
		県と市を入力
		<input type="text" name="newregion" list="regionList" class="form-control datalist-validate" placeholder="キーワードを入力して選択" autocomplete="off" id="newregion">
		<%
		if (regionList != null) {
		%>
		<datalist id="regionList">
		   	<%
			for (Region region : regionList) {
			%>
				<option value="<%= region.getKenName() + " " + region.getShiName() %>" class="newregion-suggest">
			<% } %>
		</datalist>
		<% } %>
		
		出現日
		<br>
		<input type="date" name="arrivalDate" require value="<%= formatNowDate %>" class="form-control">
		出現時間
		<br>
		<input type="time" name="arrivalTime" require value="<%= formatNowTime %>" class="form-control">
		<br>
		<input type="button" onclick="addPokemon()" value="登録" class="btn btn-primary">
		<hr>
	</form>
	<form action="AppearServlet" method="GET">
		<!--
		<input type="radio" name="item" value="ID" checked="checked">ID
		<input type="radio" name="item" value="番号">番号
		<input type="radio" name="item" value="名前">名前
		-->
		表示順の項目
		<select name="item" class="form-select">
			<option value="ID" <%= item.equals("ID") ? "selected" : "" %>>ID</option>
			<option value="番号" <%= item.equals("番号") ? "selected" : "" %>>番号</option>
			<option value="名前" <%= item.equals("名前") ? "selected" : "" %>>名前</option>
		</select>
		<br>
		<!--
		<input type="radio" name="order" value="asc" checked="checked">昇順
		<input type="radio" name="order" value="desc">降順
		-->
		表示順
		<select name="order" class="form-select">
			<option value="asc" <%= order.equals("asc") ? "selected" : "" %>>昇順</option><%= order %>
			<option value="desc" <%= order.equals("desc") ? "selected" : "" %>>降順</option>
		</select>
		<br>	
		<!--<input type="button" onclick="search()" value="並び替え" class="btn btn-primary">-->
		<input type="submit" value="並び替え" class="btn btn-primary">
		<hr>
	</form>
		<!--
		ID<input type="text" name="deleteid">
		<input type="submit" name="submit" value="削除">
		-->
	
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
	<table class="table">
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
			<td>
				<button type="button" class="btn btn-link" data-bs-toggle="modal" data-bs-target="#test-modal-<%=appear.getId()%>" style="padding: 0;">
		            <%=appear.getName()%>
		        </button>
			</td>
			<td><%=appear.getKen()%></td>
			<td><%=appear.getShi()%></td>
			<td><%=appear.getDate()%></td>
			<td><%=appear.getTime() %></td>
			<td>
				<form action="AppearServlet" method="POST">
					<input type="hidden" name="deleteid" value="<%=appear.getId()%>">
					<input type="submit" name="mode" value="削除" class="btn btn-sm btn-primary">
				</form>
			</td>
		</tr>
		
		<div id="test-modal-<%=appear.getId()%>" class="modal fade" tabindex="-1" aria-hidden="true">
	        <div class="modal-dialog">
	            <div class="modal-content">
	                <div class="modal-header">
	                    <h5 class="modal-title"><%=appear.getName()%></h5>
	                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	                </div>
	                <div class="modal-body">
	                    <img src="pokemonImages/<%=appear.getNumber()%>.gif" style="width: 100%;">
	                </div>
	                <!--
	                <div class="modal-footer">
	                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
	                    <button type="button" class="btn btn-primary" data-bs-dismiss="modal">OK</button>
	                </div>
	                -->
	            </div>
	        </div>
	    </div>
	    
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
  <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.js"></script>
  <script type="text/javascript" src="form.js"></script>
</html>