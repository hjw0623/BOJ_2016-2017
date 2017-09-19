package basic.linkedList;

public class MyArrayList {
	final static int MAX_SIZE = 100;
	private int[] data;
	int length; //실제 data 개수 
	
	public MyArrayList(){
		this.length = 0;
		data = new int[MAX_SIZE];
		
	}
	public MyArrayList(int size){
		this.length = 0;
		data = new int[size];
	}
	
	//배열 마지막에 데이터 추가
	public void add(int data){
		int size = this.data.length;
		//현재 배열의 길이(this.length)가 배열의 총 길이(size)와 같다면, 사이를 증가시켜 새로운 배열에 복사.
		if(this.length == size ){
			copyArray(data, size);
		}else{
			this.data[this.length] = data;
		}
		this.length++;
	}
	
	//배열의 특정인덱스에 데이터를 추가함
	public void add(int index, int data){
		int size = this.data.length;
		//현재 배열의 길이와 총 배열길이(사이즈)가 같다면 사이즈를 증가시켜 새로운 배열에 복사. 
		if(this.length ==size){
			copyArrayWithIndex(index, data, size);
			
		}else {
			//배열의 인덱스를 중심으로 뒤로 한칸씩 이동. 
			for(int i= this.length-1; i>index-1; i--){
				this.data[i+1] = this.data[i];
			}
			this.data[index-1] = data;
		}
		this.length++;
	}
	//배열의 마지막 데이터를 삭제 
	public void remove(){
		if(this.length ==0 ){throw new ArrayIndexOutOfBoundsException();}
		this.data[this.length-1] = 0;
		this.length--;
	}
	
	//특정 인덱스의 데이터를 삭제한다.
	public void remove(int index){
		if(this.length ==0 ){throw new ArrayIndexOutOfBoundsException();}
		
		this.data[index-1] = 0;
		
		//삭제된 이후 데이터로 차례대로 빈 자리를 채운다.
		for(int i= index-1; i<this.length-1; i++){
			this.data[i] = this.data[i+1];
		}
		this.length--;
	}
	public int get(int index){
		return this.data[index];
	}
	//특정 인덱스 배열 복사 메소드 
	private void copyArrayWithIndex(int index, int data, int size) {
		int newSize = size+1;
		int[] newArray = new int[newSize];
		newArray[index-1] = data;
		//인덱스 중심으로 이전 데이터 복사 
		for(int i=0; i<index-1; i++){
			newArray[i] = this.data[i];
		}
		//인덱스 중심으로 이후 데이터 복사 
		for(int i=newSize-1; i>index-1; i--){
			newArray[i] = this.data[i-1];
		}
		
		this.data = new int[newSize];
		
		for(int i=0; i<newArray.length; i++){
			this.data[i] = newArray[i];
		}
	}
	//배열 복사 메소드 
	private void copyArray(int data, int size) {
		int newSize = size+1;
		int[] newArray = new int[newSize];
		
		newArray[newSize-1] = data;
		//기존 size 크기의 int[]data배열을 size크기의 newArray에 복사해둔다. 
		for(int i=0; i<size; i++){
			newArray[i] = this.data[i];
		}
		//기존 size+1크기로 int[] data배열을 새로 할당. 
		this.data = new int[newSize];
		//size크기의 newArray로부터 size+1의 새로운 data배열에 값을 복사. 
		for(int i=0; i<newArray.length;i++){
			this.data[i] = newArray[i];
		}
	}
}
