package taco.jalapeno.atom.link.links.monads;

import java.math.BigDecimal;

import taco.jalapeno.atom.link.MonadicLink;
import taco.jalapeno.vars.Var;
import taco.jalapeno.vars.VarChar;
import taco.jalapeno.vars.VarList;
import taco.jalapeno.vars.VarNumber;

public class MonadHalf extends MonadicLink {
	@Override
	public Var execute(Var t){
		if(t instanceof VarList){
			VarList newlist = new VarList();
			VarList vl = t.toList();
			for(int i=0; i<vl.data.size()/2; i++){
				newlist.data.add(vl.data.get(i));
			}
			return newlist;
		}else if(t instanceof VarChar){
			return new VarChar((char)(t.toChar().data/2));
		}
		return new VarNumber(t.toNumber().data.divide(new BigDecimal(2)));
	}
}
