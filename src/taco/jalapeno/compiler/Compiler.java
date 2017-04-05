package taco.jalapeno.compiler;

import java.util.ArrayList;

import taco.jalapeno.Flags;
import taco.jalapeno.atom.Atom;
import taco.jalapeno.atom.MultiByte;
import taco.jalapeno.atom.generics.AtomTerminateChain;
import taco.jalapeno.atom.link.Link;
import taco.jalapeno.atom.link.links.nilads.literals.NiladLiteral;
import taco.jalapeno.atom.link.links.nilads.literals.NiladLiteralTerminate;
import taco.jalapeno.encoding.Encoding;
import taco.jalapeno.vars.Var;
import taco.jalapeno.vars.VarList;

public class Compiler {
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<ArrayList<Link>> compile(String code, int flags) throws InstantiationException, IllegalAccessException{
		if((flags & Flags.FLAG_TOKEN)!=0){
			code = Encoding.DeTokenize(code, (flags & Flags.FLAG_SURPRESS)!=0);
		}
		if((flags & Flags.FLAG_PRINT_CHARS)!=0){
			System.out.println(Encoding.toCharacters(code));
		}
		
		int i = 0;
		byte[] bytes = code.getBytes();
		if((flags & Flags.FLAG_PRINT_BYTES)!=0){
			System.out.println(new String(bytes));
		}
		ArrayList<ArrayList<Link>> chain_chains = new ArrayList<ArrayList<Link>>();
		ArrayList<Link> chain = new ArrayList<Link>();
		chain_chains.add(chain);
		while(i < bytes.length){
			Class<?> a = (Class<?>) Encoding.get(bytes[i]);
			if(a==null){
				i++;
				continue;
			}
			if(NiladLiteral.class.isAssignableFrom(a)){
				ArrayList<ArrayList<Byte>> bb = new ArrayList<ArrayList<Byte>>();
				ArrayList<Byte> b = new ArrayList<Byte>();
				i++;
				while(i < bytes.length){
					a = (Class<?>) Encoding.get(bytes[i]);
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
					chain = new ArrayList<Link>();
					chain_chains.add(chain);
				}
			}else{
				atom.compile(chain_chains);
			}
			
			i++;
		}
		
		
		return chain_chains;
	}
	
	
}
