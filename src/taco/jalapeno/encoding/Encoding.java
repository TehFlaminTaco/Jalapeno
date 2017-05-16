package taco.jalapeno.encoding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import taco.jalapeno.atom.Atom;
import taco.jalapeno.atom.generics.AtomNull;
import taco.jalapeno.atom.generics.AtomTerminateChain;
import taco.jalapeno.atom.link.links.dyads.*;
import taco.jalapeno.atom.link.links.monads.*;
import taco.jalapeno.atom.link.links.nilads.*;
import taco.jalapeno.atom.link.links.nilads.literals.*;
import taco.jalapeno.atom.quick.quicks.*;

public class Encoding {
	
	static HashMap<Byte, Class<?>> byte_mapping;
	static HashMap<String, Byte> token_mapping;
	static HashMap<Character, Byte> char_mapping;
	static Pattern match_strings = Pattern.compile("(?<!\\\\)((?:\\\\\\\\)*)([\"'`])(.*?(?<!\\\\)(?:\\\\\\\\)*)\\2", Pattern.DOTALL);
	
	public static void spawn(){
		byte_mapping = new HashMap<Byte, Class<?>>();
		token_mapping = new HashMap<String, Byte>();
		char_mapping = new HashMap<Character, Byte>();
		
		// These are at the start, so that the literal bytes 0 - 9 are numbers.
		add_encoding(NiladNumber.class, "ZERO", '0');
		add_encoding(NiladNumber.class, "ONE", '1');
		add_encoding(NiladNumber.class, "TWO", '2');
		add_encoding(NiladNumber.class, "THREE", '3');
		add_encoding(NiladNumber.class, "FOUR", '4');
		add_encoding(NiladNumber.class, "FIVE", '5');
		add_encoding(NiladNumber.class, "SIX", '6');
		add_encoding(NiladNumber.class, "SEVEN", '7');
		add_encoding(NiladNumber.class, "EIGHT", '8');
		add_encoding(NiladNumber.class, "NINE", '9');
		
		add_encoding(NiladNegative.class, "NEGATIVE", '-');
		
		add_encoding(AtomNull.class, "NULL", '\0');
		add_encoding(AtomTerminateChain.class, "TERMINATE_CHAIN", '\n', '¶');
		
		// QUICKS
		add_encoding(QuickCombDyad.class, "DYADIC_COMBINE", '@');
		add_encoding(QuickCombMonad.class, "MONADIC_COMBINE", '$');
		add_encoding(QuickCombNilad.class, "NILADIC_COMBINE", '#');
		add_encoding(QuickFold.class, "FOLD", '/');
		add_encoding(QuickReduce.class, "REDUCE", '\\');
		add_encoding(QuickLastDyadChain.class, "LAST_DYADIC", 'Ç');
		add_encoding(QuickThisDyadChain.class, "THIS_DYADIC", 'Ð');
		add_encoding(QuickNextDyadChain.class, "NEXT_DYADIC", 'Ñ');
		add_encoding(QuickLastMonadChain.class, "LAST_MONADIC", 'ç');
		add_encoding(QuickThisMonadChain.class, "THIS_MONADIC", 'ð');
		add_encoding(QuickNextMonadChain.class, "NEXT_MONADIC", 'ñ');
		add_encoding(QuickLastNiladChain.class, "LAST_NILADIC", 'ø');
		add_encoding(QuickThisNiladChain.class, "THIS_NILADIC", 'µ');
		add_encoding(QuickNextNiladChain.class, "NEXT_NILADIC", 'Ø');
		add_encoding(QuickTernary.class, "IF", '?');
		add_encoding(QuickFlip.class, "FLIP", '¡');
		
		
		// MONADS
		add_encoding(MonadPrint.class, "PRINT", 'p');
		add_encoding(MonadHalf.class, "HALF", 'h');
		add_encoding(MonadRange.class, "RANGE", 'r');
		add_encoding(MonadLoweredRange.class, "LOWERED_RANGE", 'l');
		add_encoding(MonadFlatten.class, "FLATTEN", 'f');
		add_encoding(MonadBinary.class, "TOBINARY", 'b');
		add_encoding(MonadProduct.class, "PRODUCT", 'm');
		add_encoding(MonadSum.class, "SUM", 's');
		
		// DYADS
		add_encoding(DyadAdd.class, "ADD", '+');
		add_encoding(DyadSubtract.class, "MINUS", '_');
		add_encoding(DyadMultiply.class, "MULTIPLY", '×');
		add_encoding(DyadDivide.class, "DIVIDE", '÷');
		add_encoding(DyadExponentiate.class, "EXPONENTIATE", 'E');
		add_encoding(DyadTimes.class, "TIMES", '‡');
		add_encoding(DyadSplit.class, "SPLIT", '|');
		add_encoding(DyadIndex.class, "INDEX", 'I');
		
		// NILADS
		//add_encoding(NiladHello.class, "HELLO_WORLD", 'h');
		add_encoding(NiladLiteral.class, "LITERAL", '“');
		add_encoding(NiladTerminateString.class, "LITERAL_END_STRING", '”');
		add_encoding(NiladTerminateNumber.class, "LITERAL_END_NUMBER", '»');
		add_encoding(NiladTerminateCompressed.class, "LITERAL_END_COMPRESSED", '’');
		add_encoding(NiladTerminateUgly.class, "LITERAL_END_UGLY", '"');
		add_encoding(NiladTerminateNumberUgly.class, "LITERAL_END_NUMBER_UGLY", '«');
		finish_encoding();
	}
	
	private static void finish_encoding() {
		while(nextbyte!=0){
			char to_use = 'ê';
			char possible = new String(new byte[]{nextbyte}).charAt(0);
			if(char_mapping.get(possible)==null){
				to_use = possible;
			}else if(char_mapping.get((char)(byte)char_mapping.get(possible))==null){
				to_use = (char)(byte)char_mapping.get(possible);
			}
			add_encoding(AtomNull.class, "NUL", to_use);
		}
	}

	public static void add_encoding(Class<?> target, String token, char... chr){
		byte b = next_byte();
		byte_mapping.put(b, target);
		token_mapping.put(token, b);
		for(int i=0; i<chr.length; i++){
			if(char_mapping.get(chr[i])!=null && !AtomNull.class.equals(byte_mapping.get(char_mapping.get(chr[i])))){
				System.err.print(chr[i] + " is already assigned to ");
				System.err.println(byte_mapping.get(char_mapping.get(chr[i])));
			}
			char_mapping.put(chr[i], b);
		}
	}

	private static byte nextbyte = 0;
	private static Byte next_byte() {
		return nextbyte++;
	}
	
	public static byte[] DeCharacterize(String code, boolean surpress){
		char[] characters = code.toCharArray();
		ArrayList<Byte> bytes = new ArrayList<Byte>();
		for(int i=0; i<characters.length; i++){
			Byte b = char_mapping.get(characters[i]);
			if(b==null){
				if(!surpress)
					System.err.println(characters[i]+" is not a recognized character!");
			}else{
				bytes.add(b);
			}
		}
		byte[] byte_array = new byte[bytes.size()];
		for(int i=0; i < bytes.size(); i++){
			byte_array[i]=bytes.get(i);
		}
		return byte_array;
	}
	
	public static byte[] DeTokenize(String code, boolean surpress){
		ArrayList<byte[]> byte_literals = new ArrayList<byte[]>();
		System.err.println(code);
		Matcher m = match_strings.matcher(code);
		StringBuffer sb = new StringBuffer();
		
		while(m.find()){
			if(m.group(2).equals("\"")){
				byte_literals.add(escape(m.group(3)).getBytes());
			}else if(m.group(2).equals("'")){
				byte_literals.add(DeCharacterize(escape(m.group(3)),surpress));
			}else{
				byte_literals.add(new byte[]{(byte)Integer.parseInt(m.group(3))});
			}
			m.appendReplacement(sb,m.group(1)+" LITERAL_BYTE ");
		}
		m.appendTail(sb);
		
		String[] tokens = (new String(sb)).split("\\s+");
		
		ArrayList<Byte> bytes = new ArrayList<Byte>();
		
		for (int i = 0; i < tokens.length; i++){
			if(tokens[i].equals("LITERAL_BYTE")){
				byte[] cur_literal = byte_literals.remove(0);
				for (int c = 0; c < cur_literal.length; c++){
					bytes.add(cur_literal[c]);
				}
			}else{
				if(tokens[i].length()==0){
					continue;
				}
				Byte mapped = token_mapping.get(tokens[i]);
				if(token_mapping.get(tokens[i])==null){
					if(!surpress)
						System.err.println("Could not find token \""+tokens[i]+"\"");
				}else{
					bytes.add(mapped);
				}
			}
		}
		byte[] byte_array = new byte[bytes.size()];
		for(int i=0; i < bytes.size(); i++){
			byte_array[i]=bytes.get(i);
		}
		return byte_array;
	}
	
	public static String toCharacters(byte[] bs){
		byte[] b = bs;
		ArrayList<Character> chars = new ArrayList<Character>();
		for(int i=0; i<b.length; i++){
			if (char_mapping.containsValue(b[i])){
				boolean found = false;
				for(Character c : char_mapping.keySet()){
					if(char_mapping.get(c)==b[i]){
						chars.add(c);
						found = true;
						break;
					}
				}
				if(!found){
					chars.add('¿');
					for(char c : String.valueOf(b[i]).toCharArray()){
						chars.add(c);
					}
					chars.add('?');
				}
			}else{
				chars.add('¿');
				for(char c : String.valueOf(b[i]).toCharArray()){
					chars.add(c);
				}
				chars.add('?');
			}
		}
		char[] char_array = new char[chars.size()];
		for(int i=0; i<char_array.length; i++)
			char_array[i]=chars.get(i);
		return new String(char_array);
	}

	private static String escape(String to_escape) {
		return to_escape.replace("\\\\(.)", "$1");
	}
	
	@SuppressWarnings("unchecked")
	public static Class<?> get(byte a){
		return (Class<Atom>) byte_mapping.get(a);
	}
	public static Class<?> get(String a){
		return get(token_mapping.get(a));
	}
	public static Class<?> get(char a){
		return get(char_mapping.get(a));
	}
	
	public static byte getByte(byte a){
		return a;
	}
	public static byte getByte(String a){
		return token_mapping.get(a);
	}
	public static byte getByte(char a){
		return char_mapping.get(a);
	}
}
