interface Selector{
	boolean select(int value);
}

class Squares{

	/*
	public static void printOdd(int[] values){
		System.out.printf("Odd squares:");
		for(int value : values){
			if((value % 2) == 1)
				System.out.printf(" %d", value * value);
		}
		System.out.println();
	}
	*/

	public static void printSelected(String message, int[] values, Selector can){
		System.out.printf("%s:", message);
		for(int value : values){
			if(can.select(value))
				System.out.printf(" %d", value * value);
		}
		System.out.println();
	}
}
public class InnerClassTest {
	//inner nested(static) member class
		//cannot refer to non-static members of outer class
		static class OddSelector implements Selector{
			public boolean select(int n){
				return (n % 2) == 1;
			}
		}

		//inner non-nested member class
		//cannot define static members
		class BigSelector implements Selector{
			private int limit;

			public BigSelector(int lim){
				limit = lim;
			}

			public boolean select(int n){
				return n > limit;
			}
		}

		public static void main(String[] args){
			int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
			System.out.print("All numbers:");
			for(int n : numbers)
				System.out.printf(" %d", n);
			System.out.println();
			//Squares.printOdd(numbers);
			Squares.printSelected("Odd squares", numbers, new InnerClassTest.OddSelector());
			Squares.printSelected("Big squares", numbers, new InnerClassTest().new BigSelector(6));
			//passing instance of an inner local anonymous class
			int m = 5; //effectively final because it is captured (by value) by inner class
			Squares.printSelected("Small squares", numbers, new Selector(){
				public boolean select(int n){
					return n < m;
				}
			});
		}
}
