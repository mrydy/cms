package org.yandinyon.basic.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class EnumUtils {

	public static List<Integer> enum2Ordinal(Class<? extends Enum> clz){
		if(!clz.isEnum()) return null;
		Enum[] enums = clz.getEnumConstants();
		List<Integer> list = new ArrayList<Integer>();
		for(Enum en : enums){
			list.add(en.ordinal());
		}
		return list;
	}
	
	public static List<String> enum2Name(Class<? extends Enum> clz){
		if(!clz.isEnum()) return null;
		Enum[] enums = clz.getEnumConstants();
		List<String> list = new ArrayList<String>();
		for(Enum en : enums){
			list.add(en.name());
		}
		return list;
	}
	
	public static Map<Integer,String> enum2BasicMap(Class<? extends Enum> clz){
		if(!clz.isEnum()) return null;
		Enum[] enums = clz.getEnumConstants();
		Map<Integer,String> map = new HashMap<Integer,String>();
		for(Enum en : enums){
			map.put(en.ordinal(),en.name());
		}
		return map;
	}
	
}
