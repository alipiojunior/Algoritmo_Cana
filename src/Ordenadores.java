import java.util.*;

public class Ordenadores {

    // QUICK SORT
    public static void quickSort(int[] vetor, int inicio, int fim) {
        if (inicio < fim) {
            int pivo = dividir(vetor, inicio, fim);
            quickSort(vetor, inicio, pivo - 1);
            quickSort(vetor, pivo + 1, fim);
        }
    }

    private static int dividir(int[] vetor, int inicio, int fim) {
        int pivo = vetor[fim];
        int i = inicio - 1;

        for (int j = inicio; j < fim; j++) {
            if (vetor[j] <= pivo) {
                i++;
                trocar(vetor, i, j);
            }
        }

        trocar(vetor, i + 1, fim);
        return i + 1;
    }

    // HEAP SORT
    public static void heapSort(int[] vetor) {
        int n = vetor.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            ajustarHeap(vetor, n, i);

        for (int i = n - 1; i > 0; i--) {
            trocar(vetor, 0, i);
            ajustarHeap(vetor, i, 0);
        }
    }

    private static void ajustarHeap(int[] vetor, int n, int i) {
        int maior = i;
        int esquerda = 2 * i + 1;
        int direita = 2 * i + 2;

        if (esquerda < n && vetor[esquerda] > vetor[maior])
            maior = esquerda;

        if (direita < n && vetor[direita] > vetor[maior])
            maior = direita;

        if (maior != i) {
            trocar(vetor, i, maior);
            ajustarHeap(vetor, n, maior);
        }
    }

    // SELECTION SORT
    public static void selectionSort(int[] vetor) {
        int n = vetor.length;
        for (int i = 0; i < n - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < n; j++) {
                if (vetor[j] < vetor[menor])
                    menor = j;
            }
            trocar(vetor, i, menor);
        }
    }

    // COUNTING SORT (somente inteiros >= 0)
    public static void countingSort(int[] vetor) {
        int maior = Arrays.stream(vetor).max().getAsInt();
        int[] contagem = new int[maior + 1];
        int[] saida = new int[vetor.length];

        for (int num : vetor)
            contagem[num]++;

        for (int i = 1; i < contagem.length; i++)
            contagem[i] += contagem[i - 1];

        for (int i = vetor.length - 1; i >= 0; i--) {
            saida[contagem[vetor[i]] - 1] = vetor[i];
            contagem[vetor[i]]--;
        }

        System.arraycopy(saida, 0, vetor, 0, vetor.length);
    }

    // BUCKET SORT (para números entre 0 e 1)
    public static void bucketSort(float[] vetor) {
        int n = vetor.length;
        List<Float>[] baldes = new ArrayList[n];

        for (int i = 0; i < n; i++)
            baldes[i] = new ArrayList<>();

        for (float num : vetor) {
            int indice = (int) (num * n);
            baldes[indice].add(num);
        }

        for (List<Float> balde : baldes)
            Collections.sort(balde);

        int k = 0;
        for (List<Float> balde : baldes)
            for (float num : balde)
                vetor[k++] = num;
    }

    // Função auxiliar para trocar elementos
    private static void trocar(int[] vetor, int i, int j) {
        int temp = vetor[i];
        vetor[i] = vetor[j];
        vetor[j] = temp;
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
