package taco.jalapeno.atom.link.links.quick_exts;

import java.util.ArrayList;

import taco.jalapeno.atom.link.Link;
import taco.jalapeno.compiler.Executer;
import taco.jalapeno.vars.Var;

public class ChainLink extends Link {
	public ArrayList<ArrayList<Link>> chain;
	int arguments = 0;
	
	public int getarguments(){return arguments;}
	public void setarguments(int i){arguments = i;}
	
	public Var execute(Var[] args){
		try {
			return Executer.execute(chain, args)[0];
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
}
