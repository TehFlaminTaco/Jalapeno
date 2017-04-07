package taco.jalapeno.atom.link.links.nilads;

import taco.jalapeno.atom.link.NiladicLink;
import taco.jalapeno.vars.Var;
import taco.jalapeno.vars.VarList;

public class NiladHello extends NiladicLink {
	@Override
	public Var execute(){
		return new VarList("Hello, World!");
	}
}
