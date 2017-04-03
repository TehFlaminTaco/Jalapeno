package taco.jalapeno.atom.link.links.nilads.literals;

import taco.jalapeno.vars.Var;
import taco.jalapeno.vars.VarList;

public class NiladTerminateUgly extends NiladLiteralTerminate {
public Var parse(Byte[] B){
		byte[] b = new byte[B.length];
		for(int i=0; i<B.length; i++){
			b[i]=B[i];
		}
		return new VarList(new String(b));
	}
}
