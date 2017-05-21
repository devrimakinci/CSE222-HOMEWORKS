/**
 * @author Devrim Akıncı
 */

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public abstract class AbstractGraphExtended extends AbstractGraph {

    /**
     * Construct a graph with the specified number of vertices
     * and the directed flag. If the directed flag is true,
     * this is a directed graph.
     *
     * @param numV     The number of vertices
     * @param directed The directed flag
     */
    public AbstractGraphExtended(int numV, boolean directed) {
        super(numV, directed);
    }

    /**
     * Bu method verilen edgeLimit sayisina kadar rastgele olarak grafa kenar ekler.
     * @param edgeLimit - Eklenecek kenar sayisinin siniri
     */

    public int addRandomEdgesToGraph (int edgeLimit){
        int randomNumber = (int) (Math.random() * (edgeLimit-1));
        int addingEdges = 0;
        for(int i=0; i<randomNumber; i++){
            int source = (int) (Math.random() * getNumV());
            int destination = (int) (Math.random() * getNumV());
            if(!(isEdge(source,destination))){
                Edge newEdge = new Edge(source,destination);
                insert(newEdge);
                addingEdges++;
            }
        }
        return addingEdges;
    }

    /**
     * Bu method verilen koseden başlayarak grafi traverse etmemizi sağlar.
     * @param start - Traverse islemine baslanacak kose
     */

    public int[] breadthFirstSearch(int start) {
        Queue< Integer > theQueue = new LinkedList< Integer >();
        // Declare array parent and initialize its elements to 1.
        int[] parent = new int[getNumV()];
        for (int i = 0; i < getNumV(); i++) {
            parent[i] = -1;
        }
        // Declare array identified and
        // initialize its elements to false.
        boolean[] identified = new boolean[getNumV()];
    /* Mark the start vertex as identified and insert it
       into the queue */
        identified[start] = true;
        theQueue.offer(start);
    /* While the queue is not empty */
        while (!theQueue.isEmpty()) {
      /* Take a vertex, current, out of the queue.
       (Begin visiting current). */
            int current = theQueue.remove();
      /* Examine each vertex, neighbor, adjacent to current. */
            Iterator< Edge > itr = edgeIterator(current);
            while (itr.hasNext()) {
                Edge edge = itr.next();
                int neighbor = edge.getDest();
                // If neighbor has not been identified
                if (!identified[neighbor]) {
                    // Mark it identified.
                    identified[neighbor] = true;
                    // Place it into the queue.
                    theQueue.offer(neighbor);
          /* Insert the edge (current, neighbor)
             into the tree. */
                    parent[neighbor] = current;
                }
            }
        }
        return parent;
    }

    /**
     * Bu method connected component graphlarin sayisi kadar Graph[] arrayi olusturur.
     * @return - Graph array
     */

    public Graph[] getConnectedComponentUndirectedGraph (){
        int vertexNumber = 0;
        ArrayList<Graph> graphs = new ArrayList<>();
        ArrayList<Edge> edges = new ArrayList<>();
        Set<Integer> vertex = new HashSet<>();
        if(isDirected()){
            return null;
        }
        else {
            vertex.add(0);
            for (int i = 0; i < getNumV(); i++) {
                vertex.add(i);
            }
            Queue<Integer> theQueue = new LinkedList<>();
            boolean[] identified = new boolean[getNumV()];
            while (vertex.size() != 0) {
                Iterator<Integer> iterSet = vertex.iterator();
                int addItem = iterSet.next();
                identified[addItem] = true;
                theQueue.offer(addItem);
                while (!theQueue.isEmpty()) {
                    int current = theQueue.remove();
                    vertex.remove(current);
                    Iterator<Edge> itr = edgeIterator(current);
                    while (itr.hasNext()) {
                        Edge edge = itr.next();
                        edges.add(edge);
                        int neighbor = edge.getDest();
                        if (!identified[neighbor]) {
                            identified[neighbor] = true;
                            theQueue.offer(neighbor);
                        }
                    }
                    vertexNumber++;
                }
                for (int i=0; i<edges.size(); i++){
                    for (int j=0; j<edges.size(); j++){
                        if((edges.get(i).getDest() == edges.get(j).getSource()) && (edges.get(i).getSource() == edges.get(j).getDest())){
                            edges.remove(j);
                        }
                    }
                }
                if(this instanceof ListGraph) { //getClass().getName().equals("ListGraph")
                    ListGraph graphList = new ListGraph(vertexNumber, false);
                    for (int i = edges.size() - 1; i >= 0; i--) {
                        Edge edge = edges.remove(i);
                        if (edge.getSource() >= vertexNumber || edge.getDest() >= vertexNumber) {
                            int source = edge.getSource() % vertexNumber;
                            int dest = edge.getDest() % vertexNumber;
                            graphList.insert(new Edge(source, dest));
                        }
                        else {
                            graphList.insert(edge);
                        }
                    }
                    graphs.add(graphList);
                    vertexNumber = 0;
                }
                else if (this instanceof MatrixGraph){ // getClass().getName().equals("MatrixGraph")
                    MatrixGraph graphMatrix = new MatrixGraph(vertexNumber, false);
                    for (int i = edges.size() - 1; i >= 0; i--) {
                        Edge edge = edges.remove(i);
                        if (edge.getSource() >= vertexNumber || edge.getDest() >= vertexNumber) {
                            int source = edge.getSource() % vertexNumber;
                            int dest = edge.getDest() % vertexNumber;
                            graphMatrix.insert(new Edge(source, dest));
                        }
                        else {
                            graphMatrix.insert(edge);
                        }
                    }
                    graphs.add(graphMatrix);
                    vertexNumber = 0;
                }
            }
        }
        Graph [] arrGraph = new Graph[graphs.size()];
        for(int i=0; i<arrGraph.length; i++){
            arrGraph[i] = graphs.get(i);
        }
        return arrGraph;
    }

    /**
     * Bu method grafin bipartite olup olmadigini soyler.
     * @return - Bipartite ise true, degilse false
     */

    public boolean isBipartiteUndirectedGraph (){
        boolean bipartited = true;
        if (isDirected()){
            bipartited = false;
            return bipartited;
        }
        Graph[] graph = getConnectedComponentUndirectedGraph();
        for(int j=0; j<graph.length; j++){
            int []colour = new int[getNumV()];
            for(int i=0; i<graph[j].getNumV(); i++){
                colour[i] = -1;
            }
            colour[0] = 1;
            for(int i=0; i<graph[j].getNumV(); i++){
                Iterator<Edge> iter = graph[j].edgeIterator(i);
                while (iter.hasNext()){
                    Edge nextEdge = iter.next();
                    int source = nextEdge.getSource();
                    int dest = nextEdge.getDest();
                    if (source == dest){
                        bipartited = false;
                        return bipartited;
                    }
                    if(colour[dest] == -1){
                        colour[dest] = 1 - colour[source];
                    }
                    else if (colour[source] == colour[dest]){
                        bipartited = false;
                        return bipartited;
                    }
                }
            }
        }
        return bipartited;
    }

    /**
     * Bu method graph'in kose sayisini ve var olan kenar sayisini dosyaya yazdirir.
     * @param fileName - Dosya Ismi
     */

    public void writeGraphToFile (String fileName){
        try {
            FileWriter fw = new FileWriter(fileName,true);
            Formatter output = new Formatter(fw);
            output.format("%d\n",getNumV());
            for(int i=0; i<getNumV(); i++){
                Iterator<Edge> iter = edgeIterator(i);
                while (iter.hasNext()){
                    Edge nextEdge = iter.next();
                    output.format("%d %d\n",nextEdge.getSource(),nextEdge.getDest());
                }
            }
            output.close();
        }
        catch (IOException e){
            System.err.println("Error: Failed to open file!");
            System.exit(1);
        }
    }
}
