package taco.jalapeno.atom.link.links.quick_exts;

import taco.jalapeno.atom.link.Link;
import taco.jalapeno.atom.link.MonadicLink;
import taco.jalapeno.vars.Var;
import taco.jalapeno.vars.VarList;

public class LinkReduce extends MonadicLink {
	Link link = null;
	public LinkReduce setlink(Link l){link=l;return this;}
	
	@Override
	public Var execute(Var[] args){
		if(args.length==0 || !(args[0] instanceof VarList)){
			return null;
		}
		VarList oldlist = (VarList) args[0];
		Var out = oldlist.data.size()>0 ? oldlist.data.get(0) : null;
		
		for(int i=1; i<oldlist.data.size(); i++){
			out = link.execute(new Var[]{out,
										 oldlist.data.get(i)});
		}
		
		return out;
	}
}
