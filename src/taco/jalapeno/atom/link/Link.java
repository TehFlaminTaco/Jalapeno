package taco.jalapeno.atom.link;

import java.util.ArrayList;

import taco.jalapeno.atom.Atom;
import taco.jalapeno.vars.Var;

public class Link extends Atom {
	public static final int arguments = -1;
	
	public void compile(ArrayList<Link> chain){
		chain.add(this);
	}
	
	public void execute(Var[] arguments){
		
	}
}
