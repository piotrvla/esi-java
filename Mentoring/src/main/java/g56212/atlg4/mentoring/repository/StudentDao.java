
package g56212.atlg4.mentoring.repository;

import g56212.atlg4.mentoring.config.ConfigManager;
import g56212.atlg4.mentoring.dto.StudentDto;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class StudentDao implements Dao<StudentDto> {
    private final Path file = Path.of(ConfigManager.getInstance().getProperties("file.url"));    
    public StudentDao(){

    }
        private static class StudentDaoHolder {
            private static final StudentDao INSTANCE = new StudentDao();
    }

    public static StudentDao getInstance() {
        return StudentDaoHolder.INSTANCE;
    }

    @Override
    public void insert(StudentDto item) throws IOException {
        String student = "";
        List<String> lines = new ArrayList<>();
        student += item.getMatricule() + "," + item.getLastName() + "," + item.getFirstName();
        lines.add(student);
        Files.write(file, lines, StandardOpenOption.APPEND);
    }

    @Override
    public void delete(StudentDto item) throws IOException {
    if (item == null) {
            throw new IllegalArgumentException("Aucune clé en param?tre " + item);
        }
        List<String> out = Files.lines(file)
                .filter(line -> !line.split(",")[0]
                        .contains(Integer.toString(item.getMatricule())))
                .collect(Collectors.toList());
        if (out.size() == Files.lines(file).count()) {
            throw new IOException("Absent du fichier " + item.getMatricule());
        }
        Files.write(file, out,
                StandardOpenOption.WRITE,
                StandardOpenOption.TRUNCATE_EXISTING);
    }

    @Override
    public void update(StudentDto item) throws IOException {
        delete(item);
        insert(item);
    }

    @Override
    public StudentDto get(StudentDto item) throws IOException {
        StudentDto[] studentDto = new StudentDto[1];
        Files.lines(file).forEach(s-> {
            String[] student = s.split(",");
            if(Integer.parseInt(student[0])==item.getMatricule())
                studentDto[0] = new StudentDto(Integer.parseInt(student[0]),
                        student[1], student[2]);
        });
        return studentDto[0];
    }

    @Override
    public List<StudentDto> getAll() throws IOException {
        List<StudentDto> students = new ArrayList<>();
        Files.lines(file).forEach(s->{
            String[] student = s.split(",");
            students.add(new StudentDto(Integer.parseInt(student[0]),
                    student[1],student[2]));
        });
        return students;
    }

}