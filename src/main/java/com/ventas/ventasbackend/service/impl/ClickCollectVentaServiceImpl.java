package com.ventas.ventasbackend.service.impl;

import com.ventas.ventasbackend.entity.dtoClickCollectVenta;
import com.ventas.ventasbackend.repository.daoClickCollectVenta;
import com.ventas.ventasbackend.service.ClickCollectVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("clickCollectVentaService")
public class ClickCollectVentaServiceImpl implements ClickCollectVentaService {

    @Autowired
    @Qualifier("daoClickCollectVenta")
    private daoClickCollectVenta dao;

    @Override
    public List<dtoClickCollectVenta> dataClickCollectVenta (int inicio, int fin) {
        return dao.ventaNeta(inicio, fin);
    }
}
