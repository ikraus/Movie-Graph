/*
 * 
 MovieGraph.java
 COSC 102, Colgate University
 
 Your code goes here.
 See instructions for explanation of methods.
 
 */

import java.io.*;
import java.util.*;



public class MovieGraph 
{
  

  private HashMap<String,HashSet<String>> graph = new HashMap<String,HashSet<String>>();
  private HashMap<String,String> path = new HashMap<String,String>();
  private LinkedList<String> queue = new LinkedList<String>();
  
  //Constructor
  //Gets passed all of the provided read in data
  //in the form of an ArrayList of String arrays.
  //Each string array represents one line of the source data
  //split on the forward slashes '/'.
  public MovieGraph(ArrayList<String[]> data)
  {
    for(int i = 0; i < data.size(); i++){
 	 	 String movie = data.get(i)[0]; //assuming the movie is always in the first position
 	 	 if(graph.get(movie) == null){
 	 	 	 HashSet<String> valueActor = new HashSet<String>();
 	 	 	 for(int j = 1; j < data.get(i).length; j++){
 	 	 	 	 String actor = data.get(i)[j]; //every position after 0 is an actor
 	 	 	 	 valueActor.add(actor);
 	 	 	 	 if(graph.get(actor) == null){ //while adding, see if actor is in hashmap actors
 	 	 	 	 	 HashSet<String> valueMovie = new HashSet<String>();
 	 	 	 	 	 valueMovie.add(movie); //add the movie to a new hashset
 	 	 	 	 	 graph.put(actor,valueMovie);
 	 	 	 	 }
 	 	 	 	 else graph.get(actor).add(movie);
 	 	 	 }
 	 	 	 graph.put(movie,valueActor);
 	 	}
 	 }

  }
  
  
  //Returns an ArrayList of Strings which is the shortest path of movies/actors between
  //target1 and target2.
  //If no path can be found, can return either null or an empty ArrayList
  public ArrayList<String> findShortestLink(String target1, String target2){
  	  ArrayList<String> finalPath = new ArrayList<String>(); //target1 --> target2
  	  
  	  //if targets don't exist
  	  if (graph.get(target1) == null || graph.get(target2) == null){
  	  		throw new NoSuchElementException("The actor or movie name entered is not applicable to this data set.");
  	  }
  	  
  	  //put the first target in the path HashMap to start
  	  path.put(target1,null); 
  	  	  
  	  //finding all connections
  	  String last = target1;
  	  while (path.get(target2) == null){// || queue.peek() != null){ //do this until you reach target 2 or queue is empty
  	  	  HashSet<String> edges = graph.get(last);
  	  	  for (String s : edges){
  	  	  		if (path.get(s) == null && !s.equals(target1)){
  	  	  			queue.add(s);
  	  	  			path.put(s,last);
  	  	  		}
  	  	  }
  	  	  if (queue.peek() == null)
  	  	  	  return finalPath; //no links found between the two targets
  	  	  last = queue.remove();
  	  }
  	  
  	  //creating the path
  	  finalPath.add(target2);
  	  String after = target2;
  	  while (!finalPath.get(0).equals(target1)){//keep making path until target1 is first 
  	  	  String before = path.get(after);
  	  	  finalPath.add(0,before);
  	  	  after = before;
  	  }
  	  
      return finalPath;
    }
    
    
 
    
  }
  
