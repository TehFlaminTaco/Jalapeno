package taco.jalapeno.atom.link.links.monads;

import taco.jalapeno.atom.link.MonadicLink;
import taco.jalapeno.atom.link.links.dyads.DyadMultiply;
import taco.jalapeno.vars.Var;
import taco.jalapeno.vars.VarList;

public class MonadProduct extends MonadicLink {
	
	private DyadMultiply multiply = new DyadMultiply();
	
	public Var execute(Var a){
		VarList l = a.toListOrEntry();
		Var out = l.data.get(0);
		for(int i=1; i < l.data.size(); i++){
			out = multiply.execute(out, l.data.get(i));
		}
		return out;
	}
}
