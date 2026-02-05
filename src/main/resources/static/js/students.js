const apiUrl = "http://localhost:8080/students";

const studentsTableBody = document.querySelector("#studentsTable tbody");
const studentForm = document.getElementById("studentForm");
const formError = document.getElementById("formError");

const searchName = document.getElementById("searchName");
const searchSurname = document.getElementById("searchSurname");
const searchBtn = document.getElementById("searchBtn");

// Load all students
async function loadStudents() {
    const res = await fetch(apiUrl);
    const students = await res.json();
    renderStudents(students);
}

// Render students table
function renderStudents(students) {
    studentsTableBody.innerHTML = "";
    students.forEach(student => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${student.id}</td>
            <td>${student.name}</td>
            <td>${student.surname}</td>
            <td>${student.email}</td>
            <td>${student.courses.map(c => c.courseName).join(", ")}</td>
            <td>
                <button onclick="editStudent(${student.id})">Edit</button>
                <button onclick="deleteStudent(${student.id})">Delete</button>
            </td>
        `;
        studentsTableBody.appendChild(row);
    });
}

// Submit form (create/update)
studentForm.addEventListener("submit", async e => {
    e.preventDefault();
    formError.textContent = "";

    const id = document.getElementById("studentId").value;
    const data = {
        name: document.getElementById("name").value,
        surname: document.getElementById("surname").value,
        email: document.getElementById("email").value
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
        await loadStudents();
        studentForm.reset();
        document.getElementById("studentId").value = "";
    } catch (err) {
        formError.textContent = err.message || JSON.stringify(err);
    }
});

// Edit student
async function editStudent(id) {
    const res = await fetch(`${apiUrl}/${id}`);
    const student = await res.json();
    document.getElementById("studentId").value = student.id;
    document.getElementById("name").value = student.name;
    document.getElementById("surname").value = student.surname;
    document.getElementById("email").value = student.email;
}

// Delete student
async function deleteStudent(id) {
    if (confirm("Are you sure?")) {
        await fetch(`${apiUrl}/${id}`, { method: "DELETE" });
        await loadStudents();
    }
}

// Search by name + surname
searchBtn.addEventListener("click", async () => {
    const name = searchName.value;
    const surname = searchSurname.value;
    if (!name || !surname) return alert("Enter both name and surname");
    const res = await fetch(`${apiUrl}/search?name=${name}&surname=${surname}`);
    const students = await res.json();
    renderStudents(students);
});

// Initial load
loadStudents();
