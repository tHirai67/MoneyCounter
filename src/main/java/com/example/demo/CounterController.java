package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;



@Controller
public class CounterController {
	List<Merchandise> list = new ArrayList<Merchandise>();
	Integer money = 0;
	
	@GetMapping(value = "/home")
	public String load(@ModelAttribute MerchandiseList ml, Model model) {
		if(money == 0) {
			model.addAttribute("m1", "所持金を入力してください");
		}else{
			model.addAttribute("m2", "所持金は"+money+"円です");
		}
	
		Integer sumPrice = 0;
		//listに格納されていたら表示
		if(list.size() != 0) {
			ml.setMerchandises(list);
			
			for(Merchandise merchandise : list ) {
				 sumPrice += merchandise.getPrice(); 
			}
		}
		model.addAttribute("m4","合計金額は" + sumPrice + "円です！");
		
		
		return "home";
	}
	
	@PostMapping(value = "/home/edit", params="money_edit")
	public String editMoney(@ModelAttribute MerchandiseList ml, Model model, @RequestParam(name = "money", required=false)Integer m) {
		//所持金の表示
		if(money != null) {
			money = m;
		}
		
		return "redirect:/home";
	}
	
	//追加ボタンを押下　→　新しい入力項目を表示する
	@PostMapping(value = "/home/edit", params="add")//paramsはbuttonタグのname属性
	public String addList(@ModelAttribute MerchandiseList ml, Model model,
			@RequestParam("m_name")String name,@RequestParam("m_price")Integer price ) {
		
		//入力をistに追加
		Merchandise merchandise = new Merchandise();
		merchandise.setName(name);
		merchandise.setPrice(price);
		list.add(merchandise);
		model.addAttribute("m3", name+"("+price+")"+"が追加されました．");
		return "redirect:/home";
	}
	
	//削除ボタンを押下
	@PostMapping(value = "/home/edit", params="remove")
	public String removeList(@ModelAttribute MerchandiseList ml, Model model, HttpServletRequest request) {
		//削除ボタンを押した行番号を取得
		int index = Integer.valueOf(request.getParameter("remove"));
		//削除
		list.remove(index);
		return "redirect:/home";
	}
	
	
	
	
	
	
	
}
