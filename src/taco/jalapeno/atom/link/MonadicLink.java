package taco.jalapeno.atom.link;

import taco.jalapeno.vars.Var;
import taco.jalapeno.vars.VarNumber;

public class MonadicLink extends Link {
	public static final int arguments = 1;
	
	public void execute(Var[] arguments){
		execute(new VarNumber());
	}
	
	public void execute(Var a){
		
	}
}
