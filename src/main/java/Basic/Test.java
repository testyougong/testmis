package Basic;

public class Test {
	private String title;
	private String content;

	public Test(String title,String content){
		this.title = title;
		this.content = content;
			
	}
	public void print(){
		System.out.println(title);
		System.out.println(content);	
	}
	
	public void print2(){
		System.out.println(this.title);
		System.out.println(this.content);	
	}
	
	public static void main(String[] args){
		Test test = new Test("123", "456");
		test.print();
		test.print2();
	}
	
}
