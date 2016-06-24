import com.sun.javafx.scene.control.TableColumnSortTypeWrapper;
import org.omg.CORBA.INTERNAL;
import structures.*;

import java.util.Comparator;

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

    private static void testCircularBuffer(){
        TCircularBuffer buffer = new TCircularBuffer(5);
        for (byte i = 0; i < 5; i++){
            buffer.write(i);
        }
        buffer.read();
        buffer.write((byte)5);
        buffer.increaseSize(1);
        buffer.write((byte)6);




        while(!buffer.isEmpty()){
            System.out.println("r: " + buffer.getReadPosition() + ", " + buffer.read());
        }
    }

    private static void testStack(){
        TStack<Integer> stack = new TStack<>();
        for (int i = 0; i < 5; i++){
            stack.push(i);
        }

        for (int i = 0; i < 5; i++){
            System.out.println(stack.pop());
        }
    }

    private static void testProcessorStack(){
        TProcessorStack processorStack = new TProcessorStack();
        processorStack.push(new byte[]{1, 2, 3});
        processorStack.newFrame();
        processorStack.push(new byte[]{1, 2, 3, 4});

        for (byte b: processorStack.pop(7)){
            System.out.println(b + ", ");
        }
        System.out.println(processorStack.getCurrentFrameStartIndex());

    }

    private static void testArray(){
        TArray<Integer> a = new TArray<>(5);
        TArray<Integer> b = new TArray<>(1);

        for (int i = 0; i < 5; i++){
            a.add(i);
        }
        a.add(-1, 0);
        a.removeAt(2);
        b.add(10);
        b.add(-20);
        a.copyTo(b, 1);
        b.sort((o1, o2) -> o1 - o2);
        for (int i = 0; i < b.getSize(); i++){
            System.out.println(b.get(i));
        }
    }

    public static void main(String[] args) {
        testArray();
    }
}
