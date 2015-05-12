import java.io.*;
import java.util.*;
import javax.swing.*;
import java.lang.IllegalStateException;

public class kmc 
{
    Scanner input = null;
    Scanner inputDescription = null;
    Double centroids[][] = new Double[270][270];
    Double data[][] = new Double[270][14];
    Double distances[][] = new Double[270][270];
    int group_matrix[][] = new int[270][270];
    int group_matrix_holder[][] = new int[270][270];
    int counter[] = new int[270];
    Double holder1 = 0.0;
    int clusters, holder2 = 0, k = 0;
    Boolean loop;


    public void openfile()
    {
        try
        {
            input = new Scanner( new File("datasets/heart.data.txt" ) );
            inputDescription = new Scanner(new File("datasets/heart.descri.txt"));
            
        } // end try

        catch ( FileNotFoundException fileNotFoundException )
        {
            System.err.println( "Error opening file." );
            System.exit( 1 );
        }
    }

    public void readfile()
    {
        try // read records from file using Scanner object
        {
            while (input.hasNext())
            {
               for (int i = 0; i<270; i++)
               {
                    for (int j = 0; j<14; j++) 
                    {
                        data[i][j] = Double.parseDouble(input.next());                                 
                    }

               }

            } // end while
            System.out.println();
            System.out.println("Data read from the file:");
            System.out.println(".....................................................................");

            for(int count = 1; count<13;count++)
            {
                if(count<10)
                {
                    System.out.printf("%s ","A0"+count+" ");                   
                }

                else
                {
                    System.out.printf("%s ","A"+count+" ");
                }

            }
            System.out.println();
            for (int i= 0; i<270; i++) 
            {
                for (int j = 0; j<13; j++) 
                {
                    System.out.printf("%2.1f ", data[i][j]);
                    
                }
                System.out.println();
                
            }
            System.out.println(".....................................................................");
        } // end try

        catch ( NoSuchElementException elementException )
        {
            System.err.println( "File improperly formed." );
            input.close();
            System.exit( 1 );
        } // end catch

        catch ( IllegalStateException stateException )
        {
            System.err.println( "Error reading from file." );
            System.exit( 1 );
        } // end catch // end catch  
    }
    
    public void readfiledescription()
    {
        // try // read records from file using Scanner object
        // {
        //     System.out.println("File Description:");
        //     System.out.println(".....................................................................");
        //     while (inputDescription.hasNext())
        //     { 
        //         System.out.println(inputDescription.next());                                

        //     } // end while
        //     System.out.println();
        //     System.out.println(".....................................................................");
        // } // end try

        // catch ( NoSuchElementException elementException )
        // {
        //     System.err.println( "File improperly formed." );
        //     input.close();
        //     System.exit( 1 );
        // } // end catch

        // catch ( IllegalStateException stateException )
        // {
        //     System.err.println( "Error reading from file." );
        //     System.exit( 1 );
        // } // end catch // end catch  
    }

    public void myclusters()
    {
        String clusters_string = JOptionPane.showInputDialog("How many clusters?:  ");
        clusters = Integer.parseInt(clusters_string);  
        //System.out.println(); 
        for (int i= 0; i<clusters; i++) 
        {
            for (int j = 0; j<14; j++) 
            {
                centroids[i][j] = data[i][j];  
            }
        } 
    }

    public void get_distance()
    {
         System.out.println("Looping through...");

            for (int i= 0; i<clusters; i++) 
            {
                
                for (int j = 0; j<270; j++) 
                {
                    Double square_dist = Math.pow((data[j][0]-centroids[i][0]), 2) + Math.pow((data[j][1]-centroids[i][1]), 2) + 
                                    Math.pow((data[j][2]-centroids[i][2]), 2) + Math.pow((data[j][3]-centroids[i][3]), 2) + 
                                    Math.pow((data[j][4]-centroids[i][4]), 2) + Math.pow((data[j][5]-centroids[i][5]), 2) + 
                                    Math.pow((data[j][6]-centroids[i][6]), 2) + Math.pow((data[j][7]-centroids[i][7]), 2) + 
                                    Math.pow((data[j][8]-centroids[i][8]), 2);

                    
                    Double dist = Math.pow(square_dist, 0.5);
                    distances[i][j] = dist;
                }  
            }
            //intialize the group matrix to zero
            for (int i= 0; i<270; i++) 
            {
                for (int j = 0; j<clusters; j++) 
                {
                	group_matrix[j][i] = 0;   
                }
            }

            //fill in the group matrix
            for (int i= 0; i<270; i++) 
            {
                holder1 = distances[0][i];
                for (int j = 0; j<clusters; j++) 
                {
                    
                    if (distances[j][i] <= holder1) 
                    {
                        k = j; 
                        holder1 = distances[j][i]; 
                    } 
                } 
                group_matrix[k][i] = 1;
            }
            
        do
        {
            loop = false;
            System.out.println("Looping Through....");

            for (int i=0; i<clusters; i++) 
            {
                for (int j=0; j<270; j++) 
                {
                    group_matrix_holder[i][j] = group_matrix[i][j];
                }    
            }

            //make the centroid matrix equal to zero
            for (int i= 0; i<clusters; i++) {
                counter[i] = 0;
                for (int j = 0; j<13; j++) 
                {
                 centroids[i][j] = 0.00;   
                }
            }

            for (int i =0; i<clusters; i++) 
            {
                for (int j=0; j<270; j++) 
                {
                    if (group_matrix[i][j] == 1) 
                    {
                        centroids[i][0] += data[j][0];  
                        centroids[i][1] += data[j][1]; 
                        centroids[i][2] += data[j][2]; 
                        centroids[i][3] += data[j][3]; 
                        centroids[i][4] += data[j][4]; 
                        centroids[i][5] += data[j][5]; 
                        centroids[i][6] += data[j][6]; 
                        centroids[i][7] += data[j][7]; 
                        centroids[i][8] += data[j][8]; 
                        centroids[i][9] += data[j][9]; 
                        centroids[i][10] += data[j][10]; 
                        centroids[i][11] += data[j][11]; 
                        centroids[i][12] += data[j][12]; 
                        centroids[i][13] += data[j][13]; 
                        counter[i] += 1;       
                    }       
                }    
            }

            for (int i =0; i<clusters; i++) 
            {
                for (int j=0; j<13; j++) 
                {
                        centroids[i][j] = (centroids[i][j])/counter[i];       
                }    
            }

            for (int i= 0; i<clusters; i++) 
            {
                
                for (int j = 0; j<270; j++) 
                {
                    Double square_dist = Math.pow((data[j][0]-centroids[i][0]), 2) + Math.pow((data[j][1]-centroids[i][1]), 2) + 
                                    Math.pow((data[j][2]-centroids[i][2]), 2) + Math.pow((data[j][3]-centroids[i][3]), 2) + 
                                    Math.pow((data[j][4]-centroids[i][4]), 2) + Math.pow((data[j][5]-centroids[i][5]), 2) + 
                                    Math.pow((data[j][6]-centroids[i][6]), 2) + Math.pow((data[j][7]-centroids[i][7]), 2) + 
                                    Math.pow((data[j][8]-centroids[i][8]), 2);

                    
                    Double dist = Math.pow(square_dist, 0.5);
                    distances[i][j] = dist;
                }  
            }

            //intialize the group matrix to zero
            for (int i= 0; i<270; i++) {
                for (int j = 0; j<clusters; j++) 
                {
                	group_matrix[j][i] = 0;   
                }
            }

            //fill in the group matrix
            for (int i= 0; i<270; i++) 
            {
                holder1 = distances[0][i];
                for (int j = 0; j<clusters; j++) 
                {
                    
                    if (distances[j][i] <= holder1) 
                    {
                        k = j; 
                        holder1 = distances[j][i]; 
                    } 
                } 
                group_matrix[k][i] = 1;
            }            

            for (int i=0; i<clusters; i++) 
            {
                for (int j=0; j<270; j++) 
                {

                    if (group_matrix_holder[i][j] != group_matrix[i][j])
                    {
                         loop = true;       
                    }            
                }    
            }

        }while(loop);

    }

    public void printclusters()
    {
        for (int i =0; i<clusters; i++) 
        {
            System.out.println("-----------------------------------------------------------------------");
            System.out.printf("CLUSTER %d\n", i+1);
            for (int j=0; j<270; j++) 
            {
                if (group_matrix[i][j] == 1) 
                {
                   for (int l=0; l<13; l++) 
                        {
                            System.out.printf("%2.1f ", data[j][l]);     
                        } 
                        System.out.println();   
                }      
            }
            System.out.println("----------------------------------------------------------------------");   
        }
    }


    public static void main(String[] args) 
    {
        kmc myClusters = new kmc();
        myClusters.openfile();
        myClusters.readfile();
        myClusters.readfiledescription();
        myClusters.myclusters();
        myClusters.get_distance();
        myClusters.printclusters();
        return;
    }
    

}