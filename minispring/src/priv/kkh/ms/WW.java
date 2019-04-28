package priv.kkh.ms;

public class WW {
	static int fd=1;
	void fdfd() {
		System.out.println(this);
	}
	public static void main(String[] args) {
		new WW().fdfd();
		WW fd=new FF();
		fd.fdfd();
		AA aa=new AA() {
			
		};
		System.out.println(aa.getClass());
		System.out.println(aa.getClass().getName());
	}
}

class FF extends WW{
	
	
}

interface AA{
	
}