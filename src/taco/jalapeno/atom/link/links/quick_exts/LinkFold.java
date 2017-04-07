package taco.jalapeno.atom.link.links.quick_exts;

import taco.jalapeno.atom.link.Link;
import taco.jalapeno.atom.link.MonadicLink;
import taco.jalapeno.vars.Var;
import taco.jalapeno.vars.VarList;
import taco.jalapeno.vars.VarNumber;

public class LinkFold extends MonadicLink {
	Link link = null;
	public LinkFold setlink(Link l){link=l;return this;}
	
	@Override
	public Var execute(Var[] args){
		if(args.length==0 || !(args[0] instanceof VarList)){
			return null;
		}
		VarList oldlist = (VarList) args[0];
		VarList newlist = new VarList();
		
		for(int i=0; i<oldlist.data.size()-(link.getarguments()-1); i++){ // the link.getarguments part here is fun, because if it returns 2, then it will be size-1, if it returns 1, then it returns size. Which conveniently gives us just the size we need!
			newlist.data.add(link.execute(new Var[]{oldlist.data.get(i), 
													link.getarguments()==2 ? oldlist.data.get(i+1) : new VarNumber()})); // Rather wasteful, after we did that super cool thing before... :(
		}
		
		return newlist;
	}
}
