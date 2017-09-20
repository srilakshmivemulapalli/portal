package com.nisum.portal.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class CommonsUtil {
	
	public static String getErrorStacktrace(Exception ex){
		StringWriter errors = new StringWriter();
		ex.printStackTrace(new PrintWriter(errors));
		return errors.toString();
	}

}
