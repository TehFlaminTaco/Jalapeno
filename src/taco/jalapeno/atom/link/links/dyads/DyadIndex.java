package taco.jalapeno.atom.link.links.dyads;

import taco.jalapeno.atom.link.DyadicLink;
import taco.jalapeno.vars.Var;
import taco.jalapeno.vars.VarList;

public class DyadIndex extends DyadicLink {
	public Var execute(Var a, Var b){
		VarList aList = a.toList();
		return aList.data.get(b.toNumber().data.intValue() % aList.data.size());
	}
}
