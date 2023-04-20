import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import CITS2200.BinaryTree;
import CITS2200.Iterator;

@SuppressWarnings("unchecked")
public class BinTree<E> extends BinaryTree<E> {

    public BinTree() {
        super();
    }

    public BinTree(E item, BinaryTree<E> ltree, BinaryTree<E> rtree) {
        super(item, ltree, rtree);
    }

    public boolean equals(Object o) {
        if (!(o instanceof BinaryTree)) {
            return false;
        }

        BinaryTree<Object> t = (BinaryTree<Object>) o;

        if (this.isEmpty() && t.isEmpty()) {
            return true;
        }

        boolean trees = this.getItem().equals(t.getItem());
        boolean right = this.getRight().equals(t.getRight());
        boolean left = this.getLeft().equals(t.getLeft());

        boolean empty = trees && right && left;

        return empty;

    }

    public Iterator<E> iterator() {
        return new BinaryTreeIterator(this);
    }
    class BinaryTreeIterator implements Iterator<E> {
        private Queue<BinaryTree<Object>> list = new LinkedList<>();

        public BinaryTreeIterator(BinTree b){
            if (b.isEmpty()){
                return;
            } else{
                list.add(b);
            }
        }

        public boolean hasNext(){
            return !list.isEmpty();
        }

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