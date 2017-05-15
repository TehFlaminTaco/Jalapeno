package taco.jalapeno.atom.link.links.dyads;

import java.math.BigDecimal;
import taco.jalapeno.atom.link.DyadicLink;
import taco.jalapeno.vars.Var;
import taco.jalapeno.vars.VarList;
import taco.jalapeno.vars.VarNumber;

public class DyadSplit extends DyadicLink {
	public Var execute(Var a, Var b){
		if(a instanceof VarList){
			VarList lists = new VarList();
			VarList asList = (VarList) a;
			int size = asList.data.size();
			int inc = b.toNumber().data.intValue();
			for(int i = 0; i < size; i += inc){
				lists.data.add(new VarList((asList.data.subList(i, Math.min(i+inc, size)))));
			}
			return lists;
		}else{
			VarList out = new VarList();
			BigDecimal n = a.toNumber().data;
			BigDecimal m = b.toNumber().data;
			while(n.compareTo(BigDecimal.ZERO)>0){
				BigDecimal[] o = n.divideAndRemainder(m);
				n = o[0];
				out.data.add(0,new VarNumber(o[1]));
			}
			return out;
		}
	}
}
