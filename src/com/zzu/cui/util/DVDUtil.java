package com.zzu.cui.util;

public class DVDUtil {
	//ÅĞ¶Ï×Ö·û´®ÖĞÊÇ·ñÊÇÊı×Ö
	public static boolean isNumber(String str){
		for(int i=str.length();--i>0;){
			if(!Character.isDigit(str.charAt(i))){
				return false;
			}
		}
		return true;
	}

}
