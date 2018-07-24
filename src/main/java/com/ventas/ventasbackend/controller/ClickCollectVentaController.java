package com.ventas.ventasbackend.controller;

import com.ventas.ventasbackend.controller.response.ClickCollectResponse;
import com.ventas.ventasbackend.service.impl.ClickCollectVentaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ClickCollectVentaController {
    @Autowired
    @Qualifier("clickCollectVentaService")
    private ClickCollectVentaServiceImpl service;

    @GetMapping("/venta-clickCollect/{inicio}/{fin}")
    public ClickCollectResponse ventaNeta (@PathVariable int inicio, @PathVariable int fin) {//Para recibir por post e usa RequestBody
        return service.dataClickCollectVenta(inicio, fin);
    }
}
