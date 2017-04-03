package taco.jalapeno.encoding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import taco.jalapeno.atom.Atom;
import taco.jalapeno.atom.MultiByte;
import taco.jalapeno.atom.generics.AtomNull;
import taco.jalapeno.atom.generics.AtomTerminateChain;
import taco.jalapeno.atom.link.DyadicLink;
import taco.jalapeno.atom.link.MonadicLink;
import taco.jalapeno.atom.link.NiladicLink;
import taco.jalapeno.atom.link.links.monads.MonadPrint;
import taco.jalapeno.atom.link.links.nilads.NiladHello;
import taco.jalapeno.atom.link.links.nilads.literals.NiladLiteral;
import taco.jalapeno.atom.link.links.nilads.literals.NiladTerminateCompressed;
import taco.jalapeno.atom.link.links.nilads.literals.NiladTerminateNumber;
import taco.jalapeno.atom.link.links.nilads.literals.NiladTerminateString;
import taco.jalapeno.atom.link.links.nilads.literals.NiladTerminateUgly;

public class Encoding {
	
	static HashMap<Byte, Class<?>> byte_mapping;
	static HashMap<String, Byte> token_mapping;
	static HashMap<Character, Byte> char_mapping;
	static Pattern match_strings = Pattern.compile("(?<!\\\\)((?:\\\\\\\\)*)([\"'`])(.*?(?<!\\\\)(?:\\\\\\\\)*)\\2", Pattern.DOTALL);
	
	public static void spawn(){
		byte_mapping = new HashMap<Byte, Class<?>>();
		token_mapping = new HashMap<String, Byte>();
		char_mapping = new HashMap<Character, Byte>();
		
		add_encoding(AtomNull.class, "NULL", '\0');
		add_encoding(AtomTerminateChain.class, "TERMINATE_CHAIN", '\n', '¶');
		add_encoding(MultiByte.class, "BLAH", 'z');
		
		// MONADS
		add_encoding(MonadPrint.class, "PRINT", 'p');
		
		// DYADS
		
		// NILADS
		add_encoding(NiladHello.class, "HELLO_WORLD", 'h');
		add_encoding(NiladLiteral.class, "LITERAL", '“');
		add_encoding(NiladTerminateString.class, "LITERAL_END_STRING", '”');
		add_encoding(NiladTerminateNumber.class, "LITERAL_END_NUMBER", '»');
		add_encoding(NiladTerminateCompressed.class, "LITERAL_END_COMPRESSED", '’');
		add_encoding(NiladTerminateUgly.class, "LITERAL_END_UGLY", '"');
		
		finish_encoding();
	}
	
	private static void finish_encoding() {
		while(nextbyte!=0){
			add_encoding(AtomNull.class, "NUL", '_');
		}
	}

	public static void add_encoding(Class<?> target, String token, char... chr){
		byte b = next_byte();
		byte_mapping.put(b, target);
		token_mapping.put(token, b);
		for(int i=0; i<chr.length; i++)
			char_mapping.put(chr[i], b);
	}

	private static byte nextbyte = 0;
	private static Byte next_byte() {
		return nextbyte++;
	}
	
	public static String DeCharacterize(String code, boolean surpress){
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
		return new String(byte_array);
	}
	
	public static String DeTokenize(String code, boolean surpress){
		ArrayList<byte[]> byte_literals = new ArrayList<byte[]>();
		System.err.println(code);
		Matcher m = match_strings.matcher(code);
		StringBuffer sb = new StringBuffer();
		
		while(m.find()){
			if(m.group(2).equals("\"")){
				byte_literals.add(escape(m.group(3)).getBytes());
			}else if(m.group(2).equals("'")){
				byte_literals.add(DeCharacterize(escape(m.group(3)),surpress).getBytes());
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
		return new String(byte_array);
	}
	
	public static String toCharacters(String code){
		byte[] b = code.getBytes();
		ArrayList<Character> chars = new ArrayList<Character>();
		for(int i=0; i<b.length; i++){
			if (char_mapping.containsValue(b[i])){
				for(Character c : char_mapping.keySet()){
					if(char_mapping.get(c)==b[i]){
						chars.add(c);
						break;
					}
				}
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
