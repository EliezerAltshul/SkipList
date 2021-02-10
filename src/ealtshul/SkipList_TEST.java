package ealtshul;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SkipList_TEST {

	@Test
	void InsertingInTheSameIndex() throws NegativeIndexException{
		SkipList<Integer> skiplist = new SkipList<Integer>();
		skiplist.insert(0, 1);
		skiplist.insert(0, 2);
		assertEquals(1, skiplist.size());
	}
	
	@Test
	void StandardTestForInsert() throws NegativeIndexException {
		SkipList<Integer> skiplist = new SkipList<Integer>();
		Exception exception = assertThrows(NegativeIndexException.class, () -> {
			skiplist.insert(-1, 1);
	    });
		String expectedMessage = "Cannot insert into a negative index";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void SearchAnEmptySkipList(){
		SkipList<Integer> skiplist = new SkipList<Integer>();
		assertNull(skiplist.search(0));
	}
	
	@Test
	void StandardTestForSearch() throws NegativeIndexException {
		SkipList<Integer> skiplist = new SkipList<Integer>();
		skiplist.insert(0, 1);
		skiplist.insert(1, 2);
		skiplist.insert(2, 3);
		
		assertEquals(1, skiplist.search(0));
	}
}
