package com.moinros.project.common.exception;

public class Base64CodeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String errorMessage;

	public Base64CodeException() {
		super();
		super.printStackTrace();
	}

	@Override
	public void printStackTrace() {
		System.err.println(errorMessage);
		super.printStackTrace();
	}

	public Base64CodeException(String message) {
		super(message);
		this.errorMessage = message;
		printStackTrace();
	}

	public Base64CodeException(Base64ExceptionEnum bee, Class<?> clazz, String name) {
		super();
		if (bee == Base64ExceptionEnum.METHOD_IS_VOID) {
			this.errorMessage = methodIsVoid(clazz, name);
		} else if (bee == Base64ExceptionEnum.METHOD_NO_PARAM) {
			this.errorMessage = methodNoParam(clazz, name);
		} else if (bee == Base64ExceptionEnum.FIELD_IS_NULL) {
			this.errorMessage = fieldIsNull(clazz, name);
		} else if (bee == Base64ExceptionEnum.REPEATED) {
			this.errorMessage = repeatedAnnotation(clazz, name);
		}
		printStackTrace();
	}

	public Base64CodeException(Base64ExceptionEnum bee, Class<?> clazz, String paramName, String methodName) {
		super();
		this.errorMessage = noFindParam(clazz, paramName, methodName);
		printStackTrace();
	}

	public Base64CodeException(Base64ExceptionEnum bee, Class<?> clazz, String paramName, String type, String paramType,
                               String methodName) {
		super();
		this.errorMessage = paramTypeMatch(clazz, paramName, type, paramType, methodName);
		printStackTrace();
	}

	public String getErrorMessage() {

		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	private String methodIsVoid(Class<?> clazz, String methodName) {
		StringBuilder sb = new StringBuilder();
		sb.append(">> @Base64Decoder 注解 指定了 [");
		sb.append(clazz);
		sb.append('.');
		sb.append(methodName);
		sb.append("() ] 方法需要对返回值进行Base64解码, 但是此方法并没有返回值 !?");
		return sb.toString();
	}

	private String methodNoParam(Class<?> clazz, String methodName) {
		StringBuilder sb = new StringBuilder();
		sb.append(">> @Base64Encoder 注解 指定了[ ");
		sb.append(clazz);
		sb.append('.');
		sb.append(methodName);
		sb.append("() ] 方法需要对参数进行Base64编码,但是这个方法并没有参数 !?");
		return sb.toString();
	}

	private String repeatedAnnotation(Class<?> clazz, String methodName) {
		StringBuilder sb = new StringBuilder();
		sb.append(">> @Base64Decoder 注解 指定了 [ ");
		sb.append(clazz);
		sb.append("] 需要使用  @Base64Decoder; 但是在 [ ");
		sb.append(clazz);
		sb.append('.');
		sb.append(methodName);
		sb.append("() ] 方法上又再次标记了 @Base64Decoder, 同时 @Base64Decoder.exclude = true !!");
		return sb.toString();
	}

	private String fieldIsNull(Class<?> clazz, String fieldName) {
		StringBuilder sb = new StringBuilder();
		sb.append(">> @Base64Mark 注解指定了 -> ");
		sb.append(clazz);
		sb.append(" 下的 [");
		sb.append(fieldName);
		sb.append(" ] 字段 值不能为 null, 但是编码时匹配到了 null !?");
		return sb.toString();
	}

	private String noFindParam(Class<?> clazz, String paramName, String methodName) {
		StringBuilder sb = new StringBuilder();
		sb.append(">> @Base64Encoder 注解指定了参数名 [ value = ");
		sb.append(paramName);
		sb.append(" ], 但是在 [ ");
		sb.append(clazz);
		sb.append('.');
		sb.append(methodName);
		sb.append(" ] 方法中没有找到对应名字的参数 !?");
		return sb.toString();
	}

	private String paramTypeMatch(Class<?> clazz, String paramName, String type, String paramType, String methodName) {
		StringBuilder sb = new StringBuilder();
		sb.append(">> @Base64Encoder 注解 指定了参数 [ value = ");
		sb.append(paramName);
		sb.append(" ] 的类型为 [ type = ");
		sb.append(type);
		sb.append(".class ], 但是在 [ ");
		sb.append(clazz);
		sb.append('.');
		sb.append(methodName);
		sb.append("() ] 方法中 [ ");
		sb.append(paramName);
		sb.append("] 参数的类型为 [ ");
		sb.append(paramType);
		sb.append(".class ]");
		return sb.toString();
	}

}
