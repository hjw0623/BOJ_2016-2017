package basic.linkedList;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;

public class SingleLinkedList {
	private Node head;
	private Node tail;

	private int size = 0;
	
	public int indexOf(Object data){
		Node temp = head;
		int index=0;
		while(temp.data != data){
			temp = temp.next;
			index++;
			if(temp==null){
				return -1;
			}
		}
		return index;
	}
	public Object get(int index){
		Node temp = findNode(index);
		return temp.data;
	}
	public int size(){
		return size;
	}
	public Object remove(int index) {
		if (index == 0) {
			return removeFirst();
		}
		// index-1번째 노드를 temp값으로 지정.
		Node temp = findNode(index - 1);

		// 삭제할 노드를 기록
		// 삭제 노드를 지금 제거하면 삭제 앞 노드와 삭제 뒤 노드를 연결할 수 없습니다.
		Node todoDeleted = temp.next;

		// 삭제 앞 노드의 다음노드로 삭제 뒤 노드를 저장
		// temp.next = todoDeleted.next
		temp.next = temp.next.next;
		
		Object returnData = todoDeleted.data;
		if(todoDeleted ==tail){
			tail = temp;
		}
		todoDeleted =null;
		size--;
		return returnData;
	}

	public Object removeFirst() {
		Node temp = head;
		head = temp.next;

		Object returnData = temp.data;
		temp = null;
		size--;
		return returnData;

	}

	// 출력
	public String toString() {
		if (head == null) {
			return "[ ]";
		}
		Node temp = head;
		String str = "[ ";

		while (temp.next != null) {
			str += temp.data + ", ";
			temp = temp.next;
		}
		str += temp.data;
		return str + " ]";
	}

	// 특정위치의 노드 찾기
	Node findNode(int index) {
		Node x = head;
		for (int i = 0; i < index; i++) {
			x = x.next;
		}
		return x;
	}

	public void add(int index, Object input) {
		if (index == 0) {
			addFirst(input);
		} else {

			// index-1번째 노드를 찾고,
			Node temp1 = findNode(index - 1);
			// index번째 노드를 찾는다.
			Node temp2 = temp1.next;
			// 새 노드 생성한다.
			Node newNode = new Node(input);
			// 기존 index-1의 다음노드로 새 노드를 지정
			temp1.next = newNode;
			// 새 노드의 다음 노드로 기존 index번째 노드 지정
			newNode.next = temp2;
			size++;
			// 만약 새 노드의 다음 노드가 없다면 새 노드 = 마지막노드이므로 tail지정
			if (newNode.next == null) {
				tail = newNode;
			}
		}
	}

	public void addLast(Object input) {
		// 노드를 생성
		Node newNode = new Node(input);
		// 리스트의 노드가 없다면 첫번째 노드를 추가하는 메소드 사용
		if (size == 0) {
			addFirst(input);
		} else {
			// 마지막 노드의 다음 노드로 생성한 노드 지정.
			tail.next = newNode;
			// 마지막 노드를 newNode로 갱신
			tail = newNode;

			size++;
		}
	}

	public void addFirst(Object input) {
		// 노드 생성
		Node newNode = new Node(input);
		// 새로운 노드의 다음 노드로 헤드를 지정 (이전 리스트의 가장 최신 머리를 바라보게끔 한다. )
		newNode.next = head;
		// head로 새로운 노드 지정
		head = newNode;

		size++;
		// 리스트가 1개면 tail=head가 되도록
		if (head.next == null) {
			tail = head;
		}
	}

	// private class로 Node 설정.
	private class Node {
		private Object data;
		private Node next;

		public Node(Object input) {
			this.data = input;
			this.next = null;
		}

		public String toString() {
			return String.valueOf(this.data);
		}
	}

}
