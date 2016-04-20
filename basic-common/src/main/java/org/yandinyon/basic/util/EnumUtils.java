package org.yandinyon.basic.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

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
	
	public static List<String> enumProp2List(Class<? extends Enum> clz, String propName) {
		try {
			Enum[] enums = clz.getEnumConstants();
			List<String> list = new ArrayList<String>();
			for (Enum en : enums) {
				list.add((String) PropertyUtils.getProperty(en, propName));
			}
			return list;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Map<Integer,String> enumProp2OrdinalMap(Class<? extends Enum> clz, String propName) {
		try {
			Enum[] enums = clz.getEnumConstants();
			Map<Integer,String> map = new HashMap<Integer,String>();
			for (Enum en : enums) {
				map.put(en.ordinal(),(String) PropertyUtils.getProperty(en, propName));
			}
			return map;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Map<String,String> enumProp2Map(Class<? extends Enum> clz,String keyProp, String valueProp) {
		try {
			Enum[] enums = clz.getEnumConstants();
			Map<String,String> map = new HashMap<String,String>();
			for (Enum en : enums) {
				map.put((String) PropertyUtils.getProperty(en, keyProp),(String) PropertyUtils.getProperty(en, valueProp));
			}
			return map;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Map<String,String> enumProp2NameMap(Class<? extends Enum> clz, String propName) {
		try {
			Enum[] enums = clz.getEnumConstants();
			Map<String,String> map = new HashMap<String,String>();
			for (Enum en : enums) {
				map.put(en.name(),(String) PropertyUtils.getProperty(en, propName));
			}
			return map;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}

}
