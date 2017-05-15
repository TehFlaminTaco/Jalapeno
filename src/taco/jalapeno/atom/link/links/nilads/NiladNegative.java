package taco.jalapeno.atom.link.links.nilads;

import java.math.BigDecimal;

public class NiladNegative extends NiladNumber {
	public BigDecimal getVal(byte b){
		return new BigDecimal(-1);
	}
	
	public BigDecimal append(BigDecimal n){
		return n.negate();
	}
}
