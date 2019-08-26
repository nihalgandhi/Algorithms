import java.util.*;
public class BinPacking {
	Random r = new Random();
	public int nextfit (double[] items, int capacity) {
		int bins = 1;
		double remaining = capacity;
		for(int i = 0;i < items.length; i++) {
			if(items[i] <= remaining)
				remaining -= items[i];
			else {
				bins++;
				remaining = 1 - items[i];
			}
		}
		return bins;
	}
	
	public int firstfit (double[] items, int capacity) {
		int bins = 1;
		double[] remaining = new double[items.length];
		Arrays.fill(remaining, capacity);
		for(int i = 0; i < items.length; i++) {
			int j;
			for(j = 0; j < bins; j++) {
				if(remaining[j] >= items[i]) {
					remaining[j] -= items[i];
					break;
				}
			}
			if (j == bins) {
				remaining[j] -= items[i];
				bins++;
			}
		}
		return bins;
	}
	
	public int decfirstfit (double[] items, int capacity) {
		Arrays.sort(items);
		items = reverse(items);
		int bins = firstfit(items, capacity);
		return bins;
	}
	
	public int bestfit (double[] items, int capacity) {
		int bins = 1;
		double[] remaining = new double[items.length];
		Arrays.fill(remaining, capacity);
		for(int i = 0; i < items.length; i++) {
			int j, index = -1;
			double min = 1.1;
			for(j = 0; j < bins; j++) {
				if(remaining[j] >= items[i]) {
					if(min > remaining[j] - items[i]) {
						min = remaining[j] - items[i];
						index = j;
					}
				}
			}
			if (min < 1.1) {
				remaining[index] -= items[i];
			}
			else {
				remaining[bins] -= items[i];
				bins++;
			}
		}
		return bins;
	}
	
	public int decbestfit (double[] items, int capacity) {
		Arrays.sort(items);
		items = reverse(items);
		int bins = bestfit(items, capacity);
		return bins;
	}
	
	public double sum (double[] items) {
		double sum = 0;
		for(int i = 0; i < items.length; i++) {
			sum += items[i];
		}
		return sum;
	}
	
	public double[] reverse (double[] items) {
		for(int i = 0; i < items.length/2; i++) {
			double temp = items[i];
			items[i] = items[items.length - i - 1];
			items[items.length - i - 1] = temp;
		}
		return items;
	}
	
	public double[] uniformPerm(int n) {
		double[] arr = new double[n];
		for(int i = 0; i < n; i++) {
			arr[i] = r.nextDouble()*0.8;
		}
		return arr;
	}
	
	public static void main (String[] args) {
		int[] sizes = {64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072};
		BinPacking bp = new BinPacking();
		//double[] items = {0.5,0.7,0.5,0.2,0.4,0.2,0.5,0.1,0.6};
		for(int size:sizes) {
			int nfbins = 0, ffbins = 0, bfbins = 0, dffbins = 0, dbfbins = 0;
			double sum = 0;
			System.out.println("Size: " + size);
			for(int i = 0; i < 5; i++) {
				double[] items = bp.uniformPerm(size);
				sum += bp.sum(items);
				nfbins += bp.nextfit(items, 1);
				ffbins += bp.firstfit(items, 1);
				bfbins += bp.bestfit(items, 1);
				dffbins += bp.decfirstfit(items, 1);
				dbfbins += bp.decbestfit(items, 1);
			}
			System.out.println("NF bins: " + nfbins/5.0);
			System.out.println("NextFit Waste W(A): " + (nfbins/5.0 - sum/5.0));
			System.out.println("FF bins: " + ffbins/5.0);
			System.out.println("FirstFit Waste W(A): " + (ffbins/5.0 - sum/5.0));
			System.out.println("BF bins: " + bfbins/5.0);
			System.out.println("BestFit Waste W(A): " + (bfbins/5.0 - sum/5.0));
			System.out.println("DFF bins: " + dffbins/5.0);
			System.out.println("DecFirstFit Waste W(A): " + (dffbins/5.0 - sum/5.0));
			System.out.println("DBF bins: " + dbfbins/5.0);		
			System.out.println("DecBestFit Waste W(A): " + (dbfbins/5.0 - sum/5.0));
			System.out.println();
		}
	}
}
