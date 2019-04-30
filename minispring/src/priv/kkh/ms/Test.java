package priv.kkh.ms;

import javax.servlet.http.HttpServletRequest;

import priv.kkh.ms.add.AAA;
import priv.kkh.ms.annotation.Controller;
import priv.kkh.ms.annotation.RequestMapping;

@Controller
public  class Test extends AAA{
	
	Test(){
		System.out.println("fd");
		super.dfdf();
		
	}
	public void dfefef(String s) {
		
	}
	public void ccc(String s) {
		
	}
	public void bbb(int i) {
		
	}
	@Override
	protected
	void dfdf(String s) {
		System.out.println("child");
	}
	@RequestMapping("/test/")
	public String aaaa(HttpServletRequest req) {
		System.out.println("aaaa    :    "+req);
		
		return "home";
	}
	
}
