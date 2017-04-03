package taco.jalapeno.vars;

import java.util.ArrayList;

public class VarList extends Var {
	ArrayList<Var> data;
	
	public VarList(ArrayList<Var> l){
		this.data = l;
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
}
