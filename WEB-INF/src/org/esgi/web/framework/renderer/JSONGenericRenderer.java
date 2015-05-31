package org.esgi.web.framework.renderer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.esgi.web.framework.context.interfaces.IContext;
import org.esgi.web.framework.renderer.interfaces.IJSONGenericRenderer;

public class JSONGenericRenderer implements IJSONGenericRenderer {

	@Override
	public String render(IContext context) {
		StringBuffer sb = new StringBuffer();
		if (context.getAttribute("model").getClass().equals(ArrayList.class)) {
			try {
				List<Object> list = (List<Object>) context
						.getAttribute("model");
				sb.append("[");
				for (int i = 0; i < list.size(); i++) {
					sb.append("{\n");
					for (Field s : list.get(i).getClass().getDeclaredFields()) {
						if (s.getType().equals(List.class)) {
							sb.append("   \"" + s.getName() + "\" : [");

							List<String> list2 = (List<String>) s.get(list
									.get(i));

							for (int j = 0; j < list2.size(); j++) {
								sb.append("\"" + list2.get(j) + "\"");
								if (j < list2.size() - 1) {
									sb.append(",");
								}
							}
							sb.append("]");
						} else {
							sb.append("   \"" + s.getName() + "\" : \""
									+ s.get(list.get(i)) + "\", \n");
						}
					}
					sb.append("\n}");
					
					if(i < list.size() - 1) {
						sb.append(",\n");
					}
				}
				sb.append("]");
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			Object object = context.getAttribute("model");
			try {

				sb.append("{\n");
				for (Field s : object.getClass().getDeclaredFields()) {
					if (s.getType().equals(List.class)) {
						sb.append("   \"" + s.getName() + "\" : [");
						List<String> list = (List<String>) s.get(object);
						for (int i = 0; i < list.size(); i++) {
							sb.append("\"" + list.get(i) + "\"");
							if (i < list.size() - 1) {
								sb.append(",");
							}
						}
						sb.append("]");
					} else {
						sb.append("   \"" + s.getName() + "\" : \""
								+ s.get(object) + "\", \n");
					}
				}
				sb.append("}");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}
