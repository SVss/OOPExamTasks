package structures;

public class TProcessorStack extends TStack<Byte> {
    private TStack<Integer> frames = new TStack<>();

    public TProcessorStack(){
        frames.push(0);
    }

    public void push(byte[] bytes) {
        for (byte b : bytes) {
            push(b);
        }
    }

    public byte[] pop(int count){
        byte[] result = new byte[count];
        for (int i = 0; i < count; i++){
            result[i] = pop();
        }

        while (getIndex() < frames.getTop() && frames.getTop() != 0){
            frames.pop();
        }

        return result;
    }

    public void newFrame(){
        frames.push(getSize());
    }

    public int getIndex(){
        return getSize() - 1;
    }

    public int getCurrentFrameStartIndex(){
        return frames.getTop();
    }

    public byte[] popFrame(){
        int lastFrame;
        if (frames.getTop() == 0){
            lastFrame = 0;
        } else {
            lastFrame = frames.pop();
        }
        int frameSize = getIndex() - lastFrame + 1;
        byte[] result = new byte[frameSize];

        for (int i = 0; i < frameSize; i++){
            result[i] = pop();
        }

        return result;
    }
}
