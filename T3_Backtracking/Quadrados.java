//autor Vinícius Azevedo dos Santos
//git @ViniciusAz

public class Quadrados {

  private static int[] geraLista(int n, int[] list, int total) {
    // caso de parada, se a lista ta cheia coloca -1 no ultimo digito e cai fora
    if(total == n) { list[total] = -1; return null; }
    // percorre os elementos de n até 1, (ramificações da arvore)
    for (int i = n; i > 0; i--) {
      boolean flag = false;
      // percorre todos elementos da lista (atualmente)
      for (int e = 0; e < total; e++) {
        // para garantir que o numero não esta na lista
        if(list[e] == i) {
          flag = true;
          break;
        }
      } // for e
      // se o numero nao esta na lista, entao verificar se ele + anterior é quadrado
      if(!flag) {
        if(total == 0 || ( i + list[total-1] == (int) Math.sqrt(i + list[total-1]) * (int) Math.sqrt(i + list[total-1]))) {
          list[total] = i;
          // if(((total+1) < n) || ((total+1) == n && ( i + list[0] == (int) Math.sqrt(i + list[0]) * (int) Math.sqrt(i + list[0]))))
            geraLista(n, list, total+1);
        }
      } // if !flag
      if(list[n] == -1) break;
    } // for i
    return list;
  }

  private static String Quadrados(int n) {
    // gera a lista de paren (n, lista, total preenchido da lista)
    if(n < 1) return "Sem resultado para n = " + n;
    return listaString(geraLista(n, new int[n+1], 0));
  }

  private static String listaString(int[] l) {
    if(l[l.length-1] == 0) return "Sem resultado para n = " + (l.length-1);
    String r = "";
    for (int i = 0; i < l.length-1; i++)
      r += l[i] + " ";
    return r;
  }

  public static void main(String[] args) {
    //manda imprimir o resultado passando como parametro essa lista
    System.out.println(Quadrados(Integer.parseInt(args[0])));

  }
}
