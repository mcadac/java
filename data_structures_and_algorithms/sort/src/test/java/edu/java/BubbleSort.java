package edu.java;

import java.util.Arrays;

import org.junit.Test;

public class BubbleSort {

	@Test
	public void implemationOne(){

		final int [] array = {20,35,-1,7,55,1,-2};

		boolean isSorted = true;

		do{
			isSorted = true;
			for (int i = 0; i < array.length - 1; i++) {

				if (array[i] > array[i + 1]) {
					int temp = array[i + 1];
					array[i + 1] = array[i];
					array[i] = temp;
					isSorted = false;
				}
			}

		} while (!isSorted);


		Arrays.stream(array)
				.forEach(System.out::println);

	}

	@Test
	public void implentationTwo(){

		final int [] array = {20,35,-1,7,55,1,-2};

		for(int lastUnsortedIndex = array.length -1; lastUnsortedIndex > 0; lastUnsortedIndex--){

			for(int i = 0; i < lastUnsortedIndex; i++){
				if(array[i] > array[i + 1]){
					swap(array,i, i + 1);
				}
			}
		}

		Arrays.stream(array).forEach(System.out::println);

	}



	private void swap(int[] array, int i, int j){

		if(i == j){
			return;
		}

		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}




}
