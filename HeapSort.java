/*
  Team top Qual Memes -- Richard Lin, Nalanda Sharadjaya, Janet Zhang
  APCS2 pd 5
  hw44 -- Sort of Like Magic
  2016-05-24
*/

import java.util.ArrayList;

public class HeapSort{

    public static void main(String args[]) {

	//Make the array
	ArrayList<Integer> test = new ArrayList<Integer>();
	for (int i = 0; i < 11; i++) {
	    test.add((int)(Math.random() * 20));
	}

	//Testing makeHeap()
	printer(test);
	makeHeap(test);
	
	printer(test);

	//Testing the sort
	heapSort(test);
	printer(test);
    }

   
    public static void heapSort(ArrayList<Integer> data) {
	//Make the heap
	makeHeap(data); //O(log n)

	//This keeps track of a sorted region starting from the end of the array back
	for (int i = data.size() - 1; i > 0; i--) {
	    //Since the array is heap-y, we can always swap the first value(guarenteed to be minimum) with the end value of the unsorted (but still heapy) region
	    swap(data, 0, i);
	    //Then you fix the unsorted region to make it heapy again
	    fixHeap(data, i - 1);
	} //O(n log n)

	//This is to reverse the array so that its min->max
	for (int i = 0; i < data.size() / 2; i++) {
	    swap(data, i, data.size()-1-i);
	} //O(n)
    } //O(n log n)
   

    public static void printer(ArrayList<Integer> data) {
	for (Integer n : data) {
	    System.out.print(n + " ");
	}
	System.out.println();
    }
    
    public static void makeHeap(ArrayList<Integer> data) {

	int index = 1; //Index of first unheaped value;

	//Makes a heaped region starting from the beginning of the array
	while (index < data.size()) {

	    //This is essentially the add code from ALHeap
	    int addValPos = index;
	    Integer addVal = data.get(addValPos);
	    int parentPos;

	    while( addValPos > 0 ) { //potentially swap until reach root
		
		//pinpoint parent
		parentPos = (addValPos-1) / 2;

		if ( addVal.compareTo(data.get(parentPos)) < 0 ) {//addVal < parent
		    swap( data, addValPos, parentPos );
		    addValPos = parentPos;
		}
		else 
		    break;
	    }

	    index++;
	}
    }

    //This is used after the swap has happened
    //Also lots of code chunks copied from ALHeap
    public static void fixHeap(ArrayList<Integer> data, int endPos) {
	
	
	// walk the now-out-of-place root node down the tree...
	int pos = 0;

	Integer foo = data.get(pos);
	
	int minChildPos;

	while( pos < endPos + 1 ) {

	    //choose child w/ min value, or check for child
	    minChildPos = minChildPos(data, pos, endPos);

	    //if no children, then i've walked far enough
	    if ( minChildPos == -1 ) 
		break;
	    //if i am less than my least child, then i've walked far enough
	    else if ( foo.compareTo( data.get(minChildPos) ) <= 0 ) 
		break;
	    //if i am > least child, swap with that child
	    else {
		swap( data, pos, minChildPos );
		pos = minChildPos;
	    }
	}
    }

    //Almost all from ALHeap
    private static int minChildPos( ArrayList<Integer> data, int pos, int endPos ) {
	int retVal;
	int lc = 2*pos + 1; //index of left child
	int rc = 2*pos + 2; //index of right child

	//pos is not in the heap or pos is a leaf position
	if ( pos < 0 || pos >= endPos + 1 || lc >= endPos + 1 )
	    retVal = -1;
	//if no right child, then left child is only option for min
	else if ( rc >= endPos + 1 )
	    retVal = lc;
	//have 2 children, so compare to find least 
	else if ( data.get(lc).compareTo(data.get(rc)) < 0 )
	    retVal = lc;
	else
	    retVal = rc;
	return retVal;
    }

    //Also ALHeap gotten
    private static void swap( ArrayList<Integer> data, int pos1, int pos2 ) {
	data.set( pos1, data.set( pos2, data.get(pos1) ) );	
    }
    
}
