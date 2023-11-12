package com.lazarev.springsolid.java;

@SuppressWarnings("all")
public class IS {
    interface Tree<K, V> {
        void add(K key, V value);
        V get(K key);
    }

    interface BalancedTree<K, V> extends Tree<K,V> {
        void balance();
    }

    class RedBlackTree<K, V> implements BalancedTree<K,V> {
        @Override
        public void add(K key, V value) {}

        @Override
        public V get(K key) { return null; }

        @Override
        public void balance() {}
    }

    class BinaryTree<K, V> implements Tree<K, V> {
        @Override
        public void add(K key, V value) {}

        @Override
        public V get(K key) { return null; }
    }
}


