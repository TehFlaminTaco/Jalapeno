package taco.jalapeno.vars;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VarList extends Var {
	public ArrayList<Var> data;
	
	public VarList(ArrayList<Var> l){
		this.data = l;
	}
	
	public boolean truthy(){
		return data.size()!=0;
	}
	
	public VarList(){
		data = new ArrayList<Var>();
	}

	public VarList(String string) {
		ArrayList<Var> l = new ArrayList<Var>();
		for(int i=0; i < string.length(); i++){
			l.add(new VarChar(string.charAt(i)));
		}
		data = l;
	}
	
	public VarList(Var[] lst){
		data = new ArrayList<Var>(Arrays.asList(lst));
	}

	public VarList(List<Var> list) {
		data = new ArrayList<Var>(list);
	}

	public String toString(){
		boolean contains_only_chars = true;
		String cString = "";
		for(Var v : data){
			if(v instanceof VarChar){
				cString += ((VarChar)v).data;
			}else{
				contains_only_chars = false;
				break;
			}
		}
		if(contains_only_chars){
			return cString;
		}else{
			return data.toString();
		}
	}
	
	public VarChar toChar(){
		return this.toNumber().toChar();
	}
	
	public VarNumber toNumber(){
		String s = this.toString();
		VarNumber out = new VarNumber();
		try{out.data = new BigDecimal(s);}finally{} // If it can convert to a number, than do so.
		return out;
	}
	
	public VarList toList(){
		return this;
	}
}
