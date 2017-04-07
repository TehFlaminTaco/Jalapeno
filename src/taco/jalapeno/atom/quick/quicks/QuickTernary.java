package taco.jalapeno.atom.quick.quicks;

import taco.jalapeno.Chain;
import taco.jalapeno.atom.link.links.quick_exts.LinkTernary;
import taco.jalapeno.atom.quick.Quick;

public class QuickTernary extends Quick {
	@Override
	public void compile_last(Chain chain){
		chain.add(new LinkTernary(chain.pop(), chain.pop()));
	}
}
