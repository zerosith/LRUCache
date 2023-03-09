package org.jipial.datastructures;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class LRUCacheTest {
    private LRUCache<Integer,Integer> cache;

    @Before
    public void setUp(){
        //Setting up cache with 5 opf capacity
        cache = new LRUCache<>(5);
        cache.put(1,1);
        cache.put(5,6);
        cache.put(3,3);
    }

    @Test
    public void testPut() {
        assertThat(cache.size()).isEqualTo(3);
        assertThat(cache.getCapacity()).isEqualTo(2);
    }

    @Test
    public void testGetExistentElement() {
        int key = 3;
        Optional<Integer> expectedValue = Optional.of(3);
        assertThat(cache.get(key)).isEqualTo(expectedValue);
    }

    @Test
    public void testGetNonExistentElement(){
        int key =2;
        Optional<Integer> expectedValue = Optional.empty();
        assertThat(cache.get(key)).isEqualTo(expectedValue);
    }

    @Test
    public void testRemoveNonExistentElement() {
        int key = 2;
        cache.remove(key);
        //2 key is not in the cache so capacity is still the same as in setUp
        assertThat(cache.getCapacity()).isEqualTo(2);
        assertThat(cache.size()).isEqualTo(3);
    }

    @Test
    public void testRemoveExistentElement() {
        int key = 5;
        cache.remove(key);
        assertThat(cache.getCapacity()).isEqualTo(3);
        assertThat(cache.size()).isEqualTo(2);
    }

    @Test
    public void testEvictionPolicy(){

        cache.put(7,7);
        assertThat(cache.getCapacity()).isEqualTo(1);
        cache.put(11,34);
        assertThat(cache.getCapacity()).isEqualTo(0);
        int expectedValueToEvict = 1;
        assertThat(cache.put(14,54)).isEqualTo(Optional.of(expectedValueToEvict));
    }

    @Test
    public void testTestToString() {
        String expected = "LRUCache{capacity=2, values={1=LRUCacheNode{value=1, key=1}, 3=LRUCacheNode{value=3, key=3}, 5=LRUCacheNode{value=6, key=5}}, valuesL=[LRUCacheNode{value=1, key=1}, LRUCacheNode{value=6, key=5}, LRUCacheNode{value=3, key=3}]}";
        assertThat(cache.toString()).isEqualTo(expected);
    }

    @Test
    public void testEqualsSymmetric() throws IllegalAccessException {
        List lst = (List) FieldUtils.readField(cache,"valuesL", true );
        assertThat(lst.get(0).hashCode()).isEqualTo(lst.get(0).hashCode());

        assertThat(lst.get(0)).isEqualTo(lst.get(0));
        assertThat(lst.get(0)).isNotEqualTo(lst.get(1));

    }

    @Test
    public void testGetElementWithEmptyCache(){
        cache.remove(1);
        cache.remove(5);
        cache.remove(3);
        assertThat(cache.get(1)).isEqualTo(Optional.empty());
        assertThat(cache.get(5)).isEqualTo(Optional.empty());
        assertThat(cache.get(3)).isEqualTo(Optional.empty());
    }
}