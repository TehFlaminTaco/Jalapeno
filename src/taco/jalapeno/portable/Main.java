package taco.jalapeno.portable;

import taco.jalapeno.Flags;
import taco.jalapeno.Jalapeno;
import taco.jalapeno.compiler.Executer;
import taco.jalapeno.vars.Var;

public class Main {

	static Jalapeno jalapeno;
	
	public static void main(String[] args) throws Exception {
		jalapeno = new Jalapeno();
		Executer.execute(jalapeno.compile("HELLO_WORLD PRINT", Flags.FLAG_TOKEN + Flags.FLAG_SURPRESS + Flags.FLAG_PRINT_CHARS), new Var[0]);
	}

}
