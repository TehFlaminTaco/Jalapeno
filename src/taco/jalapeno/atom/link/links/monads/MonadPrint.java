package taco.jalapeno.atom.link.links.monads;

import taco.jalapeno.atom.link.MonadicLink;
import taco.jalapeno.vars.Var;

public class MonadPrint extends MonadicLink {
	public Var execute(Var input){
		System.out.println(input);
		return input;
	}
}
