package g12345.myrepository.file;

import g56212.atlg4.mentoring.dto.StudentDto;
import g56212.atlg4.mentoring.repository.RepositoryException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

/**
 *
 * @author jlc
 */
public class StudentDaoTest {

    private final StudentDto bob;
    private final StudentDto patrick;

    private static final int KEY = 12_345;
    private static final String FILE_URL = "data/test_repo_students.txt";

    private final String url;

    private final List<StudentDto> all;

    public StudentDaoTest() {
        System.out.println("==== StudentDaoTest Constructor =====");
        bob = new StudentDto(KEY, "SquarePants", "SpongeBob");
        patrick = new StudentDto(99_999, "Star", "Patrick");

        all = new ArrayList<>();
        all.add(new StudentDto(64_931, "Olsen", "Maggy"));
        all.add(new StudentDto(73_780, "Frost", "Phoebe"));
        all.add(new StudentDto(94_853, "Ortega", "Skyler"));
        all.add(new StudentDto(93_371, "Blankenship", "Byron"));
        all.add(new StudentDto(82_227, "Cote", "Molly"));
        all.add(bob);

        url = getClass().getClassLoader()
                .getResource(FILE_URL)
                .getFile();
    }



}
