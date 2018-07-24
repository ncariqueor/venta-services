package com.ventas.ventasbackend.controller.response;

import com.ventas.ventasbackend.entity.dtoClickCollectVenta;

import java.util.List;

public class ClickCollectResponse {

    private List<dtoClickCollectVenta> clickCollectVenta;

    public List<dtoClickCollectVenta> getClickCollectVenta() {
        return clickCollectVenta;
    }

    public void setClickCollectVenta(List<dtoClickCollectVenta> clickCollectVenta) {
        this.clickCollectVenta = clickCollectVenta;
    }
}
