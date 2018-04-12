import ByteLib.ByteArray;
import GHash.MyHash;
import Institution.Student;
import sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        //CallHashsBench();
        CallHash();
    }


    private static void CallHash() throws IOException {
        MyHash hash = new MyHash(100);

        Student student1 = new Student("20132BSI0044","Gustavo",22,"Negro");
        Student student2 = new Student("20132BSI0099","Gustavo66",22,"Negro");

        hash.insertItem(student1.getNome(), student1);
        hash.insertItem(student2.getNome(),student2);

        hash.removeElement(student1.getNome());

        Student student = (Student) hash.findElements(student1.getNome());


        if(student != null)
        {
            System.out.println(student.getNome()+","+student.getMatricula());
        }else{
            System.out.print("ta tirando irmao");
        }
    }
}