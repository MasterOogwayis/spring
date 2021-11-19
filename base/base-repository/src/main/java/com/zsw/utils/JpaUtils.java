package com.zsw.utils;

import org.springframework.util.CollectionUtils;

import javax.persistence.Tuple;
import javax.persistence.TupleElement;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ZhangShaowei on 2019/12/26 12:15
 **/
public class JpaUtils {

    /**
     * repository Transformers.ALIAS_TO_ENTITY_MAP 的替换方式
     *
     * @param list List<Tuple>
     * @return List<Map < String, Object>>
     */
    public static List<Map<String, Object>> map(List<Tuple> list) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        return list.stream().map(JpaUtils::map).collect(Collectors.toList());
    }

    /**
     * @param tuple Tuple
     * @return Map<String, Object>
     */
    public static Map<String, Object> map(Tuple tuple) {
        return tuple.getElements()
                .stream()
                .collect(Collectors.toMap(TupleElement::getAlias, e -> tuple.get(e.getAlias())));
    }

}
