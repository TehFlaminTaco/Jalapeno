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
}
