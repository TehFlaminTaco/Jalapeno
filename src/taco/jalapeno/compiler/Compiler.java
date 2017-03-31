package taco.jalapeno.compiler;

import java.util.ArrayList;

import taco.jalapeno.Flags;
import taco.jalapeno.atom.Atom;
import taco.jalapeno.atom.MultiByte;
import taco.jalapeno.atom.generics.AtomTerminateChain;
import taco.jalapeno.atom.link.Link;
import taco.jalapeno.encoding.Encoding;

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
		ArrayList<ArrayList<Link>> chain_chains = new ArrayList<ArrayList<Link>>();
		ArrayList<Link> chain = new ArrayList<Link>();
		
		while(i < bytes.length){
			Class<?> a = (Class<?>) Encoding.get(bytes[i]);
			while(a!=null && MultiByte.class.isAssignableFrom(a)){
				Class<MultiByte> c = (Class<MultiByte>) a;
				a = c.newInstance().get(bytes[++i]);
			}
			if(a==null){
				i++;
				continue;
			}
			
			Atom atom = ((Class<Atom>) a).newInstance();
			
			if(atom instanceof AtomTerminateChain){
				if(chain.size()>0){
					chain_chains.add(chain);
					chain = new ArrayList<Link>();
				}
			}else{
				atom.compile(chain);
			}
			
			i++;
		}
		
		if(chain.size()>0){
			chain_chains.add(chain);
		}
		
		
		return chain_chains;
	}
	
	
}
