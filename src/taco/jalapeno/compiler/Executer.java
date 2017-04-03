package taco.jalapeno.compiler;

import java.util.ArrayList;

import taco.jalapeno.atom.link.Link;
import taco.jalapeno.vars.Var;

public class Executer {
	
	public static ArrayList<Var> execute(ArrayList<ArrayList<Link>> chains, Var[] arguments) throws Exception{
		for(ArrayList<Link> chain_al : chains){
			ArrayList<Link> chain = new ArrayList<Link>();
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
			for(Link l : chain){
				System.err.println(l);
			}
			if(arguments.length==0){
				if(chain.get(0).getarguments()!=0){
					throw new Exception("Failed to execute chain Niladically. Chain must start with a nilad.");
				}
				arguments = new Var[]{chain.remove(0).execute(arguments)}; // Execute Niladic Chains monadlicly.
			}
			
			// I'm not even going to begin to explain how the below works, I don't properly understand it myself, and I wrote it all.
			// Instead, here are the below chaining rules, which make more sense.
			 
			/* m = Monadic Link, d = Dyadic Link, n = Niladic Link. v = Left argument, w  = Right argument.
			// Although it usually knows, and usually cares, the chain here will ambiguously give m's both arguments. just incase. You know?
			// Called Niladicly:
			// 
			// n(...) -> (...)(n)
			// 
			// Called Monadicly:
			// dm -> d(v,m(v))
			// md -> d(m(v),v)
			// mmd -> d(m(v),m(v))
			// d -> d(v,v)
			// m -> m(v)
			// 
			// Called Dyadically:
			// ddd -> d(d(v,w),d(v,w))
			// d -> d(v,w)
			// md -> d(m(v,w?),w)
			// mmd -> d(m(v,w?),m(w,v?))
			// m -> m(v,w?)
			*/
			if(chain.size() == 0){continue;}
			while(chain.size()>0){
				int i = 0;
				if(arguments.length==1){
					if(chain.get(0).getarguments()==2){
						i = 2;
						arguments = new Var[]{chain.get(0).execute(new Var[]{
								arguments[0],chain.get(1).execute(new Var[]{arguments[0]})
						})};
					}else if(chain.size()>1 && chain.get(1).getarguments()==2){
						i = 2;
						arguments = new Var[]{chain.get(1).execute(new Var[]{
							chain.get(0).execute(new Var[]{arguments[0]}),
							arguments[0]
						})};
					}else if(chain.size()>2 && chain.get(2).getarguments()==2){
						i = 3;
						arguments = new Var[]{chain.get(2).execute(new Var[]{
							chain.get(0).execute(new Var[]{arguments[0]}),
							chain.get(1).execute(new Var[]{arguments[0]})
						})};
					}else{
						i = 1;
						arguments = new Var[]{chain.get(0).execute(new Var[]{arguments[0], arguments[0]})};
					}
				}else{
					if(chain.size()>2 && chain.get(0).getarguments()==2 && chain.get(1).getarguments()==2 && chain.get(2).getarguments()==2){
						i = 3;
						arguments = new Var[]{chain.get(1).execute(new Var[]{
								chain.get(0).execute(new Var[]{arguments[0],arguments[1]}),
								chain.get(2).execute(new Var[]{arguments[0],arguments[1]})
						})};
					}else if(chain.get(0).getarguments()==2){
						i = 1;
						arguments = new Var[]{chain.get(0).execute(new Var[]{arguments[0],arguments[1]})};
					}else if(chain.size()>1 && chain.get(1).getarguments()==2){
						i = 2;
						arguments = new Var[]{
								chain.get(1).execute(new Var[]{
										chain.get(0).execute(new Var[]{arguments[0], arguments[1]}),
										arguments[1]
								})
						};
					}else if(chain.size()>2 && chain.get(2).getarguments()==2){
						i = 3;
						arguments = new Var[]{chain.get(2).execute(new Var[]{
								chain.get(0).execute(new Var[]{arguments[0],arguments[1]}),
								chain.get(1).execute(new Var[]{arguments[1],arguments[0]})
						})};
					}else{
						i = 1;
						arguments = new Var[]{chain.get(0).execute(new Var[]{arguments[0], arguments[1]})};
					}
				}
				while(i>0 && chain.size()>0){
					chain.remove(0);
					i--;
				}
			}
		}
		return null;
	}
}
