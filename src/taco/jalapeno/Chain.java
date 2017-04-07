package taco.jalapeno;

import java.util.ArrayList;

import taco.jalapeno.atom.link.Link;

public class Chain extends ArrayList<Link> {
	private static final long serialVersionUID = 847025021885923453L;

	public Link pop(){
		if(size()<1){return null;}
		return remove(size()-1);
	}
	
	public Link peek(){
		if(size()<1){return null;}
		return get(size()-1);
	}
	
}
