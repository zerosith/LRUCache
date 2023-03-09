package org.jipial.datastructures;

public class App 
{
    public static void main( String[] args )
    {
        LRUCache <Integer,Integer> lruCache = new LRUCache<>(2);
        lruCache.put(1,1);
        lruCache.put(2,2);
        System.out.println( lruCache );
        lruCache.get(1);
        System.out.println( lruCache );
        lruCache.put(3,3);
        System.out.println( lruCache );
        lruCache.get(4);
        System.out.println( lruCache );
        lruCache.remove(1);
        System.out.println( lruCache );

        //Case2
        LRUCache <Integer,Integer> lru2 = new LRUCache<>(10);
        for (int i = 0; i < 10  ; i++) {
            lru2.put(i,i);
        }
        System.out.println( lru2 );
        lru2.get(6);
        System.out.println( lru2 );
        lru2.remove(9);
        System.out.println( lru2 );

    }
}
