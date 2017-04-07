package taco.jalapeno.atom.quick.quicks;

import taco.jalapeno.Chain;
import taco.jalapeno.atom.link.Link;
import taco.jalapeno.atom.link.links.quick_exts.LinkReduce;
import taco.jalapeno.atom.quick.Quick;

public class QuickReduce extends Quick {
	@Override
	public void compile_last(Chain chain){
		Link top = chain.remove(chain.size()-1);
		chain.add(new LinkReduce().setlink(top));
	}
}
