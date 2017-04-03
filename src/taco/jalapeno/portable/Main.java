package taco.jalapeno.portable;

import taco.jalapeno.Flags;
import taco.jalapeno.Jalapeno;
import taco.jalapeno.compiler.Executer;
import taco.jalapeno.vars.Var;

public class Main {

	static Jalapeno jalapeno;
	
	public static void main(String[] args) throws Exception {
		jalapeno = new Jalapeno();
		Var[] out = Executer.execute(jalapeno.compile("LITERAL `256` LITERAL_END_NUMBER", Flags.FLAG_TOKEN + Flags.FLAG_PRINT_CHARS), new Var[0]);
		for(int i=0; i<out.length; i++){
			System.out.print(out[i]);
		}
	}

}
