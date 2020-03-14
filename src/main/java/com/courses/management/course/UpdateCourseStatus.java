package com.courses.management.course;

import com.courses.management.common.Command;
import com.courses.management.common.View;
import com.courses.management.common.commands.util.InputString;

import java.util.Optional;

public class UpdateCourseStatus implements Command {
    private View view;
    private CourseDAO courseDAO;

    public UpdateCourseStatus(View view) {
        this.view = view;
        this.courseDAO = new CourseDAOImpl();
    }

    @Override
    public String command() {
        return "update_course_status|title|status";
    }

    @Override
    public void process(InputString input) {
        input.validateParameters(command());
        String title = input.getParameters()[1];
        Course course = courseDAO.get(title);
        if (course == null || course.getTitle() == null) {
            throw new IllegalArgumentException(String.format("Course  with title %s not exists", title));
        }

        String status = input.getParameters()[2];
        course.setCourseStatus(getStatus(status));
        courseDAO.update(course);
        view.write("Course status updated");
    }

    private CourseStatus getStatus(String status) {
        Optional<CourseStatus> courseStatus = CourseStatus.getCourseStatus(status);
        return courseStatus.orElseThrow(() ->
                new IllegalArgumentException("Course status is wrong, choose the correct one"));
    }
}