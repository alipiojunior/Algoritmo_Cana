import java.util.*;

public class Ordenadores {

    // QUICK SORT
    public static void quickSort(int[] vetor, int inicio, int fim) {
        if (inicio < fim) {                            // O(1)
            int pivo = dividir(vetor, inicio, fim);   // O(n) no pior caso
            quickSort(vetor, inicio, pivo - 1);       // Recursão - O(log n) chamadas
            quickSort(vetor, pivo + 1, fim);          // Recursão - O(log n) chamadas
        }
    }    

    private static int dividir(int[] vetor, int inicio, int fim) {
        int pivo = vetor[fim];                        // O(1)
        int i = inicio - 1;                           // O(1)
    
        for (int j = inicio; j < fim; j++) {          // O(n)
            if (vetor[j] <= pivo) {                   // O(1)
                i++;                                  // O(1)
                trocar(vetor, i, j);                  // O(1)
            }
        }
    
        trocar(vetor, i + 1, fim);                    // O(1)
        return i + 1;                                 // O(1)
    }


    // HEAP SORT
    public static void heapSort(int[] vetor) {
        int n = vetor.length;                         // O(1)
    
        for (int i = n / 2 - 1; i >= 0; i--)          // O(n)
            ajustarHeap(vetor, n, i);                 // O(log n)
    
        for (int i = n - 1; i > 0; i--) {             // O(n)
            trocar(vetor, 0, i);                      // O(1)
            ajustarHeap(vetor, i, 0);                 // O(log n)
        }
    }    

    private static void ajustarHeap(int[] vetor, int n, int i) {
        int maior = i;                                // O(1)
        int esquerda = 2 * i + 1;                     // O(1)
        int direita = 2 * i + 2;                      // O(1)
    
        if (esquerda < n && vetor[esquerda] > vetor[maior])  // O(1)
            maior = esquerda;
    
        if (direita < n && vetor[direita] > vetor[maior])    // O(1)
            maior = direita;
    
        if (maior != i) {
            trocar(vetor, i, maior);                  // O(1)
            ajustarHeap(vetor, n, maior);             // Recursão O(log n)
        }
    }


    // SELECTION SORT
    public static void selectionSort(int[] vetor) {
        int n = vetor.length;                         // O(1)
        for (int i = 0; i < n - 1; i++) {             // O(n)
            int menor = i;                            // O(1)
            for (int j = i + 1; j < n; j++) {         // O(n)
                if (vetor[j] < vetor[menor])          // O(1)
                    menor = j;                        // O(1)
            }
            trocar(vetor, i, menor);                  // O(1)
        }
    }

    // COUNTING SORT (somente inteiros >= 0)
    public static void countingSort(int[] vetor) {
        int maior = Arrays.stream(vetor).max().getAsInt(); // O(n)
        int[] contagem = new int[maior + 1];               // O(k)
        int[] saida = new int[vetor.length];               // O(n)
    
        for (int num : vetor)                              // O(n)
            contagem[num]++;                               // O(1)
    
        for (int i = 1; i < contagem.length; i++)          // O(k)
            contagem[i] += contagem[i - 1];                // O(1)
    
        for (int i = vetor.length - 1; i >= 0; i--) {       // O(n)
            saida[contagem[vetor[i]] - 1] = vetor[i];       // O(1)
            contagem[vetor[i]]--;                          // O(1)
        }
    
        System.arraycopy(saida, 0, vetor, 0, vetor.length); // O(n)
    }
    

    // BUCKET SORT (para números entre 0 e 1)
    public static void bucketSort(float[] vetor) {
        int n = vetor.length;                             // O(1)
        List<Float>[] baldes = new ArrayList[n];          // O(n)
    
        for (int i = 0; i < n; i++)                       // O(n)
            baldes[i] = new ArrayList<>();                // O(1)
    
        for (float num : vetor) {                         // O(n)
            int indice = (int) (num * n);                 // O(1)
            baldes[indice].add(num);                      // O(1)
        }
    
        for (List<Float> balde : baldes)                  // O(n)
            Collections.sort(balde);                      // O(k log k)
    
        int k = 0;
        for (List<Float> balde : baldes)                  // O(n)
            for (float num : balde)                       // O(n)
                vetor[k++] = num;                         // O(1)
    }
    

    // Função auxiliar para trocar elementos
    private static void trocar(int[] vetor, int i, int j) {
        int temp = vetor[i];      // O(1)
        vetor[i] = vetor[j];      // O(1)
        vetor[j] = temp;          // O(1)
    }
    

    // Teste dos algoritmos
    public static void main(String[] args) {
        int[] dados = {9, 4, 2, 8, 5, 1, 3};
        int[] copia1 = dados.clone();
        int[] copia2 = dados.clone();
        int[] copia3 = dados.clone();
        int[] copia4 = dados.clone();

        System.out.println("Original:       " + Arrays.toString(dados));

        quickSort(copia1, 0, copia1.length - 1);
        System.out.println("QuickSort:      " + Arrays.toString(copia1));

        heapSort(copia2);
        System.out.println("HeapSort:       " + Arrays.toString(copia2));

        selectionSort(copia3);
        System.out.println("SelectionSort:  " + Arrays.toString(copia3));

        countingSort(copia4);
        System.out.println("CountingSort:   " + Arrays.toString(copia4));

        float[] dadosFloat = {0.42f, 0.32f, 0.23f, 0.52f, 0.25f, 0.47f};
        System.out.println("BucketSort (antes): " + Arrays.toString(dadosFloat));
        bucketSort(dadosFloat);
        System.out.println("BucketSort (depois): " + Arrays.toString(dadosFloat));
    }
}
