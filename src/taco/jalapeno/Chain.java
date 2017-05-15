package taco.jalapeno;

import java.util.ArrayList;

import taco.jalapeno.atom.link.Link;
import taco.jalapeno.compiler.Chaining;
import taco.jalapeno.vars.Var;

public class Chain extends ArrayList<Link> {
	private static final long serialVersionUID = 847025021885923453L;

	private boolean executed = false;
	private Var stored = null;
	
	public Link pop(){
		if(size()<1){return null;}
		return remove(size()-1);
	}
	
	public Link peek(){
		if(size()<1){return null;}
		return get(size()-1);
	}
	
	public Var get_stored(){
		return stored;
	}
	
	public void execute(String rule, String result, Var[] args){
		if(executed){
			return;
		}
		String this_rule = Chaining.getString(this);
		if(this_rule.length()>=rule.length() && this_rule.substring(0, rule.length()).equals(rule)){
			String[] parts = rule.split("");
			stored = do_rule(parts, result, args);
			executed = true;
			
			for(int i=0; i<rule.length(); i++){
				this.remove(0);
			}
		}
		return;
	}
	
	private Var do_rule(String[] parts, String rule, Var[] int_arguments){
		char func = rule.charAt(0);
		
		if(func == 'v'){
			if(int_arguments.length == 0){
				return null;
			}
			return int_arguments[0];
		}
		if(func == 'w'){
			if(int_arguments.length < 2){
				if(int_arguments.length == 1){
					return int_arguments[0];
				}
				return null;
			}
			return int_arguments[1];
		}
		
		ArrayList<Var> arguments = new ArrayList<Var>();
		int d = 0;
		String nRule = "";
		for(int i=2; i < rule.length();i++){
			char s = rule.charAt(i);
			if(s==',' && d == 0){
				arguments.add(do_rule(parts, nRule, int_arguments));
				nRule = "";
				continue;
			}
			if(s=='('){
				d++;
			}
			if(s==')'){
				if(d==0){
					arguments.add(do_rule(parts,nRule, int_arguments));
					break;
				}
				d--;
			}
			nRule += s;
		}
		Var[] args = new Var[arguments.size()];
		for(int i = 0; i < args.length; i++){
			args[i] = arguments.get(i);
		}
		
		return get(find_rule(parts,String.valueOf(func))).execute(args);
	}
	
	private int find_rule(String[] rules, String target){
		for(int i=0; i < rules.length; i++){
			if(target.equals(rules[i])){
				return i;
			}
		}
		return -1;
	}
	
	public void reset(){
		executed = false;
	}
	
}
