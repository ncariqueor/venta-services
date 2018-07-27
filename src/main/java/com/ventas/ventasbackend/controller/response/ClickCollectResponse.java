package com.ventas.ventasbackend.controller.response;

import com.ventas.ventasbackend.entity.dtoClickCollectVenta;

import java.util.List;

public class ClickCollectResponse {

    private List<dtoClickCollectVenta> data;

    public List<dtoClickCollectVenta> getData() {
        return data;
    }

    public void setData(List<dtoClickCollectVenta> clickCollectVenta) {
        this.data = clickCollectVenta;
    }
}
