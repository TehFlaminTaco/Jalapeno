package taco.jalapeno.atom.link.links.nilads.literals;

import java.math.BigDecimal;

import taco.jalapeno.vars.Var;
import taco.jalapeno.vars.VarNumber;

public class NiladTerminateNumberUgly extends NiladLiteralTerminate {
@Override
public Var parse(Byte[] B){
		byte[] b = new byte[B.length];
		for(int i=0; i<B.length; i++){
			b[i]=B[i];
		}
		return new VarNumber(new BigDecimal(new String(b)));
	}
}
