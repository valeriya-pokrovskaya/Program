package app;

public class GetName {
	
	
	String tab[], tab1[];
	
	public void writeName(int i, String name){	
	  tab[i]= name;	
	   }
	
    public void writeName1(int i, String name){	
		tab1[i]	= name;				
		}
	
    public String[] getName(){
		return(tab1);
		}
    public String[] getName1(){
		return(tab1);		
	}
	
}
