package priv.kkh.ms;

import java.lang.annotation.Annotation;

public class CodeGen {
	static {System.out.println("static init");}
	
	public CodeGen() {
		System.out.println("codeGen init");
	}
	
	
	public Object aop_extend(String className) {
		
		
		return className;
		
	}

}
