package com.wxx.springbootvue.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 从jwt解析得到的数据是Object类型，转换为具体类型可能出现空指针，
 * 这个工具类进行了一些转换
 *
 * @author Wang
 */
public class ObjectUtils {

	public static String toString(Object obj) {
		if (obj == null) {
			return null;
		}
		return obj.toString();
	}

	public static Long toLong(Object obj) {
		if (obj == null) {
			return 0L;
		}
		if (obj instanceof Double || obj instanceof Float) {
			return Long.valueOf(StringUtils.substringBefore(obj.toString(), "."));
		}
		if (obj instanceof Number) {
			return Long.valueOf(obj.toString());
		}
		if (obj instanceof String) {
			return Long.valueOf(obj.toString());
		} else {
			return 0L;
		}
	}

	public static Integer toInt(Object obj) {
		return toLong(obj).intValue();
	}

    /**
     * Object对象转List
     * @param obj Object对象
     * @param clazz 需要被强转的class
     * @param <T> /
     * @return /
     */
	public static <T> List<T> castList(Object obj, Class<T> clazz) {
		List<T> result = new ArrayList<T>();
		if (obj instanceof List<?>) {
			for (Object o : (List<?>) obj) {
				result.add(clazz.cast(o));
			}
			return result;
		}
		return null;
	}
}