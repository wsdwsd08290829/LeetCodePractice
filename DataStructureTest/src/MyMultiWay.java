
import com.sidausc.ds.priorityqueue.IndexedPriorityQueue;
import com.sidausc.ds.priorityqueue.IndexedPriorityQueue.Entry;


public class MyMultiWay {
	public static void runMultiWay(In[] streams){
		String s = "";
		IndexedPriorityQueue<String, Integer> ipq = new IndexedPriorityQueue<String, Integer>();
		for(int i = 0 ;i < streams.length;i++){
			ipq.insert(streams[i].readString(), (Integer)i);
		}
		while(ipq.size()>0){
			Entry<String, Integer> e = ipq.remove();
			int index = e.getValue();
			String result = e.getKey();
			s+= result;
			if(!streams[index].isEmpty())ipq.insert(streams[index].readString(), index);

		}
		System.out.println(s);
	}
	public static void main(String[] args) {
		int num = args.length;
		In[] inStream = new In[num];
		for(int i=0;i<num;i++){
			inStream[i] = new In(args[i]);
		}
		runMultiWay(inStream);
		/*for(int i=0;i<num;i++){
			while(!inStream[i].isEmpty()){
				System.out.println(inStream[i].readString());
			}
		}*/
		
	}
}
