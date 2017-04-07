package taco.jalapeno.atom.link.links.nilads.literals;

import java.math.BigDecimal;

import taco.jalapeno.vars.Var;
import taco.jalapeno.vars.VarNumber;

public class NiladTerminateNumber extends NiladLiteralTerminate {
	@Override
	public Var parse(Byte[] b_arr){
		BigDecimal n = BigDecimal.ZERO;
		int[] nums = NiladLiteralTerminate.to250(b_arr);
		for(int i=0; i<nums.length; i++){
			n=n.pow(250).add(new BigDecimal(nums[i]));
		}
		return new VarNumber(n);
	}
}
