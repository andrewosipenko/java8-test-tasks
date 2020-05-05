package com.expertsoft;

import com.expertsoft.model.Student;
import com.expertsoft.model.Subject;
import com.expertsoft.model.SubjectMark;
import com.expertsoft.model.Teacher;
import org.assertj.core.util.Sets;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.*;

public class UniversityAnalyzerTest {
    private final static UniversityAnalyzer universityAnalyzer = new UniversityAnalyzer();

    private List<Student> students;

    @Test
    public void getMinSubjectMark() {
        int minSubjectMark = universityAnalyzer.getMinSubjectMark(students.stream(), 1);

        Assert.assertEquals(7, minSubjectMark);
    }

    @Test
    public void getAverageTeacherMark() {
        double averageTeacherMark =universityAnalyzer.getAverageTeacherMark(students.stream(), 2);

        Assert.assertEquals(8.4, averageTeacherMark, 0.001);
    }

    @Test
    public void getAverageStudentAgeInYears() {
        double averageStudentsAge = universityAnalyzer.getAverageStudentAgeInYears(students.stream());

        Assert.assertEquals(21.25, averageStudentsAge, 0.001);
    }

    @Test
    public void getStudentWithHighestAverageMark() {
        Student student = universityAnalyzer.getStudentWithHighestAverageMark(students.stream());

        Assert.assertEquals(2, student.getId());
    }

    @Test
    public void sortStudentsByCountOfMarks() {
        List<Student> sortedStudents = universityAnalyzer.sortStudentsByCountOfMarks(students.stream());

        Assert.assertEquals(2, sortedStudents.get(0).getId());
        Assert.assertEquals(3, sortedStudents.get(1).getId());
        Assert.assertEquals(1, sortedStudents.get(2).getId());
        Assert.assertEquals(4, sortedStudents.get(3).getId());
    }

    @Before
    public void setUp() {
        students = new ArrayList<>();
        int currentYear = LocalDate.now().getYear();
        Student studentVlad = new Student(1, "Vlad", "Waltanov", new HashSet<>(), LocalDate.of(currentYear - 22, 1, 1));
        Student studentIgor = new Student(2, "Igor", "Yaremchuk", new HashSet<>(), LocalDate.of(currentYear - 21, 1, 1));
        Student studentAlex = new Student(3, "Alex", "Potapov", new HashSet<>(), LocalDate.of(currentYear - 19, 1, 1));
        Student studentDima = new Student(4, "Dima", "Kiselev", new HashSet<>(), LocalDate.of(currentYear - 23, 1, 1));

        Subject mathematics = new Subject(1, "mathematics");
        Subject chemistry = new Subject(2, "chemistry");
        Subject physics = new Subject(3, "physics");
        Subject computerScience = new Subject(4, "computer science");
        Subject literature = new Subject(5, "literature");

        Teacher teacherAndrei = new Teacher(1, "Andrei", "Ivanov", Sets.newLinkedHashSet(mathematics, chemistry, physics));
        Teacher teacherMax = new Teacher(2, "Max", "Zhilin", Sets.newLinkedHashSet(computerScience, literature, physics));
        Teacher teacherDima = new Teacher(3, "Dima", "Petrov", Sets.newLinkedHashSet(mathematics, physics, literature));

        SubjectMark markVladMathematics = new SubjectMark(studentVlad.getId(), mathematics.getId(), teacherAndrei.getId(), 9);
        SubjectMark markVladPhysics = new SubjectMark(studentVlad.getId(), physics.getId(), teacherAndrei.getId(), 6);
        SubjectMark markVladComputerScience = new SubjectMark(studentVlad.getId(), computerScience.getId(), teacherMax.getId(), 9);
        SubjectMark markVladLiterature = new SubjectMark(studentVlad.getId(), literature.getId(), teacherDima.getId(), 4);

        SubjectMark markIgorMathematics = new SubjectMark(studentIgor.getId(), mathematics.getId(), teacherDima.getId(), 8);
        SubjectMark markIgorPhysics = new SubjectMark(studentIgor.getId(), physics.getId(), teacherMax.getId(), 9);
        SubjectMark markIgorComputerScience = new SubjectMark(studentIgor.getId(), computerScience.getId(), teacherMax.getId(), 10);
        SubjectMark markIgorChemistry = new SubjectMark(studentIgor.getId(), chemistry.getId(), teacherAndrei.getId(), 5);
        SubjectMark markIgorLiterature = new SubjectMark(studentIgor.getId(), literature.getId(), teacherDima.getId(), 10);

        SubjectMark markAlexMathematics = new SubjectMark(studentAlex.getId(), mathematics.getId(), teacherDima.getId(), 7);
        SubjectMark markAlexLiterature = new SubjectMark(studentAlex.getId(), literature.getId(), teacherDima.getId(), 6);
        SubjectMark markAlexComputerScience = new SubjectMark(studentAlex.getId(), computerScience.getId(), teacherMax.getId(), 9);
        SubjectMark markAlexChemistry = new SubjectMark(studentAlex.getId(), chemistry.getId(), teacherAndrei.getId(), 9);

        SubjectMark markDimaPhysics = new SubjectMark(studentDima.getId(), physics.getId(), teacherAndrei.getId(), 6);
        SubjectMark markDimaComputerScience = new SubjectMark(studentDima.getId(), computerScience.getId(), teacherMax.getId(), 5);
        SubjectMark markDimaChemistry = new SubjectMark(studentDima.getId(), chemistry.getId(), teacherAndrei.getId(), 9);

        studentVlad.getSubjectMarks().addAll(Arrays.asList(markVladPhysics,
                markVladComputerScience,
                markVladLiterature,
                markVladMathematics));

        studentIgor.getSubjectMarks().addAll(Arrays.asList(markIgorChemistry,
                markIgorComputerScience,
                markIgorMathematics,
                markIgorPhysics,
                markIgorLiterature));

        studentAlex.getSubjectMarks().addAll(Arrays.asList(markAlexChemistry,
                markAlexComputerScience,
                markAlexLiterature,
                markAlexMathematics));

        studentDima.getSubjectMarks().addAll(Arrays.asList(markDimaChemistry,
                markDimaComputerScience,
                markDimaPhysics));

        students.addAll(Arrays.asList(studentVlad, studentIgor, studentAlex, studentDima));

    }
}