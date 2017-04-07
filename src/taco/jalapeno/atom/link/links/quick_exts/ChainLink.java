package taco.jalapeno.atom.link.links.quick_exts;

import taco.jalapeno.Chain;
import taco.jalapeno.atom.link.Link;
import taco.jalapeno.compiler.Executer;
import taco.jalapeno.vars.Var;

public class ChainLink extends Link {
	public Chain chain;
	int arguments = 0;
	
	@Override
	public int getarguments(){return arguments;}
	public void setarguments(int i){arguments = i;}
	
	@Override
	public Var execute(Var[] args){
		try {
			return Executer.execute(chain, args)[0];
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
}
