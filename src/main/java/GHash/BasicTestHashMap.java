package GHash;

import Institution.Student;

import java.io.IOException;

public class BasicTestHashMap {
    public static void main(String[] args) throws IOException {
        CallHash();
    }

    private static void CallHash() throws IOException {
        //TODO ENGINE GENERATOR
        MyHashListChain<String, Student> hash = new MyHashListChain<>(100);
        MyHashOpenAddress<String, Student> hash2 = new MyHashOpenAddress<>();

        Student student1 = new Student("20132BSI0044","Gustavo",22,"Negro");
        Student student2 = new Student("20132BSI0099","Gustavo66",22,"Negro");

        hash2.insertItem(student1.getNome(), student1);
        hash2.insertItem(student2.getNome(),student2);

        hash2.removeElement(student1.getNome());

        Student student = hash2.findElements(student2.getNome());

        if(student != null)
        {
            System.out.println(student.getNome()+","+student.getMatricula());
        }else{
            System.out.print("ta tirando irmao");
        }
    }
}
