package seedu.address.model.student;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Student in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    private final StudentNumber studentNumber;
    private final ClassNumber classNumber;

    // Data fields

    private final Set<Tag> tags = new HashSet<>();


    /**
     * Every field must be present and not null.
     */
    public Student(StudentNumber studentNumber) {
        requireAllNonNull(studentNumber);
        this.name = null;
        this.studentNumber = studentNumber;
        this.phone = null;
        this.email = null;
        this.classNumber = null;
    }

    /**
     * Every field must be present and not null.
     */
    public Student(Name name, Phone phone, Email email, StudentNumber studentNumber,
                   ClassNumber classNumber , Set<Tag> tags) {
        requireAllNonNull(name, phone, email, studentNumber, classNumber, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.studentNumber = studentNumber;
        this.classNumber = classNumber;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public StudentNumber getStudentNumber() {
        return studentNumber;
    }

    public ClassNumber getClassNumber() {
        return classNumber;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same student number.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && otherStudent.getStudentNumber().equals(getStudentNumber());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Student)) {
            return false;
        }

        Student otherStudent = (Student) other;

        return studentNumber.equals(otherStudent.studentNumber);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, studentNumber, classNumber, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("student number", studentNumber)
                .add("class number", classNumber)
                .add("tags", tags)
                .toString();
    }

}