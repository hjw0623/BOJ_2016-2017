package basic.search;

public class BasicBinarySearchTree {
	public static void main(String []args){
		int [] arr = {1,2,3,4,5,6,7,8,9};
		binarySearch(2, arr);
	}
	public static void binarySearch(int target, int [] arr){
		int mid;
		int left =0;
		int right = arr.length-1;
		while(right>=left){
			mid = (left+right)/2;
			
			if(target == arr[mid]){
				System.out.println(target+" is in the array with index : "+mid);
				break;
			}
			if(target <arr[mid]){
				right = mid-1;
			}else{
				left = mid+1;
			}
		}

	}
}
