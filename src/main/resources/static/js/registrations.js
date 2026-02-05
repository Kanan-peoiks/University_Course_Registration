const regApi = "http://localhost:8080/registrations";

const registrationForm = document.getElementById("registrationForm");
const regError = document.getElementById("regError");

const studentCoursesId = document.getElementById("studentCoursesId");
const courseStudentsId = document.getElementById("courseStudentsId");
const viewStudentCourses = document.getElementById("viewStudentCourses");
const viewCourseStudents = document.getElementById("viewCourseStudents");

const resultsTableBody = document.querySelector("#resultsTable tbody");

// Register student to course
registrationForm.addEventListener("submit", async e => {
    e.preventDefault();
    regError.textContent = "";

    const studentId = parseInt(document.getElementById("studentId").value);
    const courseId = parseInt(document.getElementById("courseId").value);

    try {
        const res = await fetch(`${regApi}?studentId=${studentId}&courseId=${courseId}`, {
            method: "POST"
        });
        if (!res.ok) throw await res.json();
        alert("Student registered successfully!");
        registrationForm.reset();
    } catch (err) {
        regError.textContent = err.message || JSON.stringify(err);
    }
});

// View courses by student
viewStudentCourses.addEventListener("click", async () => {
    const id = studentCoursesId.value.trim();
    if (!id) return alert("Enter student ID");
    const res = await fetch(`${regApi}/student/${id}/courses`);
    const courses = await res.json();
    renderResults(courses, "course");
});

// View students by course
viewCourseStudents.addEventListener("click", async () => {
    const id = courseStudentsId.value.trim();
    if (!id) return alert("Enter course ID");
    const res = await fetch(`${regApi}/course/${id}/students`);
    const students = await res.json();
    renderResults(students, "student");
});

// Render table
function renderResults(items, type) {
    resultsTableBody.innerHTML = "";
    items.forEach(item => {
        const row = document.createElement("tr");
        let additional = type === "course" ? `Teacher: ${item.teacherName || "N/A"}`
            : `Email: ${item.email}`;
        row.innerHTML = `
            <td>${item.id}</td>
            <td>${type === "course" ? item.courseName : item.name}</td>
            <td>${additional}</td>
            <td>
                <button onclick="deleteEntry(${item.id}, '${type}')">Delete</button>
            </td>
        `;
        resultsTableBody.appendChild(row);
    });
}

// Delete registration
async function deleteEntry(id, type) {
    let studentId, courseId;
    if (type === "student") {
        courseId = prompt("Enter course ID to remove student from:");
        studentId = id;
    } else if (type === "course") {
        studentId = prompt("Enter student ID to remove from course:");
        courseId = id;
    }

    if (!studentId || !courseId) return alert("IDs required");

    await fetch(`${regApi}/registration/student?studentId=${studentId}&courseId=${courseId}`, {
        method: "DELETE"
    });
    alert("Deleted successfully!");
    resultsTableBody.innerHTML = "";
}
