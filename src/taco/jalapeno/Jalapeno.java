package taco.jalapeno;

import java.util.ArrayList;

import taco.jalapeno.atom.link.Link;
import taco.jalapeno.compiler.Compiler;
import taco.jalapeno.encoding.Encoding;

public class Jalapeno {
	Compiler compiler;
	
	public Jalapeno(){
		Encoding.spawn();
		compiler = new Compiler();
	}
	
	public ArrayList<ArrayList<Link>> compile(String input, int flags) throws InstantiationException, IllegalAccessException{
		return compiler.compile(input, flags);
	}
	
}
