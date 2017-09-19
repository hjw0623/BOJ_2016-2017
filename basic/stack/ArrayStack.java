package basic.stack;

public class ArrayStack implements MyStack {
	private int top;
	private int stackSize;
	private char itemArray[];
	
	
	public ArrayStack(int stackSize){
		this.top = -1;
		this.stackSize = stackSize;
		this.itemArray = new char[this.stackSize];
	}
	@Override
	public boolean isEmpty() {
		return (top==-1);
	}
	public boolean isFull(){
		return (top==stackSize-1);
	}
	@Override
	public void push(char item) {

		if(isFull()){
			System.out.println("stack is full");
		}else{
			itemArray[++top] =item;
		}
	}

	@Override
	public char pop() {
		if(isEmpty()){
			System.out.println("stack is empty");
			return 0;
		}else{
			return itemArray[top--];
		}
	}

	@Override
	public void delete() {

		if(isEmpty()){
			System.out.println("delete element is not Found ");
		}else{
			itemArray[top]=' ';
			top--;
		}
	}

	@Override
	public char peek() {
		if(isEmpty()){
			System.out.println("stack is empty");
		}else{
			return itemArray[top];
		}
		return 0;
	}
	public void printStack() {
        if(isEmpty()){
            System.out.println("스택이 비어있음");
        }else{
            System.out.println("<<Stack>>");
            for(int i=top; i>-1; i--){
                System.out.println(itemArray[i]);
            }
            System.out.println();
        }
    }
}
