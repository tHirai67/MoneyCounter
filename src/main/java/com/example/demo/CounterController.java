package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;



@Controller
public class CounterController {
	List<Merchandise> list = new ArrayList<Merchandise>();
	Integer money = 0;
	
	@GetMapping(value = "/home")
	public String load(@ModelAttribute MerchandiseList ml, Model model, @ModelAttribute("status")String status, @ModelAttribute("error")String error) {
		if(money == 0) {
			model.addAttribute("money", "所持金を入力してください");
		}else {
			model.addAttribute("money", "所持金:" + money + "円です");
		}
		//受信した処理内容を表示
		model.addAttribute("status", status);
	
		Integer sumPrice = 0;
		//listに格納されていたら表示
		if(list.size() != 0) {
			ml.setMerchandises(list);
			
			for(Merchandise merchandise : list ) {
				if(merchandise.getPrice() != null){
			        sumPrice += merchandise.getPrice();
			    }
			}
			model.addAttribute("sum_price","合計金額は" + sumPrice + "円です！");
			Integer balance = money - sumPrice;
			if(balance >= 0) {
				model.addAttribute("balance", "残金は" + balance + "円です！");
			}else {
				model.addAttribute("balance", Math.abs(balance) + "円足りません！");
			}
			
			
		}
		
		
		return "home";
	}
	
	//所持金の入力ボタン機能
	@PostMapping(value = "/home/edit", params="money_edit")
	public String editMoney(MerchandiseList merchandiseList, BindingResult result, RedirectAttributes ra, Model model) {
		//所持金の表示
		Integer moneyValue = merchandiseList.getMoney();
		if (moneyValue != null) {
		    money = moneyValue.intValue();
		}
		//処理内容を送信
		ra.addFlashAttribute("status","所持金が"+money+"円に更新されました");
		
		return "redirect:/home";
	}
	
	//商品の追加ボタンの機能
	@PostMapping(value = "/home/edit", params="add")//paramsはbuttonタグのname属性
	public String addList(MerchandiseList merchandiseList, @ModelAttribute Merchandise merchandise, BindingResult result, RedirectAttributes ra, Model model) {
		//所持金の表示
		Integer moneyValue = merchandiseList.getMoney();
		if (moneyValue != null) {
			money = moneyValue.intValue();
		}
		
		//入力をlistに追加
		list.add(merchandise);
		//処理内容を送信
		ra.addAttribute("status", merchandise.getName()+"("+merchandise.getPrice()+"円)"+"が追加されました．");
		return "redirect:/home";
	}
	
	//削除ボタンの機能
	@PostMapping(value = "/home/edit", params="remove")
	public String removeList(RedirectAttributes ra, HttpServletRequest request) {
		//削除ボタンを押した行番号を取得
		int index = Integer.valueOf(request.getParameter("remove"));
		//処理内容を送信
		ra.addAttribute("status", list.get(index).getName()+"("+list.get(index).getPrice()+"円)"+"が削除されました．");
		//削除
		list.remove(index);
		
		return "redirect:/home";
	}
	
}
