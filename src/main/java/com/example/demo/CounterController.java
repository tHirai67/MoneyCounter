package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
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
	public String load(@ModelAttribute MerchandiseList ml, @ModelAttribute Merchandise m, Model model, @ModelAttribute("edit_money")String edit_money, @ModelAttribute("edit_list")String edit_list, @ModelAttribute("error")String error) {
		if(money == 0) {
			model.addAttribute("money", "所持金を入力してください");
		}else {
			model.addAttribute("money", "所持金:" + money + "円です");
		}
		//所持金の変更ステータスを表示
		model.addAttribute("edit_money", edit_money);
		//商品リストの変更ステータスを表示
		model.addAttribute("edit_list", edit_list);
	
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
	@PostMapping(value = "/home/money_edit", params="money_edit")
	public String editMoney(@ModelAttribute @Validated MerchandiseList merchandiseList, BindingResult result, RedirectAttributes ra, Model model) {
		//エラーチェック
		if(result.hasErrors()) {
			List<String> errors = new ArrayList<>();
			for(ObjectError error : result.getAllErrors()) {
				errors.add(error.getDefaultMessage());
			}
			ra.addFlashAttribute("ErrorMoney", errors);
			return "redirect:/home";
		}
		
		//所持金の表示
		Integer moneyValue = merchandiseList.getMoney();
		if (moneyValue != null) {
		    money = moneyValue.intValue();
		}
		//処理内容を送信
		ra.addFlashAttribute("edit_money","所持金が"+money+"円に更新されました");
		
		return "redirect:/home";
	}
	
	//商品の追加ボタンの機能
	@PostMapping(value = "/home/edit_merchandiseList", params="add")//paramsはbuttonタグのname属性
	public String addList(@ModelAttribute @Validated Merchandise merchandise, BindingResult result, RedirectAttributes ra, Model model) {
		if(result.hasErrors()) {
			List<String> errors = new ArrayList<>();
			for(ObjectError error : result.getAllErrors()) {
				errors.add(error.getDefaultMessage());
			}
			ra.addFlashAttribute("ErrorMerchandise", errors);
			return "redirect:/home";
		}
		
		//入力をlistに追加
		list.add(merchandise);
		//処理内容を送信
		ra.addAttribute("edit_list", merchandise.getName()+"("+merchandise.getPrice()+"円)"+"が追加されました．");
		return "redirect:/home";
	}
	
	//削除ボタンの機能
	@PostMapping(value = "/home/edit_merchandiseList", params="remove")
	public String removeList(RedirectAttributes ra, HttpServletRequest request) {
		//削除ボタンを押した行番号を取得
		int index = Integer.valueOf(request.getParameter("remove"));
		//処理内容を送信
		ra.addAttribute("edit_list", list.get(index).getName()+"("+list.get(index).getPrice()+"円)"+"が削除されました．");
		//削除
		list.remove(index);
		
		return "redirect:/home";
	}
	
}
