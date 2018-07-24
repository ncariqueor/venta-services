package com.ventas.ventasbackend.service;

import com.ventas.ventasbackend.controller.response.ClickCollectResponse;

import java.util.List;

public interface ClickCollectVentaService {
    public abstract ClickCollectResponse dataClickCollectVenta (int inicio, int fin);
}
