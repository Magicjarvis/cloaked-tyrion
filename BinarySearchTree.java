import java.util.List;
import java.util.ArrayList;

public class BinarySearchTree<T extends Comparable<? super T>> implements SearchTree<T> {
  private Node<T> root;
  private int size;

  public BinarySearchTree() {
    root = null; 
    size = 0;
  }
  @Override
  public void add(T item) {
    root = add(root, item);
  }

  private Node<T> add(Node<T> curr, T item) {
    if (curr == null) { size++; return new Node(item);}
    if (item.compareTo(curr.getKey()) > 0) {
      curr.setRight(add(curr.getRight(), item)); 
    } else if (item.compareTo(curr.getKey()) < 0) {
      curr.setLeft(add(curr.getLeft(), item));
    }
    return curr;
  }


  @Override
  public void clear() {
    root = null;
    size = 0;
  }

  @Override
  public boolean contains(T item) {
    return contains(root, item);
  }

  private boolean contains(Node<T> curr, T item) {
    if (curr == null) return false; 
    if (curr.getKey().equals(item)) return true;
    int cmp = item.compareTo(curr.getKey());
    if (cmp > 0) return contains(curr.getRight(), item); 
    else return contains(curr.getLeft(), item); 
  }

  public void printRootToLeaf() {
    printRootToLeaf(root, ""); 
  }
  private void printRootToLeaf(Node<T> curr, String str) {
    if (curr.getLeft() == null && curr.getRight() == null) {
      System.out.println(str + curr.toString()); 
      return;
    } 
    printRootToLeaf(curr.getLeft(), str+curr.toString() + "->");
    printRootToLeaf(curr.getRight(), str+curr.toString() + "->");
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public T getMax() {
    return getMax(root);
  }

  private T getMax(Node<T> curr) {
    if (curr.getRight() == null) {
      return curr.getKey(); 
    }
    return getMax(curr.getRight());
  }

  @Override
  public T getMin() {
    return getMin(root);
  }
  private T getMin(Node<T> curr) {
    if (curr.getLeft() == null) {
      return curr.getKey(); 
    }
    return getMin(curr.getLeft());
  }

  @Override
  public List<T> postOrder() {
    return postOrder(new ArrayList<T>(), root);
  }

  private List<T> postOrder(List<T> list, Node<T> curr) {
    if (curr == null) return list;
    list = postOrder(postOrder(list, curr.getLeft()), curr.getRight());
    list.add(curr.getKey());
    return list;
  }

  @Override
  public List<T> preOrder() {
    return preOrder(new ArrayList<T>(), root);
  }

  private List<T> preOrder(List<T> list, Node<T> curr) {
    if (curr == null) return list;
    list.add(curr.getKey());
    list = preOrder(list, curr.getLeft());
    return preOrder(list, curr.getRight());
  }

  @Override
  public List<T> inOrder() {
    return inOrder(new ArrayList<T>(), root);
  }

  private List<T> inOrder(List<T> list, Node<T> curr) {
    if (curr == null) return list;
    list = inOrder(list, curr.getLeft());
    list.add(curr.getKey());
    return inOrder(list, curr.getRight());
  }
}
