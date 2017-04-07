package taco.jalapeno.atom.quick;

import java.util.ArrayList;

import taco.jalapeno.Chain;
import taco.jalapeno.atom.Atom;

public class Quick extends Atom {
	@Override
	public void compile(ArrayList<Chain> chains){
		compile_last(chains.get(chains.size()-1));
	}
	
	public void compile_last(Chain chain){
	}
}
