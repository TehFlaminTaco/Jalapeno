package taco.jalapeno;

import java.util.ArrayList;
import java.util.Scanner;

import taco.jalapeno.compiler.Compiler;
import taco.jalapeno.encoding.Encoding;

public class Jalapeno {
	public static final String HELP_TEXT = "Jalapeño\n"
			+ "Created by William Lemon\n"
			+ "http://www.github.com/TehFlaminTaco/Jalapeno\n"
			+ "\n"
			+ "Usage: Java -jar jalapeno.jar <target> [flags] [arguments]";
	
	Compiler compiler;
	public Scanner input;
	public String[] arguments;
	
	public Jalapeno(){
		Encoding.spawn();
		compiler = new Compiler();
	}
	
	public ArrayList<Chain> compile(byte[] bs, int flags) throws InstantiationException, IllegalAccessException{
		return compiler.compile(bs, flags);
	}
	
}
