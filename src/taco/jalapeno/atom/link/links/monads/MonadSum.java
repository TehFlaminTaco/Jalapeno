package taco.jalapeno.atom.link.links.monads;

import taco.jalapeno.atom.link.MonadicLink;
import taco.jalapeno.atom.link.links.dyads.DyadAdd;
import taco.jalapeno.vars.Var;
import taco.jalapeno.vars.VarList;

public class MonadSum extends MonadicLink {
	
	private DyadAdd add = new DyadAdd();
	
	public Var execute(Var a){
		VarList l = a.toListOrEntry();
		Var out = l.data.get(0);
		for(int i=1; i < l.data.size(); i++){
			out = add.execute(out, l.data.get(i));
		}
		return out;
	}
}
