package structures;

public class TBitArray {
    private int[] array;
    private final static int BITS_IN_ELEMENT = 8 * 4;

    private class TBitLocation {
        private int containerIndex;
        private int bitIndex;

        TBitLocation(int containerIndex, int bitIndex) {
            this.containerIndex = containerIndex;
            this.bitIndex = bitIndex;
        }
    }

    private enum TAccessType{
        AT_READ,
        AT_WRITE
    }


    private static int getContainerIndex(int bitIndex) {
        int fullBytes = bitIndex / BITS_IN_ELEMENT;
        if (fullBytes == 0){
            return fullBytes;
        }
        
        return bitIndex % BITS_IN_ELEMENT == 0 ? fullBytes : fullBytes + 1;
    }

    private int getReservedSize() {
        return array != null ? array.length : 0;
    }

    private int getReservedElements() {
        return getReservedSize() * BITS_IN_ELEMENT;
    }

    private TBitLocation readElement(int index){
        return accessElement(index, TAccessType.AT_READ);
    }

    private TBitLocation writeElement(int index){
        return accessElement(index, TAccessType.AT_WRITE);
    }

    private TBitLocation accessElement(int index, TAccessType accessType) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        TBitLocation bitLocation = new TBitLocation(getContainerIndex(index), index % BITS_IN_ELEMENT);

        if (index >= getReservedElements()) {
            if (accessType == TAccessType.AT_READ){
                return null;
            }
            expand((bitLocation.containerIndex + 1)* 2);
        }

        return bitLocation;
    }

    public void setElement(int index, boolean value) {
        TBitLocation bitLocation = writeElement(index);
        if (value){
            array[bitLocation.containerIndex] = setBit(array[bitLocation.containerIndex], bitLocation.bitIndex);
        } else {
            array[bitLocation.containerIndex] = unsetBit(array[bitLocation.containerIndex], bitLocation.bitIndex);
        }
    }

    public boolean getElement(int index) {
        TBitLocation bitLocation = readElement(index);
        if (bitLocation != null){
            return getBit(array[bitLocation.containerIndex], bitLocation.bitIndex);
        } else {
            return false;
        }
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
