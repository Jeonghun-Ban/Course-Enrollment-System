package Framework;

import java.io.Serializable;

public class Launcher implements Serializable {
    private static final long serialVersionUID = 1L;
    private String className, methodName;
    private Class<?>[] parameterTypes;
    private Object[] parameters;

    public Launcher(String className, String methodName, Class<?>[] parameterTypes, Object[] parameters) {
        this.className = className;
        this.methodName = methodName;
        this.parameterTypes = parameterTypes;
        this.parameters = parameters;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public Object[] getParameters() {
        return parameters;
    }
}
