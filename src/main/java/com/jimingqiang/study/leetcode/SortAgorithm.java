package com.jimingqiang.study.leetcode;

public class SortAgorithm {

    /**
     * 冒泡排序
     * @param a
     * @param n
     */
    public static void bubblingSort(int[] a,int n){

        int count = 0;

        for (int i = 0; i < n; i++) {

            count++;

            boolean flag = false;

            for (int j = 0; j < n-i-1; j++) {
                if(a[j] > a[j+1]){
                    int temp = a[j+1];
                    a[j+1] = a[j];
                    a[j] = temp;
                    flag = true;
                }

            }

            if(!flag) break;
        }

        System.out.println(count);

    }

    public static void main(String[] args) {
        int[] a = {2,4,1,9,6,8,7};
        //sort(a);
        mergerSort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+",");

        }
        System.out.println(1/2);
    }

    public static void sort(int[] a){

        int n = a.length;
        for (int i = 0; i < n; i++) {
            int tmp ;
            boolean flag = false;
            for (int j = 0; j < n-i-1; j++) {
                if(a[j] > a[j+1]){
                   tmp = a[j];
                   a[j] = a[j+1];
                   a[j+1] = tmp;
                   flag = true;
                }
            }

            if(!flag){
                break;
            }
        }

    }

    /**
     * 插入排序
     * @param a
     */
    public static void insertionSort(int[] a){
        int n = a.length;
        for (int i = 1; i < n; i++) {

            int value = a[i];
            int j = i-1;
            for (; j >= 0; --j) {
                if(a[j] > value){
                    a[j+1] = a[j];
                }else{
                    break;
                }
            }
            a[j+1] = value;
        }

    }

    public static void selectSort(int[] a){
        int n = a.length;

        for (int i = 0; i < n; i++) {
            int minIndex = i;

            for (int j = i+1; j < n; j++) {
                if(a[j] < a[minIndex]){
                    minIndex = j;
                }
            }
            if(minIndex != i ){
                int tmp = a[i];
                a[i] = a[minIndex];
                a[minIndex] = tmp;

            }

        }
    }




    public static void mergerSort(int []arr){
        int []temp = new int[arr.length];//在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
        sort(arr,0,arr.length-1,temp);
    }
    private static void sort(int[] arr,int left,int right,int []temp){
        if(left<right){
            int mid = (left+right)/2;
            sort(arr,left,mid,temp);//左边归并排序，使得左子序列有序
            sort(arr,mid+1,right,temp);//右边归并排序，使得右子序列有序
            merge(arr,left,mid,right,temp);//将两个有序子数组合并操作
        }
    }
    private static void merge(int[] arr,int left,int mid,int right,int[] temp){
        int i = left;//左序列指针
        int j = mid+1;//右序列指针
        int t = 0;//临时数组指针
        while (i<=mid && j<=right){
            if(arr[i]<=arr[j]){
                temp[t++] = arr[i++];
            }else {
                temp[t++] = arr[j++];
            }
        }
        while(i<=mid){//将左边剩余元素填充进temp中
            temp[t++] = arr[i++];
        }
        while(j<=right){//将右序列剩余元素填充进temp中
            temp[t++] = arr[j++];
        }
        t = 0;
        //将temp中的元素全部拷贝到原数组中
        while(left <= right){
            arr[left++] = temp[t++];
        }
    }



    //*****************快速排序*****************

    /**
     * @param arr
     * @param left  左指针
     * @param right 右指针
     */
    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            //获取枢纽值，并将其放在当前待处理序列末尾
            dealPivot(arr, left, right);
            //枢纽值被放在序列末尾
            int pivot = right - 1;
            //左指针
            int i = left;
            //右指针
            int j = right - 1;
            while (true) {
                while (arr[++i] < arr[pivot]) {
                }
                while (j > left && arr[--j] > arr[pivot]) {
                }
                if (i < j) {
                    swap(arr, i, j);
                } else {
                    break;
                }
            }
            if (i < right) {
                swap(arr, i, right - 1);
            }
            quickSort(arr, left, i - 1);
            quickSort(arr, i + 1, right);
        }

    }

    /**
     * 处理枢纽值
     *
     * @param arr
     * @param left
     * @param right
     */
    public static void dealPivot(int[] arr, int left, int right) {
        int mid = (left + right) / 2;
        if (arr[left] > arr[mid]) {
            swap(arr, left, mid);
        }
        if (arr[left] > arr[right]) {
            swap(arr, left, right);
        }
        if (arr[right] < arr[mid]) {
            swap(arr, right, mid);
        }
        swap(arr, right - 1, mid);
    }

    /**
     * 交换元素通用处理
     *
     * @param arr
     * @param a
     * @param b
     */
    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
