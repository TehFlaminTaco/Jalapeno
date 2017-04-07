package taco.jalapeno.atom.link.links.nilads.literals;

import taco.jalapeno.encoding.StringCompressor;
import taco.jalapeno.vars.Var;
import taco.jalapeno.vars.VarList;

public class NiladTerminateCompressed extends NiladLiteralTerminate {
	@Override
	public Var parse(Byte[] b_arr){
		Byte[] B = NiladLiteralTerminate.from250toBytes(NiladLiteralTerminate.to250(b_arr));
		byte[] bytes = new byte[B.length];for(int i=0;i<B.length;i++){bytes[i]=B[i];}
		return new VarList(StringCompressor.decompress(bytes));
	}
}
