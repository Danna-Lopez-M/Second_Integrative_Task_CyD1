package dataStructures;

public class SetLinkedList<T extends Comparable<T>>  {
    private SetNode<T> head;
    private int size = 0;
    public SetLinkedList(){
        this.head = null;
    }

    public SetNode<T> getHead() {
        return this.head;
    }

    public void setHead(SetNode<T> head) {
        this.head = head;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void add(T item){
        SetNode<T> helper = new SetNode<>(item);
        add(helper);
        size++;
    }
    // LIFO approach
    private void add(SetNode<T> node){
        // when list is empty
        if (this.head == null){
            this.head = node;
        } else {
            // el siguiente de mi nodo sera el que
            // hasta ahora era la cabeza.
            node.setNext(this.head);
            // el previo de la anterior cabeza sera el nuevo nodo.
            this.head = node;
        }
    }
    public String printList(){
        return printList(this.head);
    }
    private String printList(SetNode<T> current){
        String str = "";
        // dos casos base: cuando la lista esta vacia
        // y cuando llego al final de la lista
        // list is empty
        if(this.head == null){
            str = "set is empty";
        } else if (current.getNext() == null) {
            str += "" + current.getItem();
        }else {
            str += current.getItem() + " " + printList(current.getNext());
        }
        return str;
    }

    public boolean contains(T e){
        boolean result = false;
        SetNode<T> current = this.head;
        if (this.head != null){
            while( !result  && current != null){
                if (current.getItem().compareTo(e) == 0) result = true;
                current = current.getNext();
            }
        }
        return result;
    }
    public boolean isEmpty(){
        return this.head == null;
    }
    public boolean clear(){
        boolean result = false;
        if (this.head != null) {
            this.head = null;
            result = true;
        }
        return result;
    }
    public boolean delete(T e){
        boolean result = false;
        SetNode<T> current = this.head;
        SetNode<T> prev = null;
        if (this.head.getItem().compareTo(e) == 0){
            this.head = this.head.getNext();
            size--;
            result = true;
        }else{
            while( !result  && current != null){
                if (current.getItem().compareTo(e) == 0) {
                    prev.setNext(current.getNext());
                    size--;
                    result = true;
                }
                prev = current;
                current = current.getNext();
            }
        }
        return result;
    }
    // voy agregando todos los nodos del conjunto B al conjunto A
    public boolean union(Set<T> set){
        boolean result = false;
        // ojo: hay que verificar duplicados.
        SetNode<T> current = set.getFirstElement();
        while(current != null){
            // si ya tengo este elemento, no lo puedo insertar
            if(!contains(current.getItem())) {
                add(current.getItem());
                // se ha agregado por lo menos un elemento
                result = true;
            }
            current = current.getNext();
        }
        return result;
    }
    // en una nueva lista generica agrego los elementos comunes entre el
    // conjunto A y el conjunto B (pasado por parametro).
    public SetLinkedList<T> intersection(Set<T> set){
        SetLinkedList<T> intersectionList = new SetLinkedList<>();
        SetNode<T> currentA = this.head;
        if(currentA != null) {
            while (currentA != null) {
                T item = currentA.getItem();
                SetNode<T> currentB = set.getFirstElement();
                while (currentB != null) {
                    // si son iguales, van en la interseccion
                    if (currentB.getItem().compareTo(item) == 0) {
                        intersectionList.add(item);
                    }
                    currentB = currentB.getNext();

                }
                currentA = currentA.getNext();
            }
        }
        return intersectionList;
    }

    // el conjunto A es el de esta clase, el conjunto B es el que se pasa como parametro
    // por lo tanto, la diferencia sera el conjunto en el cual, de todos sus elementos, ninguno de ellos
    // se encuentra en B.
    public void difference(Set<T> set){
        //GenericLinkedList<T> differenceList = new GenericLinkedList<>();
        SetNode<T> currentA = this.head;
        while (currentA != null){
            SetNode<T> currentB = set.getFirstElement();
            while (currentB != null){
                // si son iguales, este elemento no va.
                // por lo tanto, lo elimino de mi actual lista.
                if (currentA.getItem().compareTo(currentB.getItem()) == 0){
                    delete(currentA.getItem());
                }
                currentB = currentB.getNext();
            }
            currentA = currentA.getNext();
        }
    }
}
