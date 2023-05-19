/**
 * 
 */




document.addEventListener('DOMContentLoaded', function(){
	var money = document.getElementById('money');
	var balance = document.getElementById('balance');
	var b = parseInt(balance.textContent);
	var resultBalance = document.getElementById('result2');
	
	if(b > 0){
		resultBalance.style.backgroundColor = "#99FF99";
		balance.innerText = "残高は"+b+"円です";
	}else if(b < 0){
		resultBalance.style.backgroundColor = "#FF6633";
		balance.innerText = Math.abs(b)+"円足りません";
	}else if(money != 0 && b == 0){
		resultBalance.style.backgroundColor = "#F0E68C";
		balance.innerText = "ちょうどピッタリ!!";
	}
	
})