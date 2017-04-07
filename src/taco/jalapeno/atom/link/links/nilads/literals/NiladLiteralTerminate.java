package taco.jalapeno.atom.link.links.nilads.literals;

import java.math.BigDecimal;
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
			out[i] = to250(b_arr[i]);
		}
		return out;
	}
	
	public static Byte[] from250(int[] b_arr){
		Byte[] out = new Byte[b_arr.length];
		for(int i=0; i < b_arr.length; i++){
			out[i] = from250(b_arr[i]);
		}
		return out;
	}
	
	public static int to250(byte b){
		int o = 0;
		while(b!=0){
			while(NiladLiteral.class.isAssignableFrom(Encoding.get(b)) || NiladLiteralTerminate.class.isAssignableFrom(Encoding.get(b))){
				b--;
			}
			b--;
			o++;
		}
		return o;
	}
	
	public static byte from250(int i){
		byte o = 0;
		while(i>0){
			while(NiladLiteral.class.isAssignableFrom(Encoding.get(o)) || NiladLiteralTerminate.class.isAssignableFrom(Encoding.get(o))){
				o++;
			}
			o++;
			i--;
		}
		return o;
	}
	
	public static int[] fromIntegerToInt(Integer[] i){
		int[] o = new int[i.length];
		for(int I=0; I<i.length; I++){
			o[I]=i[I];
		}
		return o;
	}
	public static Integer[] fromIntToInteger(int[] i){
		Integer[] o = new Integer[i.length];
		for(int I=0; I<i.length; I++){
			o[I]=i[I];
		}
		return o;
	}
	public static byte[] fromByteTobyte(Byte[] i){
		byte[] o = new byte[i.length];
		for(int I=0; I<i.length; I++){
			o[I]=i[I];
		}
		return o;
	}
	public static byte[] frombyteToByte(Byte[] i){
		byte[] o = new byte[i.length];
		for(int I=0; I<i.length; I++){
			o[I]=i[I];
		}
		return o;
	}

	public static int[] fromBaseToBase(int[] arr, int from_base, int to_base){
		ArrayList<Integer> newArr = new ArrayList<Integer>();
		
		BigDecimal b = BigDecimal.ZERO;
		
		for(int i=0; i<arr.length; i++){
			b = b.multiply(new BigDecimal(from_base)).add(new BigDecimal(arr[i]));
		}
		
		while(b.compareTo(BigDecimal.ZERO)==1){
			newArr.add(0,b.remainder(new BigDecimal(to_base)).intValue());
			b = b.divideToIntegralValue(new BigDecimal(to_base));
		}
		Integer[] out_arr = new Integer[newArr.size()];
		return fromIntegerToInt(newArr.toArray(out_arr));
	}
	
	public static Byte[] from250toBytes(int[] arr){
		int[] o = fromBaseToBase(arr, 250, 256);
		Byte[] out = new Byte[o.length];
		for(int i=0; i<o.length; i++){
			out[i]=(byte)o[i];
		}
		return out;
	}
	
	public static Integer[] fromBytesto250(byte[] b){
		int[] i = new int[b.length]; for(int c=0; c<b.length; c++){i[c]=b[c];}
		i=fromBaseToBase(i,256,250);
		Integer[] o = new Integer[i.length]; for(int c=0; c<i.length; c++){o[c]=i[c];}
		return o;
	}
}
