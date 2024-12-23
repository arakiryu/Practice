package framework;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import controller.Controller;

public class HandlerMapping {

	private Map<String, Controller> mappings;

	public HandlerMapping(String propName) {
		if(propName == null) propName = "bean.properties";
		mappings = new HashMap<>();
		Properties prop = new Properties();
		String propLoc = this.getClass().getResource(propName).getPath();
		System.out.println(propLoc);
		
		try(
			InputStream is = new FileInputStream(propLoc);
		) {
			prop.load(is);
			Set<Object> keys = prop.keySet();
			for(Object key : keys) {
			//	System.out.println(key.toString() + " : " + prop.getProperty(key.toString()));
				String className = prop.getProperty(key.toString()); // kr.ac.kopo.controller.BoardListController
				
				Class<?> clz = Class.forName(className);
				Constructor<?> constructor = clz.getConstructor();
				Controller instance = (Controller)constructor.newInstance();
				mappings.put(key.toString(), instance);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Controller getController(String uri) {
		return mappings.get(uri);
	}


}





