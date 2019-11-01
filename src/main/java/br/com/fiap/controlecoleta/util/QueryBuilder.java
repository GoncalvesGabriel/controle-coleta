package br.com.fiap.controlecoleta.util;

import java.util.Map;
import javax.persistence.Query;

public class QueryBuilder {

    public static void bindParameters(Query query, Map<String, Object> params) {
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
    }

}
