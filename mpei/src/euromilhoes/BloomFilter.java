package euromilhoes;

public class BloomFilter {
	
		private int[] filter;
		private int numElem=0;
		private int hashes;
		private final int[] seeds;
		private double ratio=0.005;
		
		public BloomFilter(int n) {
			
			int[] a= new int[OptimizedBF(n)];
			this.filter=a;
			this.hashes=OptimizedHF();
			//faltam as seeds (???)
		}
		
		public BloomFilter(int n, double ratio) {
			this.ratio=ratio;
			int[] a= new int[OptimizedBF(n)];
			this.filter=a;
			this.hashes=OptimizedHF();
			//faltam as seeds (???)			
		}
		
		
		public void insert(Object o) {
			
			int [] pos= new int[hashes];
			for(int i=0;i<hashes;i++) {
				//pos[i]=Math.abs((o.hashCode()^seeds[i]) % filter.length);
			}
			
			for(int i=0;i<pos.length;i++) {
				filter[pos[i]]=1;
			}
			numElem++;
		}
		
		
		public boolean check(Object o) {
			
			int [] pos= new int[hashes];
			for(int i=0;i<hashes;i++) {
				//pos[i]=Math.abs((o.hashCode()^seeds[i]) % filter.length);
			}
	
			for(int i=0;i<pos.length;i++) {
				if(filter[pos[i]]!=1) return false;
			}
			
			return true;
		}

		public int size() { return numElem;}
		
		
		//Nr de HashFunctions
		public static int OptimizedHF(int size, int elems) { return	(int) Math.round((size/elems)*Math.log(2));}
		public static int OptimizedHF(double prob) { return (int) (Math.log(prob)/Math.log(2)); }
		public int OptimizedHF() { return (int)Math.round(Math.abs(Math.log(ratio)/Math.log(2))); }

		//Nr de Elementos
		public static int OptimizedBF (int elems, double prob) { return (int) (elems*OptimizedHF(prob)/Math.log(2)); }
		public int OptimizedBF (int elems) { return (int) Math.round(elems*OptimizedHF()/Math.log(2)); }
		


}
