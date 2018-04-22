package Institution;

import java.io.Serializable;

public class Student implements Serializable {
    private String matricula;
    private String nome;
    private int idade;
    private String etnia;

    public Student(String matricula, String nome, int idade, String etnia) {
        this.matricula = matricula;
        this.nome = nome;
        this.idade = idade;
        this.etnia = etnia;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getEtnia() {
        return etnia;
    }

    public String getMatricula() {
        return matricula;
    }

    //TODO THisTESTFunction
    /*public static ArrayList<Student> GenerateStudents(int qtd){
        ArrayList<Student> students = new ArrayList<>();

        for(int i=0;i<qtd;i++){
        }

        return students;
    }*/
}


