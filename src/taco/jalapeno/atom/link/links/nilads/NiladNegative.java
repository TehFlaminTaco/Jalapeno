package taco.jalapeno.atom.link.links.nilads;

import java.math.BigDecimal;
import java.util.ArrayList;

import taco.jalapeno.Chain;
import taco.jalapeno.atom.link.NiladicLink;
import taco.jalapeno.encoding.Encoding;
import taco.jalapeno.vars.Var;
import taco.jalapeno.vars.VarNumber;

public class NiladNegative extends NiladNumber {
	public BigDecimal getVal(byte b){
		return new BigDecimal(-1);
	}
	
	public BigDecimal append(BigDecimal n){
		return n.negate();
	}
}
