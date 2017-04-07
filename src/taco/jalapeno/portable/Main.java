package taco.jalapeno.portable;

import java.util.ArrayList;

import taco.jalapeno.Chain;
import taco.jalapeno.Flags;
import taco.jalapeno.Jalapeno;
import taco.jalapeno.compiler.Executer;
import taco.jalapeno.encoding.Encoding;
import taco.jalapeno.vars.Var;
import taco.jalapeno.vars.VarNumber;

public class Main {

	static Jalapeno jalapeno;
	
	public static void main(String[] args) throws Exception {
		jalapeno = new Jalapeno();
		//jalapeno.compile("Hello, World!", Flags.FLAG_PRODUCE_STRING);
		ArrayList<Chain> chains = jalapeno.compile("+×?",
				//Flags.FLAG_TOKEN
				Flags.FLAG_UTF8
				//+ Flags.FLAG_PRINT_CHARS
				//+ Flags.FLAG_PRINT_BYTES
				);
		Var[] out = Executer.execute(chains.get(chains.size()-1),
				new Var[]{new VarNumber(1),
						  new VarNumber(12)});
		for(int i=0; i<out.length; i++){
			System.out.print(out[i]);
		}
	}

}
