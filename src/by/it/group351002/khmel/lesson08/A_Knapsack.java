package by.it.group351002.khmel.lesson08;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: рюкзак с повторами
Первая строка входа содержит целые числа
    1<=W<=100000     вместимость рюкзака
    1<=n<=300        сколько есть вариантов золотых слитков
                     (каждый можно использовать множество раз).
Следующая строка содержит n целых чисел, задающих веса слитков:
  0<=w[1]<=100000 ,..., 0<=w[n]<=100000
Найдите методами динамического программирования
максимальный вес золота, который можно унести в рюкзаке.
Sample Input:
10 3
1 4 8
Sample Output:
10
Sample Input 2:
15 3
2 8 16
Sample Output 2:
14
*/
/*найти максимальную ценность, которую можно унести в рюкзаке заданной вместимости,
учитывая набор предметов с разным весом и ценностью.*/
/*В этой конкретной задаче предметами являются золотые слитки, и каждый слиток можно
использовать несколько раз (с повторениями).

 Задача — найти максимальный вес золота, который можно унести в рюкзаке.*/
/*В этой задаче каждый предмет (золотой слиток) можно использовать повторно*/
class A_Knapsack {
/*gold для хранения весов золотых слитков и dp для хранения вычисленных максимальных весов для
рюкзаков различной вместимости.*/
    int getMaxWeight(InputStream stream ) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        Scanner scanner = new Scanner(stream);
        int w=scanner.nextInt();
        int n=scanner.nextInt();
        int gold[]=new int[n], dp[]=new int[w+1];
        for (int i = 0; i < n; i++) {
            gold[i]=scanner.nextInt();
        }
/*Для каждого ранца вместимостью iот 1 до W:

Инициализируйте dp[i]значение dp[i-1], которое представляет максимальный вес, если текущий золотой слиток не включен.
За каждый золотой слиток j, вес которого gold[j] меньше или равен текущей вместимости рюкзака i:
максимальный вес, который можно получить, включив текущий золотой слиток jи максимальный
вес оставшейся емкости i-gold[j].
Обновление dp[i]с максимальным текущим значением и рассчитанным весом.*/
        dp[0] = 0;
        for(int i=1;i<=w;i++)
        {
            dp[i] = dp[i-1];
            for (int j=0;j<n && gold[j]<=i;j++)
                dp[i] = Math.max(dp[i],gold[j]+dp[i-gold[j]]);
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return dp[w];
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson08/dataA.txt");
        A_Knapsack instance = new A_Knapsack();
        int res=instance.getMaxWeight(stream);
        System.out.println(res);
    }
}