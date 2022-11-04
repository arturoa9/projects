package catsAndDogs;

import java.util.Arrays;

public class chaseGame {

	public static void main(String[] args) {
		
		double[] catsAndDogs = {1.0,1.0,1.0,3.0,3.0,1.0,8.0,8.0,2.0,-8.0,-8.0,4.0,-1.0,5.0,3.0,-9.0,5.0,2.0,9.0,7.0,1.0,-1.0,4.0,3.0};
		
		System.out.println(catSaved(catsAndDogs));
	}
	
	
	public static boolean catSaved(double[] stats) 
	{
		boolean isSaved = false;
		

		
		double [] cat = {0,0,0};
		double [] tree = {0,0};
		double [] dog1 = {0,0,0};
		double [] dog2 = {0,0,0};
		double [] cDog = {0,0,0};
		
		
		int place = 0;
		
		
		for(int i = 0; i < stats.length-2; i++) 
		{
			if (i < 3) 
			{
				cat[i] = stats[i];
				continue;
			}
			else if(i >= 3 && i < 5) 
			{
				tree[i-3] = stats[i];
				continue;
			}
			else if(i > 5 && i < 9) 
			{
				dog1[i-6] = stats[i];
				continue;
			}
			else if(i >= 9) 
			{
				place = i;
				dog2[i-place] = stats[i];
				dog2[(i+1)-place] = stats[i+1];
				dog2[(i+2)-place] = stats[i+2];
				
				cDog = closestDog(dog1,dog2,tree);
				
				if(cDog.equals(dog1)) 
				{
					dog1 = cDog;
				}
				else 
				{
					dog1 = dog2;
				}
				i = i + 2;
			}
		}
		
		System.out.println(Arrays.toString(dog1));
		
		double distanceCat = absDistance(cat[0],cat[1],cat[2],tree[0],tree[1]);
		double distanceDog = absDistance(dog1[0],dog1[1],dog1[2],tree[0],tree[1]);
		
		System.out.println(distanceCat);
		System.out.println(distanceDog);
	
		if(distanceCat < distanceDog) 
		{
			isSaved=true;
		}
		
		return isSaved;
	}
	
	public static double[] closestDog(double[] dog1, double[] dog2, double[] tree) 
	{
		double[] closest = new double[3];
		
		double distance1 = Math.abs((dog1[0]-tree[0]) + (dog1[1]-tree[1])) / dog1[2];
		double distance2 = Math.abs((dog2[0]-tree[0]) + (dog2[1]-tree[1])) / dog2[2];
		
		if(distance1 < distance2) 
		{
			closest = dog1;
		}
		else
			closest = dog2;
		
		return closest;
	}
	
	public static double absDistance(double x, double y, double v,double xt, double yt) 
	{
		double distance;
		
		distance = Math.abs((x-xt) + (y-yt)) / v;
				
		return distance;
	}


}
