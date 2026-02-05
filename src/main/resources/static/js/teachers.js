const apiUrl = "http://localhost:8080/teachers";

const teachersTableBody = document.querySelector("#teachersTable tbody");
const teacherForm = document.getElementById("teacherForm");
const formError = document.getElementById("formError");

const filterDepartment = document.getElementById("filterDepartment");
const filterBtn = document.getElementById("filterBtn");

// Load all teachers
async function loadTeachers() {
    const res = await fetch(apiUrl);
    const teachers = await res.json();
    renderTeachers(teachers);
}

// Render teachers table
function renderTeachers(teachers) {
    teachersTableBody.innerHTML = "";
    teachers.forEach(teacher => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${teacher.id}</td>
            <td>${teacher.name}</td>
            <td>${teacher.surname}</td>
            <td>${teacher.department}</td>
            <td>${teacher.courses.map(c => c.courseName).join(", ")}</td>
            <td>
                <button onclick="editTeacher(${teacher.id})">Edit</button>
                <button onclick="deleteTeacher(${teacher.id})">Delete</button>
            </td>
        `;
        teachersTableBody.appendChild(row);
    });
}

// Submit form (create/update)
teacherForm.addEventListener("submit", async e => {
    e.preventDefault();
    formError.textContent = "";

    const id = document.getElementById("teacherId").value;
    const data = {
        name: document.getElementById("name").value,
        surname: document.getElementById("surname").value,
        department: document.getElementById("department").value
    };

    try {
        let res;
        if (id) {
            res = await fetch(`${apiUrl}/${id}`, {
                method: "PUT",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(data)
            });
        } else {
            res = await fetch(apiUrl, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(data)
            });
        }

        if (!res.ok) throw await res.json();
        await loadTeachers();
        teacherForm.reset();
        document.getElementById("teacherId").value = "";
    } catch (err) {
        formError.textContent = err.message || JSON.stringify(err);
    }
});

// Edit teacher
async function editTeacher(id) {
    const res = await fetch(`${apiUrl}/${id}`);
    const teacher = await res.json();
    document.getElementById("teacherId").value = teacher.id;
    document.getElementById("name").value = teacher.name;
    document.getElementById("surname").value = teacher.surname;
    document.getElementById("department").value = teacher.department;
}

// Delete teacher
async function deleteTeacher(id) {
    if (confirm("Are you sure?")) {
        await fetch(`${apiUrl}/${id}`, { method: "DELETE" });
        await loadTeachers();
    }
}

// Filter by department
filterBtn.addEventListener("click", async () => {
    const department = filterDepartment.value;
    if (!department) return alert("Enter a department");
    const res = await fetch(`${apiUrl}/department?department=${department}`);
    const teachers = await res.json();
    renderTeachers(teachers);
});

// Initial load
loadTeachers();
