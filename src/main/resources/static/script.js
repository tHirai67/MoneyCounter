/**
 * 
 */

 const checkMoney = document.getElementById("money_edit");
 checkMoney.addEventListener('click', (event)=>{
	 var money = document.getElementById("money").value;
	 if(money == ""){
		 alert("未入力です");
		 event.preventDefault();
	 }else if(money < 0){
		 alert("マイナスは入力できません");
		 event.preventDefault();
	 }
 });
 
 const checkMerchandise = document.getElementById("add");
 checkMerchandise.addEventListener('click', (event)=>{
	 var name = document.getElementById("name").value;
	 var price = document.getElementById("price").value;
	 
	 if(name == "" || price == ""){
		 alert("未入力の項目があります");
		 event.preventDefault();
	 }else if(price < 0){
		 alert("マイナスは入力できません");
		 event.preventDefault();
	 }
 })