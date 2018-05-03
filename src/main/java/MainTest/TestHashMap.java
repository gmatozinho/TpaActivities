package MainTest;

import HashLib.Core.MyHashListChain;
import HashLib.Core.MyHashOpenAddress;
import Institution.Student;

public class TestHashMap {
    public static void main(String[] args) {
        TestHash();
    }

    private static void TestHash() {
        //TODO HASHENGINE GENERATOR
        MyHashListChain<String, Student> hash = new MyHashListChain<>(100);
        MyHashOpenAddress<String, Student> hash2 = new MyHashOpenAddress<>();

        Student student1 = new Student("20132BSI0044","Gustavo",22,"Negro");
        Student student2 = new Student("20132BSI0099","Gustavo66",22,"Negro");
        Student student3 = new Student("20132BSI0095","Gustavo67",22,"Negro");

        hash2.insertItem(student1.getNome(), student1);
        hash2.insertItem(student2.getNome(),student2);
        hash2.insertItem(student3.getNome(),student3);

        Student student = hash2.findElements(student3.getNome());

        if(student != null)
        {
            System.out.println(student.getNome()+","+student.getMatricula());
        }else{
            System.out.print("ta tirando irmao");
        }
    }
}
