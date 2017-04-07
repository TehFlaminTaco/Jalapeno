package taco.jalapeno.atom.link.links.nilads.literals;

import taco.jalapeno.vars.Var;
import taco.jalapeno.vars.VarList;

public class NiladTerminateString extends NiladLiteralTerminate {
	@Override
	public Var parse(Byte[] b_arr){
		
		Byte[] B = NiladLiteralTerminate.from250toBytes(NiladLiteralTerminate.to250(b_arr));
		byte[] b = new byte[B.length];
		for(int i=0; i<B.length; i++){
			b[i]=B[i];
		}
		return new VarList(new String(b));
	}
}
