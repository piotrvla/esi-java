package g56212.atlg4.mentoring.dto;


public class StudentDto extends Dto<Integer>{
    
    private String firstName;
    private String lastName;
    public StudentDto(int matricule, String firstName, String lastName){
        super(matricule);
        this.firstName=firstName;
        this.lastName=lastName;
    }
    public Integer getKey(){
        return super.geyKey();
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public void setMatricule(int matricule){
    }
    public void setFirstName(String firstName){
        this.firstName=firstName;
    }
    public void setLastName(String lastName){
        this.lastName=lastName;
    }


    @Override
    public String toString() {
        return  "matricule=" + key +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                "}\n";
    }
    
}
