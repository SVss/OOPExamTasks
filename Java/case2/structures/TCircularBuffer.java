package structures;

import java.nio.BufferOverflowException;

public class TCircularBuffer {
    private byte[] buffer;
    private int readPosition, writePosition;
    private int unreadDataSize;

    public TCircularBuffer(int size) {
        readPosition = 0;
        writePosition = 0;
        buffer = new byte[size];
        unreadDataSize = 0;
    }

    public int getBufferSize() {
        return buffer.length;
    }

    public int getReadPosition() {
        return readPosition;
    }

    private void setReadPosition(int readPosition) {
        this.readPosition = readPosition;
    }

    private void setWritePosition(int writePosition) {
        this.writePosition = writePosition;
    }

    private void moveReadPosition() {
        setReadPosition((++readPosition) % getBufferSize());
    }

    private void moveWritePosition() {
        setWritePosition((++writePosition) % getBufferSize());
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
        buffer = newBuffer;
        setReadPosition(0);
        setWritePosition(unreadDataSize);
        setUnreadDataSize(unreadDataSize);

    }

    public int getUnreadDataSize() {
        return unreadDataSize;
    }

    private void setUnreadDataSize(int unreadDataSize) {
        this.unreadDataSize = unreadDataSize;
    }

    public boolean isEmpty(){
        return getUnreadDataSize() == 0;
    }

    public void write(byte b) {
        if (unreadDataSize < getBufferSize()) {
            buffer[writePosition] = b;
            unreadDataSize++;
            moveWritePosition();
        } else {
            throw new BufferOverflowException();
        }
    }

    public byte read() {
        if (unreadDataSize > 0) {
            byte result = buffer[readPosition];
            unreadDataSize--;
            moveReadPosition();
            return result;
        } else {
            throw new EmptyBufferException();
        }
    }

}
