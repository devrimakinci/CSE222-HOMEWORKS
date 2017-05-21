
/**
 * @author Devrim Akıncı
 */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String args[]){

        try {
            File graphData = new File("listGraph.txt");
            Scanner scan = new Scanner(graphData);
            File matrixGraphData = new File("matrixGraph.txt");
            Scanner matrixScanner = new Scanner(matrixGraphData);

            // Creating ListGraph object and MatrixGraph object
            ListGraph listGraphObj = (ListGraph) AbstractGraphExtended.createGraph(scan,false,"List");
            MatrixGraph matrixGraphObj = (MatrixGraph) AbstractGraphExtended.createGraph(matrixScanner,false,"Matrix");

            //Test addRandomEdgesToGraph Method for both objects(MatrixGraph and ListGraph)
            listGraphObj.addRandomEdgesToGraph(10);
            matrixGraphObj.addRandomEdgesToGraph(7);

            //Test breadthFirstSearch Method for both objects(MatrixGraph and ListGraph)
            int listArr[] = listGraphObj.breadthFirstSearch(0);
            int matrixArr[] = matrixGraphObj.breadthFirstSearch(0);

            //Printing to console
            System.out.println("For ListGraph BreadthFirstSearch Parent Array");
            System.out.println("---------------------------------------------");
            for(int i=0; i<listArr.length; i++){
                System.out.printf("%d->%3d\n",i,listArr[i]);
            }
            System.out.println("For MatrixGraph BreadthFirstSearch Parent Array");
            System.out.println("-----------------------------------------------");
            for(int i=0; i<matrixArr.length; i++){
                System.out.printf("%d->%3d\n",i,matrixArr[i]);
            }

            //Test getConnectedComponentUndirectedGraph Method for both objects(MatrixGraph and ListGraph)
            Graph[] listgraph = listGraphObj.getConnectedComponentUndirectedGraph();
            Graph[] matrixgraph = matrixGraphObj.getConnectedComponentUndirectedGraph();
            for(int i=0; i<listgraph.length; i++) {
                ((ListGraph) listgraph[i]).writeGraphToFile("outputListGraph" + (i + 1) + ".txt");
            }
            for(int i=0; i<matrixgraph.length; i++) {
                ((MatrixGraph) matrixgraph[i]).writeGraphToFile("outputMatrixGraph" + (i + 1) + ".txt");
            }

            //Test isBipartiteUndirectedGraph Method for both objects(MatrixGraph and ListGraph)
            System.out.println();
            System.out.println("Test isBipartite\n----------------");
            System.out.printf("This %s is %b\n",listGraphObj.getClass().getName(),listGraphObj.isBipartiteUndirectedGraph());
            System.out.printf("This %s is %b\n",matrixGraphObj.getClass().getName(),matrixGraphObj.isBipartiteUndirectedGraph());
        }
        catch (IOException e){
            System.out.println("Failed to open file!\n");
            System.exit(1);
        }

    }
}
