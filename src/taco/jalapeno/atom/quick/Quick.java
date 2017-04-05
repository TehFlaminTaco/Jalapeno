package taco.jalapeno.atom.quick;

import java.util.ArrayList;

import taco.jalapeno.atom.Atom;
import taco.jalapeno.atom.link.Link;

public class Quick extends Atom {
	public void compile(ArrayList<ArrayList<Link>> chains){
		compile_last(chains.get(chains.size()-1));
	}
	
	public void compile_last(ArrayList<Link> chain){
	}
}
