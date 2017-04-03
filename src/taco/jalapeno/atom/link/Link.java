package taco.jalapeno.atom.link;

import java.util.ArrayList;

import taco.jalapeno.atom.Atom;
import taco.jalapeno.vars.Var;

public class Link extends Atom {
	public void compile(ArrayList<Link> chain){
		chain.add(this);
	}
	
	public int getarguments(){
		return -1;
	}
	
	public Var execute(Var[] arguments){
		return null;
	}
}
