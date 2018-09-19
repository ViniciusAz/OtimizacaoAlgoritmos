//autor Vinícius Azevedo dos Santos
//git @ViniciusAz

public class CarroUnicornio {

  private static int CarUni_Recorr(int x, int y) {
    if(x == 0 || y == 0) return 1;
    return (CarUni_Recorr(x, y-1) + CarUni_Recorr(x-1, y-1) + CarUni_Recorr(x-1, y));
  }

  private static int CarUni_Memoria(int x, int y, int[][] table) {
    if(table[x][y] != 0) return table[x][y];
    if(x == 0 || y == 0) return table[x][y] = 1;
    return table[x][y] = CarUni_Memoria(x,y-1,table) + CarUni_Memoria(x-1,y-1,table) + CarUni_Memoria(x-1,y,table);
  }

  private static int CarUni_Tabela(int x, int y) {
    int[][] table = new int[x+1][y+1];
    for (int i = 0; i <= x; i++) {
      for (int j = 0; j <= y; j++) {
        if(i == 0 || j == 0) table[i][j] = 1;
        else table[i][j] = table[i][j-1] + table[i-1][j-1] + table[i-1][j];
      }
    }
    return table[x][y];
  }

  public static void main(String[] args) {
    int x = Integer.parseInt(args[0]);
    int y = Integer.parseInt(args[1]);

    System.out.println("(1.) Recorrência : " + CarUni_Recorr(x, y));
    System.out.println("(2.) Memoria     : " + CarUni_Memoria(x, y, new int[x+1][y+1]));
    System.out.println("(3.) Tabela      : " + CarUni_Tabela(x, y));
  }

}
