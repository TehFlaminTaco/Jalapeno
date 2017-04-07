package taco.jalapeno.vars;

import java.math.BigDecimal;

public class VarChar extends Var {
	public char data = 0;
	
	public boolean truthy(){
		return data!=0;
	}
	
	public VarChar(char a){
		this.data = a;
	}
	
	public String toString(){
		return new String(new char[]{data});
	}
	
	public VarChar toChar(){
		return this;
	}
	
	public VarNumber toNumber(){
		return new VarNumber(new BigDecimal(data));
	}
	
	public VarList toList(){
		VarList out = new VarList();
		out.data.add(this);
		return out;
	}
}
