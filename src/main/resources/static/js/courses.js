const coursesApi = "http://localhost:8080/courses";

const coursesTableBody = document.querySelector("#coursesTable tbody");
const courseForm = document.getElementById("courseForm");
const formError = document.getElementById("formError");

const searchName = document.getElementById("searchName");
const searchBtn = document.getElementById("searchBtn");
const filterTeacherId = document.getElementById("filterTeacherId");
const filterTeacherBtn = document.getElementById("filterTeacherBtn");

// Load all courses
async function loadCourses() {
    const res = await fetch(coursesApi);
    const courses = await res.json();
    renderCourses(courses);
}

// Render courses table
function renderCourses(courses) {
    coursesTableBody.innerHTML = "";
    courses.forEach(course => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${course.id}</td>
            <td>${course.courseName}</td>
            <td>${course.courseDescription}</td>
            <td>${course.credits}</td>
            <td>${course.teacherName || "N/A"}</td>
            <td>
                <button onclick="editCourse(${course.id})">Edit</button>
                <button onclick="deleteCourse(${course.id})">Delete</button>
            </td>
        `;
        coursesTableBody.appendChild(row);
    });
}

// Submit form (create/update)
courseForm.addEventListener("submit", async e => {
    e.preventDefault();
    formError.textContent = "";

    const id = document.getElementById("courseId").value;
    const data = {
        courseName: document.getElementById("courseName").value,
        courseDescription: document.getElementById("courseDescription").value,
        credits: parseInt(document.getElementById("credits").value),
        teacherId: parseInt(document.getElementById("teacherId").value)
    };

    try {
        let res;
        if (id) {
            res = await fetch(`${coursesApi}/${id}`, {
                method: "PUT",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(data)
            });
        } else {
            res = await fetch(coursesApi, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(data)
            });
        }

        if (!res.ok) throw await res.json();
        await loadCourses();
        courseForm.reset();
        document.getElementById("courseId").value = "";
    } catch (err) {
        formError.textContent = err.message || JSON.stringify(err);
    }
});

// Edit course
async function editCourse(id) {
    const res = await fetch(`${coursesApi}/${id}`);
    const course = await res.json();
    document.getElementById("courseId").value = course.id;
    document.getElementById("courseName").value = course.courseName;
    document.getElementById("courseDescription").value = course.courseDescription;
    document.getElementById("credits").value = course.credits;
    document.getElementById("teacherId").value = course.teacherId || "";
}

// Delete course
async function deleteCourse(id) {
    if (confirm("Are you sure?")) {
        await fetch(`${coursesApi}/${id}`, { method: "DELETE" });
        await loadCourses();
    }
}

// Search by name
searchBtn.addEventListener("click", async () => {
    const name = searchName.value.trim();
    if (!name) return alert("Enter a course name to search");
    const res = await fetch(`${coursesApi}/search?courseName=${name}`);
    const courses = await res.json();
    renderCourses(courses);
});

// Filter by teacher
filterTeacherBtn.addEventListener("click", async () => {
    const id = filterTeacherId.value.trim();
    if (!id) return alert("Enter a teacher ID to filter");
    const res = await fetch(`${coursesApi}/teacher/${id}`);
    const courses = await res.json();
    renderCourses(courses);
});

// Initial load
loadCourses();
