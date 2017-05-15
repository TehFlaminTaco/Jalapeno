package taco.jalapeno.atom.link.links.quick_exts;

import taco.jalapeno.atom.link.DyadicLink;
import taco.jalapeno.atom.link.Link;
import taco.jalapeno.vars.Var;

public class LinkFlip extends DyadicLink {
	public DyadicLink curLink;
	public Link setlink(Link top) {
		if(!(top instanceof DyadicLink)){
			return this;
		}
		curLink = (DyadicLink) top;
		return this;
	}
	
	public Var execute(Var a, Var b){
		return curLink.execute(b, a);
	}

}
