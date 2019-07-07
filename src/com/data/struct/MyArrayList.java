package com.data.struct;

import java.util.Comparator;

public class MyArrayList<T> {
    private T[] array;
    private int size;   // 记录当前数组长度

    public MyArrayList() {
        array = null;
        size = 0;
    }

    public MyArrayList(int length) throws Exception {
        if (length > 0) {
            array = (T[]) new Object[length];
        } else {
            throw new Exception("数组长度异常!");
        }
        size = 0;
    }

    public void add(int index,T t){
        if (array == null) {
            array = (T[]) new Object[10];
        } else if (array.length <= size + 1) {
            // 数组删除元素时 最后一个元素的前移可能会越界
            // 所以数组不能被存满 有最后一个空元素时再增加就需要扩容
            int length = size + size << 1;
            T[] newArray = (T[]) new Object[length];
            for (int i = 0; i < size; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
        for(int i=size;i>index;i--){
            array[i] = array[i-1];
        }
        array[index] = t;
        size++;
    }

    public void add(T t) {
        if (array == null) {
            array = (T[]) new Object[10];
        } else if (array.length <= size + 1) {
            // 数组删除元素时 最后一个元素的前移可能会越界
            // 所以数组不能被存满 有最后一个空元素时再增加就需要扩容
            int length = size + size << 1;
            T[] newArray = (T[]) new Object[length];
            for (int i = 0; i < size; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
        array[size++] = t;
    }

    public T get(int index) throws Exception {
        if (index >= size || index < 0) {
            throw new Exception("数组越界");
        }
        return array[index];
    }

    public int size() {
        return size;
    }

    public void remove(T t) {
        if (t == null) {
            // 判断是否相等是调用 对象的内部equals 以及Hash方法(默认是判断Hash 即是否为相同对象 不判断内容)
            // 对于null 而言，会出现空指针异常
            for (int i = 0; i < size; i++) {
                if (array[i] == null) {
                    for (int j = i; j < size; j++) {
                        array[j] = array[j + 1];  // 对于null而言 赋值不会产生问题
                    }
                    --size;
                    break;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (array[i].equals(t)) {    // 使用equals判断，如果使用== 判断的是对象Hash地址
                    for (int j = i; j < size; j++) {
                        array[j] = array[j + 1];
                    }
                    --size;
                    break;
                }
            }
        }
    }

    public T remove(int index) throws Exception {
        if (index < 0 || index >= size) {
            throw new Exception("数组越界异常!");
        }
        T t = array[index];
        for (int i = index; i < size; i++) {
            array[i] = array[i + 1];
        }
        --size;
        return t;
    }

    public void timSort(Comparator<T> comparator) throws Exception {
		int i=1;
        if(comparator.compare(array[0],array[1]) > 0){
            for(;i<size;i++){
                if(comparator.compare(array[i],array[i+1]) < 0){
					for(int j = 0;j<=i/2;j++){
					    T temp = array[j];
					    array[j] = array[i-j];
					    array[i-j] = temp;
                    }
					break;
				}
            }
        }else{
			for(;i<size;i++){
                if(comparator.compare(array[i],array[i+1]) > 0){
					break;
				}
            }
		}
        for(int j=i+1;j<size;j++){
			// 二分插入
            T value = array[j];
			int end = binarySearchTimSort(value, 0, j-1, comparator);
            for(int k = j;k>end;k--){
                array[k] = array[k-1];
            }
            array[end] = value;
        }
        return ;
    }
	private int binarySearchTimSort(T t,int left, int right, Comparator<T> comparator) throws Exception {
		if (left > right) {
            throw new Exception("异常");
        }
		if(left == right){
		    if(comparator.compare(array[left], t) > 0){
                return left;
            }else{
		        return left+1;
            }
		}
        int mid = (left + right) >>> 1;
        if (comparator.compare(array[mid], t) == 0) {
            return mid;
        } else if (comparator.compare(array[mid], t) > 0) {
            return binarySearchTimSort(t,left,mid-1,comparator);
        } else{
            return binarySearchTimSort(t, mid + 1, right, comparator);
        }
	}
	
    public void sort(Comparator<T> comparator){
        for(int i=0;i<size;i++){
            for(int j=i+1;j<size;j++){
                if(comparator.compare(array[i],array[j])>0){
                    T temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    public int binarySearch(T t , Comparator<T> comparator) throws Exception {
        return binarySearch(t,0,size,comparator);
    }
    private int binarySearch(T t, int left, int right, Comparator<T> comparator) throws Exception {
        if (left > right) {
            throw new Exception("异常");
        }
        int mid = (left + right) >>> 1;
        if (comparator.compare(array[mid], t) == 0) {
            return mid;
        } else if (comparator.compare(array[mid], t) > 0) {
            return binarySearch(t,left,mid-1,comparator);
        } else{
            return binarySearch(t, mid + 1, right, comparator);
        }
    }

    public void print() {
        System.out.printf("Array{");
        for (int i = 0; i < size; i++) {
            System.out.printf(array[i] + " ,");
        }
        System.out.println("\b}");
    }
}
