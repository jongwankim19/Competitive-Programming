class BIT {

	public long[] cumfreq;

	// Do indexes 1 to n.
	public bit(int n) {

		int size = 1;
		while (size < n) size <<= 1;
		n = size;

		cumfreq = new long[n+1];
	}

	// Uses 1 based indexing.
	public void add(int index, long value) {
		while (index < cumfreq.length) {
			cumfreq[index] += value;
			index += Integer.lowestOneBit(index);
		}
	}

	// Returns the sum of everything upto index.
	public long sum(int index) {
		long ans = 0;
		while (index > 0) {
			ans += cumfreq[index];
			index -= (Integer.lowestOneBit(index));
		}
		return ans;
	}

	// Use 1 based indexing.
	public long sum(int low, int high) {
		return sum(high) - sum(low-1);
	}

	// Return the total number of items in the BIT.
	public long all() {
		return sum(cumfreq.length-1);
	}

	// Return the total number of items in the BIT at or above index.
	public long above(int index) {
		return all() - sum(index);
	}
}
