package taco.jalapeno.vars;

import java.math.BigDecimal;
import java.util.ArrayList;

public class VarNumber extends Var {
	BigDecimal data = BigDecimal.ZERO;
	
	public VarNumber(BigDecimal n) {
		data = n;
	}

	public VarNumber() {
	}

	public String toString(){
		return data.toString();
	}
	
	public VarChar toChar(){
		return new VarChar((char)data.intValue());
	}
	
	public VarList toList(){
		String s = data.toPlainString();
		ArrayList<Var> n = new ArrayList<Var>();
		for(char a : s.toCharArray()){
			n.add(new VarChar(a));
		}
		return new VarList(n);
	}
	
	public VarNumber toNumber(){
		return this;
	}
}
