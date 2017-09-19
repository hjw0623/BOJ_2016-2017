package basic.tree;


class RedBlackTreeNode{
	private int value;
    private RedBlackTreeNode left;
    private RedBlackTreeNode right;
    private RedBlackTreeNode parent;
    private int color;
    //할아버지 노드 찾기 
    public RedBlackTreeNode grandParent() { 
        if (parent != null) // 부모가 있다면 할아버지노드 반환
            return parent.getParent();
        else {
            return null; // 부모 없다면 null 반환
        }
    }
    //형제 노드 찾기 
    public RedBlackTreeNode sibling(){
    	if (getParent() != null) { // 부모가 있다면 부모의 내가 아닌 자식노드 반환
            if (this == getParent().getLeft())
                return getParent().getRight();
            else
                return getParent().getLeft();
        } else { // 부모 없다면 null 반환
            return null;
        }
    }
    public RedBlackTreeNode uncle() { // 삼촌노드
        return parent.sibling(); // 부모의 형제노드
    }
  
    public int getValue() {
        return value;
    }
  
    public void setValue(int value) {
        this.value = value;
    }
  
    public RedBlackTreeNode getLeft() {
        return left;
    }
  
    public void setLeft(RedBlackTreeNode left) {
        this.left = left;
    }
  
    public RedBlackTreeNode getRight() {
        return right;
    }
  
    public void setRight(RedBlackTreeNode right) {
        this.right = right;
    }
  
    public RedBlackTreeNode getParent() {
        return parent;
    }
  
    public void setParent(RedBlackTreeNode parent) {
        this.parent = parent;
    }
  
    public int getColor() {
        return color;
    }
  
    public void setColor(int color) {
        this.color = color;
    }

}

/** 
 * @author hw
	1. 노드는 레드 혹은 블랙 중의 하나이다.
	2. 루트 노드는 블랙이다.
	3. 모든 리프 노드는 블랙이다.
	4. 레드 노드의 자식노드 양쪽은 언제나 모두 블랙이다. (즉, 레드 노드는 연달아 나타날 수 없으며, 블랙 노드만이 레드 노드의 부모 노드가 될 수 있다)
	5. 어떤 노드로부터 시작되어 리프 노드에 도달하는 모든 경로에는 리프 노드를 제외하면 모두 같은 개수의 블랙 노드가 있다.
 */
public class RedBlackTree {
	public static final int BLACK = 1;
	public static final int RED =0; 
	
	/** 
	 * 삽입
	 *  
	 **/
	//1) Root 노드로 시작한 경우 노드의 Color를 Black으로 바꿔준다.
	public void insertCase1(RedBlackTreeNode node){
		if(node.getParent() == null) //root에 삽입한다. 
			node.setColor(BLACK); 	//2번 속성 
		//5번 유효 
		else 
			insertCase2(node);
	}
	
	//2) 부모노드의 Color가 Black 인 경우 트리는 유효하다. 
	public void insertCase2(RedBlackTreeNode node){
		 if (node.getParent().getColor() == BLACK) // 부모의 Color가 Black 인 경우 4번 속성 만족
		        return; // 트리가 유효함
		    //5번 유효
		    else
		        insertCase3(node); // 부모의 Color가 Red 인 경우
	}
	//3) 부모노드(P)의 Color가 Red 인 경우 AND 삼촌노드(U)의 Color가 Red 인 경우  Color를 반전시킨다. 
	//부모노드(P)와 삼촌노드(U)는 Black, 할아버지노드(G)는 Red로 바꿔준다. -> 1번으로 할아버지노드(G)를 삽입하는 동작으로 간다.
	public void insertCase3(RedBlackTreeNode node){
		 RedBlackTreeNode uncle = node.uncle();
		    RedBlackTreeNode grandParent;
		  
		    if ((uncle != null) && (uncle.getColor() == RED)) { // 부모노드의 Color가 Red 인 경우 && 삼촌노드의 Color가 Red 인 경우
		        node.getParent().setColor(BLACK);
		        uncle.setColor(BLACK); // 부모노드와 삼촌노드는 Black
		        grandParent = node.grandParent();
		        grandParent.setColor(RED); // 할아버지 노드는 Red
		        insertCase1(grandParent);  // 할아버지노드가 Root 노드라면  2번 속성을 만족 안할 수 있다.
		    } else {
		        insertCase4(node); // 부모노드의 Color가 Red 인 경우 && 삼촌노드의 Color가 Black 인 경우
		    }
	}
	//부모노드(P)의 Color가 Red 인 경우 AND 삼촌노드(U)의 Color가 Red 인 경우가 아니라면 4번으로 넘어간다
	//4~5 번은 else 가 아니라 동시에 일어난다.
	
	//4) 부모노드(P)의 Color가 Red 이고, 삼촌노드 (U)의 Color가 Black 인경우
	//삽입한 노드(N) 이 오른쪽 자식이고, 부모노드(P)는 왼쪽 자식일 때는 부모노드(P)를 왼쪽 회전을 한다.
	//삽입한 노드(N) 이 왼쪽 자식이고, 부모노드(P)는 오른쪽 자식일 때는 부모노드(P)를 오른쪽 회전을 한다.
	//하지만 5번 속성을 위반하고 있기 때문에 삽입한 노드의 자식으로 변한 부모노드(P)를 더 처리해 주기 위해서 5번 삽입 단계로 넘어간다.

	public void insertCase4(RedBlackTreeNode node){
		RedBlackTreeNode grandParent = node.grandParent();
		// 삽입한 노드가 부모의 오른쪽 자식이고, 부모는 할아버지의 왼쪽 자식일 때
		if((node ==node.getParent().getRight())  
				&& (node.getParent() == grandParent.getLeft())){
			rotateLeft(node.getParent()); //// 왼쪽회전
			node = node.getLeft(); //원래 노드의 부모노드였던 자식노드에 대한 처리를 하기 위해서 부모노드로 주소 변경
		}
		// 삽입한 노드가 부모의 왼쪽 자식이고, 부모는 할아버지의 오른쪽 자식일 때
		else if ((node ==node.getParent().getLeft())
				&& (node.getParent() == grandParent.getRight())){
			rotateRight(node.getParent());
			////  원래 노드의 부모노드였던 자식노드에 대한 처리를 하기 위해서 부모노드로 주소 변경
			node = node.getRight(); 
		}
		insertCase5(node);
	}
	// 왼쪽 회전.  
	public void rotateLeft(RedBlackTreeNode node){ //부모노드가들어온다. 
		RedBlackTreeNode child = node.getRight();
		RedBlackTreeNode parent = node.getParent(); //할아버지 노드 
		
		//자식(node의 오른쪽자식)의 왼쪽에 자식이 존재하면 그 자식들의 부모로 node를 지정 
		if(child.getLeft() != null)
			child.getLeft().setParent(node);
		//부모인 node의 오른쪽 자식으로 기존의오른쪽 자식이었던 child의 왼쪽자식들을 지정 
		node.setRight(child.getLeft());
		//기존의 오른쪽 자식을 부모의 parent로 설정.
		node.setParent(child);
		//기존의 오른쪽 자식은 부모를 자신의 왼쪽 자식으로 설정. 
		child.setLeft(node);
		//기존 오른쪽 자식의 부모는할아버지 노드가 된다. 
		child.setParent(parent);
		
		//할아버지의 오른쪽 혹은 왼쪽 자식으로 child 설정 
		if(parent != null){
			if(parent.getLeft() ==node)
				parent.setLeft(child);
			else 
				parent.setRight(child);
		}
	}
	//오른쪽 회전
	public void rotateRight(RedBlackTreeNode node ){
		RedBlackTreeNode child = node.getLeft();
		RedBlackTreeNode parent = node.getParent();
		
		if(child.getRight()!=null)
			child.getRight().setParent(node);
		node.setLeft(child.getRight());
		node.setParent(child);
		child.setRight(node);
		child.setParent(parent);
		
		if(parent != null){
			if(parent.getRight() ==node){
				parent.setRight(child);
			}
			else {
				parent.setLeft(child);
			}
		}
	}
	//5)  부모노드(P)의 Color는 Red이고, 삼촌노드(U)의 Color는 Black 이고, 
	//	  삽입한 노드(N) 이 오른쪽 자식이고, 부모노드(P)는 오른쪽 자식일 때
	// 	  할아버지 노드 (G)를 왼쪽 회전
	
	//    삽입한 노드(N) 이 왼쪽 자식이고, 부모노드(P)는 왼쪽 자식일 때는
	// 	  할아버지노드 (G)를 오른쪽 회전
	
	//	  이후 부모노드(P)와 할아버지노드(G)의 Color 반전시켜준다.
	//	  4번을 거쳐온 5번이라면 삽입노드 (N)의 부모였던 부모노드 (P)가 회전을 통해 삽입노드 (N)의 자식이 되었고, 
	//    4번을 거쳐오지 않은 5번이라면 삽입노드로 바로 간주하면 된다.
	// 	  5번단계를 거치면서 4번을 만족하게 된다.
	public void insertCase5 (RedBlackTreeNode node ){
		RedBlackTreeNode grandParent = node.grandParent();
		node.getParent().setColor(BLACK);
		grandParent.setColor(RED);
		if(node ==node.getParent().getLeft()){
			rotateRight(grandParent);
		}
		
		else{ 
			rotateLeft(grandParent);
		}//새로운 노드도 부모의 왼쪽자식, 부모노드도 할아버지노드의 왼쪽자식이면 오른쪽 회전을 한다. 
	}
	
	/** 
	 * 삭제 
	 *  
	일반적인 이진트리탐색와 같은 삭제 연산을 한다.
	삭제되는 노드의 왼쪽 자식노드를 기준으로 계속 오른쪽으로 탐색한 자식중 가장 큰 값과 교체를 해주며, 중위순회시 바로 전 값이다. 
	반대로는 오른쪽 자식노드를 기준으로 계속 왼쪽으로 탐색한 자식 중 가장 작은 값과 교체를 해주며, 중위순회시 바로 다음 값이다.
	교체한 노드를 교체노드(N) 이라고 하겠다.
	삭제한 노드 색깔에 따라서 삭제를 처리해 준다.
	- 레드노드는 속성 4에 영향을 주지 않기 때문에 괜찮다.
	- 블랙노드의 삭제는 속성 4에 영향을 주기 때문에 균형을 맞춰주야한다.
	이제부터 다룰 내용은 삭제를 어떻게 하는지가 아니라, 삭제 후 어떻게 균형을 잡아 줄 지에 대한 내용이다.
	 **/
	public static RedBlackTreeNode root;
	private RedBlackTreeNode searchNode(int value){
		RedBlackTreeNode node =root;
		while(node !=null){
			if(node.getValue() ==value){
				return node;
			}else if (node.getValue()<value){
				node = node.getLeft();
			}else {
				node = node.getRight();
			}
		}
		return node;
	}
	//왼쪽노드 중 오른쪽으로 계속 찾아서 가장 큰 노드 , 중위순회시 바로전 값
	private RedBlackTreeNode leftMaximumNode(RedBlackTreeNode node) {
        while (node.getRight() != null) {
            node = node.getRight();
        }
        return node;
    }
	//노드 교체 
	private void replaceNode(RedBlackTreeNode oldNode, RedBlackTreeNode newNode) { // 교체
        if (oldNode.getParent() == null) { 
            root = newNode;
        } else {
            if (oldNode == oldNode.getParent().getLeft())
                oldNode.getParent().setLeft(newNode);
            else
                oldNode.getParent().setRight(newNode);
        }
        if (newNode != null) {
            newNode.setParent(oldNode.getParent());
        }
    }
	//삭제
	public void delete(int value) { // 삭제
        RedBlackTreeNode node = searchNode(value);
        if (node == null)
            return; // 없음
        if (node.getLeft() != null && node.getRight() != null) {
            RedBlackTreeNode leftMaxNode = leftMaximumNode(node.getLeft());
            node = leftMaxNode;
        }
 
        RedBlackTreeNode child = (node.getRight() == null) ? node.getLeft() : node.getRight();
        if (node.getColor() == BLACK) { // 노드가 블랙이라면 균형맞추는 작업 수행 
            node.setColor(child.getColor());
            deleteCase1(node);
        }
        replaceNode(node, child);
    }

	/**균형을 맞추기 위한 부분은 deleteCase1() ~ deleteCase6()에 해당한다 */
	//1) 교체노드 N이 새로운 루트가 될 경우
	public void deleteCase1(RedBlackTreeNode node){
		if(node.getParent() != null)
			deleteCase2 (node);
		
	}//아무런 행동도 하지 않아도 된다.
	
	//2) 형제노드(S)가 빨강일 경우 부모노드(P)와 형제노드(S)의 색을 바꾸고 왼쪽으로 회전한다.
	//부모노드 (P)는 빨간색 자식노드가 있다면 검은색으로 바꿔야 한다.
	//(반대의 경우는 반대로 하면 된다.)
	public void deleteCase2(RedBlackTreeNode node) {
	    RedBlackTreeNode sibling = node.sibling();
	    if (sibling.getColor() == RED) { // 형제의 color가 빨간색이면
	        node.getParent().setColor(RED);
	        sibling.setColor(BLACK); // color를 바꿔주고
	        if(node == node.getParent().getLeft()){
	            rotateLeft(node.getParent()); // 왼쪽 자식이면 왼쪽으로 돌려주고
	        }else{
	            rotateRight(node.getParent());// 오른쪽 자식이면 왼쪽으로 돌려주고
	        }
	    }
	    deleteCase3(node);
	}
	
	/** 
	 * case 2를 통과하면 N과 S는 반드시 검은색 노드가 된다. 
	 * 2) 번 이후부터는 P, Sl,Sr 에 경우에 대해서 생각한다.
		총 8개의 경우의 수에 대한 처리를 하기 위해서 3~6번 까지 작업을 진행한다.
	 * */
	//3) 부모노드 (P), 형제노드(S), 형제노드 (S)의 자식이 모두 검은색이라면 형제노드 (S)를 빨강으로 바꿔준다.
	//하지만 속성 5를 위반하므로 다시 deleteCase1로 돌아간다.
	public void deleteCase3(RedBlackTreeNode node) {
	    RedBlackTreeNode sibling = node.sibling();
	    if ((node.getParent().getColor() == BLACK)
	            && (sibling.getColor() == BLACK)
	            && (sibling.getLeft().getColor() == BLACK)
	            && (sibling.getRight().getColor() == BLACK)) {
	        sibling.setColor(RED);
	        deleteCase1(node.getParent()); // 속성 5 위반, 다시 돌아간다
	    } else
	        deleteCase4(node);
	}
	//(P, SL, SR) = (검,검,검) - case 3에서 해결
	
	//4) 형제노드 (S)와 형제노드 (S)의 자식은 검은색이지만, 부모노드(P)는 빨강색일 경우 형제노드 (S)와 부모노드(P)의 색깔을 바꿔준다.
	public void deleteCase4(RedBlackTreeNode node) {
	    RedBlackTreeNode sibling = node.sibling();
	    if ((node.getParent().getColor() == RED)
	            && (sibling.getColor() == BLACK)
	            && (sibling.getLeft().getColor() == BLACK)
	            && (sibling.getRight().getColor() == BLACK)) {
	        sibling.setColor(RED);
	        node.getParent().setColor(BLACK);
	    } else
	        deleteCase5(node);
	}
	//(P, SL, SR) = (빨,검,검) - case 4에서 해결

	//5) 형제노드 (S)가 검정, 형제노드 (S)의 왼쪽 자식이 빨강, 형제노드 (S)의 오른쪽 자식이 검정이며, 
	// 교체노드(N)이 부모의 왼쪽 자식이면, 형제노드 (S)를 오른쪽으로 회전시켜서,
	// 형제노드 (S)의 왼쪽 자식이 교체노드(N)의 형제노드가 되도록 만든다.
	// (반대의 경우는 반대로 하면 된다.)
	public void deleteCase5(RedBlackTreeNode node) {
        RedBlackTreeNode sibling = node.sibling();
        if (sibling.getColor() == BLACK) {
            if ((node == node.getParent().getLeft())
                    && (sibling.getRight().getColor() == BLACK)
                    && (sibling.getLeft().getColor() == RED)) {
                sibling.setColor(RED);
                sibling.getLeft().setColor(BLACK);
                rotateRight(sibling);
            } else if ((node == node.getParent().getRight())
                    && (sibling.getLeft().getColor() == BLACK)
                    && (sibling.getRight().getColor() == RED)) {
                sibling.setColor(RED);
                sibling.getRight().setColor(BLACK);
                rotateLeft(sibling);
            }
        }
        deleteCase6(node);
    }
	//(P, SL, SR) = (검,빨,검) - case 5에서 해결, (P, SL, SR) = (빨,빨,검) - case 5에서 해결
	// 6) 형제노드(S)가 검은색, 형제노드(S)의 오른쪽 자식이 빨간색이고, 교체노드(N)이 부모노드(P)의 왼쪽 자식인 경우 부모노드(P)를 회전해서, 형제노드(S)가 P와 형제노드(S)의 오른쪽 자식노드의 부모노드가 되게한다.
	//그러고 부모노드(P),형제노드(S)의 색을 바꾸고, 형제노드(S)의 오른쪽 자식노드를 검은색으로 한다. 
	//회전하기 전과 마찬가지로 여전히 같은 색을 띄기 때문에 속성 4 와 속성 5 가 위배되지 않는다.
	// (반대의 경우는 반대로 하면 된다.)
	public void deleteCase6(RedBlackTreeNode node) {
        RedBlackTreeNode sibling = node.sibling();
        sibling.setColor(node.getParent().getColor());
        node.getParent().setColor(BLACK);
 
        if(node == node.getParent().getLeft()){
            sibling.getRight().setColor(BLACK);
            rotateLeft(node.getParent());
        }else{
            sibling.getLeft().setColor(BLACK);
            rotateRight(node.getParent());
        }
	}
	//(P, SL, SR) = (검,검,빨) - case 6에서 해결, (P, SL, SR) = (빨,검,빨) - case 6에서 해결
	//(P, SL, SR) =  (검,빨,빨) - case 6에서 해결,(P, SL, SR) =  (빨,빨,빨) - case 6에서 해결
	
}
