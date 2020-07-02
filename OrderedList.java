/*
Autumn Capasso
UMGC
CMSC 350
PROJECT 2
 */

package Project2;
import java.util.List;


    public class OrderedList
    {
        //determins wheather a list object is supplied as a par in assending order
        public static <T extends Comparable<? super T>> boolean checkSorted(List<T> list)
        {
            boolean isSorted = true;
            for(int i = list.size()-1; i > 0 ; i--)
            {
                T current = list.get(i);
                //checks sort
                if(!checkSorted(list, current))
                {
                    isSorted = false;
                }
            }
            return isSorted;
        }

        private static <T extends Comparable<? super T>> boolean checkSorted(List<T> list, T current) {
            T currentValue = list.get(list.indexOf(current));
            T nextValue = list.get(list.indexOf(current) - 1);

            //if its not empty it keeps going to the next
            if (nextValue != null)
            {
                //returns current value
                return currentValue.compareTo(nextValue) >= 0;
            }
            return true;
        }


    }
