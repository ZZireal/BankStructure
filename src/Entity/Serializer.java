package Entity;

import java.io.*;
import java.util.List;

public class Serializer {

    public Serializer() {
    }

    //сериализация листа счетов побайтово
    public void serializeAccountList(List<Account> accountList) {
        try(ObjectOutputStream objectOutputStreamAccountList = new ObjectOutputStream(new FileOutputStream("C:\\Users\\zzire\\Desktop\\JavaLabs\\accountList.txt"))) {
            objectOutputStreamAccountList.writeObject(accountList);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //сериализация листа клиентов в виде строки
    public void serializeClientList(List<Client> clientList) {
        try(ObjectOutputStream objectOutputStreamClientList = new ObjectOutputStream(new FileOutputStream("C:\\Users\\zzire\\Desktop\\JavaLabs\\clientList.txt"))) {
            objectOutputStreamClientList.writeUTF(clientList.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
