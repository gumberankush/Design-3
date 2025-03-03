import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class FlattenNestedList {
    // Approach 1
    /**
     * // This is the interface that allows for creating nested lists.
     * // You should not implement it, or speculate about its implementation
     * public interface NestedInteger {
     *
     *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
     *     public boolean isInteger();
     *
     *     // @return the single integer that this NestedInteger holds, if it holds a single integer
     *     // Return null if this NestedInteger holds a nested list
     *     public Integer getInteger();
     *
     *     // @return the nested list that this NestedInteger holds, if it holds a nested list
     *     // Return empty list if this NestedInteger holds a single integer
     *     public List<NestedInteger> getList();
     * }
     */
    public class NestedIterator implements Iterator<Integer> {

        List<Integer> list;
        int i = 0;

        public NestedIterator(List<NestedInteger> nestedList) {
            this.list = new ArrayList<>();
            addToList(nestedList);
        }

        public void addToList(List<NestedInteger> nestedList){
            for(NestedInteger elem: nestedList){
                if(elem.isInteger()){
                    list.add(elem.getInteger());
                }else{
                    addToList(elem.getList());
                }
            }
        }

        @Override
        public Integer next() {
            if(hasNext()){
                int elem = list.get(i);
                i++;
                return elem;
            }
            return null;
        }

        @Override
        public boolean hasNext() {
            return i < list.size();
        }
    }

    // Approach 2
    public class NestedIterator implements Iterator<Integer> {
        NestedInteger nextEl;
        Stack<Iterator<NestedInteger>> st;

        public NestedIterator(List<NestedInteger> nestedList) {
            this.st = new Stack<>();
            st.push(nestedList.iterator());
        }

        @Override
        public Integer next() {
            return nextEl.getInteger();
        }

        @Override
        public boolean hasNext() { // Amortized O(1). Worst case is O(h) where h is the depth of the nested list
            while(!st.isEmpty()){
                if(!st.peek().hasNext()){
                    st.pop();
                }else if((nextEl = st.peek().next()).isInteger()){
                    return true;
                }else{
                    st.push(nextEl.getList().iterator());
                }
            }
            return false;
        }
    }

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
}
