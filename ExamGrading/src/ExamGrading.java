//Name: Adrien Philardeau-Planche
//ID: 260835598
public class ExamGrading {

	public static void main(String[] args) {  //can use main method to use the methods below; assignment does not specify to put anything in the main method
	}

		public static double[] gradeAllStudents(char[][] responses, char[] solutions){  //method that grades students in a multiple choice exam
			double [] percentages = new double[responses.length];  //creates array of percentages with a value for each student
			for(int i = 0; i < responses.length; i++){     //throws error if there are more answers than solutions and vice versa
				if(responses[i].length != solutions.length)
					throw new IllegalArgumentException("Student " + i + " has caused the error, as his responses contain " + responses[i].length + " values while the solution set contains " + solutions.length + " values.");
			}
				for(int i = 0; i < responses.length; i++){   //counts how many correct answers there are
					int correct = 0;
					for(int k = 0; k < responses[i].length; k++){
						if(responses[i][k] == solutions[k])
							correct++;
					}			
				 percentages[i] = ((correct*100.0)/solutions.length);  //calculates the percentage for each student
			}
				return percentages;
		}
		
		public static int numWrongSimilar(char[] student1, char[] student2, char[] solutions){    //method that determines how many wrong questions two students have in common
				if(student1.length != solutions.length || student2.length != solutions.length)  //throws error if 
					throw new IllegalArgumentException("The students' answers and/or solutions do not have the same number of values.");
				int wrongSame = 0;   //initializes variable
				for(int i = 0; i < solutions.length; i++){   //goes through array of solutions
					if((student1[i] == student2[i]) && (student1[i] != solutions[i])){    //checks to see if the two students share the answer and if that answer is wrong
							wrongSame++;    //increments variable by one
					}
				}
			return wrongSame;	  //returns variable		
		}
		
		public static int numMatches(char[][] responses, char[] solutions, int index, int threshold){   //returns how many students one student has a suspicious of wrong answers in common with
			int cheaters = 0;    //initializes variable
			for(int i = 0; i < responses.length; i++){   //cycles through array of responses
					if(numWrongSimilar(responses[index], responses[i], solutions) >= threshold && i != index)  //checks to see if the number of wrong answers in common is greater than or equal to the threshold of suspicion
						cheaters++;   //increments variable
				}
			return cheaters;  //returns variable
		}
		
		public static int[][] findSimilarAnswers(char[][] responses, char[] solutions, int threshold){  //creates array that displays which students a student has too many wrong answers in common with
			int[][] array = new int[responses.length][];  //initializes array
			int count = 0;
			for(int i = 0; i < responses.length; i++){  //cycles through every student
				array[i] = new int[numMatches(responses, solutions, i, threshold)];  //initializes sub-array with length of how many people might be cheating
				for(int k = 0; k < responses.length; k++){   //cycles through every combination of students			
					if((numWrongSimilar(responses[i], responses[k], solutions) >= threshold) && (i != k)){					
						array[i][count] = k;   //adds student that might be cheating to the other student's sub-array
						count++;
					}
				}
				count = 0;
			}
			return array;   
		} 
}
