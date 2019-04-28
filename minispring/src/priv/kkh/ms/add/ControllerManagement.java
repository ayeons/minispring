package priv.kkh.ms.add;

import java.lang.annotation.Annotation;

public class ControllerManagement {
	
	
	public Object add(String className) {
		
		Class<?> clazz=null;
		try {
			clazz=Class.forName(className);
			Annotation[] annos=clazz.getAnnotations();
			
		
			for(Annotation anno:annos) {
				
			
				String annoType=anno.annotationType().getName();
				annoType=annoType.substring(annoType.lastIndexOf(".")+1);
				if(annoType.equals("testAnno")) {
					
				}
				
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		
		
	}
}
