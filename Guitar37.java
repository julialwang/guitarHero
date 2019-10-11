// Julia Wang
// CSE 143 A with Professor Schafer
// Section AG with Mino Nakura
// Assignment #2

// Guitar37 models a guitar with 37 different strings in chromatic note progression.
// These strings can be plucked to simulate the vibrating sound patterns 
// of notes on a physical guitar.

public class Guitar37 implements Guitar {
	
	// stores all of the computer keyboard keys that are used in the guitar
    public static final String KEYBOARD =
        "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    
    // stores all 37 guitar strings
    private GuitarString[] guitarStrings;
    
    // stores the number of tics
    private int ticCount;
    
    // post: creates a guitar with 37 different strings
    public Guitar37() {
    	
    	guitarStrings = new GuitarString[KEYBOARD.length()];
    	
    	for (int i = 0; i < KEYBOARD.length(); i++) {
    		guitarStrings[i] = new GuitarString(440.0 * Math.pow(2.0, ((i - 24.0) / 12.0)));
    	}
    	
    }
    
    // pre:  requires an integer pitch of the note desired
    // post: plays the corresponding pitch of the given note
    public void playNote(int pitch) {
    	int index = pitch + 24;
    	
    	if ((index < KEYBOARD.length()) & (index > 0)) {
    		guitarStrings[index].pluck();
    	}
    }
    
    // pre:  requires a case-sensitive character
    // post: determines whether the input character is mapped in the keyboard or not
    // 		 returns the result of the determination as true or false
    public boolean hasString(char string) {
        return (KEYBOARD.indexOf(string) != -1);
    }
    
    // pre:  requires the key of the note desired
    //		 if key is not mapped in the guitar keyboard, throws IllegalArgumentException
    // post: plays the note of the selected guitar string
    public void pluck(char note) {
    	
    	if (!hasString(note)) {
    		throw new IllegalArgumentException();
    	}
    	
    	guitarStrings[KEYBOARD.indexOf(note)].pluck();
    }

    // post: returns the sum of all the sample values in the ring buffer
	public double sample() {
		
		double sampleTotal = 0;
		
		for (int i = 0; i < guitarStrings.length; i++) {
			sampleTotal += guitarStrings[i].sample();
		}
		return sampleTotal;
	}

	// post: advances total time forward by one tic
	public void tic() {
		for (int i = 0; i < KEYBOARD.length(); i++) {
			guitarStrings[i].tic();
		}
		ticCount++;
	}

	// post: returns the number of tics executed
	public int time() {
		return ticCount;
	}
}