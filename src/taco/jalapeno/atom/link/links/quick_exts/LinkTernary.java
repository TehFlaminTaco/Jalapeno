package taco.jalapeno.atom.link.links.quick_exts;

import taco.jalapeno.atom.link.DyadicLink;
import taco.jalapeno.atom.link.Link;
import taco.jalapeno.vars.Var;

public class LinkTernary extends DyadicLink {

	Link truthy = null;
	Link falsey = null;
	
	public LinkTernary(Link a, Link b) {
		truthy = b;
		falsey = a;
	}
	
	public int getarguments(){
		if(truthy!=null && truthy.getarguments()!=0){
			return 2;
		}
		if(falsey!=null && falsey.getarguments()!=0){
			return 2;
		}
		return 1;
	}
	
	public Var execute(Var a, Var b){
		if(a.truthy()){
			if(a!=null){
				return truthy.execute(new Var[]{b, b});
			}
		}else{
			if(b!=null){
				return falsey.execute(new Var[]{b, b});
			}
		}
		return null;
	}
	
}
