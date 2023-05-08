package com.example.demo;

import java.util.List;

import lombok.Data;

@Data
public class MerchandiseList {
	
	private Integer money;
	private List<Merchandise> merchandises;
	
}
