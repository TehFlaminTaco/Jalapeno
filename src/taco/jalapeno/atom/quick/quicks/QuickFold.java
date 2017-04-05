package taco.jalapeno.atom.quick.quicks;

import java.util.ArrayList;

import taco.jalapeno.atom.link.Link;
import taco.jalapeno.atom.link.links.quick_exts.LinkFold;
import taco.jalapeno.atom.quick.Quick;

public class QuickFold extends Quick {
	public void compile_last(ArrayList<Link> chain){
		Link top = chain.remove(chain.size()-1);
		chain.add(new LinkFold().setlink(top));
	}
}
