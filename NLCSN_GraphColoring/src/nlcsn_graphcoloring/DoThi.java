/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nlcsn_graphcoloring;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Administrator
 */
public class DoThi {
    private int n, m;
    int A[][];
    
//    Contructor.
    
    public DoThi(){
        n = 0;
        m = 0;
        A = new int[9][9];
    }

    public DoThi(int n, int m, int[][] A) {
        this.n = n;
        this.m = m;
        this.A = A;
    }
    
//    Getter & Setter.

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int[][] getA() {
        return A;
    }

    public void setA(int[][] A) {
        this.A = A;
    }
    
//    Các hàm thao tác cơ bản với Đồ thị.
    
    public void ktaoDoThi(DoThi G, int n){
        G.n = n;
        G.m = 0;
        G.A = new int[n+1][n+1];
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                G.A[i][j] = 0;
            }  
        }
    }
    
    public void themCung(DoThi G, int u, int v){
        G.m++;
        G.A[u][v] = 1;
        G.A[v][u] = 1;
    }
    
    public boolean kiemTraKe(DoThi G, int u, int v){
        return G.A[u][v] == 1;
    }
    
//    Các hàm nhập in.
    
    public void nhapDSCung(int n, int m) {
        this.ktaoDoThi(this, n);
        this.m = m;
        Scanner sc = new Scanner(System.in);
        
        int u,v;
        for(int i = 1; i <= m; i++){
            u = sc.nextInt();
            v = sc.nextInt();
            themCung(this, u, v);
        }    
    }
    
    public void nhapFile(String tenFile) throws FileNotFoundException{
        File file = new File(tenFile);
        Scanner sc = new Scanner(file);
        
        int n, m;
        n = sc.nextInt() ;
        ktaoDoThi(this, n);
        m = sc.nextInt();
        
        int u, v;
        for(int i = 1; i <= m; i++){
            u = Integer.parseInt(sc.next());
            v = Integer.parseInt(sc.next());
            themCung(this, u, v);
        }    
    }
    
    public DoThi ranDom(DoThi G, int n, int m){
        int count = 0;
        ktaoDoThi(G, n);
        Random rand = new Random();
        
        while(count < m){
            int randU = rand.nextInt(n)+1;
            int randV = rand.nextInt(n)+1;
            
            if((randU != randV) && !kiemTraKe(G, randU, randV)){
                themCung(G, randU, randV);
                count++;
            }  
        }
        return G;
    }
    
    public String in(DoThi G){
        String s = "";
        
        for(int i = 1; i <= G.n; i++){
            for(int j = 1; j <= G.n; j++){
                s+=(G.A[i][j] + "  ");
            }
            s+=("\n");
        }
        return s;
    }
}
