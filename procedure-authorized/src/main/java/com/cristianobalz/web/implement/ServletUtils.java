package com.cristianobalz.web.implement;

import java.util.Map;

public class ServletUtils {
	
	public static boolean hasParameter(Map<String, String[]> parameterMap, String parametro) {
		return parameterMap != null && parameterMap.containsKey(parametro);
	}

}
