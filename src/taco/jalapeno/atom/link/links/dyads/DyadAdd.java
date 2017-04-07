package taco.jalapeno.atom.link.links.dyads;

import taco.jalapeno.atom.link.DyadicLink;
import taco.jalapeno.vars.*;

public class DyadAdd extends DyadicLink {
	@Override
	public Var execute(Var a, Var b){
		if(a instanceof VarList || b instanceof VarList){
			VarList newList = new VarList();
			newList.data.addAll(a.toListOrEntry().data);
			newList.data.addAll(b.toListOrEntry().data);
			return newList;
		}
		if(a instanceof VarChar){
			return new VarChar((char) a.toNumber().data.add(b.toNumber().data).intValue());
		}else{
			return new VarNumber(a.toNumber().data.add(b.toNumber().data));
		}
	}
}
