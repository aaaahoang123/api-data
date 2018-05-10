package entity;

import interfaces.IApiData;

public class ApiOneData implements IApiData {
    private JsonData data;

    public ApiOneData() {
    }

    public ApiOneData(JsonData data) {

        this.data = data;
    }

    @Override
    public JsonData getData() {
        return data;
    }

    public void setData(JsonData data) {
        this.data = data;
    }
}
