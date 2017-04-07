package taco.jalapeno.atom.link.links.nilads;

import java.math.BigDecimal;
import java.util.ArrayList;

import taco.jalapeno.Chain;
import taco.jalapeno.atom.link.NiladicLink;
import taco.jalapeno.encoding.Encoding;
import taco.jalapeno.vars.Var;
import taco.jalapeno.vars.VarNumber;

public class NiladNumber extends NiladicLink {
	VarNumber toPush;
	
	@Override
	public void compile(ArrayList<Chain> chains, byte b){ // I'm rather proud of this.
		Chain chain = chains.get(chains.size()-1);
		toPush = new VarNumber();
		if(chain.size()>0 && chain.get(chain.size()-1) instanceof NiladNumber){
			toPush.data = ((NiladNumber)chain.remove(chain.size()-1)).toPush.data.scaleByPowerOfTen(1);
		}
		toPush.data = toPush.data.add(new BigDecimal(b - Encoding.getByte('0')));
		chain.add(this);
	}
	
	@Override
	public Var execute(){
		return toPush;
	}
}
