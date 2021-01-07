import java.util.*;
import java.nio.*;
import java.lang.*;

public class task5{   
    private static String VOWELS = "AEIOUYАЕЁИОУЫЭЮЯÀÁÂÃÄÅĀĂĄǺȀȂẠẢẤẦẨẪẬẮẰẲẴẶḀÆǼȄȆḔḖḘḚḜẸẺẼẾỀỂỄỆĒĔĖĘĚÈÉÊËȈȊḬḮỈỊĨĪĬĮİÌÍÎÏĲŒØǾȌȎṌṎṐṒỌỎỐỒỔỖỘỚỜỞỠỢŌÒÓŎŐÔÕÖŨŪŬŮŰŲÙÚÛÜȔȖṲṴṶṸṺỤỦỨỪỬỮỰẙỲỴỶỸŶŸÝ";
//1. Пришло время отправлять и получать секретные сообщения.
//Создайте две функции, которые принимают строку и массив и возвращают
//закодированное или декодированное сообщение.
//Первая буква строки или первый элемент массива представляет собой символьный код
//этой буквы. Следующие элементы-это различия между символами: например, A +3 --> C
//или z -1 --> y.
    public static int[] encrypt(String str){
        int [] array = new int [str.length()];// закодированное сообщение
        int i = 0;
        int k = 0;
        for (char t : str.toCharArray()){
            array[i]=t-k; //каждый следующий элемент - различие между текущим элементом в строке и предыдущим
            i+=1;
            k=t;
        }
        return array;
    }
    
    public static String decrypt(int [] array){
        StringBuilder str = new StringBuilder();
        int l=0;
        for (int i: array){
            l=(i+l); //каждый следующий элемент в строке - сумма текущего элемента массива с предыдущими элементами
            str.append((char) l);
        }
        return str.toString();
    }
    
//2. Создайте функцию, которая принимает имя шахматной фигуры, ее положение и
//целевую позицию. Функция должна возвращать true, если фигура может двигаться
//к цели, и false, если она не может этого сделать.
//Возможные входные данные - "пешка", "конь", "слон", "Ладья", "Ферзь"и " король". 
    public static boolean canMove(String figure, String src, String dst){
        src=src.toLowerCase();
        dst=dst.toLowerCase();
        
        figure=figure.toLowerCase();
        
        int x1=src.charAt(0)-'a';
        int y1=src.charAt(1)-'1';
        int x2=dst.charAt(0)-'a';
        int y2=dst.charAt(1)-'1';
        
        if ( y2>7 | y2<0 | x2>7 | x2<0 | y1>7 | y1<0 | x1>7 | x1<0 ) return false;
        //если целевая позиция или положение выходит за 1-8 и a-h, то вернуть false
        
        if ((x1==x2)&(y1==y2)) return false; //если целевая позиция равна положению,
        // чего быть не должно
        
        switch (figure){
            case "pawn":
            return (x1==x2)&(Math.abs(y1-y2)==1); // передвигается на одно поле только вперёд
            
            case "king":
            return (Math.abs(x1-x2)<=1)&(Math.abs(y1-y2)<=1); 
            // Король — передвигается со своего поля на одно из свободных смежных полей
            case "queen":
            return (Math.abs(x1-x2)==Math.abs(y1-y2))|(((x1-x2)*(y1-y2))==0);
            // Ферзь — ходит по вертикалям, диагоналям и горизонталям, на которых он находится.
            
            case "rook":
            return (Math.abs(x1-x2)==Math.abs(y1-y2));
            // Слон — ходит по диагоналям, на которых он находится.
            
            case "bishop":
            return (((x1-x2)*(y1-y2))==0);
            // Ладья — ходит по вертикалям и горизонталям, на которых она находится. 
            
            case "knight":
            return Math.abs((x1-x2)*(y1-y2))==2;
            // Слон - ходит по г - образной тропе
            
        }
        return false;
    }
    
//3. Входная строка может быть завершена, если можно добавить дополнительные
//буквы, и никакие буквы не должны быть удалены, чтобы соответствовать слову.
//Кроме того, порядок букв во входной строке должен быть таким же, как и порядок
//букв в последнем слове.
//Создайте функцию, которая, учитывая входную строку, определяет, может ли слово быть
//завершено. 
    public static boolean canComplete(String str1, String str2){
        int t = 0;
        for (char i : str1.toCharArray()){
            t=str2.indexOf(i, t);
            if (t==-1) break;
        }
        return t!=-1;
    }
    
//4. Создайте функцию, которая принимает числа в качестве аргументов, складывает их
//вместе и возвращает произведение цифр до тех пор, пока ответ не станет длиной
//всего в 1 цифру. 
    public static int sumDigProd(int... array){
        String num;
        int t = 0;
        for (int i : array){
            t+=i;
        }
        while (t>9){
            num = String.valueOf(t);
            t=1;
            for (char i: num.toCharArray()){
                t*=(i-'0');
            }
        }
        return t;
    }
      
//5. Напишите функцию, которая выбирает все слова, имеющие все те же гласные (в
//любом порядке и / или количестве), что и первое слово, включая первое слово.
    public static String [] sameVowelGroup(String [] array){
        ArrayList<String> out = new ArrayList<String>();
        String temp = "";
        for (char i : array[0].toUpperCase().toCharArray()){
            if (VOWELS.indexOf(i)!=-1){
                if (temp.indexOf(i)==-1){
                    temp+=i;
                }
            }
        }
        char [] vowel_list = temp.toCharArray();
        l1: for (String i : array){
            i = i.toUpperCase();
            for (char u : vowel_list){
                if (i.indexOf(u)==-1){
                    continue l1;
                }
                out.add(i);
            }
        }
        return out.toArray(new String[0]);
    }
    
//6. Создайте функцию, которая принимает число в качестве аргумента и возвращает
//true, если это число является действительным номером кредитной карты, а в
//противном случае-false.
//Номера кредитных карт должны быть длиной от 14 до 19 цифр и проходить тест Луна,
//описанный ниже:
//– Удалите последнюю цифру (это"контрольная цифра").
//– Переверните число.
//– Удвойте значение каждой цифры в нечетных позициях. Если удвоенное значение имеет
//более 1 цифры, сложите цифры вместе (например, 8 x 2 = 16 ➞ 1 + 6 = 7).
//– Добавьте все цифры.
//– Вычтите последнюю цифру суммы (из шага 4) из 10. Результат должен быть равен
//контрольной цифре из Шага 1. 
    public static boolean validateCard(long number){
        long z = 0;
        long t;
        char [] array = String.valueOf(number).toCharArray();
        boolean push = true;
        for (int i = array.length - 2; i>=0; i--){
            t=array[i]-'0';
            if (push){
                push = false;
                t=t*2;
                if (t>=10) t=(t%10)+1;
            }
            else{
                push = true;
            }
            z+=t;
        }
        return (10-(z%10))==(array[array.length - 1]-'0');
    };
    
//7. Напишите функцию, которая принимает положительное целое число от 0 до 999
//включительно и возвращает строковое представление этого целого числа,
//написанное на английском языке. 
    public static String numToEng(int num){
        if (num==0) return "zero"; // если число равен 0
        
        if (num<0|num>999) throw new ArrayIndexOutOfBoundsException("");//вызвать ошибку, если число 
        // больше 999 или меньше 0
        
        StringBuilder out = new StringBuilder();
       
        int t = num/100;
       
        if (t>0){
            out.append(numeric.get(t));
            out.append(" hundred ");
        }
        num = num%100;
        if (num<13){
            out.append(numeric.get(num));
            return out.toString();
        }
        else if (num<20){
            t=num%10;
            out.append((ord_num.containsKey(t))?(ord_num.get(t)):(numeric.get(t)));
            out.append("teen");
            return out.toString();
        }
        else{
            t=num%10;
            num/=10;
            if (t==4){
                out.append("forty ");
                out.append(numeric.get(num));
                return out.toString();
            }
            else {
                out.append((ord_num.containsKey(t))?(ord_num.get(t)):(numeric.get(t)));
                out.append("ty ");
                out.append(numeric.get(num));
                return out.toString();
            }
        }
    }
    public static final Map<Integer, String> ord_num = new HashMap<>();
    static{
        ord_num.put(3, "thir");
        ord_num.put(2, "twen");
        ord_num.put(5, "fif");
        ord_num.put(9, "eigh");
    } 
    static private List numeric = new ArrayList<String> (Arrays.asList("", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "teen", "eleven", "twelve"));
    
//8. Хеш-алгоритмы легко сделать одним способом, но по существу невозможно
//сделать наоборот. Например, если вы хешируете что-то простое, например,
//password123, это даст вам длинный код, уникальный для этого слова или фразы. В
//идеале, нет способа сделать это в обратном порядке. Вы не можете взять хеш-код и
//вернуться к слову или фразе, с которых вы начали.
//Создайте функцию, которая возвращает безопасный хеш SHA-256 для данной строки.
//Хеш должен быть отформатирован в виде шестнадцатеричной цифры. 

// я переписал псевдокод из википедии на язык java

//https://en.wikipedia.org/wiki/SHA-2#Pseudocode
    private static final int[] K = {
            0x428a2f98, 0x71374491, 0xb5c0fbcf, 0xe9b5dba5, 0x3956c25b, 0x59f111f1, 0x923f82a4, 0xab1c5ed5,
            0xd807aa98, 0x12835b01, 0x243185be, 0x550c7dc3, 0x72be5d74, 0x80deb1fe, 0x9bdc06a7, 0xc19bf174,
            0xe49b69c1, 0xefbe4786, 0x0fc19dc6, 0x240ca1cc, 0x2de92c6f, 0x4a7484aa, 0x5cb0a9dc, 0x76f988da,
            0x983e5152, 0xa831c66d, 0xb00327c8, 0xbf597fc7, 0xc6e00bf3, 0xd5a79147, 0x06ca6351, 0x14292967,
            0x27b70a85, 0x2e1b2138, 0x4d2c6dfc, 0x53380d13, 0x650a7354, 0x766a0abb, 0x81c2c92e, 0x92722c85,
            0xa2bfe8a1, 0xa81a664b, 0xc24b8b70, 0xc76c51a3, 0xd192e819, 0xd6990624, 0xf40e3585, 0x106aa070,
            0x19a4c116, 0x1e376c08, 0x2748774c, 0x34b0bcb5, 0x391c0cb3, 0x4ed8aa4a, 0x5b9cca4f, 0x682e6ff3,
            0x748f82ee, 0x78a5636f, 0x84c87814, 0x8cc70208, 0x90befffa, 0xa4506ceb, 0xbef9a3f7, 0xc67178f2
    };

    private static final int[] H0 = {
            0x6a09e667, 0xbb67ae85, 0x3c6ef372, 0xa54ff53a, 0x510e527f, 0x9b05688c, 0x1f83d9ab, 0x5be0cd19
    };

    private static final int[] W = new int[64];
    private static final int[] H = new int[8];
    private static final int[] TEMP = new int[8];
    
    public static String getSha256Hash(String message) {
        System.arraycopy(H0, 0, H, 0, H0.length);
        
        byte[] bytes = message.getBytes();
        int[] words = new int[((bytes.length/64)+((bytes.length%64)+1+8>64?2:1))*16 ];
        
        ByteBuffer buf = ByteBuffer.wrap(bytes);
        
        for (int i=0, n=bytes.length/4; i<n; i++){
            words[i]=buf.getInt();
        }
        
        ByteBuffer remainder = ByteBuffer.allocate(4);
        
        remainder.put(buf).put((byte) 0b10000000).rewind();
        
        words[bytes.length/4]=remainder.getInt();
        words[words.length-1]=bytes.length*8;
        
        
        for (int i = 0; i < words.length; i+=16) {
            System.arraycopy(words, i, W, 0, 16);
            for (int t = 16; t < 64; t++) {
                W[t] = s_1(W[t - 2]) + W[t - 7] + s_0(W[t - 15]) + W[t - 16];
            }
            // пусть TEMP = H
            System.arraycopy(H, 0, TEMP, 0, H.length);
            for (int t = 0; t < 64; ++t) {
                int t1 = TEMP[7] + S1(TEMP[4]) + ch(TEMP[4], TEMP[5], TEMP[6]) + K[t] + W[t];
                int t2 = S0(TEMP[0]) + maj(TEMP[0], TEMP[1], TEMP[2]);
                System.arraycopy(TEMP, 0, TEMP, 1, TEMP.length - 1);
                TEMP[4] += t1;
                TEMP[0] = t1 + t2;
            }
            for (int t = 0; t < 8; t+=1) {
                H[t] += TEMP[t];
            }
        }
        StringBuilder out = new StringBuilder();
        for (int i : H){
            out.append(Integer.toHexString(i));
        }
        return out.toString();
    }
    private static int ch(int x, int y, int z) {
        return (x & y) | ((~x) & z);
    }
    private static int maj(int x, int y, int z) {
        return (x & y) | (x & z) | (y & z);
    }
    private static int S0(int x) {
        return Integer.rotateRight(x, 2)
                ^ Integer.rotateRight(x, 13)
                ^ Integer.rotateRight(x, 22);
    }
    private static int S1(int x) {
        return Integer.rotateRight(x, 6)
                ^ Integer.rotateRight(x, 11)
                ^ Integer.rotateRight(x, 25);
    }
    private static int s_0(int x) {
        return Integer.rotateRight(x, 7)
                ^ Integer.rotateRight(x, 18)
                ^ (x >>> 3);
    }
    private static int s_1(int x) {
        return Integer.rotateRight(x, 17)
                ^ Integer.rotateRight(x, 19)
                ^ (x >>> 10);
    }

//9. Напишите функцию, которая принимает строку и возвращает строку с правильным
//регистром для заголовков символов в серии "Игра престолов".
//Слова and, the, of и in должны быть строчными. Все остальные слова должны иметь
//первый символ в верхнем регистре, а остальные-в Нижнем. 
    public static String correctTitle(String str){
        char [] array = str.toCharArray();
        boolean push = false;
        int a = 0;
        for (int i = 0; i<array.length; i++){
            if (Character.isWhitespace(array[i])) {
                if (push){
                    push = false;
                    correctSingleTitle(array, a); // скорректировать одно слово, если 
                    // слово не равно "and", "the", "of", "in"
                }
            }
            else{
                if (!push) {
                    a = i;
                    push = true;
                }
                array[i]=Character.toLowerCase(array[i]); // нижний регистр
            }
        } 
        if (push) correctSingleTitle(array, a);
        return new String(array);
    }
        
    public static void correctSingleTitle(char [] array, int a){
                    if (isEqual(array, a, "of")) return;
                    if (isEqual(array, a, "in")) return;
                    if (isEqual(array, a, "and")) return;
                    if (isEqual(array, a, "the")) return;
                    array[a]=Character.toUpperCase(array[a]);// скорректировать одно слово, если 
                    // слово не равно "and", "the", "of", "in"
    }
    
    public static boolean isEqual(char[] array, int a, String str) {
        // проверяет, содержит ли массив array строку str в индексе a
        int size = str.length();
        if (size>(array.length-a)) return false; 
        for(int i=0;i<size;i++){
            if (array[a] != str.charAt(i)) return false;
            a++;
        }
        return true;
    }
    
//10 Напишите функцию, которая принимает целое число n и возвращает "недопустимое", если
//n не является центрированным шестиугольным числом или его иллюстрацией в виде
//многострочной прямоугольной строки в противном случае.
    public static String hexLattice(int t){
        if (t==1){
            return "o";
        }
        else{
            t=(t-1)/6;
            if (t<1) return "Invalid";
            if (t==1) return "  o o\n o o o\n  o o ";
            t=t/3;
            if (t<1) return "Invalid";
            double c = log(t, 2);
            if ((c-(int)c)!=0) return "Invalid";
            else{
                StringBuilder out = new StringBuilder();
                int max = 5 + (int)c;
                int i = (max / 2);
                while (i<max){
                    i++;
                    out.append( String.join("", Collections.nCopies(max-i, " ")));
                    out.append( String.join("", Collections.nCopies(i, " o")));
                    out.append("\n");                    
                }
                int min = (max / 2)+1;
                while (i>min){
                    i--;
                    out.append( String.join("", Collections.nCopies(max-i, " ")));
                    out.append( String.join("", Collections.nCopies(i, " o")));
                    out.append("\n");                    
                }
                return out.toString();
            }
        }
    }
    private static double log(double logNumber, double base) {
         return Math.log(logNumber) / Math.log(base);
    }
      
    public static void main(String [] argv){
        System.out.println(hexLattice(19));
    }
}
