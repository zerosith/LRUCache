package org.jipial.datastructures;

import java.util.*;

public class LRUCache<K,V> {


    private class LRUCacheNode <K,V> {
        V value;
        K key;

        public  LRUCacheNode(K key,V value){
            this.value = value;
            this.key = key;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "LRUCacheNode{" +
                    "value=" + value +
                    ", key=" + key +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            LRUCacheNode<?, ?> that = (LRUCacheNode<?, ?>) o;
            return value.equals(that.value) && key.equals(that.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, key);
        }
    }


    private Integer capacity;
    private Map<K, LRUCacheNode<K, V> > values;
    private List< LRUCacheNode <K,V> > valuesL;


    public LRUCache (int capacity){
        this. capacity = capacity;
        values = new HashMap<>();
        valuesL = new LinkedList<>();

    }

    public void put(K key, V value){
        if(!capacity.equals(0)){
            LRUCacheNode <K,V>aux =new LRUCacheNode<>(key, value);
            values.put(key,aux);
            valuesL.add(aux);
            capacity --;

        }else{
            //Implement eviction policy
            LRUCacheNode<K, V> node = valuesL.remove(0);
            values.remove(node.key);
            LRUCacheNode<K, V> nNode = new LRUCacheNode<>(key,value);
            valuesL.add(nNode);
            values.put(key,nNode);
        }
    }

    public Optional<V> get(K key){

        Optional<V> valueRet = Optional.empty();
        if(values.containsKey(key)){
            for (LRUCacheNode <K,V> node: valuesL) {
                if(node.key.equals(key)){
                    boolean removed = valuesL.remove(node);
                    if(removed){
                        System.out.println("Removed:"+ "(" + node.key + ", "+ node.value + ")" );
                    }
                    valuesL.add(node);
                    break;
                }
            }
            valueRet = Optional.of(values.get(key).value) ;
        }

        return  valueRet;
    }

    public Integer size(){
        return valuesL.size();
    }

    public Integer getCapacity(){
        return capacity;
    }

    public Optional<V> remove(K key){

        Optional<LRUCacheNode <K,V>> removed;
        Optional<V> ret = Optional.empty();
         if(values.containsKey(key)) {
             removed = Optional.of(values.remove(key));
             valuesL.remove(removed.get());
             ret = Optional.of(removed.get().getValue());
             capacity++;
         }
        return ret;
    }

    @Override
    public String toString() {
        return "LRUCache{" +
                "capacity=" + capacity +
                ", values=" + values +
                ", valuesL=" + valuesL +
                '}';
    }
}
