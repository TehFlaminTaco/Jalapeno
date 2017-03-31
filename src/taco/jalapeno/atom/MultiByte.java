package taco.jalapeno.atom;

import java.util.HashMap;

public class MultiByte extends Atom {
	static HashMap<Byte, Class<?>> byte_mapping = new HashMap<Byte, Class<?>>();
	static HashMap<String, Byte> token_mapping = new HashMap<String, Byte>();
	static HashMap<Character, Byte> char_mapping = new HashMap<Character, Byte>();
	
	
	@SuppressWarnings("unchecked")
	public Class<Atom> get(byte a){
		return (Class<Atom>) byte_mapping.get(a);
	}
	public Class<Atom> get(String a){
		return get(token_mapping.get(a));
	}
	public Class<Atom> get(char a){
		return get(char_mapping.get(a));
	}
	
	public byte getByte(byte a){
		return a;
	}
	public byte getByte(String a){
		return token_mapping.get(a);
	}
	public byte getByte(char a){
		return char_mapping.get(a);
	}
	
}