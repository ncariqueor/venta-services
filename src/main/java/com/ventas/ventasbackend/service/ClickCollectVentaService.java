package com.ventas.ventasbackend.service;

import com.ventas.ventasbackend.entity.dtoClickCollectVenta;

import java.util.List;

public interface ClickCollectVentaService {
    public abstract List<dtoClickCollectVenta> dataClickCollectVenta (int inicio, int fin);
}
