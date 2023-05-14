package com.example.demo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Merchandise {
	@NotBlank(message = "商品名が入力されていません")
	private String name;	//商品名
	
	@NotNull(message = "価格が入力されていません")
	@Min(value = 0, message = "価格は0円以上を入力してください")
	private Integer price;	//価格
}
