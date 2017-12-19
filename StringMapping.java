package com.Kattis.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StringMapping {

	public static void main(String[] args) {

		
		Scanner sc = new Scanner(System.in);
		String test = sc.nextLine();
		
		HashMap<String, String> alphabetMap = new HashMap<String, String>();
		
		alphabetMap.put("a", "@");
		alphabetMap.put("b", "8");
		alphabetMap.put("c", "(");
		alphabetMap.put("d", "|)");
		alphabetMap.put("e", "3");
		alphabetMap.put("f", "#");
		alphabetMap.put("g", "6");
		alphabetMap.put("h", "[-]");
		alphabetMap.put("i", "|");
		alphabetMap.put("j", "_|");
		alphabetMap.put("k", "|<");
		alphabetMap.put("l", "1");
		alphabetMap.put("m", "[]\\/[]");
		alphabetMap.put("n", "[]\\[]");
		alphabetMap.put("o", "0");
		alphabetMap.put("p", "|D");
		alphabetMap.put("q", "(,)");
		alphabetMap.put("r", "|Z");
		alphabetMap.put("s", "$");
		alphabetMap.put("t", "']['");
		alphabetMap.put("u", "|_|");
		alphabetMap.put("v", "\\/");
		alphabetMap.put("w", "\\/\\/");
		alphabetMap.put("x", "}{");
		alphabetMap.put("y", "`/");
		alphabetMap.put("z", "2");

		System.out.println(test);
		String[] chars = test.toLowerCase().split("");
		List<String> charList = Arrays.asList(chars);	
				
		List<String> result = new ArrayList<>();
		charList.forEach(x -> {
			if(alphabetMap.containsKey(x)) {
				result.add(alphabetMap.get(x));
			}
			else {
				result.add(x);
			}
		});
		
		String r = result.stream().collect(Collectors.joining(""));
		
		System.out.println(r);
		

	}

	
	

}
