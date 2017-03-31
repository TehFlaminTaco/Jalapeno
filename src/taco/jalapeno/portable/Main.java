package taco.jalapeno.portable;

import taco.jalapeno.Flags;
import taco.jalapeno.Jalapeno;

public class Main {

	static Jalapeno jalapeno;
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		jalapeno = new Jalapeno();
		jalapeno.compile("YADA BLAH NULL YADA", Flags.FLAG_TOKEN + Flags.FLAG_SURPRESS + Flags.FLAG_PRINT_CHARS);
	}

}
