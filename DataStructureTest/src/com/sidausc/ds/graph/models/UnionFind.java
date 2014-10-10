package com.sidausc.ds.graph.models;

public interface UnionFind<K> {

	/**
	 * Returns the component identifier for the component containing site <tt>p</tt>.
	 * @param p the integer representing one object
	 * @return the component identifier for the component containing site <tt>p</tt>
	 * @throws java.lang.IndexOutOfBoundsException unless <tt>0 &le; p &lt; N</tt>
	 */
	public abstract K find(K k);

	/**
	 * Returns the number of components.
	 * @return the number of components (between <tt>1</tt> and <tt>N</tt>)
	 */
	public abstract K count();

	/**
	 * Are the two sites <tt>p</tt> and <tt>q</tt> in the same component?
	 * @param p the integer representing one site
	 * @param q the integer representing the other site
	 * @return true if the two sites <tt>p</tt> and <tt>q</tt> are in the same component; false otherwise
	 * @throws java.lang.IndexOutOfBoundsException unless
	 *      both <tt>0 &le; p &lt; N</tt> and <tt>0 &le; q &lt; N</tt>
	 */
	public abstract boolean connected(K p, K q);

	/**
	 * Merges the component containing site <tt>p</tt> with the 
	 * the component containing site <tt>q</tt>.
	 * @param p the integer representing one site
	 * @param q the integer representing the other site
	 * @throws java.lang.IndexOutOfBoundsException unless
	 *      both <tt>0 &le; p &lt; N</tt> and <tt>0 &le; q &lt; N</tt>
	 */
	public abstract void union(K p, K q);

	

}