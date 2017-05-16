package taco.jalapeno.compiler;

import taco.jalapeno.Chain;
import taco.jalapeno.atom.link.Link;
import taco.jalapeno.vars.Var;
import taco.jalapeno.vars.VarChar;
import taco.jalapeno.vars.VarList;
import taco.jalapeno.vars.VarNumber;

public class Executer {
	
	public static Var[] execute(Chain chain_al, Var[] arguments) throws Exception{
		Chain chain = new Chain();
		Link topNilad = null;
		if(chain_al.size()==0){
			return new Var[]{};
		}
		if(arguments.length==0){
			topNilad = chain_al.remove(0);
		}
		for(int i=0; i < chain_al.size(); i++){
			// This segment turns dyad-nilad and nilad-(monad-*)dyad pairs into monads. Yay.
			if(chain_al.get(i).getarguments()==0){
				Link bigLink = chain_al.get(i);
				int c = chain.size()-1;
				int covered = 1;
				while(c>=0 && chain.get(c).getarguments()==1){
					bigLink = new ExecutableLink().setLeft(bigLink).setCur(chain.get(c)).setArguments(1);
					covered++;
					c--;
				}
				if(c>=0 && chain.get(c).getarguments()==2){
					chain.add(new ExecutableLink().setRight(bigLink).setCur(chain.get(c)).setArguments(1));
					while(covered>0){
						covered--;
						chain.remove(chain.size()-2);
					}
				}else{
					int stored = i;
					bigLink = chain_al.get(i++);
					while(i < chain_al.size() && chain_al.get(i).getarguments()==1){
						bigLink = new ExecutableLink().setCur(chain_al.get(i++)).setLeft(bigLink);
					}
					if(i>=chain_al.size()){i=stored;chain.add(chain_al.get(i));continue;}
					if(chain_al.get(i).getarguments()==2){
						chain.add(new ExecutableLink().setCur(chain_al.get(i)).setLeft(bigLink).setArguments(1));
					}else{
						chain.add(chain_al.get(--i));
					}
				}
			}else{
				chain.add(chain_al.get(i));
			}
		}
		if(arguments.length==0){
			if(topNilad==null || topNilad.getarguments()!=0){
				throw new Exception("Failed to execute chain Niladically. Chain must start with a nilad.");
			}
			arguments = new Var[]{topNilad.execute(arguments)}; // Execute Niladic Chains monadlicly.
		}
		
		if(chain.size() == 0){return arguments;}
		while(chain.size()>0){
			chain.reset();
			if(arguments.length == 1){
				chain.execute("a+", "+(a(v),w)", arguments);
				chain.execute("ab+", "+(a(v),b(w))", arguments);
				chain.execute("+", "+(v,w)", arguments);
				chain.execute("a", "a(v,w)", arguments);
				chain.execute("ç", "ç(v,w)", arguments);
			}else{
				chain.execute("+×÷", "+(×(v),÷(w))", arguments);
				chain.execute("a+", "+(a(v),w)", arguments);
				chain.execute("ab+", "+(a(v),b(w))", arguments);
				chain.execute("+", "+(v,w)", arguments);
				chain.execute("a", "a(v,w)", arguments);
				chain.execute("ç", "ç(v,w)", arguments);
			}
			Var out = chain.get_stored();
			if(out==null){
				return arguments;
			}
			arguments = new Var[]{out};
		}
		return arguments;
	}

	public static Var[] execute(Chain chain_al, String[] arguments) throws Exception {
		Var[] new_args = new Var[arguments.length];
		for(int i=0; i < arguments.length; i++){
			String s = arguments[i];
			if(s.charAt(0)=='"' && s.charAt(s.length()-1) == '"'){
				new_args[i] = new VarList(s.substring(1, -2));
			}else if(s.charAt(0)=='\'' && s.charAt(s.length()-1) == '\''){
				new_args[i] = new VarChar(s.charAt(1));
			}else{
				if(s.matches("^-?\\d*(\\d|(\\.\\d+))$")){
					new_args[i] = new VarNumber(s);
				}else{
					new_args[i] = new VarList(s);
				}
			}
		}
		return execute(chain_al, new_args);
	}
}
