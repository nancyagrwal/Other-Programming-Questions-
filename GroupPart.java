import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class GroupPart {
		public ArrayList<ArrayList> groupPartion(Integer[] arr){
		Arrays.sort(arr);
		ArrayList<ArrayList> groupedSorted=new ArrayList<ArrayList>();
		int groupno=0;
		for(int i=0;i<arr.length;i++){
			if(i==0){
				ArrayList list=new ArrayList();
				list.add(arr[i]);
				groupedSorted.add(list);
				continue;}
			
			if(arr[i]>(Integer)groupedSorted.get(groupno).get(0)){
				groupno++;
				ArrayList list=new ArrayList();
				list.add(arr[i]);
				groupedSorted.add(list);}
			
			else{
				ArrayList list=groupedSorted.get(groupno);
				list.add(arr[i]);
				}}
		
		return groupedSorted;
		}
		
public ArrayList<ArrayList> merger(ArrayList<ArrayList> groupedSorted,int noOfBin){
		
		int totalsize=0;
		int largestGroup=0;
		for(int i=0;i<groupedSorted.size();i++){
			if(groupedSorted.get(i)==null)
				continue;
			totalsize+=groupedSorted.size();
			if(largestGroup < groupedSorted.get(i).size())
				largestGroup=groupedSorted.get(i).size();
		}
		if(totalsize <=noOfBin || noOfBin>=groupedSorted.size())
		  return groupedSorted;
		else{
			  int totalGroups=groupedSorted.size();
			  for(int j=0;j<groupedSorted.size()-1;j++){
				  if(groupedSorted.get(j)==null || groupedSorted.get(j) == null)
				  { totalGroups--;
					  continue;}
				  if(groupedSorted.get(j).size()+groupedSorted.get(j+1).size()<=largestGroup){
					  groupedSorted.get(j).addAll(groupedSorted.get(j+1));
					  groupedSorted.get(j+1).clear();
					  groupedSorted.get(j+1).addAll(groupedSorted.get(j));
					  groupedSorted.remove(j);
					  totalGroups--;
					  j--;
					 } }

			  if(totalGroups<=noOfBin)
				  return groupedSorted;
			  int mergeUpto=totalGroups-noOfBin;
			 
			  for(int i=0;i<mergeUpto;i++){
				int smallest=largestGroup;
				int indexofSmall=0;
				for(int j=0;j<groupedSorted.size();j++){
					if(smallest>groupedSorted.get(j).size()){
						smallest=groupedSorted.get(j).size();
						indexofSmall=j;
					}}
				
				//System.out.println(indexofSmall);
				//System.out.println(largestGroup);
				if(smallest==largestGroup && groupedSorted.size()!=2 && smallest!=1){
					int midPoint=groupedSorted.size()/2;
					ArrayList list=groupedSorted.get(midPoint);
					int targetBreak=multiPartition(list);
					if(targetBreak!=list.size()){
						ArrayList newList1= new ArrayList(list.subList(0, targetBreak));
						ArrayList newList2= new ArrayList(list.subList(targetBreak, list.size()));
						groupedSorted.get(midPoint-1).addAll(newList1);
						groupedSorted.get(midPoint+1).addAll(newList2);
						if(groupedSorted.get(midPoint-1).size()>largestGroup)
							largestGroup=groupedSorted.get(midPoint-1).size();
						if(groupedSorted.get(midPoint+1).size()>largestGroup)
							largestGroup=groupedSorted.get(midPoint+1).size();
						groupedSorted.remove(midPoint);
					}
					continue;
					
				}
				if(indexofSmall==0){
					groupedSorted.get(indexofSmall).addAll(groupedSorted.get(indexofSmall+1));
					if(groupedSorted.get(indexofSmall).size() > largestGroup)
						largestGroup=groupedSorted.get(indexofSmall).size();
					groupedSorted.remove(groupedSorted.get(indexofSmall+1));
					
					continue;
				}
				if(indexofSmall==groupedSorted.size()-1){
					groupedSorted.get(indexofSmall-1).addAll(groupedSorted.get(indexofSmall));
					if(groupedSorted.get(indexofSmall-1).size() > largestGroup)
						largestGroup=groupedSorted.get(indexofSmall-1).size();
					groupedSorted.remove(groupedSorted.get(indexofSmall));
					continue;
				}
				if(groupedSorted.get(indexofSmall-1).size() > groupedSorted.get(indexofSmall+1).size()){
					groupedSorted.get(indexofSmall).addAll(groupedSorted.get(indexofSmall+1));
					if(groupedSorted.get(indexofSmall).size() > largestGroup)
						largestGroup=groupedSorted.get(indexofSmall).size();
					groupedSorted.remove(groupedSorted.get(indexofSmall+1));
					
				}
				else{
					groupedSorted.get(indexofSmall-1).addAll(groupedSorted.get(indexofSmall));
					if(groupedSorted.get(indexofSmall-1).size() > largestGroup)
						largestGroup=groupedSorted.get(indexofSmall-1).size();
					groupedSorted.remove(groupedSorted.get(indexofSmall));
				}}}
			return groupedSorted;
	}

	public static void main(String args[]) throws NumberFormatException, IOException{
	
        Scanner scanner = new Scanner(new File("C:/Users/nancy/desktop/OptimalPart.txt"));
        int bins = scanner.nextInt();
        scanner.nextLine();
        List<Integer> list1 = new ArrayList<Integer>();
        while(scanner.hasNextInt())       
        	list1.add(scanner.nextInt());
          
        Integer[] arr = new Integer[list1.size()];
        arr = list1.toArray(arr);
        
		ArrayList<ArrayList> grouped=new GroupPart().groupPartion(arr);
		grouped=new GroupPart().merger(grouped, bins);
		
		try{
		    PrintWriter writer = new PrintWriter("C:/Users/nancy/desktop/subsets.txt", "UTF-8");
		    for(int i=0;i<grouped.size();i++){
				ArrayList list=grouped.get(i);
				for(int j=0;j<list.size();j++){
					writer.print(list.get(j) + " ");}
				writer.println(" ");}
		    writer.close();
		} catch (IOException e) {
		   // do something
		}}
	
	public int multiPartition(ArrayList list){
		int i;
		for(i =0;i<list.size();i++){
			if(!list.get(i).equals(list.get(0)))
				break;
		}
		return i;
		
		}}