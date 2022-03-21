package g12345.myrepository.repository;

import g56212.atlg4.mentoring.dto.StudentDto;
import g56212.atlg4.mentoring.repository.RepositoryException;
import g56212.atlg4.mentoring.repository.StudentDao;
import g56212.atlg4.mentoring.repository.StudentRepository;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author jlc
 */
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class StudentRepositoryTest {

    @Mock
    private StudentDao mock;

    private final StudentDto bob;

    private final StudentDto patrick;

    private static final int KEY = 12_345;

    private final List<StudentDto> all;

    public StudentRepositoryTest() {
        System.out.println("StudentRepositoryTest Constructor");
        //Test data
        bob = new StudentDto(KEY, "SquarePants", "SpongeBob");
        patrick = new StudentDto(99_999, "Star", "Patrick");

        all = new ArrayList<>();
        all.add(bob);
        all.add(patrick);
    }




}
