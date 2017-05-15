package taco.jalapeno.atom.link.links.monads;

import taco.jalapeno.atom.link.MonadicLink;
import taco.jalapeno.vars.Var;
import taco.jalapeno.vars.VarList;

public class MonadBinary extends MonadicLink {
	public Var execute(Var a){
		return new VarList(a.toNumber().data.toBigInteger().toString(2));
	}
}
