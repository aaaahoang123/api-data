package entity;

public class ApiData<T> {
    private T data;

    public ApiData() {
    }

    public ApiData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
