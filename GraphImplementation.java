import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GraphImplementation implements Graph{

    int size;
    private int[][] matrix;
    private float[][] matrixf;

    GraphImplementation(int size){
        this.size=size;
        matrix = new int[size][size];
        matrixf = new float[size][size];
    }

    public void addEdge(int v1, int v2){
        if (v1>=0 && v1<size && v2>=0 && v2<size){
            matrix[v2][v1]=1;
        }
        else {
            throw new IndexOutOfBoundsException();
        }
    }
    public void addEdge(int v1, int v2, float weight){
        if (v1>0 && v1<size && v2>0 && v2<size){
            matrixf[v1][v2]=weight;
        }
    }
    public List<Integer> topologicalSort(){
        int[] sum = new int[size];
        for (int i = 0;i<size;i++){
            for (int j = 0; j <size ;j++){
                sum[i] += matrix[i][j];

            }
        }
        List<Integer> s = new ArrayList<Integer>(sum.length);
        for (int count = 0; count<size;count++){
            int next = findZero(sum);
            sum[next]=-1;
            s.add(next);
            for (int i = 0; i<size;i++){
                sum[i]-=matrix[next][i];
            }
        }
        for (int i=0;i<sum.length;i++) {
            for (int j = 0; j < size; j++) {
                if (matrix[i][s.get(j)] == 1){
                    System.out.println("("+s.get(j)+","+i+")");
                }
            }
        }
        return s;

    }
    int findZero(int[] list) {
        int low = 0;
        if (list[low]<0){
            low=1;
        }
        for (int i = 0; i<list.length;i++){
            if (list[i]<list[low]&&list[i]>0){
                low=i;
            }
        }
        return low;
    }

    public List<Integer> neighbors(int vertex){
       List<Integer> l = new ArrayList<>(size);
       for (int i = 0;i<size;i++){
           if (matrix[i][vertex]==1){
               l.add(i);
           }
       }
       return l;
    }


}
