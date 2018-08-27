public class Karatsuba {

  private static int multiplicacoes = 0;

  public static void main(String[] args) {

    //imprime metodo karatsuba com os dois argumentos
    System.out.println(Karatsuba(args[0], args[1]) ); // + " (" + multiplicacoes + ")");

  }

  private static String Karatsuba (String a, String b) {

    //se as duas entradas estao com 1 algarismo, multiplica
    if (a.length() == 1 && b.length() == 1) return Multiplica(a, b);

    //senao, verifica se um número tem menos algarismos e iguala com zeros a esquerda
    while (a.length() < b.length()) a = "0" + a;
    while (b.length() < a.length()) b = "0" + b;

    //Pega o tamanho (tanto faz pegar o tamanho do a ou b, pois estáo igualados)
    int tamanho = a.length();
    //calcula os shifts pegando o teto da divisao (7/2 = 3.5 / teto = 4 )
    int shifts = (int) Math.ceil(tamanho / 2.0);

    //Divide cada numero em duas partes
    String a1 = a.substring(0, tamanho/2);
    String a2 = a.substring(tamanho/2, tamanho);
    String b1 = b.substring(0, tamanho/2);
    String b2 = b.substring(tamanho/2, tamanho);

    /* Monta as chamadas de recursao, sendo:            A x B
    ** head = "a1*b1" (2x shifts)                     /   |   \
    ** body = "(a1+a2)*(b1+b2)" (1x shifts)       head  body  tail
    ** tail = "a2*b2" (0 shifts)
    */
    String head = Karatsuba(a1, b1);
    String tail = Karatsuba(a2, b2);
    //body = (Karatsuba(a1+a2)*(b1+b2)) - head - tail
    String bodyKara = Karatsuba(Soma(a1, a2), Soma(b1, b2));
    String body = Subtrai(bodyKara, Soma(head, tail));

    //Depois de usar o head no calculo do body, shiftar <<
    for (int i = 0; i < shifts; i++) {
        head += "00"; body += "0";
    }
    return (Soma(Soma(head, body), tail));
  }

  private static String Soma(String a, String b){
    int carry = 0;
    String res = "";
    while (a.length() < b.length()) a = "0" + a;
    while (b.length() < a.length()) b = "0" + b;
    int i = a.length() - 1;
    int j = b.length() - 1;

    while(true){
      int i1 = Integer.parseInt(Character.toString(a.charAt(i)));
      int i2 = Integer.parseInt(Character.toString(b.charAt(j)));
      int i3  = i1 + i2 + carry;
      if(i3 > 9){
        carry = 1;
        i3 = i3 - 10;
      } else carry = 0;
      res = String.valueOf(i3) + res;
      i--; j--;
      if(i < 0){
        if(carry > 0) res = String.valueOf(carry) + res;
        break;
      }
    }
    //limpa 0 na frente dos numeros somados
    return res.replaceFirst("^0+(?!$)", "");
  }


  private static String Subtrai(String a, String b){
    boolean carry = false;
    String res = "";
    while (a.length() < b.length()) a = "0" + a;
    while (b.length() < a.length()) b = "0" + b;
    int i = a.length() - 1;
    int j = b.length() - 1;

    while(true){
      int i1 = Integer.parseInt(Character.toString(a.charAt(i)));
      int i2 = Integer.parseInt(Character.toString(b.charAt(j)));
      if(carry){
        if(i1 == 0) i1 = 9;
        else {
          i1--;
          carry = false;
        }
      }
      if(i1 < i2){
        i1 += 10;
        carry = true;
      }
      int i3  = i1 - i2;
      res = String.valueOf(i3) + res;
      i--; j--;
      if(i < 0){
        break;
      }
    }
    //limpa 0 na frente dos numeros somados
    return res.replaceFirst("^0+(?!$)", "");
  }

  private static String Multiplica(String a, String b){
//    multiplicacoes++;
    String res = String.valueOf(Integer.parseInt(a) * Integer.parseInt(b));
    return res;
  }
}
