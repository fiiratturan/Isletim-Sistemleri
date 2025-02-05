package deadlock_package;

public class deadlock_algorithm {

	public static void main(String[] args) {
		
		int A=7,B=2,C=6;
		int[][] allocation = new int[][] {{0,1,0},{2,0,0},{3,0,3},{2,1,1},{0,0,2}};
		int[][] request = new int[][] {{0,0,0},{2,0,2},{0,0,0},{1,0,0},{0,0,2}};
		
		System.out.println("Allocation");
		PrintMatris(allocation);
		
		System.out.println("Request");
		PrintMatris(request);
		
		System.out.println("Available");
		PrintMatris(Available(allocation,A,B,C));
		System.out.println("---------");
		
		ControlProcess(allocation,request,Available(allocation,A,B,C));	

	}
	
	public static void PrintMatris(int[][] matris)
	{		
		for(int i=0;i<matris.length;i++)
		{
			System.out.print("| ");
			for(int j=0;j<matris[i].length;j++)
			{	
				System.out.print(matris[i][j]+" ");
			}
			System.out.println("|");
		}
	}
		
	public static int[][] Available(int[][] allocation,int A,int B, int C)
	{
		int[][] available = new int[1][3];
		
		for(int j=0;j<allocation.length;j++)
		{
			A-=allocation[j][0];
			B-=allocation[j][1];
			C-=allocation[j][2];
		}
			
		available[0][0]=A;
		available[0][1]=B;
		available[0][2]=C;

		return available;
	}
	
	public static boolean MatrisControl(boolean[] matris)
	{	
		for(int i=0;i<matris.length;i++)
		{
			if(matris[i]==false)
			{
				return false;
			}
		}
		return true;
	}
	
	public static void ControlProcess(int[][] allocation,int[][] request,int[][] available)
	{		
		boolean[] process = new boolean[] {false,false,false,false,false};
		boolean deathlock=false;
		
		while(!MatrisControl(process))
		{
			if(!deathlock)
			{
				deathlock=true;
				
				for(int j=0;j<allocation.length;j++)
				{
					if(process[j]==false)
					{
						if(request[j][0]<=available[0][0] && request[j][1]<=available[0][1] && request[j][2]<=available[0][2])
						{
							System.out.print("|P"+j+"|->");
							available[0][0]+=allocation[j][0];
							available[0][1]+=allocation[j][1];
							available[0][2]+=allocation[j][2];
							
							process[j]=true;
							deathlock=false;
						}	
					}	
				}
			}else
			{
				System.out.println("Ölümcül Kitlenme Var");
				System.out.print("Kitlenmeler: ");
				for(int i=0; i<process.length;i++)
				{
					if(process[i]==false)
					{
						System.out.print("|P"+i+ "| ");
					}
				}
				break;
			}	
		}
		
		if(!deathlock)
		{
			System.out.println("Ölümcül Kitlenme Yok");
		}
	}

}
