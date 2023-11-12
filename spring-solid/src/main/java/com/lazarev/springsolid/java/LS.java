package com.lazarev.springsolid.java;

public class LS { }

class LS_Wrong {

    interface Tree<K, V> {
        void add(K key, V value);
        V get(K key);
        default void balance(){}
    }

    static class RedBlackTree<K, V> implements Tree<K, V> {
        @Override
        public void add(K key, V value) {}

        @Override
        public V get(K key) { return null; }

        @Override
        public void balance() {}
    }

    static class BinaryTree<K, V> implements Tree<K, V> {
        @Override
        public void add(K key, V value) {}

        @Override
        public V get(K key) { return null; }

        @Override
        public void balance() {
            throw new UnsupportedOperationException("Cannot balance binary tree");
        }
    }

    //RedBlackTree, BinaryTree -> error
    void test(Tree tree){
        tree.balance();
    }
}
