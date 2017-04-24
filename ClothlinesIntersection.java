import java.util.*;

public class ClothlinesIntersection
{
	public static class Tree{
		int num;
		Tree left;
		Tree right;
		int rightCount=0;
		public Tree(){
			this.num=-1;
			this.left=null;
			this.right=null;
		}
	}
	
	static Tree tree=new Tree();
	public static void main (String[] args) throws Exception
	{
		Scanner s=new Scanner (System.in);
		int n;
		System.out.println("enter number of clothlines....");
		n=s.nextInt();
		/*if(n<1 || n>100000)
		    return;
		*/
		   Integer[][] res = new Integer[n][2];  
		for(int i=0;i<n;i++){
				res[i][0] = new Integer(s.nextInt());
				res[i][1] = new Integer(s.nextInt()); 
				/*if(res[i][0]<0 || res[i][0]>100000 || res[i][1]<0 || res[i][1]>100000)
					return;*/
		}
		 Arrays.sort(res, new Comparator<Integer[]>() {  
         public int compare(Integer[] a, Integer[] b) {  
         return a[0].compareTo(b[0]);  
                }}); 
			 
			
       int result = 0;  
           for (int p = 0; p < n; p++) {  
        	   result+=insert(res[p][1],tree);
            /* for (int q = p + 1; q < n; q++) {  
                   if (res[p][1] > res[q][1]) {  
                       result++;  
                   } }  */
           } System.out.println(result); 
           s.close();}
	
	
	public static int insert (int num,Tree tree){
		int count=0;
		if(num > tree.num && tree.num!=-1){
			if(tree.right==null){
				Tree tr=new Tree();
				tr.num=num;
				tree.right=tr;
				tree.rightCount++;}
			else{
				//tree.rightCount++;
				insert(num,tree.right);
			}}
		else if(num < tree.num){
			
			if(tree.left==null){
				Tree tr=new Tree();
				tr.num=num;
				tree.left=tr;
				count+=tree.rightCount+1;
			}
			else {
				count=tree.rightCount+1;
				count+=insert(num,tree.left);
			}}
		else if(tree.left == null && tree.right== null){
			tree.num=num;
		}
			return count;
	}}