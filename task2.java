import java.util.*;

public class task2{


  Public static void main(String[] args){
    Scanner sc = new SCanner(System.in)
    
      System.out.println("How many days were you recording for?");
      int length = sc.nextInt();
        if (length !>0){
        System.out.println("Error input invalid. Please enter a positive number (i.e 3, 5, 8)");
        length = sc.nextInt();
        sc.nextLine();
        }
      int cases[] = new int[length];
      
      for (int x =0; x<length.length; x++){
        System.out.println("Please enter the number of cases for day "+(x+1)+": ");
        
         cases[x]=sc.nextInt();
         sc.nextLine();

          if (cases[x]!<0){
          System.out.println("Error please enter a positive number:")
            cases[x] = sc.nextInt90;
            sc.nextLine();
          }
        
      }
    for (int i = 0; i<length.length; i++;){
    System.out.println(Cases[i]);
    }
    int max;
    int min;
    for (int i = 0; i<length.length; i++;){
     if (max< cases[i]){
      max = cases;
     }
     if (min>cases[i]){
     min = cases[i];
     }
    }
    
    
    
    
   for (int i = 0; i<length.length; i++){ 
    int avgTemp += cases[i] 
    counter++;
  }
    int avg = avtemp/counter;
    
    
    
   System.out.println("Max case numbers: "+max);
   System.out.println("Min case numbers: "+min);


  sc.close;
  }

}
