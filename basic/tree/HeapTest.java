package basic.tree;

public class HeapTest {

	public static void main(String[] args) {
		 Heap mHeap = new Heap(8);
	        System.out.println("Insert 5,7,8,9,4,2,3,1");
	        mHeap.insert(5);
	        mHeap.insert(7);
	        mHeap.insert(8);
	        mHeap.insert(9);
	        mHeap.insert(4);
	        mHeap.insert(2);
	        mHeap.insert(3);
	        mHeap.insert(1);
	        mHeap.print();
	 
	        System.out.println("");
	        System.out.println("Delete");
	        mHeap.delete();
	        mHeap.print();
	 
	        System.out.println("");
	        System.out.println("Delete");
	        mHeap.delete();
	        mHeap.print();
	}

}
