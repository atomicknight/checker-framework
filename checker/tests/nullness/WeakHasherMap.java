import java.util.Map;
import java.util.AbstractMap;

import org.checkerframework.framework.qual.*;
import org.checkerframework.checker.nullness.qual.*;
import org.checkerframework.checker.regex.qual.*;
import org.checkerframework.checker.initialization.qual.*;

//:: error: (initialization.fields.uninitialized)
public abstract class WeakHasherMap<K, V> extends AbstractMap<K, V> implements
        Map<K, V> {
    private Map<Object, V> hash;

    @org.checkerframework.dataflow.qual.Pure
    //:: error: (override.param.invalid)
    public boolean containsKey(Object key) {
        //:: warning: [unchecked] unchecked cast
        K kkey = (K) key;
        hash.containsKey(null);
        return true;
    }
}
