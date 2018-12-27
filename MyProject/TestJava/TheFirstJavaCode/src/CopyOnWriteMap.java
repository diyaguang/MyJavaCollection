import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @package: PACKAGE_NAME
 * @author: diyaguang
 * @date: 2018/12/27 - 4:03 PM
 * @description:
 */
public class CopyOnWriteMap<K,V> implements Map<K,V>,Cloneable {
    private volatile Map<K,V> internalMap;

    public CopyOnWriteMap(){
        internalMap = new HashMap<K,V>();
    }

    public V put(K key,V value){
        synchronized(this){
            Map<K,V> newMap = new HashMap<K,V>(internalMap);
            V val = newMap.put(key,value);
            internalMap = newMap;
            return val;
        }
    }

    public V get(Object key){
        return internalMap.get(key);
    }

    public void putAll(Map<? extends K,? extends  V> newData){
        synchronized (this){
            Map<K,V> newMap = new HashMap<K,V>(internalMap);
            newMap.putAll(newData);
            internalMap = newMap;
        }
    }

    public int size(){
        return internalMap.size();
    }
    public boolean isEmpty(){
        return internalMap.isEmpty();
    }
    public boolean containsKey(Object key){
        return internalMap.containsKey(key);
    }
    public boolean containsValue(Object value){
        return internalMap.containsValue(value);
    }
    public V remove(Object key){
        return internalMap.remove(key);
    }
    public void clear(){
        internalMap.clear();
    }
    public Set<K> keySet(){
        return internalMap.keySet();
    }
    public Collection<V> values(){
        return internalMap.values();
    }
    public Set<Entry<K,V>> entrySet(){
        return internalMap.entrySet();
    }
}
