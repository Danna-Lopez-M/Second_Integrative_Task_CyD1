package dataStructures;

public class HashNode<K, V> {
        private final K key;
        private final V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        public K getKey() {
            return key;
        }

        public void setNext(HashNode<K, V> node) {
            next = node;
        }

        public V getValue() {
            return value;
        }

        public boolean colission(HashNode<K, V> node) {
            node.setNext(this.next);
            this.next = node;
            return true;
        }
        public HashNode<K, V> getNext() {
            return next;
        }

}
