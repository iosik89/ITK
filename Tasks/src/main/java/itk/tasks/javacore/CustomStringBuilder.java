package itk.tasks.javacore;

import java.util.ArrayDeque;
import java.util.Deque;

public class CustomStringBuilder {

    private final StringBuilder stringBuilder = new StringBuilder();
    private final Deque<Snapshoot> snapshotsHistory = new ArrayDeque<>();

    public void undo(){
        if(!snapshotsHistory.isEmpty()){
            stringBuilder.setLength(0);
            stringBuilder.append(snapshotsHistory.pop().getSnapshootSB());
        }
    }

    private void saveSnapshot(){
        snapshotsHistory.push(new Snapshoot(stringBuilder));
    }

    public CustomStringBuilder append(String text) {
        saveSnapshot();
        stringBuilder.append(text);
        return this;
    }

    private final class Snapshoot {

        private final String snapshootSB;

        Snapshoot(final StringBuilder stringBuilder){
            snapshootSB = stringBuilder.toString();
        }

        String getSnapshootSB() {
            return snapshootSB;
        }

    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }

}

