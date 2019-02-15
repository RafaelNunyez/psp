import java.util.ArrayList;
import java.util.Arrays;

/*
 author by w3cschool.cc
 ProducerConsumerTest.java
 */

public class Test {
   public static void main(String[] args) {
	   char[] test = new String(" > Entra en el Chat... *").toCharArray();
	   ArrayList<String> users = new ArrayList<>(10);
	   users.add(String.copyValueOf(Arrays.copyOfRange(test, 23, test.length)));
	   users.add(String.copyValueOf(Arrays.copyOfRange(test, 23, test.length)));
			System.out.println(users);
   }
}
