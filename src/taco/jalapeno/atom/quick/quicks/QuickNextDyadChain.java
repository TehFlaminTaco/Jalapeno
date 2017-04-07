package taco.jalapeno.atom.quick.quicks;

import java.util.ArrayList;

import taco.jalapeno.Chain;
import taco.jalapeno.atom.link.DyadicLink;
import taco.jalapeno.compiler.Executer;
import taco.jalapeno.vars.Var;

public class QuickNextDyadChain extends DyadicLink {
	ArrayList<Chain> chains = null;
	int position = 0;
	@Override
	public void compile(ArrayList<Chain> chains){
		super.compile(chains);
		this.chains = chains;
		position = chains.size()-1;
	}
	
	@Override
	public Var execute(Var[] args){
		Var[] out = new Var[0];
		try {
			out = Executer.execute(chains.get(position+1 % chains.size()), args);
		} catch (Exception e) {}
		return out.length>0 ? out[0] : null;
	}
}
