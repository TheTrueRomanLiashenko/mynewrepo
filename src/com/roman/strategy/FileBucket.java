package com.roman.strategy;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {
    private Path path;

    public FileBucket() throws IOException {
        try {
            path = Files.createTempFile(null, null);
            Files.deleteIfExists(path);
            Files.createFile(path);
            path.toFile().deleteOnExit();
        } catch (IOException e) {
            ExceptionHandler.log(e);
        }
    }

    public long getFileSize() {
        try {
            return Files.size(path);
        } catch (IOException e) {
            ExceptionHandler.log(e);
        }return 0L;
    }

    public void putEntry(Entry entry) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path));
        oos.writeObject(entry);
        oos.writeObject(entry.next);
    }

    public Entry getEntry() throws IOException, ClassNotFoundException {
        if (getFileSize() == 0)
            return null;
        else {
            Entry e;
            Entry next;
            ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path));
            e = (Entry) ois.readObject();
            next = (Entry) ois.readObject();
            e.next = next;
            return e;
        }
    }

    public void remove() throws IOException {
        Files.delete(path);
    }
}
