package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;



@Controller
public class CounterController {
	List<Merchandise> list = new ArrayList<Merchandise>();
	Integer money = 0;
	
	@GetMapping(value = "/home")
	public String load(@ModelAttribute MerchandiseList ml, Model model, @ModelAttribute("status")String status) {
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
				 sumPrice += merchandise.getPrice(); 
			}
			model.addAttribute("sum_price","合計金額は" + sumPrice + "円です！");
		}
		
		
		
		return "home";
	}
	
	@PostMapping(value = "/home/edit", params="money_edit")
	public String editMoney(RedirectAttributes ra, @RequestParam(name = "money", required=false)Integer m) {
		//所持金の表示
		money = m;
		//処理内容を送信
		ra.addFlashAttribute("status","所持金が"+m+"円に更新されました");
		
		return "redirect:/home";
	}
	
	//追加ボタンを押下　→　新しい入力項目を表示する
	@PostMapping(value = "/home/edit", params="add")//paramsはbuttonタグのname属性
	public String addList(@ModelAttribute Merchandise merchandise, RedirectAttributes ra) {
		
		//入力をlistに追加
		list.add(merchandise);
		//処理内容を送信
		ra.addAttribute("status", merchandise.getName()+"("+merchandise.getPrice()+"円)"+"が追加されました．");
		return "redirect:/home";
	}
	
	//削除ボタンを押下
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
