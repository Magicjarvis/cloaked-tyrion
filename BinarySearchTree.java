import java.util.List;
import java.util.ArrayList;

public class BinarySearchTree<T extends Comparable<? super T>> {
  private class Node<T> {
    Node<T> left;
    Node<T> right;
    T key;
    public Node(T item) {
      key = item; 
    }
    public void setKey(T key) {
      this.key = key;
    }
    public void setRight(Node<T> right) {
      this.right = right;
    }
    public void setLeft(Node<T> left) {
      this.left = left;
    }
    public T getKey() {
      return key; 
    }
    public Node<T> getRight() {
      return right; 
    }
    public Node<T> getLeft() {
      return left; 
    }
  };
  private Node<T> root;
  private int size;

  public BinarySearchTree() {
    root = null; 
    size = 0;
  }
    public void add(T item) {
    root = add(root, item);
  }

  private Node<T> add(Node<T> curr, T item) {
    if (curr == null) { size++; return new Node<T>(item);}
    if (item.compareTo(curr.getKey()) > 0) {
      curr.setRight(add(curr.getRight(), item)); 
    } else if (item.compareTo(curr.getKey()) < 0) {
      curr.setLeft(add(curr.getLeft(), item));
    }
    return curr;
  }


    public void clear() {
    root = null;
    size = 0;
  }

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

    public boolean isEmpty() {
    return size == 0;
  }

    public int size() {
    return size;
  }

    public T getMax() {
    return getMax(root);
  }

  private T getMax(Node<T> curr) {
    if (curr.getRight() == null) {
      return curr.getKey(); 
    }
    return getMax(curr.getRight());
  }

    public T getMin() {
    return getMin(root);
  }
  private T getMin(Node<T> curr) {
    if (curr.getLeft() == null) {
      return curr.getKey(); 
    }
    return getMin(curr.getLeft());
  }

    public List<T> postOrder() {
    return postOrder(new ArrayList<T>(), root);
  }

  private List<T> postOrder(List<T> list, Node<T> curr) {
    if (curr == null) return list;
    list = postOrder(postOrder(list, curr.getLeft()), curr.getRight());
    list.add(curr.getKey());
    return list;
  }

    public List<T> preOrder() {
    return preOrder(new ArrayList<T>(), root);
  }

  private List<T> preOrder(List<T> list, Node<T> curr) {
    if (curr == null) return list;
    list.add(curr.getKey());
    list = preOrder(list, curr.getLeft());
    return preOrder(list, curr.getRight());
  }

  public List<T> inOrder() {
    return inOrder(new ArrayList<T>(), root);
  }

  private List<T> inOrder(List<T> list, Node<T> curr) {
    if (curr == null) return list;
    list = inOrder(list, curr.getLeft());
    list.add(curr.getKey());
    return inOrder(list, curr.getRight());
  }

  public void restrictRange(T min, T max) {
    root = restrictRange(root, min, max);
  }
  private Node<T> restrictRange(Node<T> curr, T min, T max) {
    if (curr == null) return curr; 

    if (curr.key.compareTo(min) < 0) {
      return restrictRange(curr.right, min, max); 
    }
    if (curr.key.compareTo(max) > 0) {
      return restrictRange(curr.left, min, max); 
    }

    curr.left = restrictRange(curr.left, min, max);
    curr.right = restrictRange(curr.right, min, max);

    return curr;

  }
  public static void main(String[] args) {
    BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>(); 
    bst.add(10);
    bst.add(8);
    bst.add(14);
    bst.add(7);
    bst.add(9);
    bst.add(16);

    bst.restrictRange(7, 16);

    System.out.println(bst.preOrder());
  }
  
}
