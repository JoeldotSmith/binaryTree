import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import CITS2200.BinaryTree;
import CITS2200.Iterator;

@SuppressWarnings("unchecked")
/*
 * Binary Tree class
 * 
 * @author Joel Smith
 */
public class BinTree<E> extends BinaryTree<E> {

    public BinTree() {
        super();
    }

    public BinTree(E item, BinaryTree<E> ltree, BinaryTree<E> rtree) {
        super(item, ltree, rtree);
    }


    /*
     * Equals
     * 
     * returns true if two trees are the same
     */
    public boolean equals(Object o) {
        if (!(o instanceof BinaryTree)) {
            return false;
        }

        BinaryTree<Object> object = (BinaryTree<Object>) o;
        if (this.isEmpty() ^ object.isEmpty()) {
            return false;
        }
        if (this.isEmpty() && object.isEmpty()) {
            return true;
        }

        boolean trees = this.getItem().equals(object.getItem());
        boolean right = this.getRight().equals(object.getRight());
        boolean left = this.getLeft().equals(object.getLeft());

        boolean empty = trees && right && left;

        return empty;

    }

    public Iterator<E> iterator() {
        return new BinaryTreeIterator(this);
    }

    /*
     * Iterator
     * Iterates through binTree
     */
    class BinaryTreeIterator implements Iterator<E> {
        private Queue<BinaryTree<Object>> list = new LinkedList<>();


        /*
         * Empty check
         */
        public BinaryTreeIterator(BinTree b){
            if (b.isEmpty()){
                return;
            } else{
                list.add(b);
            }
        }
        
        /*
         * Checks if iterator has next 
         */
        public boolean hasNext(){
            return !list.isEmpty();
        }
        /*
         * gets next element
         */
        public E next(){
            if (hasNext()){

                Object ob = new Object();
                BinTree bTree = (BinTree) list.remove();

                if (!bTree.getRight().isEmpty()){
                    list.add(bTree.getRight());
                }

                if (!bTree.getLeft().isEmpty()){
                    list.add(bTree.getLeft());
                }
                
                
                if (!bTree.isEmpty()){
                    ob = bTree.getItem();
                }
                return (E) ob;
            }
            else throw new NoSuchElementException("Out of elements");
        }
    }
}