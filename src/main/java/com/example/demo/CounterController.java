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
	
	
	@GetMapping(value = "/home")
	public String load(@ModelAttribute MerchandiseList ml, Model model) {
		List<Merchandise> list = new ArrayList<Merchandise>();
		Merchandise merchandise = new Merchandise();
		list.add(merchandise);
		ml.setMerchandises(list);
		model.addAttribute("m1", "所持金を入力してください");
		
		return "home";
	}
	
	@PostMapping(value = "/home/edit", params="money_edit")
	public String editMoney(@ModelAttribute MerchandiseList ml, Model model, @RequestParam("money")Integer money) {
		model.addAttribute("m2", "所持金は"+money+"円です");
		return "home";
	}
	
	//追加ボタンを押下　→　新しい入力項目を表示する
	@PostMapping(value = "/home/edit", params="add")//paramsはbuttonタグのname属性
	public String addList(@ModelAttribute MerchandiseList ml, Model model, @RequestParam("money")Integer money) {
		ml.addList();
		model.addAttribute("m2", "所持金は"+money+"円です");
		return "home";
	}
	
	//削除ボタンを押下
	@PostMapping(value = "/home/edit", params="remove")
	public String removeList(@ModelAttribute MerchandiseList ml, Model model, HttpServletRequest request, @RequestParam("money")Integer money) {
		//削除ボタンを押した行番号を取得
		int index = Integer.valueOf(request.getParameter("remove"));
		//削除
		ml.removeList(index);
		model.addAttribute("m2", "所持金は"+money+"円です");
		return "home";
	}
	
	
	
	
	
	
	
}
