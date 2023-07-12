/**
 * 
 */

let form = document.querySelector("#pokemon-add-form");
let datalistValidate = document.querySelectorAll(".datalist-validate");

addPokemon = () => {
	for(let datalist of datalistValidate){
		let id = datalist.id;
		
		if(!inputValidate(id)){
			return;
		}
	}
	
	let touroku = document.createElement("input");
	touroku.setAttribute("type", "hidden");
	touroku.setAttribute("name", "mode");
	touroku.setAttribute("value", "登録");
	
	form.appendChild(touroku);
	
	form.submit();
}

search = () => {
	let touroku = document.createElement("input");
	touroku.setAttribute("type", "hidden");
	touroku.setAttribute("name", "mode");
	touroku.setAttribute("value", "並び替え");
	
	form.appendChild(touroku);
	
	form.submit();
}

inputValidate = (idname) => {
	// 要素の取得
	
	let inputName = document.querySelector("#" + idname);
	let suggests = document.querySelectorAll("." + idname + "-suggest");
	
	// バリデーションロジック
	
	let name = inputName.value;
	let flag = false;
	
	for(let suggest of suggests){
		if(suggest.value == name){
			flag = true;
		}
	}
	
	if(!flag){
		console.log(idname + "が原因です。");
		alert("一致するポケモンがいません。リストから選択してください。");
	}
	
	return flag;
}
