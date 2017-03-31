package taco.jalapeno.atom.link;

import taco.jalapeno.vars.Var;
import taco.jalapeno.vars.VarNumber;

public class DyadicLink extends Link {
	public static final int arguments = 2;
	
	public void execute(Var[] arguments){
		if(arguments.length==0){
			execute(new VarNumber(), new VarNumber());
			return;
		}
		if(arguments.length==1){
			execute(arguments[0], arguments[0]);
			return;
		}
		execute(arguments[0],arguments[1]);
	}
	
	public void execute(Var a, Var b){
		
	}
}
