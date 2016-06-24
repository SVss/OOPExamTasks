package structures;

import java.nio.BufferOverflowException;

public class TCircularBuffer {
    private byte[] buffer;
    private int readPosition, writePosition;
    private int unreadDataSize;

    public TCircularBuffer(int size) {
        readPosition = 0;
        buffer = new byte[size];
        unreadDataSize = 0;
    }

    public int getBufferSize() {
        return buffer.length;
    }

    public int getReadPosition() {
        return readPosition;
    }

    private void incReadPosition() {
        readPosition = (++readPosition) % getBufferSize();
    }

    private void incWritePosition() {
        writePosition = (++writePosition) % getBufferSize();
    }

    public int getWritePosition() {
        return writePosition;
    }

    public void increaseSize(int increment) {
        byte[] newBuffer = new byte[getBufferSize() + increment];
        int unreadDataSize = getUnreadDataSize();
        for (int i = 0; i < unreadDataSize; i++) {
            newBuffer[i] = read();
        }
        readPosition = 0;
        writePosition = unreadDataSize;
        this.unreadDataSize = unreadDataSize;

        buffer = newBuffer;
    }

    public int getUnreadDataSize() {
        return unreadDataSize;
    }

    public boolean isEmpty(){
        return getUnreadDataSize() == 0;
    }

    public void write(byte b) {
        if (unreadDataSize < getBufferSize()) {
            buffer[writePosition] = b;
            unreadDataSize++;
            incWritePosition();
        } else {
            throw new BufferOverflowException();
        }
    }

    public byte read() {
        if (unreadDataSize > 0) {
            byte result = buffer[readPosition];
            unreadDataSize--;
            incReadPosition();
            return result;
        } else {
            throw new EmptyBufferException();
        }
    }

}
