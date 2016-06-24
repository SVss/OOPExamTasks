package structures;

public class TBitArray {
    private int[] array;
    private final static int BITS_IN_ELEMENT = 8 * 4;

    private int containerIndex, bitNumber, container;

    private static int getContainerIndex(int bitIndex) {
        int fullBytes = bitIndex / BITS_IN_ELEMENT;
        return bitIndex % BITS_IN_ELEMENT == 0 ? fullBytes : fullBytes + 1;
    }

    private int getReservedSize() {
        return array != null ? array.length : 0;
    }

    private int getReservedElements() {
        return getReservedSize() * BITS_IN_ELEMENT;
    }

    private void accessElement(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        this.containerIndex = getContainerIndex(index);
        this.bitNumber = index % BITS_IN_ELEMENT;
        this.container = array[containerIndex];

        if (index >= getReservedElements()) {
            expand(containerIndex * 2);
        }
    }

    public void setElement(int index, boolean value) {
        accessElement(index);
        array[containerIndex] = value ? setBit(container, bitNumber) : unsetBit(container, bitNumber);
    }

    public boolean getElement(int index) {
        accessElement(index);
        return getBit(array[containerIndex], bitNumber);
    }

    private int setBit(int container, int index) {
        return container | (1 << index);
    }

    private int unsetBit(int container, int index) {
        return container & ~(1 << index);
    }

    private boolean getBit(int container, int index) {
        return (container & (1 << index)) != 0;
    }

    private void expand(int newSize) {
        int[] newArray = new int[newSize];
        if (array != null) {
            System.arraycopy(array, 0, newArray, 0, getReservedSize());
        }
        array = newArray;
    }
}
