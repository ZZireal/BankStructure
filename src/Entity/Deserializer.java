package Entity;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class Deserializer {

    public Deserializer() {
    }

    //десериализация листа счетов побайтово
    public List<Account> getDeserializeAccountList()  {
        try (ObjectInputStream objectInputStreamAccountList = new ObjectInputStream(new FileInputStream("C:\\Users\\zzire\\Desktop\\JavaLabs\\accountList.txt"))) {
            return (List<Account>) objectInputStreamAccountList.readObject();
        } catch (IOException | ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }

    //десериализация листа клиентов в виде строки
    public String getDeserializeClientList() {
        try (ObjectInputStream objectInputStreamClientList = new ObjectInputStream(new FileInputStream("C:\\Users\\zzire\\Desktop\\JavaLabs\\clientList.txt"))) {
            return objectInputStreamClientList.readUTF();
        } catch (IOException  ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
