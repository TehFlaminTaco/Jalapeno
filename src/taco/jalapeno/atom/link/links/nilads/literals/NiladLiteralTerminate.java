package taco.jalapeno.atom.link.links.nilads.literals;

import java.util.ArrayList;

import taco.jalapeno.atom.link.NiladicLink;
import taco.jalapeno.encoding.Encoding;
import taco.jalapeno.vars.Var;

// Do nothing, but allow extending, so we can know what terminates.
public class NiladLiteralTerminate extends NiladicLink {
	public Var parse(Byte[] b_arr){
		return null;
	}
	
	public static int[] to250(Byte[] b_arr){
		int[] out = new int[b_arr.length];
		for(int i=0; i < b_arr.length; i++){
			byte b = b_arr[i];
			int o = 0;
			while(b!=0){
				while(NiladLiteral.class.isAssignableFrom(Encoding.get(b)) || NiladLiteralTerminate.class.isAssignableFrom(Encoding.get(b))){
					b--;
				}
				b--;
				o++;
			}
			out[i] = o;
		}
		return out;
	}
	
	public static Byte[] from250toBytes(int[] arr){
		ArrayList<Byte> b = new ArrayList<Byte>();
		int c = 0;
		int n = 0;
		int i = arr.length-1;
		while(i>=0){
			n += arr[i]*Math.pow(256, c++);
			while(n>=256){
				b.add(0, (byte)(n%256));
				n = Math.floorDiv(n, 256);
				c--;
			}
			i--;
		}
		b.add(0,(byte)n);
		Byte[] out = new Byte[b.size()];
		out = b.toArray(out);
		return out;
	}
}
