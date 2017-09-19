package basic.circularlinkedList;

class Node {
	String data;
	Node next;

	public Node() {
		this.data = null;
		this.next = null;
	}

	public Node(String data) {
		this.data = data;
		this.next = null;
	}

	public Node(String data, Node next) {
		this.data = data;
		this.next = next;
	}

	public String getData() {
		return this.data;
	}
}

public class CircularLinkedList {
	private Node CL;
	private int size = 0;

	public CircularLinkedList() {
		this.CL = null;
	}

	// 마지막 노드에 삽입
	void insertLastNode(String input) {
		Node node = new Node(input);
		if (CL == null) {
			CL = node;
			node.next = node;
		} else {
			Node current = CL;
			// CL=처음이므로 마지막 노드를 찾는다.
			while (current.next != CL) {
				current = current.next;
			}
			node.next = current.next;
			current.next = node;
		}
		size++;
	}

	void insertFirstNode(String input) {
		Node node = new Node(input);
		if (CL == null) {
			CL = node;
		} else {
			Node current = CL;
			// 마지막 노드 찾는건 동일
			while (current.next != CL) {
				current = current.next;
			}
			node.next = current.next;
			current.next = node;
			// CLL의 첫노드 CL을 새 노드인 node로 갱신.
			CL = node;
		}
		size++;
	}

	// 중간 노드(pre)뒤에 삽입
	//
	void insertMiddleNode(Node pre, String input) {
		Node node = new Node(input);
		if (CL == null) {
			CL = node;
		} else {
			Node current = CL;
			while (current.next != pre) {
				current = current.next;
			}
			current = current.next;
			node.next = current.next;
			current.next = node;
		}
		size++;
	}

	// 마지막 노드 삭제. prev 둔다.
	void deleteLastNode() {
		if (CL == null) {
			System.out.println("list is empty ");
		} else {
			Node prev = CL;
			Node current = CL.next;
			// prev, current를 하나씩 순차 이동
			while (current.next != CL) {
				prev = current;
				current = current.next;
			}
			// prev current의 next를 연결 (current 삭제)
			prev.next = current.next;
			size--;
		}
	}

	void deleteFirstNode() {
		if (CL == null) {
			System.out.println("list is empty");
		} else {
			Node current = CL;
			while (current.next != CL) {
				current = current.next;
			}
			// old= 첫 노드이다.
			Node old = current.next;
			// CL을 2번째노드로 갱신하고,
			// 마지막 노드(current)의 다음으로 연결하면서 첫 노드(old)를 삭제한다.
			CL = old.next;
			current.next = CL;
			size--;
		}
	}

	void deleteMiddleNode(String str) {
		Node node = new Node(str);
		if (CL == null) {
			System.out.println("list is empty");
		} else {
			Node prev = CL;
			Node current = CL.next;
			// current.data==str일때까지 탐색
			while (current.data != node.data) {
				prev = current;
				current = current.next;
			}
			// 지울 노드인 current 삭제
			prev.next = current.next;
			size--;
		}
	}

	Node searchNode(String str) {
		Node node = new Node(str);
		if (CL == null) {
			System.out.println("노드가 비어있습니다.");
			return null;
		} else {
			Node current = CL;
			while (current.data != node.data) {
				current = current.next;
			}
			return current;
		}
	}
	public int size(){
		return this.size;
	}
	public void printList() {
		if (CL == null) {
			System.out.println("출력할 리스트가 존재하지 않습니다.");
		} else {
			Node current = CL;
			System.out.print("[");
			while (current.next != CL) {
				System.out.print(current.data + " ");
				current = current.next;
			}
			System.out.print(current.data);
			System.out.print("]");
			System.out.println(" size: "+size());

		}
	}

}
