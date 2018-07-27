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

        this.SQL = "SELECT\n" +
                "  actual.tienda as tienda,\n" +
                "  cast(actual.pxq_ingreso as unsigned) as pxq_ingreso_actual,\n" +
                "  cast(actual.numorden_ingreso as unsigned) as numorden_ingreso_actual,\n" +
                "  cast(actual.cosprom_ingreso as unsigned) as cosprom_ingreso_actual,\n" +
                "  cast(actual.pxq_nota_credito as unsigned) as pxq_nota_credito_actual,\n" +
                "  cast(actual.numorden_nota_credito as unsigned) as numorden_nota_credito_actual,\n" +
                "  cast(actual.cosprom_nota_credito as unsigned) as cosprom_nota_credito_actual,\n" +
                "\n" +
                "  cast(anterior.pxq_ingreso as unsigned) as pxq_ingreso_anterior,\n" +
                "  cast(anterior.numorden_ingreso as unsigned) as numorden_ingreso_anterior,\n" +
                "  cast(anterior.cosprom_ingreso as unsigned) as cosprom_ingreso_anterior,\n" +
                "  cast(anterior.pxq_nota_credito as unsigned) as pxq_nota_credito_anterior,\n" +
                "  cast(anterior.numorden_nota_credito as unsigned) as numorden_nota_credito_anterior,\n" +
                "  cast(anterior.cosprom_nota_credito as unsigned) as cosprom_nota_credito_anterior\n" +
                "\n" +
                "FROM\n" +
                "\n" +
                "  (SELECT\n" +
                "     d.tienda_utf8      AS tienda,\n" +
                "     d.codcomdes        as codcomdes,\n" +
                "     CASE WHEN i.pxq IS NULL\n" +
                "       THEN 0\n" +
                "     ELSE i.pxq END     AS pxq_ingreso,\n" +
                "     case when i.numorden is null then 0 else i.numorden end AS numorden_ingreso,\n" +
                "     CASE WHEN i.cosprom IS NULL\n" +
                "       THEN 0\n" +
                "     ELSE i.cosprom END AS cosprom_ingreso,\n" +
                "     CASE WHEN n.pxq IS NULL\n" +
                "       THEN 0\n" +
                "     ELSE n.pxq END     AS pxq_nota_credito,\n" +
                "     case when n.numorden is null then 0 else n.numorden end AS numorden_nota_credito,\n" +
                "     CASE WHEN n.cosprom IS NULL\n" +
                "       THEN 0\n" +
                "     ELSE n.cosprom END AS cosprom_nota_credito\n" +
                "   FROM distribucion.tiendas d LEFT JOIN (SELECT\n" +
                "                                            i.codcomdes                AS codcomdes,\n" +
                "                                            sum(i.pxq)                 AS pxq,\n" +
                "                                            count(DISTINCT i.numorden) AS numorden,\n" +
                "                                            sum(i.cosprom)             AS cosprom\n" +
                "                                          FROM venta_paris.ingreso i\n" +
                "                                          WHERE i.fectrantsl BETWEEN " + inicio + " AND " + fin + " AND i.coddesp = 22\n" +
                "                                          GROUP BY i.codcomdes) i ON i.codcomdes = d.codcomdes\n" +
                "     LEFT JOIN (SELECT\n" +
                "                  n.codcomdes                AS codcomdes,\n" +
                "                  sum(n.pxq)                 AS pxq,\n" +
                "                  count(DISTINCT n.numorden) AS numorden,\n" +
                "                  sum(n.cosprom)             AS cosprom\n" +
                "                FROM venta_paris.nota_credito n\n" +
                "                WHERE n.feceminc BETWEEN " + inicio + " AND " + fin + " AND n.coddesp = 22\n" +
                "                GROUP BY n.codcomdes) n ON n.codcomdes = d.codcomdes) actual\n" +
                "\n" +
                "  INNER JOIN\n" +
                "\n" +
                "  (SELECT\n" +
                "     d.tienda_utf8      AS tienda,\n" +
                "     d.codcomdes        as codcomdes,\n" +
                "     CASE WHEN i.pxq IS NULL\n" +
                "       THEN 0\n" +
                "     ELSE i.pxq END     AS pxq_ingreso,\n" +
                "     case when i.numorden is null then 0 else i.numorden end AS numorden_ingreso,\n" +
                "     CASE WHEN i.cosprom IS NULL\n" +
                "       THEN 0\n" +
                "     ELSE i.cosprom END AS cosprom_ingreso,\n" +
                "     CASE WHEN n.pxq IS NULL\n" +
                "       THEN 0\n" +
                "     ELSE n.pxq END     AS pxq_nota_credito,\n" +
                "     case when n.numorden is null then 0 else n.numorden end AS numorden_nota_credito,\n" +
                "     CASE WHEN n.cosprom IS NULL\n" +
                "       THEN 0\n" +
                "     ELSE n.cosprom END AS cosprom_nota_credito\n" +
                "   FROM distribucion.tiendas d LEFT JOIN (SELECT\n" +
                "                                            i.codcomdes                AS codcomdes,\n" +
                "                                            sum(i.pxq)                 AS pxq,\n" +
                "                                            count(DISTINCT i.numorden) AS numorden,\n" +
                "                                            sum(i.cosprom)             AS cosprom\n" +
                "                                          FROM venta_paris.ingreso i\n" +
                "                                          WHERE i.fectrantsl BETWEEN date_format(" + inicio + " - interval 364 day, '%Y%m%d') AND date_format(" + inicio + " - interval 364 day, '%Y%m%d') AND i.coddesp = 22\n" +
                "                                          GROUP BY i.codcomdes) i ON i.codcomdes = d.codcomdes\n" +
                "     LEFT JOIN (SELECT\n" +
                "                  n.codcomdes                AS codcomdes,\n" +
                "                  sum(n.pxq)                 AS pxq,\n" +
                "                  count(DISTINCT n.numorden) AS numorden,\n" +
                "                  sum(n.cosprom)             AS cosprom\n" +
                "                FROM venta_paris.nota_credito n\n" +
                "                WHERE n.feceminc BETWEEN date_format(" + inicio + " - interval 364 day, '%Y%m%d') AND date_format(" + inicio + " - interval 364 day, '%Y%m%d') AND n.coddesp = 22\n" +
                "                GROUP BY n.codcomdes) n ON n.codcomdes = d.codcomdes) anterior\n" +
                "\n" +
                "    ON actual.codcomdes = anterior.codcomdes";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(SQL);

        for (Map<String, Object> row : rows)
        {
            dtoClickCollectVenta dto = new dtoClickCollectVenta();

            dto.setTienda((String) row.get("tienda"));
            dto.setPxq_ingreso_actual((BigInteger) row.get("pxq_ingreso_actual"));
            dto.setNumorden_ingreso_actual((BigInteger) row.get("numorden_ingreso_actual"));
            dto.setCosprom_ingreso_actual((BigInteger) row.get("cosprom_ingreso_actual"));
            dto.setPxq_nota_credito_actual((BigInteger) row.get("pxq_nota_credito_actual"));
            dto.setNumorden_nota_credito_actual((BigInteger) row.get("numorden_nota_credito_actual"));
            dto.setCosprom_nota_credito_actual((BigInteger) row.get("cosprom_nota_credito_actual"));

            dto.setPxq_ingreso_anterior((BigInteger) row.get("pxq_ingreso_anterior"));
            dto.setNumorden_ingreso_anterior((BigInteger) row.get("numorden_ingreso_anterior"));
            dto.setCosprom_ingreso_anterior((BigInteger) row.get("cosprom_ingreso_anterior"));
            dto.setPxq_nota_credito_anterior((BigInteger) row.get("pxq_nota_credito_anterior"));
            dto.setNumorden_nota_credito_anterior((BigInteger) row.get("numorden_nota_credito_anterior"));
            dto.setCosprom_nota_credito_anterior((BigInteger) row.get("cosprom_nota_credito_anterior"));

            l.add(dto);
        }

        ClickCollectResponse response = new ClickCollectResponse();

        response.setData(l);

        return response;
    }
}
