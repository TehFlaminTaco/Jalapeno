package taco.jalapeno.atom.link;

import java.util.ArrayList;

import taco.jalapeno.Chain;
import taco.jalapeno.atom.Atom;
import taco.jalapeno.vars.Var;

public class Link extends Atom {
	@Override
	public void compile(ArrayList<Chain> chains){
		chains.get(chains.size()-1).add(this);
	}
	
	public int getarguments(){
		return -1;
	}
	
	public Var execute(Var[] arguments){
		return null;
	}
}
