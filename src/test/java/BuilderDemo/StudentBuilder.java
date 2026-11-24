package BuilderDemo;

import java.util.List;

public class StudentBuilder {

    private Student student;

    public StudentBuilder() {
        this.student = new Student();
    }

    public StudentBuilder setName(String name) {
        student.name = name;
        return this;
    }

    public StudentBuilder setSecondName(String secondName) {
        student.secondName = secondName;
        return this;
    }

    public StudentBuilder setStudyGroup(String studyGroup) {
        student.studyGroup = studyGroup;
        return this;
    }

    public StudentBuilder setStreamNumber(Integer streamNumber) {
        student.streamNumber = streamNumber;
        return this;
    }

    public StudentBuilder setMarksList(List<Integer> marksList) {
        student.marksList = marksList;
        return this;
    }

    public StudentBuilder setPassedBlocks(List<String> passedBlocks) {
        student.passedBlocks = passedBlocks;
        return this;
    }

    public Student builder() {
        return student;
    }

}
