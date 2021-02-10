package ealtshul;

import java.util.*;
public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random rand = new Random();
		SkipList<Integer> skipList = new SkipList<Integer>();
		
		for(int i = 0; i<10; i++) {
			int index, data;
			index = rand.nextInt(15);
			data = rand.nextInt(15);
			System.out.println("Index: " + index + " Data: " + data);
			try {
				skipList.insert(index, data);
			} catch (NegativeIndexException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
			
		
		System.out.println("Size: " + skipList.size());
		System.out.println("Height: " + skipList.height());
		System.out.println(skipList.toString());
		
		for(int i = 0; i<10; i++) {
			int index = rand.nextInt(15);
			System.out.println("Index: " + index + " Data: " + skipList.search(index));
		}
	}

}
