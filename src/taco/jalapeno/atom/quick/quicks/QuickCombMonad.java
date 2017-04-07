package taco.jalapeno.atom.quick.quicks;

import java.util.ArrayList;

import taco.jalapeno.Chain;
import taco.jalapeno.atom.link.Link;
import taco.jalapeno.atom.link.links.quick_exts.ChainLink;
import taco.jalapeno.atom.quick.Quick;

public class QuickCombMonad extends Quick {
	@Override
	public void compile(ArrayList<Chain> chains){
		Chain chain = chains.get(chains.size()-1);
		Chain micro_chain = new Chain();
		Link a = chain.remove(chain.size()-1);
		Link b = chain.remove(chain.size()-1);
		micro_chain.add(b);
		micro_chain.add(a);
		ChainLink chain_link = new ChainLink();
		chain_link.setarguments(1);
		chain_link.chain = micro_chain;
		chain.add(chain_link);
	}
}
