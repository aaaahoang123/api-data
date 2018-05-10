package entity;

import interfaces.IApiData;

import java.util.List;

public class ApiListData implements IApiData {
    private List<JsonData> data;
    @Override
    public List<JsonData> getData() {
        return data;
    }

    public void setData(List<JsonData> data) {
        this.data = data;
    }

    
}
