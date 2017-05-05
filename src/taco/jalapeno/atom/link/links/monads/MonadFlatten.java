package taco.jalapeno.atom.link.links.monads;

import taco.jalapeno.atom.link.MonadicLink;
import taco.jalapeno.vars.Var;
import taco.jalapeno.vars.VarList;

public class MonadFlatten extends MonadicLink {
	public Var execute(Var a){
		VarList out = new VarList();
		
		VarList inp = a.toListOrEntry();
		
		for(int i=0; i<inp.data.size(); i++){
			Var b = inp.data.get(i);
			if(b instanceof VarList){
				VarList flattened = (VarList) execute(b);
				out.data.addAll(flattened.data);
			}else{
				out.data.add(b);
			}
		}
		
		return out;
	}
}
