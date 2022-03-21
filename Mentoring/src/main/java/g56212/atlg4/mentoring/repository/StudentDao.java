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

public class StudentDao implements Dao<StudentDto, Integer> {

    private final Path file = Path.of(ConfigManager.getInstance().getProperties("file.url"));

    public StudentDao() {

    }

    private static class StudentDaoHolder {

        private static final StudentDao INSTANCE = new StudentDao();
    }

    public static StudentDao getInstance() {
        return StudentDaoHolder.INSTANCE;
    }

    @Override
    public void insert(StudentDto item) throws RepositoryException {
        try {
            String student = "";
            List<String> lines = new ArrayList<>();
            student += item.geyKey() + "," + item.getLastName() + "," + item.getFirstName();
            lines.add(student);
            Files.write(file, lines, StandardOpenOption.APPEND);
        } catch (IOException ex) {
            throw new RepositoryException(ex);
        }
    }

    @Override
    public void delete(Integer key) throws RepositoryException {
        if (key == null) {
            throw new IllegalArgumentException("Aucune clé en param?tre " + key);
        }
        try {
            List<String> out = Files.lines(file)
                    .filter(line -> !line.split(",")[0]
                    .contains(Integer.toString(key)))
                    .collect(Collectors.toList());
            if (out.size() == Files.lines(file).count()) {
                throw new RepositoryException("Absent du fichier " + key);
            }
            Files.write(file, out,
                    StandardOpenOption.WRITE,
                    StandardOpenOption.TRUNCATE_EXISTING);

        } catch (IOException ex) {
            throw new RepositoryException(ex);

        }

    }

    @Override
    public void update(StudentDto item) throws RepositoryException {
        try {
            delete(item.getKey());
            insert(item);
        } catch (RepositoryException ex) {
            throw new RepositoryException(ex);

        }

    }

    @Override
    public StudentDto get(Integer key) throws RepositoryException {
        try {
            StudentDto[] studentDto = new StudentDto[1];
            Files.lines(file).forEach(s -> {
                String[] student = s.split(",");
                if (Integer.parseInt(student[0]) == key) {
                    studentDto[0] = new StudentDto(Integer.parseInt(student[0]),
                            student[1], student[2]);
                }
            });

            return studentDto[0];
        } catch (IOException ex) {
            throw new RepositoryException(ex);

        }
    }

    @Override
    public List<StudentDto> getAll() throws RepositoryException {
        try {
            List<StudentDto> students = new ArrayList<>();
            Files.lines(file).forEach(s -> {
                String[] student = s.split(",");
                students.add(new StudentDto(Integer.parseInt(student[0]),
                        student[1], student[2]));
            });
            return students;

        } catch (IOException ex) {
            throw new RepositoryException(ex);

        }
    }

}
