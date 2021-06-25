interface Selector1{
	boolean select(int value);
}

class Squares1{

	public static void printSelected(String message, int[] values, Selector1 can){
		System.out.printf("%s:", message);
		for(int value : values){
			if(can.select(value))
				System.out.printf(" %d", value * value);
		}
		System.out.println();
	}
}
public class LambdaTest {
	private static boolean isOdd(int n){
		return (n % 2) == 1;
	}

	public static void main(String[] args){
		int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		System.out.print("All numbers:");
		for(int n : numbers)
			System.out.printf(" %d", n);
		System.out.println();
		//passing a method reference
		Squares1.printSelected("Odd squares", numbers, LambdaTest::isOdd);
		//passing lambda expression: (arguments) -> expression
		Squares1.printSelected("Big squares", numbers, n -> n > 6);
	}
}
