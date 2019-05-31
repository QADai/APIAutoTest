package qadai.auto.apitest.common.enums;

public enum HttpType {
	POST("post"),
    GET("get"),
    PUT("put"),
    DELETE("delete");

    private String value;

    HttpType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
