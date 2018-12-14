package com.masterc.jfinal.converter;

import java.util.HashMap;
import java.util.Map;

import com.masterc.jfinal.converter.Converters.BigDecimalConverter;
import com.masterc.jfinal.converter.Converters.BigIntegerConverter;
import com.masterc.jfinal.converter.Converters.BooleanConverter;
import com.masterc.jfinal.converter.Converters.ByteArrayConverter;
import com.masterc.jfinal.converter.Converters.ByteConverter;
import com.masterc.jfinal.converter.Converters.DateConverter;
import com.masterc.jfinal.converter.Converters.DoubleConverter;
import com.masterc.jfinal.converter.Converters.FloatConverter;
import com.masterc.jfinal.converter.Converters.IntegerConverter;
import com.masterc.jfinal.converter.Converters.LongConverter;
import com.masterc.jfinal.converter.Converters.ShortConverter;
import com.masterc.jfinal.converter.Converters.SqlDateConverter;
import com.masterc.jfinal.converter.Converters.TimeConverter;
import com.masterc.jfinal.converter.Converters.TimestampConverter;
import com.jfinal.core.JFinal;
import com.jfinal.kit.StrKit;

public class JsonTypeConverter {

	private final Map<Class<?>, IConverter<?>> converterMap = new HashMap<Class<?>, IConverter<?>>();
	private static JsonTypeConverter me = new JsonTypeConverter();

	private JsonTypeConverter() {
		regist(Integer.class, new IntegerConverter());
		regist(int.class, new IntegerConverter());
		regist(Long.class, new LongConverter());
		regist(long.class, new LongConverter());
		regist(Double.class, new DoubleConverter());
		regist(double.class, new DoubleConverter());
		regist(Float.class, new FloatConverter());
		regist(float.class, new FloatConverter());
		regist(Boolean.class, new BooleanConverter());
		regist(boolean.class, new BooleanConverter());
		regist(java.util.Date.class, new DateConverter());
		regist(java.sql.Date.class, new SqlDateConverter());
		regist(java.sql.Time.class, new TimeConverter());
		regist(java.sql.Timestamp.class, new TimestampConverter());
		regist(java.math.BigDecimal.class, new BigDecimalConverter());
		regist(java.math.BigInteger.class, new BigIntegerConverter());
		regist(byte[].class, new ByteArrayConverter());

		regist(Short.class, new ShortConverter());
		regist(short.class, new ShortConverter());
		regist(Byte.class, new ByteConverter());
		regist(byte.class, new ByteConverter());
	}

	public static JsonTypeConverter me() {
		return me;
	}

	public <T> void regist(Class<T> type, IConverter<T> converter) {
		converterMap.put(type, converter);
	}

	/**
	 * 将 String 数据转换为指定的类型
	 * 
	 * @param type
	 *            需要转换成为的数据类型
	 * @param s
	 *            被转换的 String 类型数据，注意： s 参数不接受 null 值，否则会抛出异常
	 * @return 转换成功的数据
	 */
	public final Object convert(Class<?> type, String s) throws ConverterException {
		// mysql type: varchar, char, enum, set, text, tinytext, mediumtext, longtext
		if (type == String.class) {
			return ("".equals(s) ? null : s); // 用户在表单域中没有输入内容时将提交过来 "", 因为没有输入,所以要转成 null.
		}
		if (StrKit.isBlank(s)) { // 前面的 String跳过以后,所有的空字符串全都转成 null, 这是合理的
			return null;
		}
		s = s.trim();
		// 以上两种情况无需转换,直接返回, 注意
		// String.class提前处理

		// --------
		IConverter<?> converter = converterMap.get(type);
		if (converter != null) {
			try {
				return converter.convert(s);
			} catch (Exception e) {
				throw new ConverterException(e.getMessage(), e.getCause());
			}
		}
		if (JFinal.me().getConstants().getDevMode()) {
			throw new RuntimeException("Please add code in " + JsonTypeConverter.class
					+ ". The type can't be converted: " + type.getName());
		} else {
			throw new RuntimeException(
					type.getName() + " can not be converted, please use other type of attributes in your model!");
		}
	}
}
