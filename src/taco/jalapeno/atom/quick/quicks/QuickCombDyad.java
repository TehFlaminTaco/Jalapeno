package taco.jalapeno.atom.quick.quicks;

import java.util.ArrayList;

import taco.jalapeno.atom.link.Link;
import taco.jalapeno.atom.link.links.quick_exts.ChainLink;
import taco.jalapeno.atom.quick.Quick;

public class QuickCombDyad extends Quick {
	public void compile(ArrayList<ArrayList<Link>> chains){
		ArrayList<Link> chain = chains.get(chains.size()-1);
		ArrayList<Link> micro_chain = new ArrayList<Link>();
		Link a = chain.remove(chain.size()-1);
		Link b = chain.remove(chain.size()-1);
		micro_chain.add(b);
		micro_chain.add(a);
		ChainLink chain_link = new ChainLink();
		chain_link.setarguments(2);
		ArrayList<ArrayList<Link>> micro_holder = new ArrayList<ArrayList<Link>>();
		micro_holder.add(micro_chain);
		chain_link.chain = micro_holder;
		chain.add(chain_link);
	}
}
