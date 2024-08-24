package first;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Stream;

public class CustomArrayList<T> {
    private static final int DEFAULT_CAPACITY = 10;

    private Object[] elements;
    private int size;

    //  Constructors
    public CustomArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @SafeVarargs
    public CustomArrayList(T... args) {
        elements = new Object[args.length];
        System.arraycopy(args, 0, elements, 0, args.length);
        size = args.length;
    }

    public CustomArrayList(int capacity) {
        elements = new Object[capacity];
        size = 0;
    }

    //private methods
    private void grow() {
        Object[] newArray = new Object[elements.length * 2];
        System.arraycopy(elements, 0, newArray, 0, elements.length);
        elements = newArray;
    }

    private void checkCapacity() {
        if (size == elements.length)
            grow();
    }

    private int getIndex(T el) {
        for (int i=0; i<size; i++) {
            if (el.equals(elements[i]))
                return i;
        }
        return -1;
    }

    private Object[] qsort(Object[] a, Comparator<? super T> c) {
        if (a.length < 2) return a;
        if (a.length > 2) {
            int supportIndex = a.length/2;
            Object supportValue = a[supportIndex];
            Object[] leftSide = Stream.concat(Arrays.stream(a, 0, supportIndex),
                            Arrays.stream(a, supportIndex+1, a.length))
                    .filter(e -> c.compare((T)e, (T)supportValue) <= 0)
                    .toArray();
            Object[] rightSide = Stream.concat(Arrays.stream(a, 0, supportIndex),
                            Arrays.stream(a, supportIndex+1, a.length))
                    .filter(e -> c.compare((T)e, (T)supportValue) > 0)
                    .toArray();
            Object[] result = new Object[a.length];

            System.arraycopy(qsort(leftSide, c), 0, result, 0, leftSide.length);
            result[a.length-rightSide.length-1] = supportValue;
            System.arraycopy(qsort(rightSide, c), 0, result, a.length-rightSide.length, rightSide.length);

            return result;
        }
        if (c.compare((T)a[0], (T)a[1]) <= 0) return a;
        return (new Object[]{a[1], a[0]});
    }

    private Object[] msort(Object[] a, Comparator<? super T> c) {
        if (a.length < 2) return a;
        Object[] left = Arrays.copyOfRange(a, 0, a.length/2);
        Object[] right = Arrays.copyOfRange(a, a.length/2, a.length);
        return merge(msort(left, c), msort(right, c), c);
    }
    // merge two sorted arrays to one sorted array
    private Object[] merge(Object[] left, Object[] right, Comparator<? super T> c) {
        Object[] res = new Object[left.length+right.length];
        int leftIndex = 0, rightIndex = 0;
        for (int i=0;i<res.length;i++) {
            res[i] = c.compare((T)left[leftIndex], (T)right[rightIndex]) <= 0 ? left[leftIndex++] : right[rightIndex++];
            if (leftIndex == left.length) {
                System.arraycopy(right, rightIndex, res, i+1, right.length-rightIndex);
                break;
            }
            if (rightIndex == right.length) {
                System.arraycopy(left, leftIndex, res, i+1, left.length-leftIndex);
                break;
            }
        }
        return res;
    }

    //public methods
    public boolean add(T element) {
        checkCapacity();
        elements[size++] = element;
        return true;
    }

    public void add(int index, T element) {
        checkCapacity();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    public void addAll(Collection<? extends T> c) {
        c.forEach(this::add);
    }

    public T get(int index) {
        return (T) elements[index];
    }

    public void set(int index, T element) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        elements[index] = element;
    }

    public void clear() {
        Arrays.stream(elements).forEach(el -> el = null);
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T remove(int index) {
        T elForRemove = (T) elements[index];
        System.arraycopy(elements,index+1, elements, index, size-index-1);
        elements[size--] = null;
        return elForRemove;
    }

    public T remove(T el) {
        return remove(getIndex(el));
    }

    @Override
    public String toString() {
        if (size == 0)
            return "[]";
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]).append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");
        return sb.toString();
    }

    //quick sort
    public void sort(Comparator<? super T> c) {
        Object[] tempArray = new Object[size];
        System.arraycopy(elements, 0, tempArray, 0, size);

        //***quick sort***
        //System.arraycopy(qsort(tempArray, c), 0, tempArray, 0, size);

        System.arraycopy(msort(tempArray, c), 0, elements, 0, size);
    }
}
