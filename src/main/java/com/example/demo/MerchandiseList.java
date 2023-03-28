package com.example.demo;

import java.util.List;

import lombok.Data;

@Data
public class MerchandiseList {
	private Integer money;
	private List<Merchandise> merchandises;
	
	//新しい記入欄を追加
	public void addList() {
		Merchandise merchandise = new Merchandise();
		merchandises.add(merchandise);
	}
	
	public void removeList(int index) {
		if(merchandises.size() > 1) {
			merchandises.remove(index);
		}
	}
	
}
