package taco.jalapeno.compiler;

import taco.jalapeno.atom.link.DyadicLink;
import taco.jalapeno.atom.link.Link;
import taco.jalapeno.atom.link.MonadicLink;
import taco.jalapeno.vars.Var;
import taco.jalapeno.vars.VarNumber;

public class ExecutableLink extends Link {
	Link left;
	Link right;
	Link cur;
	Var argLeft;
	Var argRight;
	
	public int arguments = 0;
	
	public ExecutableLink setLeft(Link left){this.left = left;return this;}
	public ExecutableLink setRight(Link right){this.right = right;return this;}
	public ExecutableLink setCur(Link cur){this.cur = cur;return this;}
	public ExecutableLink setArgLeft(Var argLeft){this.argLeft = argLeft;return this;}
	public ExecutableLink setArgRight(Var argRight){this.argRight = argRight;return this;}
	public ExecutableLink setArguments(int args){this.arguments = args;return this;}
	
	public int getarguments(){
		return arguments;
	}
	
	public Var execute(Var[] args){
		int n = -1;
		Var aLeft = null;
		Var aRight = null;
		if(argLeft!=null){aLeft = argLeft;}
		else{if(left!=null && left.getarguments()==0){aLeft = null;}else if(n+1<args.length){aLeft = args[++n];}}
		if(argRight!=null){aRight = argRight;}
		else{if(right!=null && right.getarguments()==0){aRight = null;}else if(n+1<args.length){aRight = args[++n];}}
		if(cur.getarguments()==0){
			return cur.execute(new Var[0]);
		}
		if(cur.getarguments()==1){
			MonadicLink m = (MonadicLink) cur;
			return m.execute(left==null ? aLeft : left.execute(new Var[]{aLeft}));
		}
		if(cur.getarguments()==2){
			DyadicLink d = (DyadicLink) cur;
			Var lVar = left==null ? aLeft : left.execute(new Var[]{aLeft});
			Var rVar = right==null ? aRight : right.execute(new Var[]{aRight});
			return d.execute(lVar,rVar);
		}
		return null;
	}
}
