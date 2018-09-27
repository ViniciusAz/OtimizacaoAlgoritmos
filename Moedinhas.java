// Problema: "As Moedinhas"

public class Moedinhas {

  static int best = 99999999;
  static int[] total = {0, 0, 0};

  private static int Moedinhas(int v1, int v2, int v3, int q, int[] mem, int nivel, int m1, int m2, int m3) {
    if(q <  0) return 0;
    if(mem[q] != 0 && nivel >= best) return mem[q];
    if(q == 0){
      if(nivel < best) {
        best = nivel;
        total[0] = m1;
        total[1] = m2;
        total[2] = m3;
      }
      return mem[q] = 1;
    }

    return mem[q] = Moedinhas(v1, v2, v3, q-v1, mem, nivel+1, m1+1, m2, m3) +
                    Moedinhas(v1, v2, v3, q-v2, mem, nivel+1, m1, m2+1, m3) +
                    Moedinhas(v1, v2, v3, q-v3, mem, nivel+1, m1, m2, m3+1);

  }

  public static void main(String[] args) {
    int v1 = Integer.parseInt(args[0]);
    int v2 = Integer.parseInt(args[1]);
    int v3 = Integer.parseInt(args[2]);
    int q  = Integer.parseInt(args[3]);

    System.out.println("Total ramificações       : " + Moedinhas(v1, v2, v3, q, (new int[q+1]), 0, 0, 0, 0));
    System.out.println("Menor número de moedas   : " + best );
    System.out.println("Quantidade de cada Moeda : [" + v1 + "] = " + total[0] + " [" + v2 + "] = " + total[1] + " [" + v3 + "] = " + total[2]);
  }
}
