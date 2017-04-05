package taco.jalapeno.portable;

import taco.jalapeno.Flags;
import taco.jalapeno.Jalapeno;
import taco.jalapeno.compiler.Executer;
import taco.jalapeno.vars.Var;
import taco.jalapeno.vars.VarList;

public class Main {

	static Jalapeno jalapeno;
	
	public static void main(String[] args) throws Exception {
		jalapeno = new Jalapeno();
		Var[] out = Executer.execute(jalapeno.compile("LITERAL \"Henk\" LITERAL_END_UGLY PRINT ADD FOLD", Flags.FLAG_TOKEN + Flags.FLAG_PRINT_CHARS + Flags.FLAG_PRINT_BYTES), new Var[]{});
		for(int i=0; i<out.length; i++){
			System.out.print(out[i]);
		}
	}

}
