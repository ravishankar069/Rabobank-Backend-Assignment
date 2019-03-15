package com.rabo.statementprocessorservice.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import org.springframework.stereotype.Component;

/*
 * Class which used to split the record based on distinct key
 */
@Component
public class UniqueReferenceUtility {
	public <T> Predicate<T> distinctByReference(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
