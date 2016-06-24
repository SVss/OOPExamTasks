import structures.TBitArray;
import structures.TList;

public class Main {
    private static void printList(TList<Integer> list) {
        for (int i = 0; i < list.getSize(); i++) {
            System.out.print(list.get(i) + ", ");
        }
        System.out.println("");

    }

    private static void testList() {
        TList<Integer> list = new TList<>();
        for (int i = 1; i < 10; i++) {
            list.add(i);
        }
        printList(list);
        list.remove(0);
        printList(list);
        list.remove(list.getSize());
        printList(list);
        list.setElementAt(0, 100);
        printList(list);

        TList<Integer> newList = new TList<>();
        newList.add(1);
        newList.add(2);
        list.moveTo(newList);
        printList(newList);
    }

    private static void testBitArray(){
        TBitArray bitArray = new TBitArray();
        bitArray.setElement(1, true);
        bitArray.setElement(2, true);
//        bitArray.setElement(3, true);

        System.out.println(bitArray.getElement(1));
        System.out.println(bitArray.getElement(2));
        System.out.println(bitArray.getElement(3));

//        bitArray.setElement(3, false);
//        System.out.println(bitArray.getElement(3));
    }

    public static void main(String[] args) {
        testBitArray();
    }
}
