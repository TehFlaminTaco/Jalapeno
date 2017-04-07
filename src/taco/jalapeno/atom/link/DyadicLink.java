package taco.jalapeno.atom.link;

import taco.jalapeno.vars.Var;
import taco.jalapeno.vars.VarNumber;

public class DyadicLink extends Link {
	@Override
	public int getarguments(){
		return 2;
	}
	
	@Override
	public Var execute(Var[] arguments){
		for(int i=0; i<arguments.length; i++){
			if(arguments[i]==null){
				arguments[i] = new VarNumber();
			}
		}
		if(arguments.length==0){
			return execute(new VarNumber(), new VarNumber());
		}
		if(arguments.length==1){
			return execute(arguments[0], arguments[0]);
		}
		return execute(arguments[0],arguments[1]);
	}
	
	public Var execute(Var a, Var b){
		return null;
	}
}
