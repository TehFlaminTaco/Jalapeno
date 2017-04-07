package taco.jalapeno.atom.link;

import taco.jalapeno.vars.Var;

public class NiladicLink extends Link {
	@Override
	public int getarguments(){
		return 0;
	}
	
	@Override
	public Var execute(Var[] arguments){
		return execute();
	}
	
	public Var execute(){return null;}
}
