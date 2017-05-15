package taco.jalapeno.portable;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import taco.jalapeno.Chain;
import taco.jalapeno.Flags;
import taco.jalapeno.Jalapeno;
import taco.jalapeno.compiler.Executer;
import taco.jalapeno.vars.Var;

public class Main {

	static Jalapeno jalapeno;
	
	public static void main(String[] args) throws Exception {
		jalapeno = new Jalapeno();
		jalapeno.input = new Scanner(System.in);
		
		if(args.length==0){
			System.out.println(Jalapeno.HELP_TEXT);
			return;
		}
		File target = new File(args[0]);
		if(!target.exists()){
			System.err.println(String.format("Could not find file \"%s\"",args[0]));
			return;
		}
		
		int flags = 0;
		int args_count = 0;
		String[] tmp_args = new String[args.length];
		for(int i=1; i<args.length; i++){
			if(args[i].charAt(0) == '+'){
				flags += Flags.getFlag(args[i].substring(1));
			}else{
				tmp_args[args_count++] = args[i];
			}
		}
		jalapeno.arguments = new String[args_count];
		for(int i=0; i < args_count; i++){
			jalapeno.arguments[i] = tmp_args[i];
		}
		
		ArrayList<Chain> chains = jalapeno.compile(Files.readAllBytes(Paths.get(args[0])), flags);
		Var[] out = Executer.execute(chains.get(chains.size()-1),
									 jalapeno.arguments);
		for(int i=0; i<out.length; i++){
			System.out.print(out[i]);
		}
	}

}
