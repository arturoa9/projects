package catsAndDogs;

import java.util.Arrays;

public class chaseGame {

	public static void main(String[] args) {
		//mimic user input in the form of an array following the same format: cat position, velocity, tree position, amount of dogs,
		//dogs position, and velocity of dogs.
		double[] catsAndDogs = {1.0,1.0,1.0,3.0,3.0,1.0,8.0,8.0,2.0,-8.0,-8.0,4.0,-1.0,5.0,3.0,-9.0,5.0,2.0,9.0,7.0,1.0,-1.0,4.0,3.0};
		
		//print result, I did it as a boolean, if the cat survives then it is true if not the method prints false.
		System.out.println(catSaved(catsAndDogs));
	}
	
	
	public static boolean catSaved(double[] stats) 
	{
		//create return variable.
		boolean isSaved = false;
		//initialize variables for the cat, tree, and 3 dogs to compare which is the closes dog to the tree.
		double [] cat = {0,0,0};
		double [] tree = {0,0};
		double [] dog1 = {0,0,0};
		double [] dog2 = {0,0,0};
		double [] cDog = {0,0,0};
		
		//we use the place variable to make sure we are adding in array position 0 regardless of where i is in the loop.
		int place = 0;
		
		//initialize for loop to mimic while loop getting user input, since I didnt know the values to input it was easier for me to test this way
		//something similar could be achieved with a while loop by creating a counter int and placing it inside the while loop to increment after
		//every interation. 
		for(int i = 0; i < stats.length-2; i++) 
		{
			//first if statement, if we are in the first 3 "inputs" then we are talking about the cat so we add these stats (x,y,v) into the
			//cat array
			if (i < 3) 
			{
				cat[i] = stats[i];
				continue;
			}
			//second if statement, if i is at 3 to 4 then we are talking about the trees position so we save it on the tree array
			else if(i >= 3 && i < 5) 
			{
				tree[i-3] = stats[i];
				continue;
			}
			//third if, after the 5th position we are talking about the first dogs values (x,y,v) so we add that to the first dog array
			//to later compare to the next dog and see which is the closest dog.
			else if(i > 5 && i < 9) 
			{
				dog1[i-6] = stats[i];
				continue;
			}
			//last if, we now have our cat array, tree array, and first dog array set up. After this what we have to do is keep listening for
			//other dog values to see if the new dog can reach the tree faster than the previous dog. We only need to keep the values for
			//the dog who can get to the tree the fastest regardles of how many dogs there are.
			else if(i >= 9) 
			{
				//here we add the info (x,y,v) of the second dog to the dog2 array in order to compare it to dog1
				place = i;
				dog2[i-place] = stats[i];
				dog2[(i+1)-place] = stats[i+1];
				dog2[(i+2)-place] = stats[i+2];
				//here we use the third dog array cDog to store the value of the closest dog after we send it to the closestDog method
				//we could of have not used cDog and just set dog1 to the result but I wanted to make all the parts separate from each
				//other in order to visualize it a little better.
				cDog = closestDog(dog1,dog2,tree);
				//now we set dog1 to the value of cDog which points to either dog1 or dog2
				dog1 = cDog;
				//since we already looked at the stats for dog two we can skip to the next possible dog in the input by making i jump 
				//forward two spaces before the loop finishes and jumps over one more space at the start of the next dog. In a while
				//loop we could achieve this by checking if the counter is greater than 9 and a separate in that counts to 3 indicating
				//that we have all the info for a new dog and sending that info the closest dog method
				i = i + 2;
			}
		}
		
		//after we have found the closest dog, we send the dog stats and the cat stats to the absDistance method to calculate the distance
		//of ieach from the tree
		double distanceCat = absDistance(cat,tree);
		double distanceDog = absDistance(dog1,tree);
		
		//if the cat gets there first then it is saved changing "isSaved" to true;
		if(distanceCat < distanceDog) 
		{
			isSaved=true;
		}
		
		return isSaved;
	}
	//method to check which is the closes dog by comparing two dog arrays and their distance to the tree position
	public static double[] closestDog(double[] dog1, double[] dog2, double[] tree) 
	{
		//initialize closest array
		double[] closest = new double[3];
		//use the absDistance method to determin a value for how many moves it takes each animal to reach the tree
		double distance1 = absDistance(dog1,tree);
		double distance2 = absDistance(dog2,tree);
		//set the closes array to point at the values of whichever dog got to the tree faster
		if(distance1 < distance2) 
		{
			closest = dog1;
		}
		else
			closest = dog2;
		//return the answer
		return closest;
	}
	//method used to get the amount each animal has to travel to get to the tree and divide that by each animals speed, to check which animal
	//gets to the tree fastest
	public static double absDistance(double[] animal,double[]tree) 
	{
		double distance;
		
		distance = Math.abs((animal[0]-tree[0]) + (animal[1]-tree[1])) / animal[2];
				
		return distance;
	}


}
