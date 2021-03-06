package taco.jalapeno;

public class Flags {
	public static final int FLAG_TOKEN = 1;
	public static final int FLAG_UTF8 = 2;
	public static final int FLAG_SURPRESS = 4;
	public static final int FLAG_PRINT_CHARS = 8;
	public static final int FLAG_PRINT_BYTES = 16;
	public static final int FLAG_PRODUCE_STRING = 32;
	public static final int FLAG_PRODUCE_NUMBER = 64;
	public static final int FLAG_PRODUCE_COMPRESSED = 128;
	
	public static int getFlag(String str){
		switch(str){
			case "t":
				return FLAG_TOKEN;
			case "u":
				return FLAG_UTF8;
			case "s":
				return FLAG_SURPRESS;
			case "c":
				return FLAG_PRINT_CHARS;
			case "b":
				return FLAG_PRINT_BYTES;
			case "S":
				return FLAG_PRODUCE_STRING;
			case "N":
				return FLAG_PRODUCE_NUMBER;
			case "C":
				return FLAG_PRODUCE_COMPRESSED;
			default:
				return 0;
		}
	}
}
