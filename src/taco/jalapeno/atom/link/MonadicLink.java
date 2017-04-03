package taco.jalapeno.atom.link;

import taco.jalapeno.vars.Var;
import taco.jalapeno.vars.VarNumber;

public class MonadicLink extends Link {
	public int getarguments(){
		return 1;
	}
	
	public Var execute(Var[] arguments){
		for(int i=0; i<arguments.length; i++){
			if(arguments[i]==null){
				arguments[i] = new VarNumber();
			}
		}
		if(arguments.length==0){
			return execute(new VarNumber());
		}else{
			return execute(arguments[0]);
		}
	}
	
	public Var execute(Var a){
		return null;
	}
}
