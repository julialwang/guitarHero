// Julia Wang
// CSE 143 A with Professor Schafer
// Section AG with Mino Nakura
// Assignment #2

// GuitarString models a vibrating guitar string of a given frequency and 
// keeps track of a ring buffer. It utilizes the Karplus-Strong algorithm to 
// mimic the sounds that string vibrations on a physical guitar make.

import java.util.*;

public class GuitarString {

	// class constant that stores the sound decay factor as a double
	public static final double DECAY_FACTOR = 0.996;	
	
	// field that stores the size of the ring buffer
	private int ringSize;
	
	// stores the values of ring buffer
	private Queue<Double> ringBuffer;

	// pre:  requires input frequency as a double
	//	 	 if frequency is less than or equal to 0 or if the resulting ring buffer is less than 2,
	//		 then throws IllegalArgumentException
	// post: creates a ring buffer of the desired capacity, which is calculated based on frequency
	public GuitarString(double frequency) {
		ringSize = Math.round((float)(StdAudio.SAMPLE_RATE / frequency));
		ringBuffer = new LinkedList<Double>();
		
		if (frequency <= 0 || ringSize < 2) {
			throw new IllegalArgumentException();
		}
		
		for (int i = 0; i < ringSize; i++) {
			ringBuffer.add(0.0);
		}
	}
	
	// pre:  requires input array of desired ring buffer double values
	//		 if the array has fewer than two elements, throws IllegalArgumentException
	// post: constructs a ring buffer whose contents are the values of the input array
	public GuitarString(double[] init) {

		ringBuffer = new LinkedList<Double>();
		ringSize = init.length;
		
		if (ringSize < 2) {
			throw new IllegalArgumentException();
		}
		
		for (int i = 0; i < ringSize; i++) {
			ringBuffer.add(init[i]);
		}
	}

	// post: replaces the elements in the ring buffer with random values between -0.5 and 0.5
	public void pluck() {
		
		for (int i = 0; i < ringSize; i++){
			ringBuffer.remove();
			ringBuffer.add(Math.random() - 0.5);
		}
	}
	
	// post: uses the Karplus-Strong algorithm to change ring buffer values
	public void tic() {
		
		double first = ringBuffer.remove();
		double second = ringBuffer.peek();
		double total = first + second;
		ringBuffer.add(DECAY_FACTOR * 0.5 * total);
	}
	
	// post: returns the current sample value
	public double sample() {
		return ringBuffer.peek();
	}
	
}
