/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nlcsn_graphcoloring;

import java.io.FileNotFoundException;

/**
 *
 * @author Administrator
 */
public class ToMau {
    DoThi G;
    
//    Constructor.
    
    public ToMau(){
        G = new DoThi();
    }
    
    public ToMau(DoThi G) {
        this.G = G;
    }
    
//    Các hàm xử lý tô màu theo thuật toán Greedy.
    
    int cotheTo(DoThi G, int u, int v[], int mau){
        for(int i = 1; i <= G.getN(); i++){
            if((G.A[u][i] == 1) && (v[i] == mau))
                   return 0;
        }
        return 1;
    }
    
    int to1Mau(DoThi G, int v[], int mau){
        int count = 0;
        for(int i = 1; i <= G.getN(); i++){
            if(v[i] == 0 && cotheTo(G, i, v, mau) == 1){
                v[i] = mau;
                count++;
            }
        }
        return count;
    }
    
    int toMau(DoThi G, int v[]){
        int count = 0, soMau = 0;
        
        for(int i = 1; i <= G.getN(); i++)
               v[i] = 0;
        
        while(count < G.getN()){
            soMau++;
            count += to1Mau(G, v, soMau);
        }
        return soMau;
    }
    
//    Các hàm thực hiện tô theo thuật toán Greedy.
    
    public String thucHien(DoThi G, int n) {
        G.in(G);
        int soMau = 0;
        int v[] = new int[G.getN()+1];
        soMau = toMau(G, v);
        String s = "Tổng số màu cần dùng để tô là: " +soMau+ ".\n";        
        for(int i = 1; i <= G.getN(); i++){
            s+=("Đỉnh " +i+ " được tô màu: " +v[i]+ ".\n");
        }
        return s;
    }
    
    public String thucHien(DoThi G, String tenFile) throws FileNotFoundException {
        G.nhapFile(tenFile);
        System.out.println(G.in(G));
        int soMau = 0;
        int v[] = new int[G.getN()+1];
        soMau = toMau(G, v);
        String s = "Tổng số màu cần dùng để tô là: " +soMau+ ".\n";
        for(int i = 1; i <= G.getN(); i++){
            s+=("Đỉnh "+i+" được tô màu: " +v[i]+ ".\n");
        }
        return s;
    }
    
    public String thucHien(DoThi G, int n, int m) {
        G = G.ranDom(G, n, m);
        System.out.println(G.in(G));
        int soMau = 0;
        int v[] = new int[G.getN()+1];
        soMau = toMau(G, v);
        String s = "Tổng số màu cần dùng để tô là: " +soMau+ ".\n";
        for(int i = 1; i <= G.getN(); i++){
            s+=("Đỉnh "+i+" được tô màu: " +v[i]+ ".\n");
        }
        return s;
    }

//    Các hàm tính và sắp xếp (Dùng để sắp xếp danh sách đỉnh theo thứ tự bậc giảm dần cho thuật toán WP).
    
    public int tinhBac(DoThi G, int u){
        int bac = 0;
        for(int i = 0; i <= G.getN(); i++)
            bac += G.A[i][u];
        return bac;
    }
    
    public void bubbleSort(int A[], int n, int dinh[]){
        for(int i = 1; i<= n; i++){
            for(int j = i+1; j<= n; j++){
                if(A[i] < A[j]){
                    int temp = A[i];
                    A[i] = A[j];
                    A[j] = temp;
                    
                    int u = dinh[i];
                    dinh[i] = dinh[j];
                    dinh[j] = u;
                }
            }
        }
    }
    
    public void sapXepBac(DoThi G, int bac[], int dinh[]){
        for(int i = 1; i <= G.getN(); i++){
            bac[i] = tinhBac(G, i);
            dinh[i] = i;
        }
        bubbleSort(bac, G.getN(), dinh);
    }
    
//   Các hàm xử lý tô màu theo thuật toán Welsh Powell.
    
    int to1Mau(DoThi G, int v[], int mau, int dinh[]){
        int count = 0;
        
        for(int i = 1; i <= G.getN(); i++){
            if(v[dinh[i]] == 0 && cotheTo(G, dinh[i], v, mau) == 1){
                v[dinh[i]] = mau;
                count++;
            }
        }
        return count;
    }
    
    int toMau(DoThi G, int v[], int dinh[]){
        int count = 0, soMau = 0;
        
        for(int i = 1; i <= G.getN(); i++)
               v[i] = 0;
        
        while(count < G.getN()){
            soMau++;
            count += to1Mau(G, v, soMau, dinh);
        }
        return soMau;
    }
    
//    Các hàm thực hiện tô theo thuật toán Welsh Powell.
    
    public String thucHienWP(DoThi G, String tenFile) throws FileNotFoundException {
        G.nhapFile(tenFile);
        System.out.println(G.in(G));
        
        int soMau = 0;
        int v[] = new int[G.getN()+1];
        int dinh[] = new int[G.getN()+1];
        int bac[] = new int[G.getN()+1];
        
        sapXepBac(G, bac, dinh);
        soMau = toMau(G, v, dinh);
        String s = "Tổng số màu cần dùng để tô là: " +soMau+ ".\n";
        for(int i = 1; i <= G.getN(); i++){
            s+=("Đỉnh " +dinh[i]+ " được tô màu " +v[dinh[i]]+ ".\n");
        }
        return s;
    }    
    public String thucHienWP(DoThi G, int n, int m) {
        System.out.println(G.in(G));
        int soMau = 0;
        int v[] = new int[G.getN()+1];
        int dinh[] = new int[G.getN()+1];
        int bac[] = new int[G.getN()+1];
        sapXepBac(G, bac, dinh);
        soMau = toMau(G, v, dinh);
        String s = "Tổng số màu cần dùng để tô là: " +soMau+ ".\n";
        for(int i = 1; i <= G.getN(); i++){
            s+=("Đỉnh " +dinh[i]+ " được tô màu " +v[dinh[i]]+ ".\n");
        }
        return s;
    }
        
    public String thucHienWP(DoThi G, int n) {
        G.in(G);
        int soMau = 0;
        int v[] = new int[G.getN()+1];
        int dinh[] = new int[G.getN()+1];
        int bac[] = new int[G.getN()+1];
        sapXepBac(G, bac, dinh);
        soMau = toMau(G, v, dinh);
        String s = "Tổng số màu cần dùng để tô là: " +soMau+ ".\n";
        for(int i = 1; i <= G.getN(); i++){
            s+=("Đỉnh " +dinh[i]+ " được tô màu " +v[dinh[i]]+ ".\n");
        }
        return s;
    }
}
