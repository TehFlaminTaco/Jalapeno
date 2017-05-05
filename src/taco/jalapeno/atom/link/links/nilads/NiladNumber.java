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
			toPush.data = ((NiladNumber)chain.remove(chain.size()-1)).append(getVal(b));
		}else{
			toPush.data = toPush.data.add(getVal(b));
		}
		chain.add(this);
	}
	
	public BigDecimal getVal(byte b){
		return new BigDecimal(b - Encoding.getByte('0'));
	}
	
	public BigDecimal append(BigDecimal n){
		return toPush.data.scaleByPowerOfTen(1).add(toPush.data.compareTo(BigDecimal.ZERO)>=0 ? n : n.negate());
	}
	
	@Override
	public Var execute(){
		return toPush;
	}
}
