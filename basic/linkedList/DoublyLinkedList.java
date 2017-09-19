package basic.linkedList;

public class DoublyLinkedList {
	private Node head;
	private Node tail;

	private int size = 0;
	
	public int indexOf(Object data){
		Node temp = head;
		int index=0;
		while(temp.data != data){
			temp = temp.next;
			index++;
			
			if(temp==null)
				return -1;
		}
		return index;
	}
	public Object get(int k){
		Node temp = node(k);
		return temp.data;
	}
	public Object removeLast(){
		return remove(size-1);
	}
	public Object remove(int index){
		if(index==0){
			return removeFirst();
		}
		//index-1번째 노드 temp선택 
		Node temp = node(index-1);
		//지울노드 저장. 
		Node todoDeleted = temp.next;
		//index-1번째 노드 index+1번 노드 연결 
		temp.next = todoDeleted.next;
		
		if(temp.next !=null){
			temp.next.prev = temp; //todoDeleted.next.prev = temp
		}
		Object returnData = todoDeleted.data;
		
		//삭제될 index번째 노드가 tail이었다면 tail의 이전 노드인 temp(index-1번째노드)를 tail로 지정 
		if(todoDeleted ==tail){
			tail = temp; 
		}
		
		//index노드 삭제
		todoDeleted= null;
		size--;
		
		return returnData;
	}
	public Object removeFirst(){
		Node temp = head;
		head = temp.next;
		
		Object returnData = temp.data;
		temp = null;
		
		// 리스트 내에 노드가 있다면 head의 이전노드를 null로 지정. 
		if(head!=null){
			head.prev = null;
		}
		size--;
		return returnData;
	}
	
	public Node node(int index){
		if(index<size/2){
			Node find = head;
			for(int i=0; i<index; i++){
				find = find.next;
			}
			return find;
		}else{
			Node find = tail;
			for(int i=size-1; i>index; i--){
				find = find.prev;
			}
			return find;
		}
	}
	public void add(int index, Object input){
		if(index==0){
			addFirst(input);
		}else{
			Node temp1 = node(index-1);
			//기 index번째 노드 temp2 
			Node temp2 = temp1.next;
			Node newNode = new Node(input);
			
			temp1.next = newNode;
			newNode.next = temp2;
			
			if(temp2!=null){
				temp2.prev = newNode;
			}
			newNode.prev = temp1;
			size++;
			//새 노드의 다음노드가 없으면 새노드==마지막노드 
			if(newNode.next ==null){
				tail = newNode;
			}
		}
	}
	public void addLast(Object input){
		Node newNode = new Node(input);
		
		if(size==0){
			addFirst(input);
		}else{
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
			size++;
		}
	}
	public void addFirst(Object input){
		Node newNode = new Node(input);
		newNode.next = head;
		
	    // 기존에 노드가 있었다면 현재 헤드의 이전 노드로 새로운 노드를 지정합니다.
		if(head!=null)
			head.prev = newNode;
		
		head = newNode;
		size++;
		if(head.next ==null){
			tail = head;
		}
	}
	private class Node {
		private Object data;
		private Node next;
		private Node prev;
		
		public Node(Object input){
			this.data = input;
			this.next= null;
			this.prev = null;
		}
		//노드의 내용을 쉽게 출력해서 확인해볼 수 있는 기능 
		public String toString(){
			return String.valueOf(this.data);
		}
		
	}
}
