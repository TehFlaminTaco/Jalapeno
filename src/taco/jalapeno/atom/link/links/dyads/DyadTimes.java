package taco.jalapeno.atom.link.links.dyads;

import java.math.BigDecimal;

import taco.jalapeno.atom.link.DyadicLink;
import taco.jalapeno.vars.Var;
import taco.jalapeno.vars.VarChar;
import taco.jalapeno.vars.VarList;
import taco.jalapeno.vars.VarNumber;

public class DyadTimes extends DyadicLink {
	public Var execute(Var a, Var b){
		VarList out = new VarList();
		if(b instanceof VarNumber || b instanceof VarChar){
			for(BigDecimal i=BigDecimal.ZERO; i.compareTo(b.toNumber().data)<0; i=i.add(BigDecimal.ONE)){
				out.data.add(a);
			}
		}
		return out;
	}
}
