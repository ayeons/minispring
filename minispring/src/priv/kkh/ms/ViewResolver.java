package priv.kkh.ms;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewResolver {
	private String prefix="/WEB-INF/views/";
	private String subfix=".jsp";
	
	ViewResolver(){

		new Logger("viewResolver").info("init");
	}
	
	public void mappingView(String viewName,HttpServletRequest req,HttpServletResponse resp) {
		if(viewName.substring(0,3)=="rid") {	
			try {
				resp.sendRedirect(viewName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
				req.getRequestDispatcher(prefix+viewName+subfix).forward(req, resp);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
