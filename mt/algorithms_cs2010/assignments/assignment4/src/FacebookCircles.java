import java.util.ArrayList;

/**
 * Class FacebookCircles: calculates the statistics about the friendship circles in facebook data.
 *
 * @author
 *
 * @version 01/12/15 02:03:28
 */
public class FacebookCircles {

	//Declare global variables
	int numberOfUsers;
	int numberOfCircles;
	
	int sizeOfLargestCircle;
	int sizeOfArvergeCircle;
	int sizeOfSmallestCircle;
	
	ArrayList<FacebookUser> users;
	ArrayList<Integer> circleSizes;
	
	//Create FacebookUser object for each user (contains their ID and friends)
	  private class FacebookUser {
		  
		  int IDnum;
		  ArrayList<FacebookUser> friendsList;
		  /*
		   * Constructor
		   * @param IDnum : integer representing users ID
		   * Each user will have a unique ID and an arraylist containing their friends ID's
		   */
		  public FacebookUser(int IDnum){
			  this.IDnum = IDnum;
			  this.friendsList = new ArrayList<FacebookUser>();
		  }
	  }
	
  /**
   * Constructor
   * @param numberOfFacebookUsers : the number of users in the sample data.
   * Each user will be represented with an integer id from 0 to numberOfFacebookUsers-1.
   */
  public FacebookCircles(int numberOfFacebookUsers) {
    
	  //Initialise globals
	  this.numberOfUsers = numberOfFacebookUsers;
	  //Every circle is a circle of size 1
	  this.numberOfCircles = numberOfFacebookUsers;
	  
	  this.sizeOfLargestCircle = 1;
	  this.sizeOfArvergeCircle = 1;
	  this.sizeOfSmallestCircle = 1;
	  
	  this.users = new ArrayList<FacebookUser>();
	  this.circleSizes = new ArrayList<Integer>();
	  
	  //Initialise all users in the data set to a user containing IDnum i and an empty friends list 
	  for(int i=0; i<numberOfFacebookUsers; i++) {
		  //Let i = user IDnum, create each user
		  FacebookUser x = new FacebookUser(i);
		  users.add(x);
	  }
	  this.circleSizes = createCircles();
  }

  /**
   * creates a friendship connection between two users, represented by their corresponding integer ids.
   * @param user1 : int id of first user
   * @param user2 : int id of second  user
   */
  public void friends( int user1, int user2 ) {
	  
	//Get each user from list of all users
    FacebookUser A = users.get(user1);
    FacebookUser B = users.get(user2);
    
    //Add userA to userB's friends list and vice versa
    A.friendsList.add(B);
    B.friendsList.add(A);
  }
  
  /**
   * @return the number of friend circles in the data already loaded.
   */
  public int numberOfCircles() {
    return this.circleSizes.size();
  }

  /**
   * @return the size of the largest circle in the data already loaded.
   */
  public int sizeOfLargestCircle() {
    int largestCircleSize = 0;
    
    //Iterate through all circle sizes and find largest size
    for(Integer x : this.circleSizes)
    	if(x>=largestCircleSize)
    		largestCircleSize = x;
    
    return largestCircleSize;
  }

  /**
   * @return the size of the median circle in the data already loaded.
   */
  public int sizeOfAverageCircle() {
	//Get median value
    int x = this.users.size()/this.circleSizes.size();
    return x;
  }

  /**
   * @return the size of the smallest circle in the data already loaded.
   */
  public int sizeOfSmallestCircle() {
	  int smallestCircleSize = 0;
	    
	  //Iterate through all circle sizes and find largest size
	  for(Integer x : this.circleSizes)
		  if(x>=smallestCircleSize)
			  smallestCircleSize = x;
	    
	  return smallestCircleSize;
  }
  
  public ArrayList<Integer> createCircles(){
	  ArrayList<Integer> circleSizes = new ArrayList<Integer>();
	  ArrayList<FacebookUser> userList = this.users;
	  
	  //Iterate through all users in data set tmp
	  for(FacebookUser user : userList) {
		  int circleSize = 1;
		  //Iterate through each user in tmp's friends list
		  for(FacebookUser usersFriend : user.friendsList) {
			  //Since two facebook users who are friends have the same circle of friends, only compute circle once
			  userList.remove(usersFriend);
			  for(FacebookUser friendOfFriend : usersFriend.friendsList) {
				  if(userList.contains(friendOfFriend)) {
					  userList.remove(friendOfFriend);
					  user.friendsList.add(friendOfFriend);
					  circleSize++;
				  }
			  }
		  }
		  circleSizes.add(circleSize);
	  }
	  return circleSizes;
  }


}
