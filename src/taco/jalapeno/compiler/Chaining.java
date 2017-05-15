package taco.jalapeno.compiler;

import taco.jalapeno.Chain;

public class Chaining {
	
	public static final String nilads = "çñÇÑ";
	public static final String monads = "abcd";
	public static final String dyads = "+×÷-";
	
	public static String getString(Chain chn){
		String str = "";
		int i = 0;
		
		int n = 0;
		int m = 0;
		int d = 0;
		
		for(;i < chn.size() && n < nilads.length() && m < monads.length() && d < dyads.length(); i++){
			int a = chn.get(i).getarguments();
			if(a == 0){
				str += nilads.substring(n++, n);
			}
			if(a == 1){
				str += monads.substring(m++,  m);
			}
			if(a == 2){
				str += dyads.substring(d++, d);
			}
		}
		
		return str;
		
	}
}
