import java.util.*;
public class ShellSort {
	Random r = new Random();
	public List<Integer> generateGapSequence1(int n) {
		List<Integer> gaps = new ArrayList<>();
		
		while(n/2 > 0) {
			gaps.add(n/2);
			n /= 2;
		}
		
		return gaps;
	}
	public List<Integer> generateGapSequence2(int n) {
		List<Integer> gaps = new ArrayList<>();
		int temp = 2;
		while(temp <= n) {
			gaps.add(temp - 1);
			temp *= 2;
		}
		Collections.reverse(gaps);
		return gaps;
	}
	public List<Integer> generateGapSequence3(int n) {
		List<Integer> gaps = new ArrayList<>();
		for(int p = 0; p < n; p++) {
			int pp = (int)Math.pow(2, p);
			if(pp >= n)
				break;
			for(int q = 0; q < n; q++) {
				int pq = pp*(int)Math.pow(3, q);
				if(pq >= n)
					break;
				gaps.add(pq);
			}
		}
		Collections.sort(gaps, Collections.reverseOrder());
		return gaps;
	}
	public List<Integer> generateGapSequence4(int n) {
		List<Integer> gaps = new ArrayList<>();
		int p = 1, m = 0;
		while (p < n) {
			gaps.add(p);
			p = (int)Math.pow(4, m+1) + 3*(int)Math.pow(2, m) + 1;
			m++;
		}
		
		Collections.sort(gaps, Collections.reverseOrder());
		return gaps;
	}
	public List<Integer> generateGapSequence5(int n) {
		List<Integer> gaps = new ArrayList<>();
		int p = 1, m = 1;
		while (p < n) {
			gaps.add(p);
			p = (1 + (int)Math.pow(3,m))/2;
			m++;
		}
		
		Collections.sort(gaps, Collections.reverseOrder());
		return gaps;
	}
	
	public void sort(int[] arr, List<Integer> gaps) {
		int n = arr.length;
		
		for (int gap:gaps) {
			for (int i = gap; i < n; i++) {
				int temp = arr[i];
				int j = i;
				while (j >= gap && temp < arr[j - gap]) {
					arr[j] = arr[j - gap];
					j -= gap;
				}
				arr[j] = temp;
			}
		}
	}
	
	public int[] uniformPerm(int n) {
		int[] arr = new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = r.nextInt(n*10);
		}
		return arr;
	}
	
	public void fisheryates(int[] arr) {
		int n = arr.length;
		for(int i = n - 1; i > 0; i--) {
			int ind = r.nextInt(i+1);
			int temp = arr[i];
			arr[i] = arr[ind];
			arr[ind] = temp;
		}
	}
	
	public int[] almostSorted(int n) {
		int[] arr = new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = i + 1;
		}
		return arr;
	}
	
	public void swapNumbers(int[] arr) {
		int n = arr.length;
		int m = 2*(int)(Math.log(n)/Math.log(2));
		for(int k = 0; k < m; k++) {
			int i = r.nextInt(n), j = r.nextInt(n);
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
	}
	
	public static void main(String[] args) {
		int[] sizes = {16, 128, 1024, 8192, 65536};
		ShellSort ss = new ShellSort();
		
		
		for(int size:sizes) {
			System.out.println("Size: " + size);
			int[] arr1 = ss.uniformPerm(size);
			int[] arr2 = ss.almostSorted(size);
			
			System.out.println("Sequence 1:");
			List<Integer> gaps = ss.generateGapSequence1(size);
			System.out.println("Uniformly Distributed Permutations");
			long time = 0;
			for(int i = 0; i < 5; i++) {
				ss.fisheryates(arr1);
				//System.out.println("Unsorted List: " + Arrays.toString(arr));
				long start = System.nanoTime();
				ss.sort(arr1, gaps);
				long end = System.nanoTime();
				time += end - start;
				//System.out.println("Sorted List: " + Arrays.toString(arr));
			}
			System.out.println("Time taken: " + time/5);
			System.out.println();
			
			time = 0;
			System.out.println("Almost Sorted Permutations");
			for(int i = 0; i < 5; i++) {
				ss.swapNumbers(arr2);
				//System.out.println("Unsorted List: " + Arrays.toString(arr));
				long start = System.nanoTime();
				ss.sort(arr2, gaps);
				long end = System.nanoTime();
				time += end - start;
				//System.out.println("Sorted List: " + Arrays.toString(arr));
			}
			System.out.println("Time taken: " + time/5);
			System.out.println();
			
			
			System.out.println("Sequence 2:");
			gaps = ss.generateGapSequence2(size);
			System.out.println("Uniformly Distributed Permutations");
			time = 0;
			for(int i = 0; i < 5; i++) {
				ss.fisheryates(arr1);
				//System.out.println("Unsorted List: " + Arrays.toString(arr));
				long start = System.nanoTime();
				ss.sort(arr1, gaps);
				long end = System.nanoTime();
				time += end - start;
				//System.out.println("Sorted List: " + Arrays.toString(arr));
			}
			System.out.println("Time taken: " + time/5);
			System.out.println();
			
			time = 0;
			System.out.println("Almost Sorted Permutations");
			for(int i = 0; i < 5; i++) {
				ss.swapNumbers(arr2);
				//System.out.println("Unsorted List: " + Arrays.toString(arr));
				long start = System.nanoTime();
				ss.sort(arr2, gaps);
				long end = System.nanoTime();
				time += end - start;
				//System.out.println("Sorted List: " + Arrays.toString(arr));
			}
			System.out.println("Time taken: " + time/5);
			System.out.println();
			
			
			System.out.println("Sequence 3:");
			gaps = ss.generateGapSequence3(size);
			System.out.println("Uniformly Distributed Permutations");
			time = 0;
			for(int i = 0; i < 5; i++) {
				ss.fisheryates(arr1);
				//System.out.println("Unsorted List: " + Arrays.toString(arr));
				long start = System.nanoTime();
				ss.sort(arr1, gaps);
				long end = System.nanoTime();
				time += end - start;
				//System.out.println("Sorted List: " + Arrays.toString(arr));
			}
			System.out.println("Time taken: " + time/5);
			System.out.println();
			
			time = 0;
			System.out.println("Almost Sorted Permutations");
			for(int i = 0; i < 5; i++) {
				ss.swapNumbers(arr2);
				//System.out.println("Unsorted List: " + Arrays.toString(arr));
				long start = System.nanoTime();
				ss.sort(arr2, gaps);
				long end = System.nanoTime();
				time += end - start;
				//System.out.println("Sorted List: " + Arrays.toString(arr));
			}
			System.out.println("Time taken: " + time/5);
			System.out.println();
			
			
			System.out.println("Sequence 4:");
			gaps = ss.generateGapSequence4(size);
			System.out.println("Uniformly Distributed Permutations");
			time = 0;
			for(int i = 0; i < 5; i++) {
				ss.fisheryates(arr1);
				//System.out.println("Unsorted List: " + Arrays.toString(arr));
				long start = System.nanoTime();
				ss.sort(arr1, gaps);
				long end = System.nanoTime();
				time += end - start;
				//System.out.println("Sorted List: " + Arrays.toString(arr));
			}
			System.out.println("Time taken: " + time/5);
			System.out.println();
			
			time = 0;
			System.out.println("Almost Sorted Permutations");
			for(int i = 0; i < 5; i++) {
				ss.swapNumbers(arr2);
				//System.out.println("Unsorted List: " + Arrays.toString(arr));
				long start = System.nanoTime();
				ss.sort(arr2, gaps);
				long end = System.nanoTime();
				time += end - start;
				//System.out.println("Sorted List: " + Arrays.toString(arr));
			}
			System.out.println("Time taken: " + time/5);
			System.out.println();
			
			
			System.out.println("Sequence 5:");
			gaps = ss.generateGapSequence1(size);
			System.out.println("Uniformly Distributed Permutations");
			time = 0;
			for(int i = 0; i < 5; i++) {
				ss.fisheryates(arr1);
				//System.out.println("Unsorted List: " + Arrays.toString(arr));
				long start = System.nanoTime();
				ss.sort(arr1, gaps);
				long end = System.nanoTime();
				time += end - start;
				//System.out.println("Sorted List: " + Arrays.toString(arr));
			}
			System.out.println("Time taken: " + time/5);
			System.out.println();
			
			time = 0;
			System.out.println("Almost Sorted Permutations");
			for(int i = 0; i < 5; i++) {
				ss.swapNumbers(arr2);
				//System.out.println("Unsorted List: " + Arrays.toString(arr));
				long start = System.nanoTime();
				ss.sort(arr2, gaps);
				long end = System.nanoTime();
				time += end - start;
				//System.out.println("Sorted List: " + Arrays.toString(arr));
			}
			System.out.println("Time taken: " + time/5);
			System.out.println();
		}
		
	}
}
