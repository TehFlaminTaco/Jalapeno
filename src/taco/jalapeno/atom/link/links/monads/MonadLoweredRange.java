package taco.jalapeno.atom.link.links.monads;

import java.math.BigDecimal;

import taco.jalapeno.atom.link.MonadicLink;
import taco.jalapeno.vars.Var;
import taco.jalapeno.vars.VarChar;
import taco.jalapeno.vars.VarList;
import taco.jalapeno.vars.VarNumber;

public class MonadLoweredRange extends MonadicLink {
	public Var execute(Var a){
		VarList out = new VarList();
		if(a instanceof VarNumber){
			for(BigDecimal i = ((VarNumber) a).data; i.compareTo(BigDecimal.ZERO)>0; i=i.subtract(BigDecimal.ONE)){
				out.data.add(0,new VarNumber(i.subtract(BigDecimal.ONE)));
			}
		}else if(a instanceof VarChar){
			for(char i=1; i<((VarChar)a).data; i++){
				out.data.add(new VarChar((char)(i-((char)1))));
			}
		}else{
			for(int i=0; i < ((VarList) a).data.size(); i++){
				out.data.add(this.execute(((VarList) a).data.get(i)));
			}
		}
		
		return out;
	}
}
