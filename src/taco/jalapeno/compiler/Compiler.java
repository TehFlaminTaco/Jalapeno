package taco.jalapeno.compiler;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.ArrayList;

import taco.jalapeno.Chain;
import taco.jalapeno.Flags;
import taco.jalapeno.atom.Atom;
import taco.jalapeno.atom.MultiByte;
import taco.jalapeno.atom.generics.AtomTerminateChain;
import taco.jalapeno.atom.link.links.nilads.literals.NiladLiteral;
import taco.jalapeno.atom.link.links.nilads.literals.NiladLiteralTerminate;
import taco.jalapeno.encoding.Encoding;
import taco.jalapeno.vars.Var;
import taco.jalapeno.vars.VarList;

public class Compiler {
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<Chain> compile(byte[] bs, int flags) throws InstantiationException, IllegalAccessException{
		if((flags & Flags.FLAG_PRODUCE_STRING)!=0){
			int[] encoded = NiladLiteralTerminate.fromIntegerToInt(
					NiladLiteralTerminate.fromBytesto250(bs)
					);
			Byte[] Bnewstr = NiladLiteralTerminate.from250(encoded);
			
			byte[] newstr = new byte[encoded.length];
			for(int i=0; i<encoded.length; i++){
				newstr[i] = Bnewstr[i];
			}
			System.out.println(new String(newstr));
			System.out.println(Encoding.toCharacters(newstr));
			return new ArrayList<Chain>();
		}
		
		if((flags & Flags.FLAG_PRODUCE_NUMBER)!=0){
			String numb_s = new String(bs, Charset.forName("UTF-8"));
			BigDecimal big_n = new BigDecimal(numb_s);
			ArrayList<Integer> encoded = new ArrayList<Integer>();
			while(big_n.compareTo(BigDecimal.ZERO)>0){
				BigDecimal[] dnr = big_n.divideAndRemainder(new BigDecimal(250));
				encoded.add(0,dnr[1].intValue());
				big_n = dnr[0];
			}
			Integer[] encoded_raw = new Integer[encoded.size()];
			encoded_raw = encoded.toArray(encoded_raw);
			int[] encoded_raw_primitive = NiladLiteralTerminate.fromIntegerToInt(encoded_raw);
			byte[] bytes = NiladLiteralTerminate.fromByteTobyte(NiladLiteralTerminate.from250(encoded_raw_primitive));
			System.out.println(new String(bytes));
			System.out.println(new String(bytes, Charset.forName("UTF-8")));
			System.out.println(new String(Encoding.toCharacters(bytes)));
			return new ArrayList<Chain>();
		}
		
		if((flags & Flags.FLAG_TOKEN)!=0){
			bs = Encoding.DeTokenize(new String(bs, Charset.forName("UTF-8")), (flags & Flags.FLAG_SURPRESS)!=0);
		}
		if((flags & Flags.FLAG_UTF8)!=0){
			bs = Encoding.DeCharacterize(new String(bs, Charset.forName("UTF-8")), (flags & Flags.FLAG_SURPRESS)!=0);
		}
		if((flags & Flags.FLAG_PRINT_CHARS)!=0){
			System.out.println(Encoding.toCharacters(bs));
		}
		
		int i = 0;
		byte[] bytes = bs;
		if((flags & Flags.FLAG_PRINT_BYTES)!=0){
			System.out.println(new String(bytes));
		}
		ArrayList<Chain> chain_chains = new ArrayList<Chain>();
		Chain chain = new Chain();
		chain_chains.add(chain);
		while(i < bytes.length){
			Class<?> a = Encoding.get(bytes[i]);
			if(a==null){
				i++;
				continue;
			}
			if(NiladLiteral.class.isAssignableFrom(a)){
				ArrayList<ArrayList<Byte>> bb = new ArrayList<ArrayList<Byte>>();
				ArrayList<Byte> b = new ArrayList<Byte>();
				i++;
				while(i < bytes.length){
					a = Encoding.get(bytes[i]);
					if(NiladLiteralTerminate.class.isAssignableFrom(a)){
						bb.add(b);
						ArrayList<Var> lst = new ArrayList<Var>();
						for(ArrayList<Byte> B : bb){
							Byte[] b_arr = new Byte[B.size()];
							b_arr = B.toArray(b_arr);
							lst.add(((NiladLiteralTerminate) a.newInstance()).parse(b_arr));
						}
						if(lst.size()==1){
							chain.add(new NiladLiteral(lst.get(0)));
						}else if(lst.size()>1){
							chain.add(new NiladLiteral(new VarList(lst)));
						}
						i++;
						break;
					}else if(NiladLiteral.class.isAssignableFrom(a)){
						bb.add(b);
						b = new ArrayList<Byte>();
					}else{
						b.add(bytes[i]);
					}
					i++;
				}
				continue;
			}
			while(a!=null && MultiByte.class.isAssignableFrom(a)){
				Class<MultiByte> c = (Class<MultiByte>) a;
				a = c.newInstance().get(bytes[++i]);
			}
			
			Atom atom = ((Class<Atom>) a).newInstance();
			
			if(atom instanceof AtomTerminateChain){
				if(chain.size()>0){
					chain = new Chain();
					chain_chains.add(chain);
				}
			}else{
				atom.compile(chain_chains, bytes[i]);
			}
			
			i++;
		}
		
		
		return chain_chains;
	}
	
	
}
