/**Dictionary With terms and Meaning
*
*@author Idan Calvo
*@version 1.0
*/

import java.util.TreeMap;
import java.util.Map;
import java.io.Serializable;

public class Dictionary implements Serializable {

	public static final int SUCCESS = 1;
	public static final int FAILD_EXISTS = 0;
	public static final int FAILD_INVALID = -1;

//#Fields
	private TreeMap<String, String> base;

//#Constructor
	public Dictionary() {
		base = new TreeMap<String, String>();
	}

//#Methods
	
	//added	value (term and Meaning) to dictionary
	public int addVal(String term, String meaning) {
		if (term != null || meaning != null) {
			if (!this.base.containsKey(term)) {
				this.base.put(term, meaning);
				return SUCCESS;
			} else {
				return FAILD_EXISTS;
			}
		} else {
			return FAILD_INVALID;
		}
	}
	
	//Deletion	value (term and Meaning) from dictionary
	public int delVal(String term) {
		if (term != null) {
			if (this.base.containsKey(term)) {
				this.base.remove(term);
				return SUCCESS;
			} else {
				return FAILD_EXISTS;
			}
		} else {
			return FAILD_INVALID;
		}
	}
	
	//update value (term and Meaning) on dictionary
	public int updateVal(String term, String meaning) {
		if (term != null || meaning != null) {
			if (this.base.containsKey(term)) {
				this.base.replace(term, meaning);
				return SUCCESS;
			} else {
				return FAILD_EXISTS;
			}
		} else {
			return FAILD_INVALID;
		}
	}

	//Search value (term and Meaning) in dictionary
	public String Search(String term) {
		return this.base.get(term);
	}

	//return all values [term:	Meaning]
	@Override
	public String toString() {
		String ans1 = "";
		String ans2 = "";
		for (Map.Entry m : base.entrySet()) {
			ans1 = m.getKey() + " :\t\t" + m.getValue();
			ans1 = ans1.replaceAll("\n", ".\t") + "\n";
			ans2 += ans1;
		}
		return ans2;
	}
}
