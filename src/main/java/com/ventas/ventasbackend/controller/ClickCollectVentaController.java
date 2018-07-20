package com.ventas.ventasbackend.controller;

import com.ventas.ventasbackend.controller.request.masterRequest;
import com.ventas.ventasbackend.entity.dtoClickCollectVenta;
import com.ventas.ventasbackend.service.impl.ClickCollectVentaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/venta")
public class ClickCollectVentaController {
    @Autowired
    @Qualifier("clickCollectVentaService")
    private ClickCollectVentaServiceImpl service;

    @PostMapping("/venta-clickCollect")
    public List<dtoClickCollectVenta> ventaNeta (@RequestBody masterRequest master) {//Para recibir por get, se usa pathvariable
        return service.dataClickCollectVenta(master.getInicio(), master.getFin());
    }
}
