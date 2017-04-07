package taco.jalapeno.atom.link.links.dyads;

import taco.jalapeno.atom.link.DyadicLink;
import taco.jalapeno.vars.Var;
import taco.jalapeno.vars.VarChar;
import taco.jalapeno.vars.VarList;
import taco.jalapeno.vars.VarNumber;

public class DyadMultiply extends DyadicLink {
	
	@Override
	public Var execute(Var a, Var b){
		if(a instanceof VarList){
			if(b instanceof VarList){
				VarList newList = new VarList();
				VarList lA = a.toList();
				VarList lB = b.toList();
				for(int i=0; i<Math.max(lA.data.size(), lB.data.size()); i++){
					Var out = null;
					if(i < lA.data.size()){
						if(i < lB.data.size()){
							out = execute(lA.data.get(i), lB.data.get(i));
						}else{
							out = lA.data.get(i);
						}
					}else{
						out = lB.data.get(i);
					}
					newList.data.add(out);
				}
				return newList;
			}else{
				VarList newList = new VarList();
				VarList lA = a.toList();
				for(int i=0; i<lA.data.size(); i++){
					newList.data.add(execute(lA.data.get(i), b));
				}
				return newList;
			}
		}else{
			if(b instanceof VarList){
				VarList newList = new VarList();
				VarList lB = b.toList();
				for(int i=0; i<lB.data.size(); i++){
					newList.data.add(execute(a, lB.data.get(i)));
				}
				return newList;
			}else{
				if(a instanceof VarChar || b instanceof VarChar){
					return new VarChar((char) a.toNumber().data.multiply(b.toNumber().data).intValue());
				}else{
					return new VarNumber(a.toNumber().data.multiply(b.toNumber().data));
				}
			}
		}
	}
}
