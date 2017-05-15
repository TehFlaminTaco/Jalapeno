package taco.jalapeno.atom.quick.quicks;

import taco.jalapeno.Chain;
import taco.jalapeno.atom.link.Link;
import taco.jalapeno.atom.link.links.quick_exts.LinkFlip;
import taco.jalapeno.atom.quick.Quick;

public class QuickFlip extends Quick {
	@Override
	public void compile_last(Chain chain){
		Link top = chain.remove(chain.size()-1);
		chain.add(new LinkFlip().setlink(top));
	}
}
