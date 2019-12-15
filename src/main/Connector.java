package main;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;

import Framework.Launcher;

public class Connector {
    private static Socket socket = null;
    private static InputStream inputStream = null;
    private static OutputStream outputStream = null;

    public static void initialize() throws IOException {
//    	socket = new Socket("localhost", 10000);
    	socket = new Socket("15.164.103.147", 10000);
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
    }

    public static Object invoke(Launcher launcher) throws InvocationTargetException {
        try {
            ObjectOutputStream output = new ObjectOutputStream(outputStream);
            output.writeObject(launcher);
            output.flush();
            ObjectInputStream input = new ObjectInputStream(inputStream);
            Object object = input.readObject();
            if (object instanceof InvocationTargetException) throw ((InvocationTargetException) object);
            return object;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("please Connector.initialize()");
        }
        return null;
    }

    public static void disconnect(){
        try {
            ObjectOutputStream output = new ObjectOutputStream(outputStream);
            output.writeObject("exit");
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
