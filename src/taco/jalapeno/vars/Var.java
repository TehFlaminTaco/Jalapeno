package taco.jalapeno.vars;

public class Var {

	public VarChar toChar(){
		return null;
	}
	
	public VarList toList(){
		return null;
	}
	
	public VarNumber toNumber(){
		return null;
	}
	
	public boolean truthy(){
		return false;
	}
	
	public VarList toListOrEntry(){
		if(this instanceof VarList){
			return (VarList)this;
		}else{
			VarList out = new VarList();
			out.data.add(this);
			return out;
		}
	}
}
