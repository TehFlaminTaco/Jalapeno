package taco.jalapeno.atom.link.links.nilads.literals;

import java.util.ArrayList;

import taco.jalapeno.atom.link.Link;
import taco.jalapeno.atom.link.NiladicLink;
import taco.jalapeno.vars.Var;


public class NiladLiteral extends NiladicLink {
	Var data;
	
	public NiladLiteral(Var dat){
		this.data = dat;
	}
	
	public Var execute(){
		return data;
	}
}
