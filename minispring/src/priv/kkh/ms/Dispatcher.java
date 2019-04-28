package priv.kkh.ms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.startup.ClassLoaderFactory;
import org.apache.tomcat.jdbc.pool.ClassLoaderUtil;

import com.sun.xml.internal.ws.server.DefaultResourceInjector;

import priv.kkh.ms.annotation.Controller;
import priv.kkh.ms.annotation.RequestMapping;


public class Dispatcher extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String path;
	private List<Class> classList;
	public void setPath(String path){
		this.path=path;
	}
	

		


	@Override
	public void init(ServletConfig config) throws ServletException {

		  System.out.println("init");
		 
		  List<String> result=new ArrayList<>();
		  String path=config.getInitParameter("path");
		  File tempFile=new File(path);
		  path=tempFile.toString();
		  System.out.println(path);
		  classList=new ArrayList<>();
		
		  result=findClassNameList(path,result,path);
		  
		  for(String s:result) { 
			  Class clazz=search(s); 
			  if(clazz!=null) { 
				  classList.add(clazz); 
			  } 
		  }
		  
		  
		  
		/*
		 * for(Class cl:classList) { try { load(cl); } catch (InstantiationException |
		 * IllegalAccessException e) { e.printStackTrace(); } }
		 */
	}

	
	
	
	
	  public static void main(String[] args) {
		
		  
		 
		 
		 
		 
		/*
		 * try { File file=new File("WebContent/WEB-INF/web.xml"); FileReader fr=new
		 * FileReader(file);
		 * 
		 * BufferedReader ip=new BufferedReader(fr); String s=null; String ss=null;
		 * String str=null; while((s=ip.readLine())!=null) {
		 * 
		 * 
		 * 
		 * int begin=s.indexOf("<"); int end=s.indexOf(">");
		 * 
		 * 
		 * if(begin>=0&&end>=0) { ss=s.substring(begin+1,end);
		 * 
		 * if(ss.equals("page")) { int endI=0; if((endI=s.lastIndexOf("<"))>0) {
		 * 
		 * str=s.substring(end+1,endI); }else { str=s.substring(end+1); } }
		 * 
		 * } } System.out.println(str);
		 * 
		 * }catch(Exception e) { e.printStackTrace(); }
		 */
	}





	 
	  public List<String> findClassNameList(String dirName,List<String> result,String originDirName) { 
		  
		  File dir=new File(dirName);
		  //System.out.println(dirName);
		  File[] files=dir.listFiles();
		  if(files==null)return null;
		 
		 
		  
		  
		  for(int i=0;i<files.length;++i) {
			 
			  if(files[i].getName().indexOf(".")!=-1) {
				 
				System.out.println(files[i].toString());
				  String temp=files[i].toString().substring(originDirName.length()+1,files[i].toString().lastIndexOf("."));
				  temp=temp.toString().replace('\\', '.');
				  System.out.println(temp);
				  result.add(temp);
			  }
			  else {
				  findClassNameList(files[i].toString(),result,originDirName);
			  }
			
		  }
		  
		  return result;
	  }

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path=req.getRequestURI();
		System.out.println(path);
		for(Class clazz:classList) {
			Object result=link(clazz,req.getRequestURI(),req);
			System.out.println(result);
		}
	}

	
	

	public Object link(Class clazz,String requestPath,HttpServletRequest req) {
		String requestPathTrim=requestPath.substring(req.getContextPath().length());
		//TODO
		System.out.println(requestPathTrim);
		Object result=null;
		Method[] methods=clazz.getDeclaredMethods();
		for(Method me:methods) {
			System.out.println(me);
			RequestMapping an=me.getAnnotation(RequestMapping.class);
			if(an!=null)System.out.println("an.value : "+ an.value());
			if(me.isAnnotationPresent(RequestMapping.class)) {
				 if(an.value().equals(requestPathTrim)) {
					/*
					 * Class[] clazzz=me.getParameterTypes(); String[] s=new String[clazzz.length];
					 * int i=0; for (Class cl:clazzz) { s[i]=cl.getName(); i++; }
					 */
					 try {
						
						result=me.invoke(clazz.newInstance(),req);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 }
			}
		}
		
		return result;
	}
	
	public Class search(String className) {
		Class<?> clazz=null;
		try {
			
			clazz=this.getClass().getClassLoader().loadClass(className);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
		
		
		if(clazz.isAnnotationPresent(Controller.class)) {
			return clazz;
			
		}
		return null;
	}
}
