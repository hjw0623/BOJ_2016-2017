package basic.circularlinkedList;

public class CircularTest {

	public static void main(String[] args) {

		CircularLinkedList list = new CircularLinkedList();
		  System.out.println("(1) 공백 리스트에 노드 3개 삽입하기");
	      list.insertLastNode("월");
	      list.insertLastNode("수");
	      list.insertLastNode("일");
	      list.printList();
	      
	      System.out.println("(2) \"수\"노드 뒤에 \"금\" 노드 삽입하기");
	      Node pre = list.searchNode("수");
	      
	      if(pre==null){
	         System.out.println("검색 실패 >> 찾는 데이터가 없습니다.");
	      }else{
	         list.insertMiddleNode(pre, "금");
	      }
	      list.printList();
	      
	      System.out.println("(3) 리스트의 첫번째에 노드 추가하기");
	      list.insertFirstNode("토");
	      list.printList();
	      
	      System.out.println("(4) 리스트의 마지막 노드 삭제하기");
	      list.deleteLastNode();
	      list.printList();
	      
	      System.out.println("(5) 리스트의 중간 노드 \"수\" 삭제하기");
	      list.deleteMiddleNode("수");
	      list.printList();
	      
	      System.out.println("(6) 리스트의 첫번째 노드 삭제하기");
	      list.deleteFirstNode();
	      list.printList();

	}

}
