package com.ventas.ventasbackend.repository;

import com.ventas.ventasbackend.controller.response.ClickCollectResponse;
import com.ventas.ventasbackend.entity.dtoClickCollectVenta;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository("daoClickCollectVenta")
public class daoClickCollectVenta {
    private static final Log LOG = LogFactory.getLog(daoClickCollectVenta.class);

    @Autowired
    @Qualifier("JdbcVta")
    private JdbcTemplate jdbcTemplate;

    private String SQL;

    public ClickCollectResponse ventaNeta (int inicio, int fin) {
        List<dtoClickCollectVenta> l = new ArrayList<dtoClickCollectVenta>();

        this.SQL = "select actual.fecha as fecha_actual, actual.ingreso_neto as ingreso_neto_actual, anterior.fecha as fecha_anterior, anterior.ingreso_neto as ingreso_neto_anterior\n" +
                "\n" +
                "  from\n" +
                "\n" +
                "(select i.fecha as fecha, cast(round((if(i.pxq is null, 0, i.pxq) - if(n.pxq is null, 0, n.pxq))/1.19, 0) as unsigned) as ingreso_neto\n" +
                "\n" +
                "from\n" +
                "\n" +
                "(select i.fectrantsl as fecha, sum(i.pxq) as pxq\n" +
                "from venta_paris.ingreso i\n" +
                "where i.fectrantsl between " + inicio + " and " + fin + " and i.coddesp = 22\n" +
                "group by i.fectrantsl\n" +
                "order by i.fectrantsl desc) i\n" +
                "\n" +
                "left join\n" +
                "\n" +
                "  (select n.feceminc as fecha, sum(n.pxq) as pxq\n" +
                "from venta_paris.nota_credito n\n" +
                "where n.feceminc between " + inicio + " and " + fin + " and n.coddesp = 22\n" +
                "group by n.feceminc\n" +
                "order by n.feceminc desc) n\n" +
                "\n" +
                "  on i.fecha = n.fecha) actual\n" +
                "\n" +
                "left join\n" +
                "\n" +
                "  (select i.fecha as fecha, cast(round((if(i.pxq is null, 0, i.pxq) - if(n.pxq is null, 0, n.pxq))/1.19, 0) as unsigned) as ingreso_neto\n" +
                "\n" +
                "from\n" +
                "\n" +
                "(select i.fectrantsl as fecha, sum(i.pxq) as pxq\n" +
                "from venta_paris.ingreso i\n" +
                "where i.fectrantsl between date_format(" + inicio + " - interval 364 day, '%Y%m%d') and date_format(" + fin + " - interval 364 day, '%Y%m%d') and i.coddesp = 22\n" +
                "group by i.fectrantsl\n" +
                "order by i.fectrantsl desc) i\n" +
                "\n" +
                "left join\n" +
                "\n" +
                "  (select n.feceminc as fecha, sum(n.pxq) as pxq\n" +
                "from venta_paris.nota_credito n\n" +
                "where n.feceminc between date_format(" + inicio + " - interval 364 day, '%Y%m%d') and date_format(" + fin + " - interval 364 day, '%Y%m%d') and n.coddesp = 22\n" +
                "group by n.feceminc\n" +
                "order by n.feceminc desc) n\n" +
                "\n" +
                "  on i.fecha = n.fecha) anterior\n" +
                "\n" +
                "  on anterior.fecha = date_format(actual.fecha - interval 364 day, '%Y%m%d')";

        LOG.info("Se consulto la query " + SQL);

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(SQL);

        LOG.info("Se consulto la query " + SQL);

        for (Map<String, Object> row : rows)
        {


            dtoClickCollectVenta dto = new dtoClickCollectVenta();

            dto.setFechaActual((Integer) row.get("fecha_actual"));
            dto.setIngresoNetoActual((BigInteger) row.get("ingreso_neto_actual"));

            dto.setFechaAnterior((Integer) row.get("fecha_anterior"));
            dto.setIngresoNetoAnterior((BigInteger) row.get("ingreso_neto_anterior"));

            l.add(dto);
        }

        ClickCollectResponse response = new ClickCollectResponse();

        response.setClickCollectVenta(l);

        return response;
    }
}
