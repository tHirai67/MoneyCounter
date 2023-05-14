package com.example.demo;

import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MerchandiseList {
	@NotNull(message = "所持金が入力されていません")
	@Min(value = 0, message = "所持金は0円以上を入力してください")
	private Integer money;
	private List<Merchandise> merchandises;
	
}
