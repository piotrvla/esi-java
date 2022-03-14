package g56212.atlg4.mentoring.dto;

import java.util.Objects;

public class StudentDto {
    private int matricule;
    private String firstName;
    private String lastName;
    public StudentDto(int matricule, String firstName, String lastName){
        this.matricule=matricule;
        this.firstName=firstName;
        this.lastName=lastName;
    }
    public int getMatricule(){
        return matricule;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public void setMatricule(int matricule){
        this.matricule=matricule;
    }
    public void setFirstName(String firstName){
        this.firstName=firstName;
    }
    public void setLastName(String lastName){
        this.lastName=lastName;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentDto that = (StudentDto) o;
        return matricule == that.matricule && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName);
    }
    @Override
    public int hashCode() {
        return Objects.hash(matricule, firstName, lastName);
    }

    @Override
    public String toString() {
        return  "matricule=" + matricule +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                "}\n";
    }
    
}
