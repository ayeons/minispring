package priv.kkh.ms;

public class ViewResolverFactory {
	private ViewResolverFactory() {}
	
	public static ViewResolver getViewResolver() {
		return new ViewResolver();
	}
}
